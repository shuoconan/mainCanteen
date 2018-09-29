package com.js.jhjs;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class CardCheck{

	private JFrame frame = new JFrame("卡片校验");
	private JLabel label = new JLabel("这是一个模版",JLabel.CENTER);
	private JPanel contentPane;
	private DatabaseManipulate dm = new DatabaseManipulate();

	/**
	 * Create the frame.
	 */
	public CardCheck() {
		this.frame.setBounds(100, 100, 450, 300);
		this.frame.setLayout(null);
		this.label.setBounds(0, 0, 450, 300);
		this.label.setFont(new Font("黑体", Font.BOLD, 40));
		this.frame.add(this.label);
	}
	public void setLabelName(String stringNum){
		this.label.setText(dm.searchWithNameGetIcNum(stringNum));
	}
	public void setVisibles(boolean b){
		this.frame.setVisible(b);
	}

}
