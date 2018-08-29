package com.js.jhjs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.TooManyListenersException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.http.impl.NoConnectionReuseStrategy;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.js.jhjs.wmFrame.SerialTools;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.prism.Graphics;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import jdk.internal.util.xml.impl.ReaderUTF8;

public class wmFrame implements MouseListener {

	private JFrame wmFrames;
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
	private JLabel weighLabel;
	private JLayeredPane jcLayeredPane = new JLayeredPane();
	private String strUser;
	private JScrollPane jp = null;
	private DatabaseManipulate dbm_charge = new DatabaseManipulate();
	private DatabaseManipulate dm = new DatabaseManipulate();
	private InfiniteProgressPanel glasspane = new InfiniteProgressPanel();
	private Vector<Vector<String>> vectorLabel = new Vector<Vector<String>>();
	private JTable table = new JTable();
	private ArrayList<String> list = new ArrayList<String>();

	public wmFrame(String str) {
		this.initwmFrame("img/4.jpg");
		this.strUser = str;
	}

	public void initwmFrame(String pathName) {
		this.jp = new JScrollPane(this.table);
		initTable();
		// 初始化frame
		this.wmFrames = new JFrame("外卖");
		this.wmFrames.setUndecorated(true);
		this.wmFrames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.wmFrames.setBounds(0, 0, 1280, 1024);
		// 载入日志模块
		this.logLabel = new JLabel();
		this.logLabel.setBounds(0, 0, 1280, 50);
		this.logLabel.setForeground(Color.white);
		// 载入背景图片
		this.bgImage = new JLabel(new ImageIcon(pathName));
		this.bgImage.setSize(1280, 1024);
		this.jcPanel.add(this.bgImage);
		this.jcPanel.setBounds(0, 0, 1280, 1024);
		// 用户头像设置
		this.userImg = new JLabel("这里要放用户照片哦");
		this.userImg.setBounds(198, 189, 280, 367);
		this.userImg.setBackground(Color.GRAY);
		this.userImg.setOpaque(true);
		// 用户名显示
		this.userName = new JLabel("这里是用户名");
		this.userName.setBounds(251, 651, 222, 50);
		this.userName.setBackground(Color.GRAY);
		this.userName.setOpaque(true);
		// 用户ID显示
		this.userId = new JLabel("这里是用户ID");
		this.userId.setBounds(251, 731, 222, 50);
		this.userId.setBackground(Color.GRAY);
		this.userId.setOpaque(true);
		// 用户余额显示
		this.userChange = new JLabel("这里是用户余额显示");
		this.userChange.setBounds(251, 811, 222, 50);
		this.userChange.setBackground(Color.GRAY);
		this.userChange.setOpaque(true);
		// 早中晚餐显示
		this.mealsKind = new JLabel("外卖");
		this.mealsKind.setBounds(1050, 147, 200, 50);
		this.mealsKind.setFont(new Font("黑体", Font.BOLD, 30));
		// 时间显示
		this.timeEcho = new JLabel();
		this.timeEcho.setBounds(34, 22, 600, 50);
		this.timeEcho.setFont(new Font("黑体", Font.BOLD, 30));
		this.timeEcho.setForeground(Color.white);
		this.addTimeLabel();
		// 设置按钮
		this.settingBackJLabel = new JLabel();
		this.settingBackJLabel.setBounds(1094, 12, 100, 80);
		this.settingBackJLabel.addMouseListener(this);
		
		this.weighLabel = new JLabel("这是称重界面");
		this.weighLabel.setBounds(200, 400, 880, 228);
//		this.weighLabel.setBackground(Color.white);
		this.weighLabel.setOpaque(true);
		this.jcPanel2.setLayout(null);
		
		this.jcPanel2.add(this.userImg);
		this.jcPanel2.add(this.userName);
		this.jcPanel2.add(this.userId);
		this.jcPanel2.add(this.userChange);
		this.jcPanel2.add(this.timeEcho);
		this.jcPanel2.add(this.logLabel);
		this.jcPanel2.add(this.mealsKind);
		this.jcPanel2.add(this.settingBackJLabel);
		this.jcPanel2.add(this.jp);
		this.jcPanel2.setBounds(0, 0, 1280, 1024);
		this.jcPanel2.setOpaque(false);

		this.jcLayeredPane.add(this.jcPanel, 2);
		this.jcLayeredPane.add(this.jcPanel2, 1);
		wmFrames.setGlassPane(glasspane);
		this.wmFrames.add(this.jcLayeredPane);
		this.wmFrames.setVisible(true);
		// 启动线程进行对时操作
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				gainTime gtt = new gainTime();
				if (gtt.gainWebTime().equals("GAINERROR")) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "对时失败，请注意系统时间是否正确！", "对时失败", JOptionPane.INFORMATION_MESSAGE);
				} else {
					wmFrame.this.logLabel.setText("系统对时成功！");
					try {
						Thread.sleep(3000);
						wmFrame.this.logLabel.setText("");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				gtt.setSystemTime(gtt.gainWebTime());
			}
		});
		// 启动线程进行饭卡监听
		Thread thread_wm = new Thread(() -> {
			String mealValue = null;
			cardDuty cDuty = new cardDuty();
			// 监听饭卡刷卡形式的循环
			while (true) {
				String icNums = cDuty.openICcardPort();
				if (!icNums.equals("ERROR")) {
					// wmFrame.this.dealDuties(wmFrame.this.dm.searchWithNum(icNums));
				}
			}
		});
		wmFrame.SerialTools sTools = this.new SerialTools();
		Set<CommPortIdentifier> portgetList = sTools.getPortList();
		for (CommPortIdentifier cpif : portgetList) {
			// 处理串口发来数据的线程
			new Thread(() -> {
				sTools.openSerialPort(cpif, 20);
			}).start();
		}

		thread.start();
		thread_wm.start();
	}

	public void addTimeLabel() {
		Thread threadTime = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				gainTime gTime = new gainTime();
				gTime.setSystemTime(gTime.gainDateAndTime());
				String string = "";
				while (true) {
					string = "";
					string = string + gTime.gainDateAndTime();
					wmFrame.this.timeEcho.setText(string);
				}

			}
		});
		threadTime.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getComponent().equals(this.settingBackJLabel)) {
			new settingFrame(this.wmFrames, this.strUser);
		}
		if(e.getComponent().equals(this.table)){
			if(this.table.getValueAt(this.table.getSelectedRow(), 3).equals("称重")){
				this.weighLabel.setText("请放上"+this.table.getValueAt(this.table.getSelectedRow(), 0));
				this.weighLabel.setHorizontalAlignment(JLabel.CENTER);
				this.weighLabel.setFont(new Font("黑体",Font.BOLD,40));
				this.jcLayeredPane.add(this.weighLabel,0);
			}
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

		private boolean isOpen = false;
		private Set<CommPortIdentifier> portList = new HashSet<CommPortIdentifier>();
		final static String appName = "MyApp";
		private InputStream is;
		private OutputStream os;
		private BufferedReader br;
		private SerialPort serialPort;
		byte[] readBuffer = new byte[100];
		protected String num = null;

		public Set<CommPortIdentifier> getPortList() {
			Enumeration tempPortList; // 枚举类
			CommPortIdentifier portIp;
			tempPortList = CommPortIdentifier.getPortIdentifiers();
			/*
			 * 不带参数的getPortIdentifiers方法获得一个枚举对象，
			 * 该对象又包含了系统中管理每个端口的CommPortIdentifier对象。 注意这里的端口不仅仅是指串口，也包括并口。
			 * 这个方法还可以带参数。 getPortIdentifiers(CommPort)
			 * 获得与已经被应用程序打开的端口相对应的CommPortIdentifier对象。 getPortIdentifier(String
			 * portName)获取指定端口名（比如“COM1”）的CommPortIdentifier对象。
			 */
			while (tempPortList.hasMoreElements()) {
				// 在这里可以调用getPortType方法返回端口类型，串口为CommPortIdentifier.PORT_SERIAL
				portIp = (CommPortIdentifier) tempPortList.nextElement();
				portList.add(portIp);
			}
			return portList;
		}

		public boolean openSerialPort(CommPortIdentifier portIp, int delay) {
			try {
				serialPort = (SerialPort) portIp.open(appName, delay);
				/*
				 * open方法打开通讯端口，获得一个CommPort对象。它使程序独占端口。 如果端口正被其他应用程序占用，将使用
				 * CommPortOwnershipListener事件机制，传递一个PORT_OWNERSHIP_REQUESTED事件。
				 * 每个端口都关联一个 InputStream 和一个OutputStream。
				 * 如果端口是用open方法打开的，那么任何的getInputStream都将返回相同的数据流对象，除非有close 被调用。
				 * 有两个参数，第一个为应用程序名；第二个参数是在端口打开时阻塞等待的毫秒数。
				 */
			} catch (PortInUseException e) {
				return false;
			}
			try {
				is = serialPort.getInputStream();/* 获取端口的输入流对象 */
				os = serialPort.getOutputStream();/* 获取端口的输出流对象 */
			} catch (IOException e) {
				return false;
			}
			try {
				serialPort.addEventListener(this);/* 注册一个SerialPortEventListener事件来监听串口事件 */
			} catch (TooManyListenersException e) {
				return false;
			}
			serialPort.notifyOnDataAvailable(true);/* 数据可用 */
			try {
				/* 设置串口初始化参数，依次是波特率，数据位，停止位和校验 */
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);
			} catch (UnsupportedCommOperationException e) {
				return false;
			}
			return true;
		}

		public boolean closeSerialPort() {
			if (isOpen) {
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

		public boolean sendMessage(String message) {
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
			 * 此处省略一下事件，可酌情添加 SerialPortEvent.BI:/*Break interrupt,通讯中断
			 * SerialPortEvent.OE:/*Overrun error，溢位错误
			 * SerialPortEvent.FE:/*Framing error，传帧错误
			 * SerialPortEvent.PE:/*Parity error，校验错误
			 * SerialPortEvent.CD:/*Carrier detect，载波检测
			 * SerialPortEvent.CTS:/*Clear to send，清除发送
			 * SerialPortEvent.DSR:/*Data set ready，数据设备就绪
			 * SerialPortEvent.RI:/*Ring indicator，响铃指示
			 * SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*Output buffer is
			 * empty，输出缓冲区清空
			 */
			if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
				/* Data available at the serial port，端口有可用数据。读到缓冲数组，输出到终端 */
				try {
					while (is.available() > 0) {
						// is.read(readBuffer);//收到的数据再此，可视情况处理
						br = new BufferedReader(new ReaderUTF8(is));
						String str = br.readLine();
						System.out.println(str);
						wmFrame.this.dealDuties(str);

					}
				} catch (IOException e) {
				}
			}
		}

		public String putNumOut() {
			return this.num;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(50);// 每次收发数据完毕后线程暂停50ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void dealDuties(String string) {
		String str1;
		String str2;
		String strComsuption = null;
		String remains = null;
		JsonObject jsonObject = null;
		JsonObject jsonObject2 = null;
		ImageIcon imagIcon = null;
		if (string.length() == 24) {
			glasspane.start();
			jsonObject = new JsonObject();
			str1 = Encrypt.decrypt2(string, gainTime.gainDate()).substring(3);
			jsonObject.addProperty("time", gainTime.gainDateAndTime());
			jsonObject.addProperty("mobile", str1);
			str2 = Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate());
			jsonObject = new JsonObject();
			jsonObject.addProperty("contents", str2);
			// 第一次HTTP请求，获取用户信息
			jsonObject = testHttPInterface.doPost("http://text.jinshangfoods.com/api/user/show", jsonObject);
			System.out.println(jsonObject.toString());
			jsonObject = (JsonObject) jsonObject.get("data");
			// 将token存入数据库中
			// dm.saveToken(jsonObject.get("mobile").getAsString(),
			// jsonObject.get("remember_token").getAsString(),gainTime.gainDateAndTime());
			System.out.println(jsonObject.toString());
			// 获取订单信息
			jsonObject = new JsonObject();
			jsonObject.addProperty("time", gainTime.gainDateAndTime());
			jsonObject.addProperty("mobile", str1);
			str2 = Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate());
			jsonObject = new JsonObject();
			jsonObject.addProperty("contents", str2);
			jsonObject = testHttPInterface.doPost("http://text.jinshangfoods.com/api/order/takeout", jsonObject);
			System.out.println(jsonObject.toString());
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("名称");
			columnNames.add("单价");
			columnNames.add("数量");
			columnNames.add("总价");
			initDataByVector(jsonObject);
			DefaultTableModel dtm = new DefaultTableModel(this.vectorLabel, columnNames);
			this.table.setModel(dtm);
			TableCellRenderer tcr = new MyTableCellRender();
			this.table.setDefaultRenderer(Object.class, tcr);
			System.out.println(jsonObject.toString());
			dm.queryUserInfo(str1);
			Image img = Toolkit.getDefaultToolkit().createImage(dm.getBytes(), 0, dm.getBytes().length);
			imagIcon = new ImageIcon(img.getScaledInstance(280, 367, 0));
			glasspane.stop();
			// 统一界面显示
			// this.comsuptionLabel.setText(toolsClass.coinToYuan(strComsuption));
			this.userChange.setText(remains);
			this.userImg.setIcon(imagIcon);
			this.userName.setText(dm.getName());
			this.userId.setText(dm.getMobile());

		}
		if (string.startsWith("ST")) {
			System.out.println(string);
			this.weighLabel.setText(string.substring(3));

		}
		System.gc();
	}

	public void initTable() {
		this.jp.setBounds(667, 204, 534, 428);
		this.table.setFont(new Font("宋体", Font.PLAIN, 20));
		this.table.addMouseListener(this);
		this.table.setRowHeight(50);

	}

	public void initDataByVector(JsonObject json) {
		this.list.removeAll(list);
		JsonArray jArray = new JsonArray();
		JsonObject jsonobject = (JsonObject) json.get("data");
		jArray = (JsonArray) jsonobject.get("order_takeout");
		JsonObject jsonCache = null;
		for (int i = 0; i < jArray.size(); i++) {
			jsonCache = (JsonObject) jArray.get(i);
			Vector<String> vectors = new Vector<String>();
			vectors.add(jsonCache.get("name").getAsString());
			vectors.add(toolsClass.coinToYuan(jsonCache.get("price").getAsString()));
			vectors.add(jsonCache.get("num").getAsString());
			
			if (jsonCache.get("is_weigh").getAsString().equals("1")) {
				this.list.add(String.valueOf(i));
				vectors.add("称重");
			}else {
				vectors.add(toolsClass.coinToYuan(String.valueOf(Integer.parseInt(jsonCache.get("price").getAsString())
						* Integer.parseInt(jsonCache.get("num").getAsString()))));
			}
			this.vectorLabel.add(vectors);
		}

	}


	class MyTableCellRender extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(wmFrame.this.list.contains(String.valueOf(row))){
				setBackground(Color.red);
				setForeground(Color.white);
			}else {
				setBackground(Color.white);
				setForeground(Color.black);
			}
			setHorizontalAlignment(JLabel.CENTER);
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}

}
