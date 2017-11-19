package com.mum.paper.clip.util;

public class Utility {
	
	public static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.util.Date convertSqlDateToUtilDate(java.sql.Date date) {
		return new java.util.Date(date.getTime());
	}

}
