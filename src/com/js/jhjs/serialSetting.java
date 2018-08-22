package com.js.jhjs;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class serialSetting{

	private JFrame jFrame = new JFrame("串口设置");
	private JPanel jPanel;
	private JLabel jLabel = new JLabel();
	private JLabel jbutton = new JLabel("下一步");
	private SerialTools sTools = new SerialTools();
	public serialSetting() {
		// TODO Auto-generated constructor stub
		this.jFrame.setBounds(100, 100, 800, 200);
		this.jPanel = new JPanel();
		this.jPanel.setLayout(null);
		this.jLabel.setBounds(0,0,800,80);
		this.jLabel.setFont(new Font("黑体",Font.BOLD,40));
		this.jbutton.setBounds(0,80,800,80);
		this.jbutton.setFont(new Font("黑体",Font.BOLD,40));
		this.jbutton.setHorizontalAlignment(JLabel.CENTER);
		this.jPanel.add(this.jLabel);
		this.jPanel.add(this.jbutton);
		this.jFrame.add(this.jPanel);
		this.jFrame.setVisible(true);
		this.dutyDealing();
	}
	public void dutyDealing() {
		this.jLabel.setText("STEP1:请拔下所有串口");
	}

}
