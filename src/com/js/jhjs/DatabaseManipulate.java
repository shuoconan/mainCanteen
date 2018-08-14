package com.js.jhjs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * check username and user password
 * manipulate sqldata(remains,customer card id and customer imgs)
 */
public class DatabaseManipulate {
	Connection connection;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/canteen";
	String user = "root";
	String password = "bonjava";
	Statement statement;
	public DatabaseManipulate() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(driver);
			this.connection =DriverManager.getConnection(url, user, password);
			if(!this.connection.isClosed()){
				this.statement = this.connection.createStatement();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String queryStringUser(String str){
		String sqlString = "select * from users where username = '"+str+"'";
		ResultSet rs;
		String resString = null;
		try {
			rs = this.statement.executeQuery(sqlString);
			while (rs.next()) {
				resString = rs.getString("userpsd");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resString;
	}
	public String[] searchUsers(){
		String string = "select * from users";
		String[] str = null;
		try {
			ResultSet rs = this.statement.executeQuery(string);
			if(rs.last()){
				str = new String[rs.getRow()];
			}
			rs.beforeFirst();
			int j = 0;
			while (rs.next()) {
				str[j] = rs.getString("username"); 
				j=j+1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
