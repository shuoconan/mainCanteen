package com.js.jhjs;

public class toolsClass {
	public static String coinToYuan(String string){
		int i = Integer.parseInt(string);
		String str = String.valueOf(i/100)+"."+String.valueOf(i%100)+"Ԫ";
		return str;
	}
}
