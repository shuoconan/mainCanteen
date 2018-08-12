package com.js.jhjs;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class settingFrame{

	private JFrame settingJFrame;
	private JPanel jPanel = new JPanel();
	private JLabel updatePsd;
	
	public settingFrame(){
		this.initSettingFrame();
	}
	public void initSettingFrame(){
		this.settingJFrame = new JFrame("…Ë÷√");
		this.settingJFrame.setBounds(100, 100, 300, 800);
		this.updatePsd = new JLabel("–ﬁ∏ƒ√‹¬Î");
		this.updatePsd.setBounds(0,0,300,50);
		this.jPanel.add(this.updatePsd);
		this.settingJFrame.add(jPanel);
		this.settingJFrame.setVisible(true);
	}
	

}
