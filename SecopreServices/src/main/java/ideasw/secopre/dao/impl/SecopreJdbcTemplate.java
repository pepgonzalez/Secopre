package ideasw.secopre.dao.impl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

/**
 * Clase de Servicio que encapsula el {@link DataSource} definido para MySQL y
 * provee el {@link JdbcTemplate} especificio para MySQL
 *
 * @author jorge.canoc@gmail.com
 *
 */
@Component("secopreJdbcTemplate")
public class SecopreJdbcTemplate {
	static final Logger LOG = LoggerFactory
			.getLogger(SecopreJdbcTemplate.class);

	public static final String SECOPRE_SCHEMA = "secopre";
	/**
	 * Datasource vinculado ala BD de BD2
	 */
	@Autowired
	private DataSource dataSource;

	@Autowired
	private DataSource tsadbitntstDataSource;

	/**
	 * retorna el {@link DataSource} en caso que se requiera realizar una
	 * conexion JDBC
	 *
	 * @return the db2DataSource
	 */
	public DataSource getSecopreDataSource() {
		return dataSource;
	}

	public DataSource getTsadbitntstDataSource() {
		return tsadbitntstDataSource;
	}

	/**
	 * Retorna {@link JdbcTemplate} creado apartir del {@link DataSource} de DB2
	 *
	 * @return jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * Retorna {@link SimpleJdbcCall} creado apartir del {@link DataSource} de
	 * DB2
	 *
	 * @return
	 */
	public SimpleJdbcCall getSimpleJdbcCall() {
		return new SimpleJdbcCall(dataSource);
	}

	public SimpleJdbcInsert getSimpleJdbcInsert(String table, String columnKey) {
		return new SimpleJdbcInsert(dataSource).withTableName(table)
				.usingGeneratedKeyColumns(columnKey);
	}

}
