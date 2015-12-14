package ideasw.secopre.service.impl;

import ideasw.secopre.dao.impl.SecopreJdbcTemplate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

@Service
public class AccessNativeServiceBaseImpl {

	@Autowired
	SecopreJdbcTemplate sql;

	public Long insertAndReturnId(String tableName, String id,
			Map<String, Object> params) {
		Number key = sql.getSimpleJdbcInsert(tableName, id)
				.executeAndReturnKey(params);
		return new Long(key.longValue());
	}

	public int insertOrUpdate(String q, SqlParameterSource params) {
		return sql.getNamedParameterJdbcTemplate().update(q, params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <E> List<E> queryForList(Class<E> dto, String q,
			SqlParameterSource params, RowMapper mapper) {
		return (List<E>) sql.getNamedParameterJdbcTemplate().query(q, params,
				mapper);
	}

	public <E> E queryForObject(Class<E> o, String q, SqlParameterSource params) {
		try {
			return sql.getNamedParameterJdbcTemplate().queryForObject(q,
					params, o);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public Connection getSecopreConnection() throws SQLException {
		return sql.getSecopreDataSource().getConnection();
	}

	public JdbcTemplate getJdbcTemplate() throws SQLException {
		return sql.getJdbcTemplate();
	}	
	public Connection getTsadbitntstConnection() throws SQLException {
		return sql.getTsadbitntstDataSource().getConnection();
	}

	public void executeSp(String spName, SqlParameterSource params)throws SQLException {
		final String procedureCall = "{call secopre.CLONE_ENTRIES()}";

		Connection connection = null;

		try {
			connection = sql.getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection
					.prepareCall(procedureCall);
//			callableSt.setString(1, "abcdefg");
//			callableSt.setInt(2, 1);
			// callableSt.registerOutParameter(2, Types.INTEGER);
			boolean hadResults = callableSt.execute();

		} finally {

			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		//
		// sql.getJdbcTemplate().update("{call secopre.CLONE_ENTRIES}");
		//
		// SimpleJdbcCall jdbcCall = sql.getSimpleJdbcCall();
		//
		// jdbcCall.withProcedureName(spName);
		// jdbcCall.withSchemaName(SecopreJdbcTemplate.SECOPRE_SCHEMA);
		//
		// Map<String, Object> out = jdbcCall.execute(params);
		//
		// if (out != null) {
		// Log.info("Se clonaron entidades OK");
		// }
	}
}
