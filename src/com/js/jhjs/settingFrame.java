package com.js.jhjs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class settingFrame implements MouseListener{

	private JFrame settingJFrame;
	private JPanel jPanel = new JPanel();
	private JLabel updatePsd;
	private JLabel loginOut;
	private JLabel switchFrame;
	private JLabel checkUpdate;
	private JFrame jf;
	
	public settingFrame(){
		this.initSettingFrame();
	}
	public settingFrame(JFrame jfs){
		this.jf = jfs;
		this.initSettingFrame();
	}
	
	public void initSettingFrame(){
		this.settingJFrame = new JFrame("设置");
		this.settingJFrame.setBounds(100, 100, 300, 250);
		this.jPanel.setBounds(0, 0, 300, 800);
		this.jPanel.setLayout(null);
		this.updatePsd = new JLabel("修改密码");
		this.updatePsd.setBounds(0,0,300,50);
		this.setButtonLableStyle(this.updatePsd);
		
		this.loginOut = new JLabel("注销");
		this.loginOut.setBounds(0,50,300,50);
		this.setButtonLableStyle(this.loginOut);
		
		this.checkUpdate = new JLabel("检查更新");
		this.checkUpdate.setBounds(0, 100, 300, 50);
		this.setButtonLableStyle(this.checkUpdate);
		
		this.switchFrame = new JLabel("切换业务");
		this.switchFrame.setBounds(0, 150, 300, 50);
		this.setButtonLableStyle(this.switchFrame);
		
		this.jPanel.add(this.updatePsd);
		this.jPanel.add(this.checkUpdate);
		this.jPanel.add(this.loginOut);
		this.jPanel.add(this.switchFrame);
		
		this.settingJFrame.add(jPanel);
		this.settingJFrame.setVisible(true);
	}
	public void setButtonLableStyle(JLabel label){
		label.setBackground(Color.white);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("黑体",Font.BOLD,30));
		label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		label.setOpaque(true);
		label.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent().equals(this.switchFrame)){
			//this.jf.dispose();
			if (this.jf.getTitle().equals("就餐")) {
				new wmFrame();
				this.jf.dispose();
				this.settingJFrame.dispose();
			}
			if (this.jf.getTitle().equals("外卖")) {
				new jcFrame();
				this.jf.dispose();
				this.settingJFrame.dispose();
			}
		}
		if(e.getComponent().equals(this.loginOut)){
			new LoginFrame();
			this.jf.dispose();
			this.settingJFrame.dispose();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent().equals(this.updatePsd)){
			this.updatePsd.setBackground(Color.DARK_GRAY);
			this.updatePsd.setForeground(Color.white);
		}
		if(e.getComponent().equals(this.loginOut)){
			this.loginOut.setBackground(Color.DARK_GRAY);
			this.loginOut.setForeground(Color.white);
		}
		if(e.getComponent().equals(this.checkUpdate)){
			this.checkUpdate.setBackground(Color.DARK_GRAY);
			this.checkUpdate.setForeground(Color.white);
		}
		if(e.getComponent().equals(this.switchFrame)){
			this.switchFrame.setBackground(Color.DARK_GRAY);
			this.switchFrame.setForeground(Color.white);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent().equals(this.updatePsd)){
			this.updatePsd.setBackground(Color.white);
			this.updatePsd.setForeground(Color.black);
		}
		if(e.getComponent().equals(this.loginOut)){
			this.loginOut.setBackground(Color.white);
			this.loginOut.setForeground(Color.black);
		}
		if(e.getComponent().equals(this.checkUpdate)){
			this.checkUpdate.setBackground(Color.white);
			this.checkUpdate.setForeground(Color.black);
		}
		if(e.getComponent().equals(this.switchFrame)){
			this.switchFrame.setBackground(Color.white);
			this.switchFrame.setForeground(Color.black);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
