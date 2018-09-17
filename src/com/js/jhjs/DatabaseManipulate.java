package com.js.jhjs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;


import com.google.gson.JsonObject;

public class DatabaseManipulate {
	private Connection connection;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/canteen";
	private String user = "root";
	private String password = "bonjava";
	private Statement statement;
	private byte[] bytes= null;
	private String name = null;
	private String mobile = null;
	
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
	//返回目前操作员角色
	public String queryStringRole(String str){
		String sqlString = "select * from users where username = '"+str+"'";
		ResultSet rs;
		String resString = null;
		try {
			rs = this.statement.executeQuery(sqlString);
			while (rs.next()) {
				resString = rs.getString("roles");
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
	
	
	public String searchWithNum(String icNum){
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
	//将用户个人信息保存至数据库保存至数据库
	public void writeImgIntoDatabase(String name,String phonenum,String icNum,String pathStr,String department){
		String string = "insert into charge(name,icnum,phonenum,img,department,chargenum)values(?,?,?,?,?,0)";
		try {
			PreparedStatement ps = connection.prepareStatement(string);
			ps.setString(1, name);
			ps.setString(2, icNum);
			ps.setString(3, phonenum);
			ps.setString(5, department);
			File file = new File(pathStr);
			FileInputStream fi = new FileInputStream(file);
			ps.setBinaryStream(4, fi, (int)file.length());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void queryUserInfo(String querynum){
		String string;
		if(querynum.length() == 11){
			string = "select * from charge where phonenum=?";
		}else {
			string = "select * from charge where icnum=?";
		}
		this.bytes = new byte[10240*10];
		InputStream is = null;
		ResultSet rs = null;
		try {
			PreparedStatement ps = connection.prepareStatement(string);
			ps.setString(1, querynum);
			rs = ps.executeQuery();
			while(rs.next()){
				is = rs.getBinaryStream("img");
				is.read(this.bytes);
				this.name = rs.getString("name");
				this.mobile = rs.getString("phonenum");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void saveToken(String mobile,String remember_token,String tokenTime){
		String stringQuery = "select * from token_cache where mobile = ?";
		try {
			PreparedStatement psQuery = this.connection.prepareStatement(stringQuery);
			psQuery.setString(1, mobile);
			ResultSet rs = psQuery.executeQuery();
			if(rs.next()){
				System.out.println("更新操作！"+tokenTime);
				String stringUpdate = "update token_cache set remember_token = ? ,tokenTime = ? where mobile = ?";
				PreparedStatement ps = this.connection.prepareStatement(stringUpdate);
				ps.setString(3, mobile);
				ps.setString(2, tokenTime);
				ps.setString(1, remember_token);
				ps.execute();
			}else{
				System.out.println("插入操作");
				String stringSQL = "insert into token_cache(mobile,remember_token,tokenTime) values(?,?,?)";
				PreparedStatement ps = this.connection.prepareStatement(stringSQL);
				ps.setString(1, mobile);
				ps.setString(2, remember_token);
				ps.setString(3, tokenTime);
				ps.execute();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public String fenchToken(String mobile){
		String strQuery = "select * from token_cache where mobile = ?";
		String strToken = null;
		try {
			PreparedStatement ps = this.connection.prepareStatement(strQuery);
			ps.setString(1, mobile);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				strToken = rs.getString("remember_token");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strToken;
	}
	public void putMoneyUpdate(String money,String mobile){
		String strUpdate = "update charge set chargenum = ? where phonenum = ?";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(strUpdate);
			ps.setString(1, money);
			ps.setString(2, mobile);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String returnNumsByMobile(String mobile,String time){
		String str = "select * from foods_num where mobile = ?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(str);
			ps.setString(1, mobile);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getString("time").equals(time)){
					return rs.getString("nums");
				}else{
					String str2 = "update foods_num set time = ? ,nums = \"0\" where mobile = ?";
					PreparedStatement ps2 = this.connection.prepareStatement(str2);
					ps2.setString(1, time);
					ps2.setString(2, mobile);
					ps2.execute();
					return "0";
				}
			}else{
				String str2 = "insert into foods_num(mobile,nums,time) values (?,\"0\",?)";
				PreparedStatement ps2 = this.connection.prepareStatement(str2);
				ps2.setString(1, mobile);
				ps2.setString(2, time);
				ps2.execute();
				return "0";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setFoodsNums(String mobile,String time,String nums){
		String strUpdate = "update foods_num set time = ?,nums = ? where mobile = ?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(strUpdate);
			ps.setString(1, time);
			ps.setString(2, nums);
			ps.setString(3, mobile);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getBreakfastPrice(String mealsKinds){
		String strSelect = "select * from meals_price where name = ?";
		String returnPrice = null;
		try {
			PreparedStatement ps = this.connection.prepareStatement(strSelect);
			ps.setString(1, mealsKinds);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				returnPrice = rs.getString("price");
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnPrice;
	}
}















