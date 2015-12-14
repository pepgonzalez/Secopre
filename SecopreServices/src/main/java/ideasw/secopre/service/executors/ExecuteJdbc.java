package ideasw.secopre.service.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class ExecuteJdbc {
	static final Logger LOG = LoggerFactory.getLogger(ExecuteJdbcTask.class);

	public boolean executeJdbcData(JdbcTemplate jdbcTemplate, String... sqlData) {
		for (String sql : sqlData) {

			int val = jdbcTemplate.update(sql);
			LOG.info("Se el resutudado es: " + val + " de la sentencia==>"
					+ sql);
		}
		return true;
	}
}
