package com.js.jhjs;

import java.awt.EventQueue;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
