package ideasw.secopre.service.impl;

import ideasw.secopre.dto.AnnualBudgetFile;
import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryDistrictDetail;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.dto.UpdateEntry;
import ideasw.secopre.enums.Month;
import ideasw.secopre.enums.StatusEntry;
import ideasw.secopre.exception.EntryDistrictException;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.service.EntryConfigService;
import ideasw.secopre.service.executors.ExecuteJdbcTask;
import ideasw.secopre.service.executors.ExecutorPoolService;
import ideasw.secopre.service.impl.mapper.EntryDistrictDetailMapper;
import ideasw.secopre.service.impl.mapper.EntryDistrictMapper;
import ideasw.secopre.sql.QueryContainer;
import ideasw.secopre.sql.SQLConstants;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntryConfigServiceImpl extends AccessNativeServiceBaseImpl
		implements EntryConfigService {

	static final Logger LOG = LoggerFactory
			.getLogger(EntryConfigServiceImpl.class);

	@Autowired
	BaseService baseService;

	@Autowired
	QueryContainer queryContainer;

	@Autowired
	AccessNativeService nativeService;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean cloneEntries(String userId) throws Exception {
		// busca si existen partidas en configuracion o activas en el año
		// siguiente

		// busca si para el año siguiente no existen partidas vigentes.
		Integer entriesNumber = numberEntriesNextYear();
		if (entriesNumber == null || entriesNumber > 0) {
			// Si hay partidas que cumplan la condicion anterior termina con
			// error
			LOG.error("Existen "
					+ entriesNumber
					+ " partidas registradas para el siguiente año, la operacion no puede ser efectuada.");

			throw new EntryDistrictException(
					"Existen "
							+ entriesNumber
							+ " partidas registradas para el siguiente año, la operacion no puede ser efectuada.");

		}

		try {
			callSPCloneEntries(userId);
		} catch (Exception e) {
			Log.info("Ocurrio un error al clonar las partidas: " + e.getCause());
			Log.error("Error al clonar las partidas", e);
			throw new EntryDistrictException(
					"Ocurrion un error al clonar las partidas, contacte a su administrador: "
							+ e.getMessage());

		}
		// retorna true
		return true;
	}

	public Integer numberEntriesNextYear() {
		int numberEntries = this.queryForObject(Integer.class,
				queryContainer.getSQL(SQLConstants.VALIDATE_ENTRIES_NEXT_YEAR),
				new MapSqlParameterSource());
		return numberEntries;
	}

	private void callSPCloneEntries(String userId) throws SQLException {
		SqlParameterSource in = new MapSqlParameterSource().addValue("userId",
				userId);
		this.executeSp(queryContainer.getSQL(SQLConstants.CLONE_ENTRIES), in);
	}

	@Override
	public boolean updateEntries(List<UpdateEntry> updateList) {

		return false;
	}

	@Override
	public EntryBalance getEntryBalance(EntryFilter filter, StatusEntry status) {

		EntryBalance balance = new EntryBalance();

		EntryDistrict result = getBalance(filter, status);

		StringBuffer sql = new StringBuffer(
				queryContainer.getSQL(SQLConstants.GET_ENTRY_DETAIL));
		if(filter.getType()){
			sql = new StringBuffer(
					queryContainer.getSQL(SQLConstants.GET_ENTRY_DETAIL_MOD));
		}
		MapSqlParameterSource params = new MapSqlParameterSource();

		if (status.equals(StatusEntry.ACTIVE)) {
			sql.append(" AND PK.YEAR = YEAR(CURDATE())");
		}
		sql.append(" AND E.STATUS = '" + status.name() + "' ");

		if (filter.getStateId() != null) {
			sql.append(" AND S.ID = :stateid");
			params.addValue("stateid", filter.getStateId());
		}
		if (filter.getDistrictId() != null) {
			sql.append(" AND D.ID = :districtId");
			params.addValue("districtId", filter.getDistrictId());
		}

		if (filter.getEntryId() != null) {
			sql.append(" AND E.ID = :entryId");
			params.addValue("entryId", filter.getEntryId());
		}

		sql.append(" GROUP BY DISTRICT_ID, ENTRY_ID");

		List<EntryDistrictDetail> entryList = this.queryForList(
				EntryDistrictDetail.class, sql.toString(), params,
				new EntryDistrictDetailMapper());

		Double anualAmount = 0D;
		if (entryList != null && !entryList.isEmpty()) {

			for (EntryDistrictDetail item : entryList) {
				if(filter.getMonths() != null && filter.getMonths().length > 0){
					removeBalance(item, filter.getMonths());
				}
				anualAmount += item.getAnnualAmount();
			}
		}
		balance.setEntries(entryList);
		balance.setAnnualAmount(anualAmount);
		balance.setBudgetAmount(result.getBudgetAmount());
		balance.setBudgetAsing(result.getBudgetAmountAssign());
		balance.setBudgetCommit(result.getCommittedAmount());

		return balance;
	}

	private void removeBalance(EntryDistrictDetail item, Integer[] months){
		Map<Long,Double> balance = new HashedMap<Long,Double>(); 
		for(Integer month: months){
			balance.put(Long.valueOf(month), 0D);
		}
		if(!balance.containsKey(Month.ENERO.getId() - 1))
			item.setJanuary(0D);
		if(!balance.containsKey(Month.FEBRERO.getId() - 1))
			item.setFebruary(0D);
		if(!balance.containsKey(Month.MARZO.getId() - 1))
			item.setMarch(0D);
		if(!balance.containsKey(Month.ABRIL.getId() - 1))
			item.setApril(0D);
		if(!balance.containsKey(Month.MAYO.getId() - 1))
			item.setMay(0D);
		if(!balance.containsKey(Month.JUNIO.getId() - 1))
			item.setJune(0D);
		if(!balance.containsKey(Month.JULIO.getId() - 1))
			item.setJuly(0D);
		if(!balance.containsKey(Month.AGOSTO.getId() - 1))
			item.setAugust(0D);
		if(!balance.containsKey(Month.SEPTIEMBRE.getId() - 1))
			item.setSeptember(0D);
		if(!balance.containsKey(Month.OCTUBRE.getId() - 1))
			item.setOctober(0D);
		if(!balance.containsKey(Month.NOVIEMBRE.getId() - 1))
			item.setNovember(0D);
		if(!balance.containsKey(Month.DICIEMBRE.getId() - 1))
			item.setDecember(0D);

		
	}
	
	private EntryDistrict getBalance(EntryFilter filter, StatusEntry status) {
		StringBuffer sql = new StringBuffer("");
		MapSqlParameterSource params = new MapSqlParameterSource();

		sql.append("SELECT ");
		sql.append("IFNULL(SUM(ED.BUDGET_AMOUNT),0) AS BUDGET_AMOUNT,");
		sql.append("IFNULL(SUM(ED.BUDGET_AMOUNT_ASSIGN),0) AS BUDGET_AMOUNT_ASSIGN,");
		sql.append("IFNULL(SUM(ED.COMMITTED_AMOUNT),0) AS COMMITTED_AMOUNT,");
		sql.append("S.ID AS STATE, D.ID AS DISTRICT, E.ID AS ENTRY");
		sql.append(" FROM ");
		sql.append(" secopre.ENTRY E");
		sql.append(" INNER JOIN");
		sql.append(" secopre.ENTRYDISTRICT ED ON E.ID = ED.ENTRY_ID");
		sql.append(" INNER JOIN");
		sql.append(" secopre.PROGRAMMATIC_KEY PK ON E.PROGRAMMATIC_ID = PK.ID");
		sql.append(" INNER JOIN");
		sql.append(" secopre.DISTRICT D ON ED.DISTRICT_ID = D.ID ");
		sql.append(" INNER JOIN");
		sql.append(" secopre.STATE S ON D.STATE_ID = S.ID ");
		sql.append(" WHERE");
		sql.append(" E.STATUS = '" + status.name() + "'");

		if (status.equals(StatusEntry.ACTIVE)) {
			sql.append(" AND PK.YEAR = YEAR(CURDATE())");
		}

		String groupBy = " GROUP BY ";
		if (filter.getStateId() != null) {
			sql.append(" AND D.STATE_ID = :stateId");
			params.addValue("stateId", filter.getStateId());
			groupBy += "STATE,";
		}

		if (filter.getDistrictId() != null) {
			sql.append(" AND ED.DISTRICT_ID = :districtId");
			params.addValue("districtId", filter.getDistrictId());
			groupBy += "DISTRICT,";
		}

		if (filter.getEntryId() != null) {
			sql.append(" AND ED.ENTRY_ID = :entryId");
			params.addValue("entryId", filter.getEntryId());
			groupBy += "ENTRY,";
		}

		if (filter.getMonths() != null && filter.getMonths().length != 0) {
			sql.append(" AND ED.MONTH IN ( :months ) ");
			params.addValue("months", Arrays.asList(filter.getMonths()));
		}

		List<EntryDistrict> entryList = this.queryForList(EntryDistrict.class,
				sql.toString() + groupBy.substring(0, groupBy.length() - 1),
				params, new EntryDistrictMapper());
		if (entryList != null && !entryList.isEmpty()) {
			return entryList.get(0);
		} else {
			return new EntryDistrict();
		}

	}

	@Override
	public Long importExcel(AnnualBudgetFile fileBean, String username)
			throws Exception {

		// Valida que existan partidas en configuracion
		if (!existEntriesInConfig(false)) {
			throw new EntryDistrictException("No Existen partidas registradas para el siguiente año, la operacion no puede ser efectuada.");
		}

		List<EntryDistrictDetail> allRows = new ArrayList<EntryDistrictDetail>(0);
		// si no genero excepcion al clonar las entidades actualizar los saldos

		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(fileBean.getFile().getBytes());
			// Parsea el ByteArrayInputStream a Workbook
			Workbook workbook = WorkbookFactory.create(bis);

			// Obtiene la primer Hoja
			Sheet sheet = workbook.getSheetAt(0);

			// Itera los renglones de la hoja
			Iterator<Row> rowIterator = sheet.iterator();

			EntryDistrictDetail detail = null;

			DataFormatter fmt = new DataFormatter();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				detail = getBudgetDetail(row, fmt);
				if (detail != null) {
					allRows.add(detail);
				}
			}
			
			LOG.info("Total de registros a actualizar: " + allRows.size());

		} catch (IOException e) {
			LOG.error("IOException", e);
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			LOG.error("InvalidFormatException", e);
			e.printStackTrace();
		}

		// actualiza los objetos Obtenidos:
		updateEntriesByDistrict(allRows, username);
		return null;
	}

	private void updateEntriesByDistrict(List<EntryDistrictDetail> allRows, String username) {
		List<String> batchStatements = null;
		String statement = null;
		
		for (EntryDistrictDetail detail : allRows) {
			batchStatements = new ArrayList<String>(0);
			String[] statementArray = null;
			
			statement = getInsertMonth(detail, 0, detail.getJanuary(), username);
			if (statement != null)
				batchStatements.add(statement);
			statement = getInsertMonth(detail, 1, detail.getFebruary(), username);
			if (statement != null)
				batchStatements.add(statement);

			statement = getInsertMonth(detail, 2, detail.getMarch(), username);
			if (statement != null)
				batchStatements.add(statement);

			statement = getInsertMonth(detail, 3, detail.getApril(), username);
			if (statement != null)
				batchStatements.add(statement);

			statement = getInsertMonth(detail, 4, detail.getMay(), username);
			if (statement != null)
				batchStatements.add(statement);

			statement = getInsertMonth(detail, 5, detail.getJune(), username);
			if (statement != null)
				batchStatements.add(statement);

			statement = getInsertMonth(detail, 6, detail.getJuly(), username);
			if (statement != null)
				batchStatements.add(statement);

			statement = getInsertMonth(detail, 7, detail.getAugust(), username);
			if (statement != null)
				batchStatements.add(statement);

			statement = getInsertMonth(detail, 8, detail.getSeptember(),username);
			if (statement != null)
				batchStatements.add(statement);

			statement = getInsertMonth(detail, 9, detail.getOctober(), username);
			if (statement != null)
				batchStatements.add(statement);

			statement = getInsertMonth(detail, 10, detail.getNovember(),username);
			if (statement != null)
				batchStatements.add(statement);

			statement = getInsertMonth(detail, 11, detail.getDecember(),username);
			if (statement != null)
				batchStatements.add(statement);

			if (!batchStatements.isEmpty()) {
				statementArray = batchStatements.toArray(new String[batchStatements.size()]);
				ExecutorPoolService.getService().execute(new ExecuteJdbcTask(statementArray));
			}

		}

		LOG.info("Update Realizado ");
	}

	private String getInsertMonth(EntryDistrictDetail detail, int month, Double amount, String username) {
		if (detail.getAnnualAmount().intValue() == 0 && amount.intValue() == 0) {
			return null;
		}
		StringBuffer insert = new StringBuffer();
		
		insert.append(" UPDATE secopre.ENTRYDISTRICT AS ED  ");
		insert.append(" INNER JOIN secopre.ENTRY AS E ON ED.ENTRY_ID = E.ID ");
		insert.append(" INNER JOIN secopre.DISTRICT AS D ON ED.DISTRICT_ID = D.ID ");
		insert.append(" INNER JOIN secopre.PROGRAMMATIC_KEY AS PK ON E.PROGRAMMATIC_ID = PK.ID ");	
		insert.append(" set ED.ANNUAL_AMOUNT = ")
				.append(detail.getAnnualAmount()).append(",");
		insert.append(" ED.BUDGET_AMOUNT = ").append(amount)
				.append(",");
		insert.append(" ED.BUDGET_AMOUNT_ASSIGN = ").append(amount);

		insert.append(" WHERE D.NUMBER = '").append(detail.getDistrictNumber()).append("' AND ");
		insert.append(" E.CODE = '").append(detail.getEntryCode()).append("' AND ");
		insert.append(" ED.MONTH = ").append(month).append(" AND ");
		insert.append(" PK.YEAR = YEAR(NOW()) + 1");

//		insert.append(" WHERE DISTRICT_ID = ").append(detail.getDistrictId())
//				.append(" AND ");
//		insert.append(" ENTRY_ID = ").append(detail.getEntryId())
//				.append(" AND ");
//		insert.append(" MONTH = ").append(month);
		return insert.toString();
	}

	private EntryDistrictDetail getBudgetDetail(Row row, DataFormatter fmt) {
		Iterator<Cell> cellIterator = row.cellIterator();
		int cellCount = 0;
		EntryDistrictDetail entryDistrict = new EntryDistrictDetail();
		;
		StringBuffer sb = new StringBuffer();
		String cellValue = "";
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();

			switch (cell.getCellType()) {

			case Cell.CELL_TYPE_NUMERIC:
				cellValue = cell.getNumericCellValue() + "";
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			}

			sb.append(fmt.formatCellValue(cell));
			if (cellCount == 1 && cellValue.equals("") && !isNumeric(cellValue)) {
				break;
			}
			// Almacenar Informacion
			if (cellCount == 0 && isNumeric(cellValue)) { // Es el ID del
															// distrito
				entryDistrict.setDistrictId(Long.valueOf((new Double(cellValue).intValue())));
				
			} else if (cellCount == 1 && cellValue != null) {// Es el numero del distrito
				
				entryDistrict.setDistrictNumber(cellValue.trim());
			
		    }else if (cellCount == 2 && isNumeric(cellValue.trim())) {// Es el id de
																// la partida
				entryDistrict.setEntryId(Long.valueOf((new Double(cellValue).intValue())));
			} else if (cellCount == 3 && cellValue != null) {// Es el codigo de la partida
				
				entryDistrict.setEntryCode(cellValue.trim());
			
		    } else if (cellCount == 5 && isNumeric(cellValue)) {// Es el monto
																// anual
				entryDistrict.setAnnualAmount(Double.valueOf(cellValue));
			} else if (cellCount == 6 && isNumeric(cellValue)) {// Es el monto
																// de Enero
				entryDistrict.setJanuary(Double.valueOf(cellValue));
			} else if (cellCount == 7 && isNumeric(cellValue)) {// Es el monto
																// de Febrero
				entryDistrict.setFebruary(Double.valueOf(cellValue));
			} else if (cellCount == 8 && isNumeric(cellValue)) {// Es el monto
																// de Marzo
				entryDistrict.setMarch(Double.valueOf(cellValue));
			} else if (cellCount == 9 && isNumeric(cellValue)) {// Es el monto
																// de Abril
				entryDistrict.setApril(Double.valueOf(cellValue));
			} else if (cellCount == 10 && isNumeric(cellValue)) {// Es el monto
																	// de Mayo
				entryDistrict.setMay(Double.valueOf(cellValue));
			} else if (cellCount == 11 && isNumeric(cellValue)) {// Es el monto
																	// de Junio
				entryDistrict.setJune(Double.valueOf(cellValue));
			} else if (cellCount == 12 && isNumeric(cellValue)) {// Es el monto
																	// de Julio
				entryDistrict.setJuly(Double.valueOf(cellValue));
			} else if (cellCount == 13 && isNumeric(cellValue)) {// Es el monto
																	// de Agosto
				entryDistrict.setAugust(Double.valueOf(cellValue));
			} else if (cellCount == 14 && isNumeric(cellValue)) {// Es el monto
																	// de
																	// Septiembre
				entryDistrict.setSeptember(Double.valueOf(cellValue));
			} else if (cellCount == 15 && isNumeric(cellValue)) {// Es el monto
																	// de
																	// Octubre
				entryDistrict.setOctober(Double.valueOf(cellValue));
			} else if (cellCount == 16 && isNumeric(cellValue)) {// Es el monto
																	// de
																	// Noviembre
				entryDistrict.setNovember(Double.valueOf(cellValue));
			} else if (cellCount == 17 && isNumeric(cellValue)) {// Es el monto
																	// de
																	// Diciembre
				entryDistrict.setDecember(Double.valueOf(cellValue));
			}

			cellCount++;
		}
		if (entryDistrict.getAnnualAmount() == null || entryDistrict.getEntryId() == null || entryDistrict.getDistrictId() == null) {
			entryDistrict = null;
			LOG.info("desechando row inválido: " + cellCount);
		}else{
			LOG.info("row de excel obtenido: " +  cellCount);
		}
		LOG.debug("CELL ===>" + sb.toString());
		return entryDistrict;

	}

	public static boolean isNumeric(String str) {
		try {
			@SuppressWarnings("unused")
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	@Override
	public boolean existEntriesInConfig(boolean currentYear) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT COUNT(*)  AS TOTAL ");
		sql.append(" FROM ");
		sql.append(" secopre.ENTRY E");
		sql.append(" INNER JOIN");
		sql.append(" secopre.ENTRYDISTRICT ED ON E.ID = ED.ENTRY_ID");
		sql.append(" INNER JOIN");
		sql.append(" secopre.PROGRAMMATIC_KEY PK ON E.PROGRAMMATIC_ID = PK.ID");
		sql.append(" INNER JOIN");
		sql.append(" secopre.DISTRICT D ON ED.DISTRICT_ID = D.ID ");
		sql.append(" INNER JOIN");
		sql.append(" secopre.STATE S ON D.STATE_ID = S.ID ");
		sql.append(" WHERE");
		if(currentYear){
			sql.append(" PK.YEAR = YEAR(CURDATE())");
		}else{
			sql.append(" PK.YEAR = YEAR(CURDATE()) + 1");
		}
		
		sql.append(" AND E.STATUS = 'CONFIG' ");

		int total = this.queryForObject(Integer.class, sql.toString(),
				new MapSqlParameterSource());

		if (total > 0) {
			return true;
		}

		return false;

	}

	@Override
	public boolean releaseBudget(String userId) throws Exception {
		try {
			callSPRelease(userId);
		} catch (Exception e) {
			LOG.error("Error al ejectuar la liberacion del presupuesto", e);
			LOG.info("Error al ejectuar la liberacion del presupuesto", e);
			throw new EntryDistrictException(
					"El presupuesto no pudo ser liberado, informe al administrador: "
							+ e.getMessage());

		}
		return true;
	}

	private void callSPRelease(String userId) throws SQLException {
		SqlParameterSource in = new MapSqlParameterSource().addValue("userId",
				userId);
		this.executeSp(queryContainer.getSQL(SQLConstants.RELEASE_BUDGET), in);
	}

	@Override
	public boolean schedulingBalance(EntryFilter filter) throws Exception {
		// Setea el mes actual al filtro
		java.util.Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		if(month == Calendar.JANUARY){
			LOG.info("Error al calendarizar los saldos, en el mes de Enero no se puede realizar una calendarizacion de saldos");
			throw new EntryDistrictException(
					"Error al calendarizar los saldos, en el mes de Enero no se puede realizar una calendarizacion de saldos");			
		}
		
		month = month - 1;
		filter.setMonths(new Integer[] { month});

		// Lanza error de que no se pueden calendarizar saldos
//		if (month == Calendar.DECEMBER) {
//			LOG.info("Error al calendarizar los saldos, en el mes de diciembre no se puede realizar una calendarizacion de saldos");
//			throw new EntryDistrictException(
//					"Error al calendarizar los saldos, en el mes de diciembre no se puede realizar una calendarizacion de saldos");
//		}

		EntryBalance balance = getEntryBalance(filter, StatusEntry.ACTIVE);

		for (EntryDistrictDetail detail : balance.getEntries()) {
			Double amount = getAmount(detail, month);
			if (amount != null && amount.intValue() > 0) {
				try {
					boolean success = nativeService
							.moveBugdetAmountToNextMonth(
									detail.getDistrictId(),
									detail.getEntryId(), Long.valueOf(month));
					LOG.info(success
							+ " al calenzarizar el saldo de: Distrito => "
							+ detail.getDistrictNumber() + "Partida =>"
							+ detail.getEntryCode());
				} catch (Exception e) {
					LOG.error("Error al calenzarizar el saldo de: Distrito => "
							+ detail.getDistrictNumber() + "Partida =>"
							+ detail.getEntryCode(), e);
				}

			}
		}

		return true;
	}

	private Double getAmount(EntryDistrictDetail detail, int month) {
		Double amount = 0D;
		switch (month) {
		case Calendar.JANUARY:
			amount = detail.getJanuary();
			break;
		case Calendar.FEBRUARY:
			amount = detail.getFebruary();
			break;
		case Calendar.MARCH:
			amount = detail.getMarch();
			break;
		case Calendar.APRIL:
			amount = detail.getApril();
			break;
		case Calendar.MAY:
			amount = detail.getMay();
			break;
		case Calendar.JUNE:
			amount = detail.getJune();
			break;
		case Calendar.JULY:
			amount = detail.getJuly();
			break;
		case Calendar.AUGUST:
			amount = detail.getAugust();
			break;
		case Calendar.SEPTEMBER:
			amount = detail.getSeptember();
			break;
		case Calendar.NOVEMBER:
			amount = detail.getNovember();
			break;

		default:

			break;

		}
		return amount;

	}
}
