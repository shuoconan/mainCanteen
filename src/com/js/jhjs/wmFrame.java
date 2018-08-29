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
		// ��ʼ��frame
		this.wmFrames = new JFrame("����");
		this.wmFrames.setUndecorated(true);
		this.wmFrames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.wmFrames.setBounds(0, 0, 1280, 1024);
		// ������־ģ��
		this.logLabel = new JLabel();
		this.logLabel.setBounds(0, 0, 1280, 50);
		this.logLabel.setForeground(Color.white);
		// ���뱳��ͼƬ
		this.bgImage = new JLabel(new ImageIcon(pathName));
		this.bgImage.setSize(1280, 1024);
		this.jcPanel.add(this.bgImage);
		this.jcPanel.setBounds(0, 0, 1280, 1024);
		// �û�ͷ������
		this.userImg = new JLabel("����Ҫ���û���ƬŶ");
		this.userImg.setBounds(198, 189, 280, 367);
		this.userImg.setBackground(Color.GRAY);
		this.userImg.setOpaque(true);
		// �û�����ʾ
		this.userName = new JLabel("�������û���");
		this.userName.setBounds(251, 651, 222, 50);
		this.userName.setBackground(Color.GRAY);
		this.userName.setOpaque(true);
		// �û�ID��ʾ
		this.userId = new JLabel("�������û�ID");
		this.userId.setBounds(251, 731, 222, 50);
		this.userId.setBackground(Color.GRAY);
		this.userId.setOpaque(true);
		// �û������ʾ
		this.userChange = new JLabel("�������û������ʾ");
		this.userChange.setBounds(251, 811, 222, 50);
		this.userChange.setBackground(Color.GRAY);
		this.userChange.setOpaque(true);
		// ���������ʾ
		this.mealsKind = new JLabel("����");
		this.mealsKind.setBounds(1050, 147, 200, 50);
		this.mealsKind.setFont(new Font("����", Font.BOLD, 30));
		// ʱ����ʾ
		this.timeEcho = new JLabel();
		this.timeEcho.setBounds(34, 22, 600, 50);
		this.timeEcho.setFont(new Font("����", Font.BOLD, 30));
		this.timeEcho.setForeground(Color.white);
		this.addTimeLabel();
		// ���ð�ť
		this.settingBackJLabel = new JLabel();
		this.settingBackJLabel.setBounds(1094, 12, 100, 80);
		this.settingBackJLabel.addMouseListener(this);
		
		this.weighLabel = new JLabel("���ǳ��ؽ���");
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
		// �����߳̽��ж�ʱ����
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				gainTime gtt = new gainTime();
				if (gtt.gainWebTime().equals("GAINERROR")) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "��ʱʧ�ܣ���ע��ϵͳʱ���Ƿ���ȷ��", "��ʱʧ��", JOptionPane.INFORMATION_MESSAGE);
				} else {
					wmFrame.this.logLabel.setText("ϵͳ��ʱ�ɹ���");
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
		// �����߳̽��з�������
		Thread thread_wm = new Thread(() -> {
			String mealValue = null;
			cardDuty cDuty = new cardDuty();
			// ��������ˢ����ʽ��ѭ��
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
			// �����ڷ������ݵ��߳�
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
			if(this.table.getValueAt(this.table.getSelectedRow(), 3).equals("����")){
				this.weighLabel.setText("�����"+this.table.getValueAt(this.table.getSelectedRow(), 0));
				this.weighLabel.setHorizontalAlignment(JLabel.CENTER);
				this.weighLabel.setFont(new Font("����",Font.BOLD,40));
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
			Enumeration tempPortList; // ö����
			CommPortIdentifier portIp;
			tempPortList = CommPortIdentifier.getPortIdentifiers();
			/*
			 * ����������getPortIdentifiers�������һ��ö�ٶ���
			 * �ö����ְ�����ϵͳ�й���ÿ���˿ڵ�CommPortIdentifier���� ע������Ķ˿ڲ�������ָ���ڣ�Ҳ�������ڡ�
			 * ������������Դ������� getPortIdentifiers(CommPort)
			 * ������Ѿ���Ӧ�ó���򿪵Ķ˿����Ӧ��CommPortIdentifier���� getPortIdentifier(String
			 * portName)��ȡָ���˿��������硰COM1������CommPortIdentifier����
			 */
			while (tempPortList.hasMoreElements()) {
				// ��������Ե���getPortType�������ض˿����ͣ�����ΪCommPortIdentifier.PORT_SERIAL
				portIp = (CommPortIdentifier) tempPortList.nextElement();
				portList.add(portIp);
			}
			return portList;
		}

		public boolean openSerialPort(CommPortIdentifier portIp, int delay) {
			try {
				serialPort = (SerialPort) portIp.open(appName, delay);
				/*
				 * open������ͨѶ�˿ڣ����һ��CommPort������ʹ�����ռ�˿ڡ� ����˿���������Ӧ�ó���ռ�ã���ʹ��
				 * CommPortOwnershipListener�¼����ƣ�����һ��PORT_OWNERSHIP_REQUESTED�¼���
				 * ÿ���˿ڶ�����һ�� InputStream ��һ��OutputStream��
				 * ����˿�����open�����򿪵ģ���ô�κε�getInputStream����������ͬ�����������󣬳�����close �����á�
				 * ��������������һ��ΪӦ�ó��������ڶ����������ڶ˿ڴ�ʱ�����ȴ��ĺ�������
				 */
			} catch (PortInUseException e) {
				return false;
			}
			try {
				is = serialPort.getInputStream();/* ��ȡ�˿ڵ����������� */
				os = serialPort.getOutputStream();/* ��ȡ�˿ڵ���������� */
			} catch (IOException e) {
				return false;
			}
			try {
				serialPort.addEventListener(this);/* ע��һ��SerialPortEventListener�¼������������¼� */
			} catch (TooManyListenersException e) {
				return false;
			}
			serialPort.notifyOnDataAvailable(true);/* ���ݿ��� */
			try {
				/* ���ô��ڳ�ʼ�������������ǲ����ʣ�����λ��ֹͣλ��У�� */
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
			 * �˴�ʡ��һ���¼������������ SerialPortEvent.BI:/*Break interrupt,ͨѶ�ж�
			 * SerialPortEvent.OE:/*Overrun error����λ����
			 * SerialPortEvent.FE:/*Framing error����֡����
			 * SerialPortEvent.PE:/*Parity error��У�����
			 * SerialPortEvent.CD:/*Carrier detect���ز����
			 * SerialPortEvent.CTS:/*Clear to send���������
			 * SerialPortEvent.DSR:/*Data set ready�������豸����
			 * SerialPortEvent.RI:/*Ring indicator������ָʾ
			 * SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*Output buffer is
			 * empty��������������
			 */
			if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
				/* Data available at the serial port���˿��п������ݡ������������飬������ն� */
				try {
					while (is.available() > 0) {
						// is.read(readBuffer);//�յ��������ٴˣ������������
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
				Thread.sleep(50);// ÿ���շ�������Ϻ��߳���ͣ50ms
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
			// ��һ��HTTP���󣬻�ȡ�û���Ϣ
			jsonObject = testHttPInterface.doPost("http://text.jinshangfoods.com/api/user/show", jsonObject);
			System.out.println(jsonObject.toString());
			jsonObject = (JsonObject) jsonObject.get("data");
			// ��token�������ݿ���
			// dm.saveToken(jsonObject.get("mobile").getAsString(),
			// jsonObject.get("remember_token").getAsString(),gainTime.gainDateAndTime());
			System.out.println(jsonObject.toString());
			// ��ȡ������Ϣ
			jsonObject = new JsonObject();
			jsonObject.addProperty("time", gainTime.gainDateAndTime());
			jsonObject.addProperty("mobile", str1);
			str2 = Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate());
			jsonObject = new JsonObject();
			jsonObject.addProperty("contents", str2);
			jsonObject = testHttPInterface.doPost("http://text.jinshangfoods.com/api/order/takeout", jsonObject);
			System.out.println(jsonObject.toString());
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("����");
			columnNames.add("����");
			columnNames.add("����");
			columnNames.add("�ܼ�");
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
			// ͳһ������ʾ
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
		this.table.setFont(new Font("����", Font.PLAIN, 20));
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
				vectors.add("����");
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
