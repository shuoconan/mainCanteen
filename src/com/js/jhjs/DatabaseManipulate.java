package com.js.jhjs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonObject;


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
	//给用户名返回用户名密码的加密值
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
	//返回目前操作系统管理员用户的字符串数组
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
	public JsonObject searchLunchWithIc(String icnum,String num){
		String string = "select * from charge where icnum = '"+icnum+"'";
		String string2 = null;
		String str = null;
		String str_charge = null;
		ResultSet rs;
		try {
			rs = this.statement.executeQuery(string);
			while (rs.next()) {
				str = rs.getString("name"); 
				str_charge = rs.getString("chargenum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(str_charge);
		System.out.println(num);
		str_charge = String.valueOf(Integer.parseInt(str_charge)-Integer.parseInt(num));
		string2 = "update charge set chargenum ='"+str_charge+"' where icnum ='"+icnum+"'";
		try {
			this.statement.execute(string2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonObject jobject = new JsonObject();
		jobject.addProperty("name", str);
		jobject.addProperty("charge", str_charge);
	    return jobject;		
	}
	public String searchPhoneNum(String icNum){
		String sqlString = "select * from users where phonenum = '"+icNum+"'";
		ResultSet rs;
		String resString = null;
		try {
			rs = this.statement.executeQuery(sqlString);
			while (rs.next()) {
				resString = rs.getString("phonenum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resString;
	}
}
