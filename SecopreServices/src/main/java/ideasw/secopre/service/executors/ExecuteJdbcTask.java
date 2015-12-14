package ideasw.secopre.service.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class ExecuteJdbcTask implements Runnable {
	private String[] jdbcData;

	static final Logger LOG = LoggerFactory.getLogger(ExecuteJdbcTask.class);

	public ExecuteJdbcTask(String[] jdbcData) {
		this.jdbcData = jdbcData;
	}

	private static final ThreadLocal<JdbcTemplate> threadLocal = new ThreadLocal<JdbcTemplate>() {
		@Override
		protected JdbcTemplate initialValue() {
			try {
				LOG.info("======>Adquiriendo Conexion Template<======");
				return JdbcTemplateFactory.getJdbcTemplate();
			} catch (Exception e) {
				LOG.error("Error al iniciar comunicacion con el JdbcTemplate",
						e);
			}
			return null;
		}
	};

	@Override
	public void run() {
		JdbcTemplate template = threadLocal.get();
		try {
			new ExecuteJdbc().executeJdbcData(template, jdbcData);
		} catch (Exception e) {
			LOG.error("Error al ejecutar la sentencia SQL ", e);
		}

	}

}
