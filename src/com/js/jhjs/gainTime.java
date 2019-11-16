package com.js.jhjs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class gainTime {
	private String WebUrl = "http://api.k780.com:88/?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
	
	public static String gainDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str1 = sdf.format(new Date());
		StringBuffer sBuffer = new StringBuffer(str1);
		str1 = new String(sBuffer)+new String(sBuffer.reverse());
		return str1;
	}
	public static String gainDateAndTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	public static String gainFullDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
	public static String gainTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		return sdf.format(new Date());
	}
	public String gainWebTime(){
		HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;
		try {
			URL url = new URL(this.WebUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(15000);
			connection.setReadTimeout(60000);
			connection.connect();
			if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
                JsonParser jParser = new JsonParser();
                JsonObject jsonTime = (JsonObject) jParser.parse(result);
                JsonObject resultGetTime = jsonTime.get("result").getAsJsonObject();
                result = resultGetTime.get("datetime_1").getAsString();
                
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "GAINERROR";
		}
		return result;
		
	}
	
	public String setSystemTime(String setData){
		String osName = System.getProperty("os.name");
		String cmd = "";
		int i = 0;
		try {
	    if (osName.matches("^(?i)Windows.*$")) {// Window 系统
		    // 格式 HH:mm:ss
		    cmd = "cmd /c time "+setData.substring(11, 19);
		    Runtime.getRuntime().exec(cmd);
		    // 格式：yyyy-MM-dd
		    cmd = "cmd /c date "+setData.substring(0, 10);
		    Runtime.getRuntime().exec(cmd);
		} else {// Linux 系统
		   // 格式：yyyyMMdd
		   cmd = "date -s 20090326";
		   Runtime.getRuntime().exec(cmd);
		   // 格式 HH:mm:ss
		   cmd = "date -s 22:35:00";
		   Runtime.getRuntime().exec(cmd);
		}
		} catch (IOException e) {
		    i=1;
		}
		if (i==0) {
			return "SUCCESS";
		}else {
			return "ERROR";
		}
	}
}
