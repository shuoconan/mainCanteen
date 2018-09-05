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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.TooManyListenersException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import com.sun.javafx.font.Disposer;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
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
	private SerialTools st = null;
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
	private JLabel logsLabel;
	private JLabel totalLabel;
	private JLabel toPayLabel;
	private JLabel payLabel;
	private JLabel okTocalcu;
	private JLabel payMoney;
	private String orderId;
	private String amount;
	private JLayeredPane jcLayeredPane = new JLayeredPane();
	private String strUser;
	private JScrollPane jp = null;
	private DatabaseManipulate dbm_charge = new DatabaseManipulate();
	private DatabaseManipulate dm = new DatabaseManipulate();
	private InfiniteProgressPanel glasspane = new InfiniteProgressPanel();
	private Vector<Vector<String>> vectorLabel = new Vector<Vector<String>>();
	private JTable table = new JTable();
	private ArrayList<String> list = new ArrayList<String>();
	private Set<CommPortIdentifier> portList = null;
	private ArrayList<SerialTools> cpifList = new ArrayList<SerialTools>();
	private settingFrame sf = null;


	public wmFrame(String str) {
		this.initwmFrame("img/4.jpg");
		this.strUser = str;
	}
	public void setOtherFrame(settingFrame sf){
		this.sf = sf;
	}
	public boolean getVisible(){
		return this.wmFrames.isVisible();
	}
	public void setVisible(boolean status,String str){
		this.strUser = str;
		this.wmFrames.setVisible(status);
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
//		this.timeEcho.setOpaque(true);
		this.addTimeLabel();
		// ���ð�ť
		this.settingBackJLabel = new JLabel();
		this.settingBackJLabel.setBounds(1094, 12, 100, 80);
		this.settingBackJLabel.addMouseListener(this);
		//������ɺ��ȷ����ť
		this.okTocalcu = new JLabel("ȷ��");
		this.okTocalcu.setBounds(806, 889, 177, 68);
		this.okTocalcu.setFont(new Font("����",Font.BOLD,40));
		this.okTocalcu.setForeground(Color.white);
		this.okTocalcu.setHorizontalAlignment(JLabel.CENTER);
		this.okTocalcu.addMouseListener(this);
		//֧����ť
		this.payMoney = new JLabel("֧��");
		this.payMoney.setBounds(1020, 889, 177, 68);
		this.payMoney.setFont(new Font("����",Font.BOLD,40));
		this.payMoney.setForeground(Color.white);
		this.payMoney.setHorizontalAlignment(JLabel.CENTER);
		this.payMoney.addMouseListener(this);
		
		this.weighLabel = new JLabel("���ǳ��ؽ���");
		this.weighLabel.setBounds(200, 400, 880, 228);
		this.weighLabel.setVisible(false);
		this.weighLabel.setBackground(Color.white);
		
		this.logsLabel = new JLabel();
		this.logsLabel.setBounds(200, 400, 880, 228);
		this.logsLabel.setVisible(false);
		this.logsLabel.setBackground(Color.white);
//		this.weighLabel.setOpaque(true);
		//�ϼƽ����ʾ
		this.totalLabel = new JLabel("���Ǻϼƽ�����");
		this.totalLabel.setBounds(883, 648, 262, 60);
		this.totalLabel.setBackground(Color.GRAY);
		this.totalLabel.setOpaque(true);
		//��֧�������ʾ
		this.toPayLabel = new JLabel("���Ǵ�֧��������");
		this.toPayLabel.setBounds(883, 726, 262, 60);
		this.toPayLabel.setBackground(Color.GRAY);
		this.toPayLabel.setOpaque(true);
		//֧�������ʾ
		this.payLabel = new JLabel("����֧��������");
		this.payLabel.setBounds(883, 807, 262, 60);
		this.payLabel.setBackground(Color.GRAY);
		this.payLabel.setOpaque(true);
		
		this.jcPanel2.setLayout(null);
		
		this.jcPanel2.add(this.userImg);
		this.jcPanel2.add(this.userName);
		this.jcPanel2.add(this.userId);
		this.jcPanel2.add(this.userChange);
		this.jcPanel2.add(this.timeEcho);
		this.jcPanel2.add(this.logLabel);
		this.jcPanel2.add(this.mealsKind);
		this.jcPanel2.add(this.settingBackJLabel);
		this.jcPanel2.add(this.totalLabel);
		this.jcPanel2.add(this.toPayLabel);
		this.jcPanel2.add(this.payLabel);
		this.jcPanel2.add(this.payMoney);
		this.jcPanel2.add(this.okTocalcu);
		this.jcPanel2.add(this.jp);
		this.jcPanel2.setBounds(0, 0, 1280, 1024);
		this.jcPanel2.setOpaque(false);

		this.jcLayeredPane.add(this.jcPanel, JLayeredPane.DEFAULT_LAYER);
		this.jcLayeredPane.add(this.jcPanel2, JLayeredPane.PALETTE_LAYER);
		wmFrames.setGlassPane(glasspane);
		this.wmFrames.add(this.jcLayeredPane);
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
		if(e.getSource().equals(this.settingBackJLabel)){
			this.sf.setVisibles(true);
		}
		if(e.getComponent().equals(this.table)){
			if(this.table.getValueAt(this.table.getSelectedRow(), 3).equals("����")){
				this.weighLabel.setText("�����"+this.table.getValueAt(this.table.getSelectedRow(), 0));
				this.weighLabel.setVisible(true);
				this.weighLabel.setOpaque(true);
				this.weighLabel.setHorizontalAlignment(JLabel.CENTER);
				this.weighLabel.setFont(new Font("����",Font.BOLD,40));
				this.jcLayeredPane.add(this.weighLabel,JLayeredPane.MODAL_LAYER);
			}
		}
		if(e.getComponent().equals(this.okTocalcu)){
			if(this.list.size()==0){
				int totals = 0;
				String cacluString;
				DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
				for(int i=0;i<this.table.getRowCount();i++){
					cacluString = (String) dtm.getValueAt(i, 3);
					System.out.println(cacluString);
					totals = totals+toolsClass.yuanToDouble(cacluString);
				}
				this.totalLabel.setText(toolsClass.coinToYuan(String.valueOf(totals)));
				this.payLabel.setText(toolsClass.coinToYuan(String.valueOf(toolsClass.yuanToDouble(this.totalLabel.getText())-toolsClass.yuanToDouble(this.toPayLabel.getText()))));
			}else{
				
			}
		}
		if(e.getComponent().equals(this.payMoney)){
			JsonObject jObject = new JsonObject();
			JsonObject jObject2 = new JsonObject();
			jObject.addProperty("time", gainTime.gainDateAndTime());
			jObject.addProperty("mobile", this.userId.getText());
			jObject2.addProperty("contents", Encrypt.encrypt(jObject.toString(), gainTime.gainDate()));
			// ��һ��HTTP���󣬻�ȡ�û���Ϣ
			jObject = testHttPInterface.doPost("http://text.jinshangfoods.com/api/user/show", jObject2);
			jObject = (JsonObject) jObject.get("data");
			// ��token�������ݿ���
			dm.saveToken(jObject.get("mobile").getAsString(), jObject.get("remember_token").getAsString(),
					gainTime.gainDateAndTime());
			jObject = new JsonObject();
			jObject2 = new JsonObject();
			jObject.addProperty("time", gainTime.gainDateAndTime());
			jObject.addProperty("mobile",this.userId.getText());
			jObject.addProperty("token", dm.fenchToken(this.userId.getText()));
			jObject.addProperty("order_id",this.orderId);
			System.out.println(Integer.parseInt(this.amount));
			System.out.println(toolsClass.yuanToDouble(this.toPayLabel.getText()));
			jObject.addProperty("price",String.valueOf(toolsClass.yuanToDouble(this.totalLabel.getText().replaceAll("Ԫ", ""))-Integer.parseInt(this.amount)));
			jObject2.addProperty("contents", Encrypt.encrypt(jObject.toString(), gainTime.gainDate()));
			jObject = testHttPInterface.doPost("http://text.jinshangfoods.com/api/order/payment", jObject2);
			System.out.println(jObject.toString());
			if(jObject.get("code").getAsString().equals("0")){
				jObject = (JsonObject) jObject.get("data");
				this.userChange.setText(toolsClass.coinToYuan(jObject.get("money").getAsString()));
				JOptionPane.showMessageDialog(null, "֧���ɹ�", "֧���ɹ�", JOptionPane.YES_OPTION);
			}else {
				JOptionPane.showMessageDialog(null, "֧��ʧ��", "֧��ʧ��", JOptionPane.ERROR_MESSAGE);
			}
			new Thread(()->{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.userImg.setIcon(null);
				this.userName.setText("");
				this.userId.setText("");
				this.userChange.setText("");
				this.totalLabel.setText("");
				this.toPayLabel.setText("");
				this.payLabel.setText("");
				DefaultTableModel dtm = (DefaultTableModel) wmFrame.this.table.getModel();
				dtm.setRowCount(0);
				this.table.setModel(dtm);
			}).start();
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
			dm.saveToken(jsonObject.get("mobile").getAsString(),
			jsonObject.get("remember_token").getAsString(),gainTime.gainDateAndTime());
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
			if(jsonObject.get("code").getAsString().equals("0")){
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
			}else if (jsonObject.get("code").getAsString().equals("1050003")) {
				glasspane.stop();
				this.logsLabel.setText("û�в�ѯ������������Ϣ");
				this.logsLabel.setVisible(true);
				this.logsLabel.setOpaque(true);
				this.logsLabel.setHorizontalAlignment(JLabel.CENTER);
				this.logsLabel.setFont(new Font("����",Font.BOLD,40));
				this.jcLayeredPane.add(this.logsLabel,JLayeredPane.MODAL_LAYER);
				new Thread(()->{
					try {
						Thread.sleep(2000);
						wmFrame.this.jcLayeredPane.remove(wmFrame.this.logsLabel);
						wmFrame.this.jcLayeredPane.repaint();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
			}
			

		}
		if (string.startsWith("ST")) {
			DecimalFormat df=new DecimalFormat(".##");
			System.out.println(string);
			string = string.substring(9);
			this.weighLabel.setText(this.weighLabel.getText().substring(3)+":"+string);
			DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
			this.list.remove(String.valueOf(this.table.getSelectedRow()));
			if(((String) dtm.getValueAt(this.table.getSelectedRow(), 3)).equals("����")){
				dtm.setValueAt(string, this.table.getSelectedRow(), 2);
			}
			Double price = Double.parseDouble(((String)dtm.getValueAt(this.table.getSelectedRow(), 1)).replaceAll("Ԫ", ""));
			Double num = Double.parseDouble(((String)dtm.getValueAt(this.table.getSelectedRow(), 2)).replaceAll("kg", ""));
			dtm.setValueAt(df.format(price*num)+"Ԫ", this.table.getSelectedRow(), 3);
			new Thread(()->{
				try {
					Thread.sleep(1000);
					wmFrame.this.jcLayeredPane.remove(wmFrame.this.weighLabel);
					wmFrame.this.jcLayeredPane.repaint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();

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
		this.toPayLabel.setText(toolsClass.coinToYuan(jsonobject.get("deposit").getAsString()));
		this.amount = jsonobject.get("amount").getAsString();
		this.orderId = jsonobject.get("id").getAsString();
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
