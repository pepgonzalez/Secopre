package ideasw.secopre.sql;

import java.util.Properties;

public class QueryContainer {
	Properties queries;
	
	public String getSQL(String key) {
		return queries.getProperty(key);
	}


	/**
	 * @return
	 */
	public Properties getQueries() {
		return queries;
	}

	/**
	 * @param properties
	 */
	public void setQueries(Properties properties) {
		queries = properties;
	}

}
