package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.EntryDistrictDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EntryDistrictDetailMapper implements RowMapper<Object> {

	public EntryDistrictDetail mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		EntryDistrictDetail entry = new EntryDistrictDetail();
		entry.setStateId(rs.getLong("STATE_ID"));
		entry.setStateName(rs.getString("STATE_NAME"));
		entry.setDistrictId(rs.getLong("DISTRICT_ID"));
		entry.setDistrictNumber(rs.getString("DISTRICT_NUMBER"));
		entry.setEntryId(rs.getLong("ENTRY_ID"));
		entry.setEntryDescription(rs.getString("ENTRY_DESCRIPTION"));
		entry.setEntryCode(rs.getString("ENTRY_CODE"));
		entry.setAnnualAmount(rs.getDouble("ANNUAL_AMOUNT"));
		entry.setJanuary(rs.getDouble("ENERO"));
		entry.setFebruary(rs.getDouble("FEBRERO"));
		entry.setMarch(rs.getDouble("MARZO"));
		entry.setApril(rs.getDouble("ABRIL"));
		entry.setMay(rs.getDouble("MAYO"));
		entry.setJune(rs.getDouble("JUNIO"));
		entry.setJuly(rs.getDouble("JULIO"));
		entry.setAugust(rs.getDouble("AGOSTO"));
		entry.setSeptember(rs.getDouble("SEPTIEMBRE"));
		entry.setOctober(rs.getDouble("OCTUBRE"));
		entry.setNovember(rs.getDouble("NOVIEMBRE"));
		entry.setDecember(rs.getDouble("DICIEMBRE"));
		return entry;
	}
}