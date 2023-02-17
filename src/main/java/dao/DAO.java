package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//import oracle.jdbc.pool.OracleDataSource;

public class DAO {
	private static final String url = "jdbc:oracle:thin:@//localhost:1521/xe";
	private static final String user = "ar";
	private static final String password = "1234";
	
	private static HikariDataSource dataSource;

	private DAO() {}

	public static Connection getConnection() {
		try {
			return getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static DataSource getDataSource() {
		if(dataSource == null) {
			return initDataSource();
		}else 
			return dataSource;
	}

	public static DataSource initDataSource() {
		return initDataSource(url, user, password);
	}
	
	public static DataSource initDataSource(String url, String user, String password) {
		if(dataSource == null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(url);
			config.setUsername(user);
			config.setPassword(password);
			config.setMaximumPoolSize(3);
			config.setConnectionTimeout(30000);
			config.setValidationTimeout(5000);
			return dataSource = new HikariDataSource(config);
		}
		return dataSource;
	}
	
//	public static DataSource initDataSource(String url, String user, String password) {
//		OracleDataSource dataSource = null;
//		try {
//			dataSource = new OracleDataSource();
//			dataSource.setURL(url);
//			dataSource.setUser(user);
//			dataSource.setPassword(password);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return dataSource;
//	}
}