package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.model.Entry;
import ideasw.secopre.model.catalog.Address;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.catalog.State;
import ideasw.secopre.service.BaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

public class EntryComparator implements Comparator<Entry> {
    
	@Override
    public int compare(Entry o1, Entry o2) {
        if(o1.getConcept() != null && o2.getConcept() != null && o1.getConcept().getCode() > o2.getConcept().getCode()){
        	return (o1.getCode() > o2.getCode()) ? 1 : -1;
        }else{
        	return -1;
        }
    }
}