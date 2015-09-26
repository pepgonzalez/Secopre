package ideasw.secopre.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import ideasw.secopre.dao.impl.SecopreJdbcTemplate;

@Service
public class AccessNativeServiceBaseImpl {
	
	@Autowired
	SecopreJdbcTemplate sql;
	
	public Long insertAndReturnId(String tableName, String id, Map<String, Object> params){
		Number key = sql.getSimpleJdbcInsert(tableName, id).executeAndReturnKey(params);
		return new Long(key.longValue());
	}
	
	public int insertOrUpdate(String q, SqlParameterSource params){
		return sql.getNamedParameterJdbcTemplate().update(q, params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <E> List<E> queryForList(Class<E> dto, String q, SqlParameterSource params, RowMapper mapper){
		return (List<E>) sql.getNamedParameterJdbcTemplate().query(q, params, mapper);
	}
	
	public <E> E queryForObject(Class<E> o, String q, SqlParameterSource params){
		return sql.getNamedParameterJdbcTemplate().queryForObject(q, params, o);
	}
}
