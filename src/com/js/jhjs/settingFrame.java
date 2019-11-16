package com.js.jhjs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class settingFrame implements MouseListener{

	private JFrame settingJFrame;
	private JPanel jPanel = new JPanel();
	private JLabel updatePsd;
	private JLabel loginOut;
	private JLabel switchFrame;
	private JLabel checkUpdate;
	private JLabel serialLabel;
	private JLabel shutDown;
	private JFrame jf;
	private String strUser;
	private jcFrame jfs;
	private wmFrame wmfs;
	private LoginFrame lfs;
	private lxwmFrame lxf;
	
	public settingFrame(String string){
		this.initSettingFrame();
		this.strUser = string;
	}

	public void setOtherFrame(jcFrame jfs,wmFrame wmfs,LoginFrame lfs,lxwmFrame lxf){
		this.lfs = lfs;
		this.jfs = jfs;
		this.wmfs = wmfs;
		this.lxf = lxf;
	}
	public void initSettingFrame(){
		this.settingJFrame = new JFrame("设置");
		this.settingJFrame.setBounds(100, 100, 300, 400);
		this.jPanel.setBounds(0, 0, 300, 800);
		this.jPanel.setLayout(null);
		this.updatePsd = new JLabel("零星外卖");
		this.updatePsd.setBounds(0,0,300,50);
		this.setButtonLableStyle(this.updatePsd);
		
		this.loginOut = new JLabel("注销");
		this.loginOut.setBounds(0,50,300,50);
		this.setButtonLableStyle(this.loginOut);
		
		this.checkUpdate = new JLabel("食堂刷卡");
		this.checkUpdate.setBounds(0, 100, 300, 50);
		this.setButtonLableStyle(this.checkUpdate);
		
		this.switchFrame = new JLabel("切换外卖");
		this.switchFrame.setBounds(0, 150, 300, 50);
		this.setButtonLableStyle(this.switchFrame);
		
		this.serialLabel = new JLabel("卡片校验");
		this.serialLabel.setBounds(0, 200, 300, 50);
		this.setButtonLableStyle(this.serialLabel);
		
		this.shutDown = new JLabel("关闭程序");
		this.shutDown.setBounds(0, 250, 300, 50);
		this.setButtonLableStyle(this.shutDown);
		
		this.jPanel.add(this.updatePsd);
		this.jPanel.add(this.checkUpdate);
		this.jPanel.add(this.loginOut);
		this.jPanel.add(this.switchFrame);
		this.jPanel.add(this.serialLabel);
		this.jPanel.add(this.shutDown);
		
		this.settingJFrame.add(jPanel);
	}
	public void setButtonLableStyle(JLabel label){
		label.setBackground(Color.white);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("黑体",Font.BOLD,30));
		label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		label.setOpaque(true);
		label.addMouseListener(this);
	}
	public void setVisibles(boolean b,String str){
		this.settingJFrame.setVisible(b);
		this.strUser = str;
	}
	public void setVisibles(boolean b){
		this.settingJFrame.setVisible(b);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent().equals(this.switchFrame)){
			if(this.wmfs.getVisible()){
				JOptionPane.showMessageDialog(null, "外卖窗口已打开！", "打开失败", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else {
				this.wmfs.setVisible(true, this.strUser);
				this.jfs.setVisible(false, this.strUser);
				this.lxf.setVisible(false, this.strUser);
				this.settingJFrame.dispose();
			}
		}
		if(e.getComponent().equals(this.checkUpdate)){
			if(this.jfs.getVisible()){
				JOptionPane.showMessageDialog(null, "食堂就餐窗口已打开！", "打开失败", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else {
				this.jfs.setVisible(true, this.strUser);
				this.wmfs.setVisible(false, this.strUser);
				this.lxf.setVisible(false, this.strUser);
				this.settingJFrame.dispose();
			}
		}
		if(e.getComponent().equals(this.updatePsd)){
			if(this.lxf.getVisible()){
				JOptionPane.showMessageDialog(null, "外卖窗口已打开！", "打开失败", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else {
				this.wmfs.setVisible(false, this.strUser);
				this.jfs.setVisible(false, this.strUser);
				this.lxf.setVisible(true, this.strUser);
				this.settingJFrame.dispose();
			}
		}
		if(e.getComponent().equals(this.loginOut)){
			this.wmfs.setVisible(false, this.strUser);
			this.jfs.setVisible(false, this.strUser);
			this.lfs.setVisible(true);
			this.settingJFrame.dispose();
		}
		if(e.getComponent().equals(this.shutDown)){
			System.exit(0);
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
		if(e.getComponent().equals(this.serialLabel)){
			this.serialLabel.setBackground(Color.DARK_GRAY);
			this.serialLabel.setForeground(Color.white);
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
		if(e.getComponent().equals(this.serialLabel)){
			this.serialLabel.setBackground(Color.white);
			this.serialLabel.setForeground(Color.black);
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
