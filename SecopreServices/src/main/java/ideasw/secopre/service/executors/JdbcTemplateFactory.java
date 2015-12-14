package ideasw.secopre.service.executors;

import ideasw.secopre.dao.impl.SecopreJdbcTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateFactory {

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"Secopre-services.xml");

	public static JdbcTemplate getJdbcTemplate() {
		SecopreJdbcTemplate secopreJdbcTemplate = (SecopreJdbcTemplate) ctx
				.getBean("secopreJdbcTemplate");
		return secopreJdbcTemplate.getJdbcTemplate();
	}
}
