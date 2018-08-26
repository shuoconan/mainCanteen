package com.js.jhjs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.NonWritableChannelException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.TooManyListenersException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.JsonObject;
import com.sun.org.apache.bcel.internal.generic.NEW;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import jdk.internal.util.xml.impl.ReaderUTF8;

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
	private JLabel comsuptionLabel;
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
    private DatabaseManipulate dbm_charge = new DatabaseManipulate();
    private DatabaseManipulate dm = new DatabaseManipulate();

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
		this.userImg = new JLabel("");
		this.userImg.setBounds(198, 189, 280, 367);
		//�û�����ʾ
		this.userName = new JLabel("");
		this.userName.setBounds(251, 651, 222, 50);
		this.userName.setFont(new Font("����",Font.BOLD,30));
		//�û�ID��ʾ
		this.userId = new JLabel("");
		this.userId.setBounds(251, 731, 222, 50);
		this.userId.setFont(new Font("����",Font.BOLD,30));
		//�û������ʾ
		this.userChange = new JLabel("");
		this.userChange.setBounds(251, 811, 222, 50);
		this.userChange.setFont(new Font("����",Font.BOLD,30));
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
		//���ѽ����ʾ
		this.comsuptionLabel = new JLabel();
		this.comsuptionLabel.setBounds(731,746, 400, 100);
		this.comsuptionLabel.setFont(new Font("����",Font.BOLD,40));
		this.comsuptionLabel.setHorizontalAlignment(JLabel.CENTER);		
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
		this.jcPanel2.add(this.comsuptionLabel);
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
		jcFrame.SerialTools sTools = this.new SerialTools();
		Set<CommPortIdentifier> portgetList = sTools.getPortList();
		for(CommPortIdentifier cpif:portgetList){
			//�����ڷ������ݵ��߳�
			new Thread(()->{
				sTools.openSerialPort(cpif, 20);
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
	public class SerialTools implements Runnable, SerialPortEventListener {
		
		private  boolean isOpen=false;
		private Set<CommPortIdentifier> portList=new HashSet<CommPortIdentifier>();
		final static String appName="MyApp";
		private  InputStream is;
		private  OutputStream os;
		private BufferedReader br;
		private  SerialPort serialPort;
		byte[] readBuffer=new byte[100];
		protected String num = null;
		
		
		public Set<CommPortIdentifier> getPortList(){
			Enumeration tempPortList;  //ö����
			CommPortIdentifier portIp;
			tempPortList=CommPortIdentifier.getPortIdentifiers();
			/*����������getPortIdentifiers�������һ��ö�ٶ��󣬸ö����ְ�����ϵͳ�й���ÿ���˿ڵ�CommPortIdentifier����
			 * ע������Ķ˿ڲ�������ָ���ڣ�Ҳ�������ڡ�
			 * ������������Դ�������
			 * getPortIdentifiers(CommPort)������Ѿ���Ӧ�ó���򿪵Ķ˿����Ӧ��CommPortIdentifier���� 
			 * getPortIdentifier(String portName)��ȡָ���˿��������硰COM1������CommPortIdentifier����
			 */
			while(tempPortList.hasMoreElements()){
				//��������Ե���getPortType�������ض˿����ͣ�����ΪCommPortIdentifier.PORT_SERIAL
				portIp=(CommPortIdentifier) tempPortList.nextElement();
				portList.add(portIp);
			}
			return portList;
		}
		public boolean openSerialPort(CommPortIdentifier portIp,int delay){
			try {
				serialPort=(SerialPort) portIp.open(appName, delay);
				/* open������ͨѶ�˿ڣ����һ��CommPort������ʹ�����ռ�˿ڡ�
				 * ����˿���������Ӧ�ó���ռ�ã���ʹ�� CommPortOwnershipListener�¼����ƣ�����һ��PORT_OWNERSHIP_REQUESTED�¼���
				 * ÿ���˿ڶ�����һ�� InputStream ��һ��OutputStream��
				 * ����˿�����open�����򿪵ģ���ô�κε�getInputStream����������ͬ�����������󣬳�����close �����á�
				 * ��������������һ��ΪӦ�ó��������ڶ����������ڶ˿ڴ�ʱ�����ȴ��ĺ�������
				 */
			} catch (PortInUseException e) {
				return false;
			}
			try {
				is=serialPort.getInputStream();/*��ȡ�˿ڵ�����������*/
				os=serialPort.getOutputStream();/*��ȡ�˿ڵ����������*/
			} catch (IOException e) {
				return false;
			}
			try {
				serialPort.addEventListener(this);/*ע��һ��SerialPortEventListener�¼������������¼�*/
			} catch (TooManyListenersException e) {
				return false;
			}
			serialPort.notifyOnDataAvailable(true);/*���ݿ���*/
			try {
				/*���ô��ڳ�ʼ�������������ǲ����ʣ�����λ��ֹͣλ��У��*/
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,SerialPort.STOPBITS_1 , SerialPort.PARITY_NONE);
			} catch (UnsupportedCommOperationException e) {
				return false;
			}
			return true;
		}
		public boolean closeSerialPort(){
			if(isOpen){
				try {
					is.close();
					os.close();
					serialPort.notifyOnDataAvailable(false);
					serialPort.removeEventListener();
					serialPort.close();
					isOpen = false;
				} catch (IOException e) {
					return false;
				}
			}
			return true;
		}
		public boolean sendMessage(String message){
			try {
				os.write(message.getBytes());
			} catch (IOException e) {
				return false;
			}
			return true;
		}
		@Override
		public void serialEvent(SerialPortEvent event) {
			int k = 0;
			/*
			 * �˴�ʡ��һ���¼������������
			 *  SerialPortEvent.BI:/*Break interrupt,ͨѶ�ж�
			 *  SerialPortEvent.OE:/*Overrun error����λ����
			 *  SerialPortEvent.FE:/*Framing error����֡����
			 *  SerialPortEvent.PE:/*Parity error��У�����
			 *  SerialPortEvent.CD:/*Carrier detect���ز���� 
			 *  SerialPortEvent.CTS:/*Clear to send��������� 
			 *  SerialPortEvent.DSR:/*Data set ready�������豸����
			 *  SerialPortEvent.RI:/*Ring indicator������ָʾ
			 *  SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*Output buffer is empty��������������
			 */
			if(event.getEventType()==SerialPortEvent.DATA_AVAILABLE){	
				/*Data available at the serial port���˿��п������ݡ������������飬������ն�*/
				try {
					while(is.available()>0){
					// is.read(readBuffer);//�յ��������ٴˣ������������
						br = new BufferedReader(new ReaderUTF8(is));
						String str = br.readLine();
						System.out.println(str);
						jcFrame.this.dealDuties(str);
						
					}
				} catch (IOException e) {
				}
			}
		}
		public String putNumOut(){
			return this.num;
		}
		

		@Override
		public void run() {
			try {
				Thread.sleep(50);//ÿ���շ�������Ϻ��߳���ͣ50ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	 
	}
	public void dealDuties(String string){
		String str1;
		String str2;
		JsonObject jsonObject = null;
		JsonObject jsonObject2 = null;
		ImageIcon imagIcon = null;
		if(string.length()==24){
			jsonObject = new JsonObject();
			str1 = Encrypt.decrypt2(string, gainTime.gainDate()).substring(3);	
			jsonObject.addProperty("time", gainTime.gainDateAndTime());
			jsonObject.addProperty("mobile", str1);
			str2 = Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate());
			jsonObject = new JsonObject();
			jsonObject.addProperty("contents", str2);
			//��һ��HTTP���󣬻�ȡ�û���Ϣ
			jsonObject = testHttPInterface.doPost("http://text.jinshangfoods.com/api/user/show", jsonObject);
			System.out.println(jsonObject.toString());
			jsonObject = (JsonObject) jsonObject.get("data");
			//��token�������ݿ���
			dm.saveToken(jsonObject.get("mobile").getAsString(), jsonObject.get("remember_token").getAsString(),gainTime.gainDateAndTime());
			System.out.println(jsonObject.toString());
			jsonObject = new JsonObject();
			jsonObject.addProperty("time",gainTime.gainDateAndTime());
			jsonObject.addProperty("mobile", str1);
			if (jcFrame.this.mealsKind.getText().equals("���")) {
				jsonObject.addProperty("type", 1);
			}
			if (jcFrame.this.mealsKind.getText().equals("�в�")) {
				jsonObject.addProperty("type", 2);
			}
			if (jcFrame.this.mealsKind.getText().equals("���")) {
				jsonObject.addProperty("type", 3);
			}
			jsonObject2 = new JsonObject();
			jsonObject2.addProperty("contents", Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate()));
			//�ڶ���HTTP���󣬻�ȡ�û�������Ϣ.
			jsonObject  = testHttPInterface.doPost("http://text.jinshangfoods.com/api/order/meal", jsonObject2);
			System.out.println(jsonObject.toString());
			if(jsonObject.get("msg").getAsString().equals("success")){
				jsonObject2 = (JsonObject) jsonObject.get("data");
				jsonObject2 = (JsonObject)jsonObject2.get("order_meal");
				String strnum = jsonObject2.get("num").getAsString();
				setNumLabelBackground(strnum);
				jsonObject2 = (JsonObject) jsonObject.get("data");
				String amount = jsonObject2.get("amount").getAsString();
				String orderId = jsonObject2.get("id").getAsString();
				this.comsuptionLabel.setText(toolsClass.coinToYuan(amount)+"Ԫ");
				//������HTTP���󣬽��ж���֧��
				jsonObject = new JsonObject();
				jsonObject.addProperty("time", gainTime.gainDateAndTime());
				jsonObject.addProperty("mobile", str1);
				jsonObject.addProperty("token", dm.fenchToken(str1));
				jsonObject.addProperty("order_id", orderId);
				jsonObject2 = new JsonObject();
				jsonObject2.addProperty("contents", Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate()));
				jsonObject = testHttPInterface.doPost("http://text.jinshangfoods.com/api/order/payment", jsonObject2);
				System.out.println(jsonObject.toString());
				if(jsonObject.get("msg").equals("success")){
					jsonObject = (JsonObject) jsonObject.get("data");
					this.userChange.setText(jsonObject.get("money").getAsString());
					dm.putMoneyUpdate(jsonObject.get("money").getAsString(), jsonObject.get("mobile").getAsString());
				}
				//û�ж�����Ϣ�����ֳ�֧������
			}else if (jsonObject.get("code").getAsString().equals("1050003")) {
				
			}
			dm.queryUserInfo(str1);
			Image img=Toolkit.getDefaultToolkit().createImage(dm.getBytes(), 0,dm.getBytes().length);
			imagIcon = new ImageIcon(img.getScaledInstance(280,367,0));
			jcFrame.this.userImg.setIcon(imagIcon);
			jcFrame.this.userName.setText(dm.getName());
			jcFrame.this.userId.setText(dm.getMobile());
			//3������ָ�
			new Thread(()->{
				try {
					Thread.sleep(3000);
					jcFrame.this.userImg.setIcon(null);
					jcFrame.this.userName.setText("");
					jcFrame.this.userId.setText("");
					jcFrame.this.comsuptionLabel.setText("");
					jcFrame.this.userChange.setText("");
					jcFrame.this.num1.setOpaque(false);
					jcFrame.this.num1.setForeground(Color.black);
					jcFrame.this.num2.setOpaque(false);
					jcFrame.this.num2.setForeground(Color.black);
					jcFrame.this.num3.setOpaque(false);
					jcFrame.this.num3.setForeground(Color.black);
					jcFrame.this.num4.setOpaque(false);
					jcFrame.this.num4.setForeground(Color.black);
					jcFrame.this.num5.setOpaque(false);
					jcFrame.this.num5.setForeground(Color.black);
					jcFrame.this.num6.setOpaque(false);
					jcFrame.this.num6.setForeground(Color.black);
					jcFrame.this.num7.setOpaque(false);
					jcFrame.this.num7.setForeground(Color.black);
					jcFrame.this.num8.setOpaque(false);
					jcFrame.this.num8.setForeground(Color.black);
					jcFrame.this.num9.setOpaque(false);
					jcFrame.this.num9.setForeground(Color.black);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
			
			
		}
		if(string.length()==17){
			
		}
	}
	public void setNumLabelBackground(String str){
		if(str.equals("1")){
			this.num1.setOpaque(true);
			this.num1.setBackground(Color.DARK_GRAY);
			this.num1.setForeground(Color.white);
		}
		if(str.equals("2")){
			this.num2.setOpaque(true);
			this.num2.setBackground(Color.DARK_GRAY);
			this.num2.setForeground(Color.white);
		}
		if(str.equals("3")){
			this.num3.setOpaque(true);
			this.num3.setBackground(Color.DARK_GRAY);
			this.num3.setForeground(Color.white);
		}
		if(str.equals("4")){
			this.num4.setOpaque(true);
			this.num4.setBackground(Color.DARK_GRAY);
			this.num4.setForeground(Color.white);
		}
		if(str.equals("5")){
			this.num5.setOpaque(true);
			this.num5.setBackground(Color.DARK_GRAY);
			this.num5.setForeground(Color.white);
		}
		if(str.equals("6")){
			this.num6.setOpaque(true);
			this.num6.setBackground(Color.DARK_GRAY);
			this.num6.setForeground(Color.white);
		}
		if(str.equals("7")){
			this.num7.setOpaque(true);
			this.num7.setBackground(Color.DARK_GRAY);
			this.num7.setForeground(Color.white);
		}
		if(str.equals("8")){
			this.num8.setOpaque(true);
			this.num8.setBackground(Color.DARK_GRAY);
			this.num8.setForeground(Color.white);
		}
		if(str.equals("9")){
			this.num9.setOpaque(true);
			this.num9.setBackground(Color.DARK_GRAY);
			this.num9.setForeground(Color.white);
		}
	}

	

}
