package com.mum.paper.clip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionHelper {

	private Connection connection;/*
	private static ConnectionHelper instance = new ConnectionHelper();*/

	public ConnectionHelper() {

		Properties configFile = new Properties();
		try {
			configFile.load(ConnectionHelper.class.getClassLoader().getResourceAsStream("dbconnection.properties"));
			String driver = configFile.getProperty("connection.driver");
			String userName = configFile.getProperty("connection.username");
			String password = configFile.getProperty("connection.password");
			String url = configFile.getProperty("connection.url");

			Class.forName(driver);

			connection = DriverManager.getConnection(url, userName, password);

		} catch (Exception ex) {

			ex.printStackTrace();
			connection = null;
		}

	}

	/*public static ConnectionHelper getInstance() {

		if (instance.getConnection() == null) {
			
			instance = new ConnectionHelper();
			return instance;

		} else {
			return instance;
		}
	}*/

	public Connection getConnection() {
		
		return connection;
	}

}
