package com.js.jhjs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.charset.Charset;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.JsonObject;
import com.sun.jna.Native;

import gnu.io.CommPortIdentifier;

public class jcFrame implements MouseListener{
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
	private JLabel settingBackJLabel;
	private JLayeredPane jcLayeredPane = new JLayeredPane();
	private String strUser;
	private JLabel userEcho;
	private JLabel num1 = new JLabel("1",JLabel.CENTER);
    private JLabel num2 = new JLabel("2",JLabel.CENTER);
    private JLabel num3 = new JLabel("3",JLabel.CENTER);
    private JLabel num4 = new JLabel("4",JLabel.CENTER);
    private JLabel num5 = new JLabel("5",JLabel.CENTER);
    private JLabel num6 = new JLabel("6",JLabel.CENTER);
    private JLabel num7 = new JLabel("7",JLabel.CENTER);
    private JLabel num8 = new JLabel("8",JLabel.CENTER);
    private JLabel num9 = new JLabel("9",JLabel.CENTER);
	

	public jcFrame(String str) {
		this.strUser = str;
		this.initJcFrame("img/3.jpg");
		
	}
	public void initJcFrame(String pathName){
		//��ʼ��frame
		this.jcFrames = new JFrame("�Ͳ�");
		this.jcFrames.setUndecorated(true);
		this.jcFrames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jcFrames.setBounds(0,0,1280,1024);
		//������־ģ��
		this.logLabel = new JLabel();
		this.logLabel.setBounds(0, 0, 1280, 50);
		this.logLabel.setForeground(Color.white);
		//���뱳��ͼƬ
		this.bgImage = new JLabel(new ImageIcon(pathName));
		this.bgImage.setSize(1280,1024);
		this.jcPanel.add(this.bgImage);
		this.jcPanel.setBounds(0, 0, 1280, 1024);
		//�û�ͷ������
		this.userImg = new JLabel("����Ҫ���û���ƬŶ");
		this.userImg.setBounds(198, 189, 280, 367);
		this.userImg.setBackground(Color.GRAY);
		this.userImg.setOpaque(true);
		//�û�����ʾ
		this.userName = new JLabel("�������û���");
		this.userName.setBounds(251, 651, 222, 50);
	//	this.userName.setBackground(Color.GRAY);
		this.userName.setFont(new Font("����",Font.BOLD,30));
		this.userName.setOpaque(true);
		//�û�ID��ʾ
		this.userId = new JLabel("�������û�ID");
		this.userId.setBounds(251, 731, 222, 50);
		this.userId.setFont(new Font("����",Font.BOLD,30));
	//	this.userId.setBackground(Color.GRAY);
		this.userId.setOpaque(true);
		//�û������ʾ
		this.userChange = new JLabel("�������û������ʾ");
		this.userChange.setBounds(251, 811, 222, 50);
		this.userChange.setFont(new Font("����",Font.BOLD,30));
//		this.userChange.setBackground(Color.GRAY);
		this.userChange.setOpaque(true);
		//���������ʾ
		this.mealsKind = new JLabel();
		this.mealsKind.setBounds(1050, 147, 200, 50);
		this.mealsKind.setFont(new Font("����",Font.BOLD,30));
		//ʱ����ʾ
		this.timeEcho = new JLabel();
		this.timeEcho.setBounds(34, 22, 600, 50);
		this.timeEcho.setFont(new Font("����",Font.BOLD,30));
		this.timeEcho.setForeground(Color.white);
		this.addTimeLabel();
		//���ð�ť
		this.settingBackJLabel = new JLabel();
		this.settingBackJLabel.setBounds(1094, 12, 100, 80);
		this.settingBackJLabel.addMouseListener(this);
		//��ǰ����Ա�û�����ʾ
		this.userEcho = new JLabel();
		this.userEcho.setBounds(500, 22, 500, 50);
		this.userEcho.setFont(new Font("����",Font.BOLD,30));
		this.userEcho.setForeground(Color.white);
		this.userEcho.setText("��ǰ����Ա:"+this.strUser);
		//�Ͳ�������ʾ
		this.num1.setBounds(666, 248, 165, 68);
		this.num2.setBounds(850, 248, 165, 68);
		this.num3.setBounds(1036, 248, 165, 68);
		this.num4.setBounds(665, 337, 165, 68);
		this.num5.setBounds(850, 337, 165, 68);
		this.num6.setBounds(1036, 337, 165, 68);
		this.num7.setBounds(666, 429, 165, 68);
		this.num8.setBounds(850, 429, 165, 68);
		this.num9.setBounds(1036, 429, 165, 68);
		this.setNums(this.num1);
		this.setNums(this.num2);
		this.setNums(this.num3);
		this.setNums(this.num4);
		this.setNums(this.num5);
		this.setNums(this.num6);
		this.setNums(this.num7);
		this.setNums(this.num8);
		this.setNums(this.num9);
		
		
		this.jcPanel2.setLayout(null);
		this.jcPanel2.add(this.userImg);
		this.jcPanel2.add(this.userName);
		this.jcPanel2.add(this.userId);
		this.jcPanel2.add(this.userChange);
		this.jcPanel2.add(this.timeEcho);
		this.jcPanel2.add(this.logLabel);
		this.jcPanel2.add(this.mealsKind);
		this.jcPanel2.add(this.settingBackJLabel);
		this.jcPanel2.add(this.userEcho);
		this.jcPanel2.add(this.num1);
		this.jcPanel2.add(this.num2);
		this.jcPanel2.add(this.num3);
		this.jcPanel2.add(this.num4);
		this.jcPanel2.add(this.num5);
		this.jcPanel2.add(this.num6);
		this.jcPanel2.add(this.num7);
		this.jcPanel2.add(this.num8);
		this.jcPanel2.add(this.num9);
		this.jcPanel2.setBounds(0, 0, 1280, 1024);
		this.jcPanel2.setOpaque(false);
		
		this.jcLayeredPane.add(this.jcPanel,1);
		this.jcLayeredPane.add(this.jcPanel2,0);
		
		this.jcFrames.add(this.jcLayeredPane);
		this.jcFrames.setVisible(true);
		//�����߳̽��ж�ʱ����
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				gainTime gtt = new gainTime();
				if(gtt.gainWebTime().equals("GAINERROR")){
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "��ʱʧ�ܣ���ע��ϵͳʱ���Ƿ���ȷ��","��ʱʧ��" ,JOptionPane.INFORMATION_MESSAGE);
				}else {
					jcFrame.this.logLabel.setText("ϵͳ��ʱ�ɹ���");
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
		//�����߳̽��з�������
		Thread thread_jc = new Thread(()->{
			String mealValue = null;
			DatabaseManipulate dbm_charge = new DatabaseManipulate();
			cardDuty cDuty = new cardDuty();
					//��������ˢ����ʽ��ѭ��
				while (true) {
					while(!(cDuty.openICcardPort().equals("ERROR"))){
						if (jcFrame.this.mealsKind.getText().equals("���")) {
							mealValue = "4";
						}
						if (jcFrame.this.mealsKind.getText().equals("���")) {
							mealValue = "10";
						}
						if (jcFrame.this.mealsKind.getText().equals("���")) {
							mealValue = "4";
						}
		
						JsonObject jobject = dbm_charge.searchLunchWithIc(cDuty.openICcardPort(), mealValue);
						jcFrame.this.userName.setText(jobject.get("name").getAsString());
						jcFrame.this.userId.setText(dbm_charge.searchPhoneNum(cDuty.openICcardPort()));
						jcFrame.this.userChange.setText(jobject.get("charge").getAsString());
						try {
							Thread.sleep(1000);
							jcFrame.this.userName.setText("");
							jcFrame.this.userId.setText("");
							jcFrame.this.userChange.setText("");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}

		});
		SerialTools sTools = new SerialTools();
		Set<CommPortIdentifier> portgetList = sTools.getPortList();
		for(CommPortIdentifier cpif:portgetList){
			//�����ڷ������ݵ��߳�
			new Thread(()->{
				sTools.openSerialPort(cpif, 2000);
			}).start();
		}
		
		thread.start();
		thread_jc.start();
	}
	private void setNums(JLabel label){
		label.setFont(new Font("����",Font.BOLD,30));
		label.setOpaque(false);
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
						jcFrame.this.mealsKind.setText("���");
					}
					if((Integer.parseInt(gTime.gainTime())>1000)&&((Integer.parseInt(gTime.gainTime())<1400))){
						jcFrame.this.mealsKind.setText("���");
					}
					if((Integer.parseInt(gTime.gainTime())>1400)&&((Integer.parseInt(gTime.gainTime())<2400))){
						jcFrame.this.mealsKind.setText("���");
					}
					jcFrame.this.timeEcho.setText(string);
				}
				
			}
		});
		threadTime.start();	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getComponent().equals(this.settingBackJLabel)) {
			new settingFrame(this.jcFrames,this.strUser);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
