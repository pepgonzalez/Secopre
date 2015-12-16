package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.model.catalog.Address;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.catalog.State;
import ideasw.secopre.service.BaseService;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

public class DistrictMapper implements RowMapper<Object> {    
	
	@Autowired
	public BaseService baseService;
	 
	public District mapRow(ResultSet rs, int rowNum) throws SQLException {    
		District district = new District();
		district.setId(rs.getLong("ID"));
		district.setNumber(rs.getString("DESCRIPTION"));
		district.setActivo(rs.getBoolean("ACTIVE"));
		district.setEntity(rs.getString("ENTITY"));
	
		district.setEmail(rs.getString("EMAIL"));
        district.setTelephone(rs.getString("TELEPHONE"));
        Address address = new Address();
        address.setId(rs.getLong("ADDRESS_ID"));
        address.setExteriorNumber(rs.getString("NUMBER_EXT"));
        address.setStreet(rs.getString("STREET"));
        address.setColony(rs.getString("COLONY"));
        address.setCity(rs.getString("CITY"));    
        State state = new State();
        state.setId(rs.getLong("STATE_ID"));
        state.setCode(rs.getString("CODE"));
        state.setName(rs.getString("NAME"));    
    	district.setAddress(address);
    	district.setState(state);

		return district;   
	 }    
} 
