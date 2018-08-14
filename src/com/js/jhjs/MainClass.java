package com.js.jhjs;

import java.awt.EventQueue;
import java.util.Set;

import gnu.io.CommPortIdentifier;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//	md5Duty md5duty = new md5Duty();
//	System.out.println(md5duty.toMd5("123456"));
//	System.out.println(md5duty.toMd5("123456"));
//	System.out.println(md5duty.toMd5("123456"));
//
	}

}
