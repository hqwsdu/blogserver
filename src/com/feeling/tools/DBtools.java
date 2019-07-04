package com.feeling.tools;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;

public class DBtools {
	private static final String URL = "jdbc:sqlite://E:/zijizuode/bishede/feeling.db";
	private static final String DRIVERCLASSNAME = "org.sqlite.JDBC";
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>(); // map

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			if (conn == null) { // 代表线程上没有绑定连接
				DbUtils.loadDriver(DRIVERCLASSNAME);
				conn = DriverManager.getConnection(URL);
				tl.set(conn);
			}
			// 得到当前线程上绑定的连接
			Connection s = tl.get();

			return s;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
