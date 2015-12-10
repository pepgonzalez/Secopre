package ideasw.secopre.service.impl;

import ideasw.secopre.dto.AnnualBudgetFile;
import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryDistrictDetail;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.dto.UpdateEntry;
import ideasw.secopre.enums.StatusEntry;
import ideasw.secopre.exception.EntryDistrictException;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.service.EntryConfigService;
import ideasw.secopre.service.impl.mapper.EntryDistrictDetailMapper;
import ideasw.secopre.service.impl.mapper.EntryDistrictMapper;
import ideasw.secopre.sql.QueryContainer;
import ideasw.secopre.sql.SQLConstants;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
		SqlParameterSource params = new MapSqlParameterSource();
		Integer numberEntries = this.queryForObject(Integer.class,
				queryContainer.getSQL(SQLConstants.VALIDATE_ENTRIES_NEXT_YEAR),
				params);
		if (numberEntries == null) {
			return Integer.valueOf(0);
		}
		return numberEntries;
	}

	private void callSPCloneEntries(String userId) {
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
		MapSqlParameterSource params = new MapSqlParameterSource();

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

	private EntryDistrict getBalance(EntryFilter filter, StatusEntry status) {
		StringBuffer sql = new StringBuffer("");
		MapSqlParameterSource params = new MapSqlParameterSource();

		sql.append("SELECT ");
		sql.append("SUM(ED.BUDGET_AMOUNT) AS BUDGET_AMOUNT,");
		sql.append("SUM(ED.BUDGET_AMOUNT_ASSIGN) AS BUDGET_AMOUNT_ASSIGN,");
		sql.append("SUM(ED.COMMITTED_AMOUNT) AS COMMITTED_AMOUNT,");
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
		sql.append(" PK.YEAR = YEAR(CURDATE())");
		sql.append(" AND E.STATUS = '" + status.name() + "'");

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
		if (!existEntriesInConfig()) {
			throw new EntryDistrictException(
					"No Existen partidas registradas para el siguiente año, la operacion no puede ser efectuada.");
		}

		List<EntryDistrictDetail> allRows = new ArrayList<EntryDistrictDetail>(
				0);
		// si no genero excepcion al clonar las entidades actualizar los saldos

		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(fileBean
					.getFile().getBytes());
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

		} catch (IOException e) {
			LOG.error("IOException", e);
		} catch (InvalidFormatException e) {
			LOG.error("InvalidFormatException", e);
		}

		// Inserta los objetos Obtenidos:
		insertEntriesByDistrict(allRows, username);
		return null;
	}

	private void insertEntriesByDistrict(List<EntryDistrictDetail> allRows,
			String username) {
		StringBuffer sb = new StringBuffer(
				"INSER INTO secopre.ENTRYDISTRICT (ANNUAL_AMOUNT,BUDGET_AMOUNT,BUDGET_AMOUNT_ASSIGN,");
		sb.append("COMMITTED_AMOUNT,MONTH,DISTRICT_ID,ENTRY_ID,ACTIVE,CREATE_DATE,CREATED_BY,UPDATE_DATE,UPDATED_BY) VALUES ");
		for (EntryDistrictDetail detail : allRows) {
			sb.append(getInsertMonth(detail, 0, detail.getJanuary(), username));
			sb.append(getInsertMonth(detail, 1, detail.getFebruary(), username));
			sb.append(getInsertMonth(detail, 2, detail.getMarch(), username));
			sb.append(getInsertMonth(detail, 3, detail.getApril(), username));
			sb.append(getInsertMonth(detail, 4, detail.getMay(), username));
			sb.append(getInsertMonth(detail, 5, detail.getJune(), username));
			sb.append(getInsertMonth(detail, 6, detail.getJuly(), username));
			sb.append(getInsertMonth(detail, 7, detail.getAugust(), username));
			sb.append(getInsertMonth(detail, 8, detail.getSeptember(), username));
			sb.append(getInsertMonth(detail, 9, detail.getOctober(), username));
			sb.append(getInsertMonth(detail, 10, detail.getNovember(), username));
			sb.append(getInsertMonth(detail, 11, detail.getDecember(), username));
		}

		LOG.info("SQL Insert ====> "
				+ sb.toString().substring(0, sb.length() - 1));
	}

	private String getInsertMonth(EntryDistrictDetail detail, int month,
			Double amount, String username) {

		StringBuffer insert = new StringBuffer();
		insert.append("(");
		insert.append(detail.getAnnualAmount()).append(",");
		insert.append(detail.getBudgetAmount()).append(",");
		insert.append(amount).append(",");
		insert.append(detail.getCommittedAmount()).append(",");
		insert.append(month).append(",");
		insert.append(detail.getDistrictId()).append(",");
		insert.append(detail.getEntryId()).append(",");
		insert.append("1").append(",");
		insert.append("now()").append(",");
		insert.append("'").append(username).append("'").append(",");
		insert.append("now()").append(",");
		insert.append("'").append(username).append("'").append("),");

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
			if (cellCount == 1 && isNumeric(cellValue)) { // Es el ID del
															// distrito
				entryDistrict.setDistrictId(Long.valueOf(cellValue));
			} else if (cellCount == 3 && isNumeric(cellValue)) {// Es el id de
																// la partida
				entryDistrict.setEntryId(Long.valueOf(cellValue));
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
		if (entryDistrict.getAnnualAmount() == null
				|| entryDistrict.getEntryId() == null) {
			entryDistrict = null;
		}
		LOG.info("CELL ===>" + sb.toString());
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
	public boolean existEntriesInConfig() {
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
		sql.append(" PK.YEAR = YEAR(CURDATE()) + 1");
		sql.append(" AND E.STATUS = 'CONFIG' ");

		Integer total = this.queryForObject(Integer.class, sql.toString(),
				new MapSqlParameterSource());

		if (total != null && total.intValue() > 0) {
			return true;
		}

		return false;

	}
}
