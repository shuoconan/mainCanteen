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
		this.userImg = new JLabel("");
		this.userImg.setBounds(198, 189, 280, 367);
		//用户名显示
		this.userName = new JLabel("");
		this.userName.setBounds(251, 651, 222, 50);
		this.userName.setFont(new Font("黑体",Font.BOLD,30));
		//用户ID显示
		this.userId = new JLabel("");
		this.userId.setBounds(251, 731, 222, 50);
		this.userId.setFont(new Font("黑体",Font.BOLD,30));
		//用户余额显示
		this.userChange = new JLabel("");
		this.userChange.setBounds(251, 811, 222, 50);
		this.userChange.setFont(new Font("黑体",Font.BOLD,30));
		//早中晚餐显示
		this.mealsKind = new JLabel();
		this.mealsKind.setBounds(1050, 147, 200, 50);
		this.mealsKind.setFont(new Font("黑体",Font.BOLD,30));
		//时间显示
		this.timeEcho = new JLabel();
		this.timeEcho.setBounds(34, 22, 600, 50);
		this.timeEcho.setFont(new Font("黑体",Font.BOLD,30));
		this.timeEcho.setForeground(Color.white);
		this.addTimeLabel();
		//设置按钮
		this.settingBackJLabel = new JLabel();
		this.settingBackJLabel.setBounds(1094, 12, 100, 80);
		this.settingBackJLabel.addMouseListener(this);
		//当前操作员用户名显示
		this.userEcho = new JLabel();
		this.userEcho.setBounds(500, 22, 500, 50);
		this.userEcho.setFont(new Font("黑体",Font.BOLD,30));
		this.userEcho.setForeground(Color.white);
		this.userEcho.setText("当前操作员:"+this.strUser);
		//消费金额显示
		this.comsuptionLabel = new JLabel();
		this.comsuptionLabel.setBounds(731,746, 400, 100);
		this.comsuptionLabel.setFont(new Font("黑体",Font.BOLD,40));
		this.comsuptionLabel.setHorizontalAlignment(JLabel.CENTER);		
		//就餐人数显示
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
		//启动线程进行饭卡监听
		Thread thread_jc = new Thread(()->{
			String mealValue = null;
			
			cardDuty cDuty = new cardDuty();
					//监听饭卡刷卡形式的循环
				while (true) {
					while(!(cDuty.openICcardPort().equals("ERROR"))){
						if (jcFrame.this.mealsKind.getText().equals("早餐")) {
							mealValue = "4";
						}
						if (jcFrame.this.mealsKind.getText().equals("午餐")) {
							mealValue = "10";
						}
						if (jcFrame.this.mealsKind.getText().equals("晚餐")) {
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
			//处理串口发来数据的线程
			new Thread(()->{
				sTools.openSerialPort(cpif, 20);
			}).start();
		}
		
		thread.start();
		thread_jc.start();
	}
	private void setNums(JLabel label){
		label.setFont(new Font("黑体",Font.BOLD,30));
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
			Enumeration tempPortList;  //枚举类
			CommPortIdentifier portIp;
			tempPortList=CommPortIdentifier.getPortIdentifiers();
			/*不带参数的getPortIdentifiers方法获得一个枚举对象，该对象又包含了系统中管理每个端口的CommPortIdentifier对象。
			 * 注意这里的端口不仅仅是指串口，也包括并口。
			 * 这个方法还可以带参数。
			 * getPortIdentifiers(CommPort)获得与已经被应用程序打开的端口相对应的CommPortIdentifier对象。 
			 * getPortIdentifier(String portName)获取指定端口名（比如“COM1”）的CommPortIdentifier对象。
			 */
			while(tempPortList.hasMoreElements()){
				//在这里可以调用getPortType方法返回端口类型，串口为CommPortIdentifier.PORT_SERIAL
				portIp=(CommPortIdentifier) tempPortList.nextElement();
				portList.add(portIp);
			}
			return portList;
		}
		public boolean openSerialPort(CommPortIdentifier portIp,int delay){
			try {
				serialPort=(SerialPort) portIp.open(appName, delay);
				/* open方法打开通讯端口，获得一个CommPort对象。它使程序独占端口。
				 * 如果端口正被其他应用程序占用，将使用 CommPortOwnershipListener事件机制，传递一个PORT_OWNERSHIP_REQUESTED事件。
				 * 每个端口都关联一个 InputStream 和一个OutputStream。
				 * 如果端口是用open方法打开的，那么任何的getInputStream都将返回相同的数据流对象，除非有close 被调用。
				 * 有两个参数，第一个为应用程序名；第二个参数是在端口打开时阻塞等待的毫秒数。
				 */
			} catch (PortInUseException e) {
				return false;
			}
			try {
				is=serialPort.getInputStream();/*获取端口的输入流对象*/
				os=serialPort.getOutputStream();/*获取端口的输出流对象*/
			} catch (IOException e) {
				return false;
			}
			try {
				serialPort.addEventListener(this);/*注册一个SerialPortEventListener事件来监听串口事件*/
			} catch (TooManyListenersException e) {
				return false;
			}
			serialPort.notifyOnDataAvailable(true);/*数据可用*/
			try {
				/*设置串口初始化参数，依次是波特率，数据位，停止位和校验*/
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
			 * 此处省略一下事件，可酌情添加
			 *  SerialPortEvent.BI:/*Break interrupt,通讯中断
			 *  SerialPortEvent.OE:/*Overrun error，溢位错误
			 *  SerialPortEvent.FE:/*Framing error，传帧错误
			 *  SerialPortEvent.PE:/*Parity error，校验错误
			 *  SerialPortEvent.CD:/*Carrier detect，载波检测 
			 *  SerialPortEvent.CTS:/*Clear to send，清除发送 
			 *  SerialPortEvent.DSR:/*Data set ready，数据设备就绪
			 *  SerialPortEvent.RI:/*Ring indicator，响铃指示
			 *  SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*Output buffer is empty，输出缓冲区清空
			 */
			if(event.getEventType()==SerialPortEvent.DATA_AVAILABLE){	
				/*Data available at the serial port，端口有可用数据。读到缓冲数组，输出到终端*/
				try {
					while(is.available()>0){
					// is.read(readBuffer);//收到的数据再此，可视情况处理
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
				Thread.sleep(50);//每次收发数据完毕后线程暂停50ms
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
			//第一次HTTP请求，获取用户信息
			jsonObject = testHttPInterface.doPost("http://text.jinshangfoods.com/api/user/show", jsonObject);
			System.out.println(jsonObject.toString());
			jsonObject = (JsonObject) jsonObject.get("data");
			//将token存入数据库中
			dm.saveToken(jsonObject.get("mobile").getAsString(), jsonObject.get("remember_token").getAsString(),gainTime.gainDateAndTime());
			System.out.println(jsonObject.toString());
			jsonObject = new JsonObject();
			jsonObject.addProperty("time",gainTime.gainDateAndTime());
			jsonObject.addProperty("mobile", str1);
			if (jcFrame.this.mealsKind.getText().equals("早餐")) {
				jsonObject.addProperty("type", 1);
			}
			if (jcFrame.this.mealsKind.getText().equals("中餐")) {
				jsonObject.addProperty("type", 2);
			}
			if (jcFrame.this.mealsKind.getText().equals("晚餐")) {
				jsonObject.addProperty("type", 3);
			}
			jsonObject2 = new JsonObject();
			jsonObject2.addProperty("contents", Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate()));
			//第二次HTTP请求，获取用户订餐信息.
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
				this.comsuptionLabel.setText(toolsClass.coinToYuan(amount)+"元");
				//第三次HTTP请求，进行订单支付
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
				//没有订单信息就走现场支付流程
			}else if (jsonObject.get("code").getAsString().equals("1050003")) {
				
			}
			dm.queryUserInfo(str1);
			Image img=Toolkit.getDefaultToolkit().createImage(dm.getBytes(), 0,dm.getBytes().length);
			imagIcon = new ImageIcon(img.getScaledInstance(280,367,0));
			jcFrame.this.userImg.setIcon(imagIcon);
			jcFrame.this.userName.setText(dm.getName());
			jcFrame.this.userId.setText(dm.getMobile());
			//3秒后界面恢复
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
