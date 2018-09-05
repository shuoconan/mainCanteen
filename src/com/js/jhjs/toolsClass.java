package com.js.jhjs;

public class toolsClass {
	public static String coinToYuan(String string){
		int i = Integer.parseInt(string);
		String str = String.valueOf(i/100)+"."+String.valueOf(i%100)+"Ԫ";
		return str;
	}
	public static int yuanToDouble(String string){
		return (int)(Double.parseDouble(string.substring(0,string.length()-1))*100);
	}
}
