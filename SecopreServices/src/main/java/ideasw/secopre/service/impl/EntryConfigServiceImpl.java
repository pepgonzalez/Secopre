package ideasw.secopre.service.impl;

import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.dto.UpdateEntry;
import ideasw.secopre.exception.EntryDistrictException;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.service.EntryConfigService;
import ideasw.secopre.service.impl.mapper.EntryDistrictMapper;
import ideasw.secopre.sql.QueryContainer;
import ideasw.secopre.sql.SQLConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class EntryConfigServiceImpl extends AccessNativeServiceBaseImpl implements EntryConfigService {

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

	private Integer numberEntriesNextYear() {
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
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"userId", userId);
		this.executeCall(
				queryContainer.getSQL(SQLConstants.CLONE_ENTRIES), params);
	}

	@Override
	public boolean updateEntries(List<UpdateEntry> updateList) {

		return false;
	}

	@Override
	public EntryBalance getEntryBalance(EntryFilter filter) {
		
		EntryBalance balance = new EntryBalance();
		
		EntryDistrict result = getBalance(filter);
		
		StringBuffer sql = new StringBuffer(queryContainer.getSQL(SQLConstants.GET_DISTRICT_ENTRIES_JPQL));
		Map<String,Object> params = new HashMap<String,Object>();
		if (filter.getDistrictId() != null) {
			sql.append(" AND ed.district.id = :districtId");
			params.put("districtId", filter.getDistrictId());
		}

		if (filter.getEntryId() != null) {
			sql.append(" AND ed.entry.id = :entryId");
			params.put("entryId", filter.getEntryId());

		}

		if (filter.getMonths() != null && filter.getMonths().length != 0) {
			sql.append(" AND ed.month IN (:months)");
			params.put("months", filter.getMonths());
		}

		List<EntryDistrict> entryList = baseService.executeQueryMultipleResult(EntryDistrict.class, sql.toString(), params);		
		
		balance.setEntries(entryList);
		balance.setAnnualAmount(result.getAnnualAmount());
		balance.setBudgetAmount(result.getBudgetAmount());
		balance.setBudgetAsing(result.getBudgetAmountAssign());
		balance.setBudgetCommit(result.getCommittedAmount());
		
		return balance;
	}
	
	private EntryDistrict getBalance(EntryFilter filter) {
		StringBuffer sql = new StringBuffer("");
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		sql.append("SELECT ");
		sql.append("ED.ANNUAL_AMOUNT,");
		sql.append("SUM(ED.BUDGET_AMOUNT) AS BUDGET_AMOUNT,");
		sql.append("SUM(ED.BUDGET_AMOUNT_ASSIGN) AS BUDGET_AMOUNT_ASSIGN,");
		sql.append("SUM(ED.COMMITTED_AMOUNT) AS COMMITTED_AMOUNT");
		sql.append(" FROM ");
		sql.append(" secopre.ENTRY E");
		sql.append(" INNER JOIN");
		sql.append(" secopre.ENTRYDISTRICT ED ON E.ID = ED.ENTRY_ID");
		sql.append(" INNER JOIN");
		sql.append(" secopre.PROGRAMMATIC_KEY PK ON E.PROGRAMMATIC_ID = PK.ID");
		sql.append(" WHERE");
		sql.append(" PK.YEAR = YEAR(CURDATE())");
		if (filter.getDistrictId() != null) {
			sql.append(" AND ED.DISTRICT_ID = :districtId");
			params.addValue("districtId", filter.getDistrictId());
		}

		if (filter.getEntryId() != null) {
			sql.append(" AND ED.ENTRY_ID = :entryId");
			params.addValue("entryId", filter.getEntryId());

		}

		if (filter.getMonths() != null && filter.getMonths().length != 0) {
			sql.append(" AND ED.MONTH IN (:months)");
			params.addValue("entryId", filter.getMonths());
		}

		sql.append(";");

		List<EntryDistrict> entryList = this.queryForList(
				EntryDistrict.class, sql.toString(), params,
				new EntryDistrictMapper());
		if(entryList!= null && !entryList.isEmpty()){
			return entryList.get(0);
		}else{
			return new EntryDistrict();
		}
		
		
	}

}
