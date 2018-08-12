package com.js.jhjs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class jcFrame{
	private JFrame jcFrames;
	private JPanel jcPanel = new JPanel();
	private JPanel jcPanel2 = new JPanel();
	private JLabel bgImage;
	private JLabel userImg;
	private JLabel userName;
	private JLabel userId;
	private JLabel userChange;
	private JLabel timeEcho;
	private JLabel logLabel;
	private JLabel mealsKind;
	private JLayeredPane jcLayeredPane = new JLayeredPane();
	

	public jcFrame() {
		this.initJcFrame("img/3.jpg");
		new settingFrame();
	}
	public void initJcFrame(String pathName){
		//初始化frame
		this.jcFrames = new JFrame("就餐");
		this.jcFrames.setUndecorated(true);
		this.jcFrames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jcFrames.setBounds(0,0,1280,1024);
		//载入日志模块
		this.logLabel = new JLabel();
		this.logLabel.setBounds(0, 0, 1280, 50);
		this.logLabel.setForeground(Color.white);
		//载入背景图片
		this.bgImage = new JLabel(new ImageIcon(pathName));
		this.bgImage.setSize(1280,1024);
		this.jcPanel.add(this.bgImage);
		this.jcPanel.setBounds(0, 0, 1280, 1024);
		//用户头像设置
		this.userImg = new JLabel("这里要放用户照片哦");
		this.userImg.setBounds(198, 189, 280, 367);
		this.userImg.setBackground(Color.GRAY);
		this.userImg.setOpaque(true);
		//用户名显示
		this.userName = new JLabel("这里是用户名");
		this.userName.setBounds(251, 651, 222, 50);
		this.userName.setBackground(Color.GRAY);
		this.userName.setOpaque(true);
		//用户ID显示
		this.userId = new JLabel("这里是用户ID");
		this.userId.setBounds(251, 731, 222, 50);
		this.userId.setBackground(Color.GRAY);
		this.userId.setOpaque(true);
		//用户余额显示
		this.userChange = new JLabel("这里是用户余额显示");
		this.userChange.setBounds(251, 811, 222, 50);
		this.userChange.setBackground(Color.GRAY);
		this.userChange.setOpaque(true);
		//早中晚餐显示
		this.mealsKind = new JLabel();
		this.mealsKind.setBounds(1050, 147, 200, 50);
		this.mealsKind.setFont(new Font("黑体",Font.BOLD,30));
		//时间显示
		this.timeEcho = new JLabel();
		this.timeEcho.setBounds(684, 147, 600, 50);
		this.timeEcho.setFont(new Font("黑体",Font.BOLD,30));
		this.timeEcho.setForeground(Color.black);
		this.addTimeLabel();
		
		this.jcPanel2.setLayout(null);
		this.jcPanel2.add(this.userImg);
		this.jcPanel2.add(this.userName);
		this.jcPanel2.add(this.userId);
		this.jcPanel2.add(this.userChange);
		this.jcPanel2.add(this.timeEcho);
		this.jcPanel2.add(this.logLabel);
		this.jcPanel2.add(this.mealsKind);
		this.jcPanel2.setBounds(0, 0, 1280, 1024);
		this.jcPanel2.setOpaque(false);
		
		this.jcLayeredPane.add(this.jcPanel,1);
		this.jcLayeredPane.add(this.jcPanel2,0);
		
		this.jcFrames.add(this.jcLayeredPane);
		this.jcFrames.setVisible(true);
		//启动线程进行对时操作
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				gainTime gtt = new gainTime();
				if(gtt.gainWebTime().equals("GAINERROR")){
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "对时失败，请注意系统时间是否正确！","对时失败" ,JOptionPane.INFORMATION_MESSAGE);
				}else {
					jcFrame.this.logLabel.setText("系统对时成功！");
					try {
						Thread.sleep(3000);
						jcFrame.this.logLabel.setText("");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				gtt.setSystemTime(gtt.gainWebTime());
			}
		});
		thread.start();
	}
	public void addTimeLabel(){
		Thread threadTime = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				gainTime gTime = new gainTime();
				gTime.setSystemTime(gTime.gainDateAndTime());
				String string = "";
				while(true){
					string = "";
					string = string+gTime.gainDateAndTime();
					if((Integer.parseInt(gTime.gainTime())>=0)&&((Integer.parseInt(gTime.gainTime())<=1000))){
						jcFrame.this.mealsKind.setText("早餐");
					}
					if((Integer.parseInt(gTime.gainTime())>1000)&&((Integer.parseInt(gTime.gainTime())<1400))){
						jcFrame.this.mealsKind.setText("午餐");
					}
					if((Integer.parseInt(gTime.gainTime())>1400)&&((Integer.parseInt(gTime.gainTime())<2400))){
						jcFrame.this.mealsKind.setText("晚餐");
					}
					jcFrame.this.timeEcho.setText(string);
				}
				
			}
		});
		threadTime.start();	
	}
	

}
