package com.js.jhjs;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonObject;

import gnu.io.CommPortIdentifier;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(Encrypt.encrypt("hello", "2018082112808102"));
//		System.out.print("十六进制密文为：");
//		System.out.println(Encrypt.encrypt2("zgz15261760619", "2018082112808102"));
//		dealDuty.dealDuties("2E1DDFC81FF7560234D3002CB9E12A7D");
//		JsonObject jobject = new JsonObject();
//		jobject.addProperty("name", "15261760619");
//		System.out.println(jobject.toString());
//		System.out.println(Encrypt.encrypt2(jobject.toString(), "2018082112808102"));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new wmFrame("lalala");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		SerialTools sTools = new SerialTools();
//		Set<CommPortIdentifier> cpifSet = sTools.getPortList();
//		for(CommPortIdentifier cpif:cpifSet){
//				sTools.openSerialPort(cpif, 20);	
//		}
//	md5Duty md5duty = new md5Duty();
//	System.out.println(md5duty.toMd5("123456"));
//	System.out.println(md5duty.toMd5("123456"));
//	System.out.println(md5duty.toMd5("123456"));
//
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("contents", Encrypt.encrypt("{\"time\":\"2018-08-22 17:27:22\"}", "2018082222808102"));
//		JsonObject jo = new JsonObject();
//		jo.addProperty("contents", "aL1jjF+fxTVEFyskFkeEkFncngQzNOwa3Sz2+8Ti2pJkSDMrngFzT4W0DGRLCPY8Wt9DawLoim9YRT626tR4Fw==");
//		System.out.println(jo.toString());
//		System.out.println(testHttPInterface.doPost("http://text.jinshangfoods.com/api/order/takeout",jo));
		//		DatabaseManipulate dm = new DatabaseManipulate();
//		dm.writeImgIntoDatabase("顾阳", "15195388207", "4A303030303130350", "D:\\123.jpg", "全民");
	}

}
