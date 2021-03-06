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
	private String strUser = "";
	private JScrollPane jp = null;
//	private DatabaseManipulate dm = new DatabaseManipulate();
	private InfiniteProgressPanel glasspane = new InfiniteProgressPanel();
	private Vector<Vector<String>> vectorLabel = new Vector<Vector<String>>();
	private JTable table = new JTable();
	private ArrayList<String> list = new ArrayList<String>();
	private settingFrame sf = null;
	private ArrayList<String> listQueue = new ArrayList<String>();

	public wmFrame(String str) {
		this.initwmFrame("img/4.jpg");
		this.strUser = str;
	}

	public void setOtherFrame(settingFrame sf) {
		this.sf = sf;
	}

	public boolean getVisible() {
		return this.wmFrames.isVisible();
	}

	public void setVisible(boolean status, String str) {
		this.strUser = str;
		this.wmFrames.setVisible(status);
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
		this.userImg = new JLabel("");
		this.userImg.setBounds(198, 189, 280, 367);
		// 用户名显示
		this.userName = new JLabel("");
		this.userName.setBounds(251, 651, 222, 50);
		this.userName.setFont(new Font("黑体", Font.BOLD, 30));
		// 用户ID显示
		this.userId = new JLabel("");
		this.userId.setBounds(251, 731, 222, 50);
		this.userId.setFont(new Font("黑体", Font.BOLD, 30));
		// 用户余额显示
		this.userChange = new JLabel("");
		this.userChange.setBounds(251, 811, 222, 50);
		this.userChange.setFont(new Font("黑体", Font.BOLD, 30));
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
		// 称重完成后的确定按钮
		this.okTocalcu = new JLabel("确定");
		this.okTocalcu.setBounds(806, 889, 177, 68);
		this.okTocalcu.setFont(new Font("黑体", Font.BOLD, 40));
		this.okTocalcu.setForeground(Color.white);
		this.okTocalcu.setHorizontalAlignment(JLabel.CENTER);
		this.okTocalcu.addMouseListener(this);
		// 支付按钮
		this.payMoney = new JLabel("支付");
		this.payMoney.setBounds(1020, 889, 177, 68);
		this.payMoney.setFont(new Font("黑体", Font.BOLD, 40));
		this.payMoney.setForeground(Color.white);
		this.payMoney.setHorizontalAlignment(JLabel.CENTER);
		this.payMoney.addMouseListener(this);
		// 称重界面显示
		this.weighLabel = new JLabel("");
		this.weighLabel.setBounds(200, 400, 880, 228);
		this.weighLabel.setVisible(false);
		this.weighLabel.setBackground(Color.white);

		this.logsLabel = new JLabel();
		this.logsLabel.setBounds(200, 400, 880, 228);
		this.logsLabel.setVisible(false);
		this.logsLabel.setBackground(Color.white);
		// 合计金额显示
		this.totalLabel = new JLabel("");
		this.totalLabel.setBounds(883, 648, 262, 60);
		this.totalLabel.setFont(new Font("黑体", Font.BOLD, 30));
		// 待支付金额显示
		this.toPayLabel = new JLabel("");
		this.toPayLabel.setBounds(883, 726, 262, 60);
		this.toPayLabel.setFont(new Font("黑体", Font.BOLD, 30));
		// 支付金额显示
		this.payLabel = new JLabel("");
		this.payLabel.setBounds(883, 807, 262, 60);
		this.payLabel.setFont(new Font("黑体", Font.BOLD, 30));

		// 特权用户设置
		this.userId.addMouseListener(this);
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
		// 启动线程进行对时操作
	}

	public void addTimeLabel() {
		Thread threadTime = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				gainTime gTime = new gainTime();
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

	public void addArrayList(String str) {
		this.listQueue.add(str);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(this.settingBackJLabel)) {
			this.sf.setVisibles(true,this.strUser);
		}
		if (e.getComponent().equals(this.table)) {
			if (this.table.getValueAt(this.table.getSelectedRow(), 3).equals("称重")) {
				this.weighLabel.setText("请放上" + this.table.getValueAt(this.table.getSelectedRow(), 0));
				this.weighLabel.setVisible(true);
				this.weighLabel.setOpaque(true);
				this.weighLabel.setHorizontalAlignment(JLabel.CENTER);
				this.weighLabel.setFont(new Font("黑体", Font.BOLD, 40));
				this.jcLayeredPane.add(this.weighLabel, JLayeredPane.MODAL_LAYER);
			}
		}
		if (e.getComponent().equals(this.okTocalcu)) {
			if (this.list.size() == 0) {
				int totals = 0;
				String cacluString;
				DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
				for (int i = 0; i < this.table.getRowCount(); i++) {
					cacluString = (String) dtm.getValueAt(i, 3);
					System.out.println(cacluString);
					totals = totals + toolsClass.yuanToDouble(cacluString);
				}
				this.totalLabel.setText(toolsClass.coinToYuan(String.valueOf(totals)));
				this.payLabel
						.setText(toolsClass.coinToYuan(String.valueOf(toolsClass.yuanToDouble(this.totalLabel.getText())
								- toolsClass.yuanToDouble(this.toPayLabel.getText()))));
			} else {

			}
		}
		if (e.getComponent().equals(this.payMoney)) {
			JsonObject jObject = new JsonObject();
			JsonObject jObject2 = new JsonObject();
			jObject.addProperty("time", gainTime.gainDateAndTime());
			jObject.addProperty("mobile", this.userId.getText());
			jObject2.addProperty("contents", Encrypt.encrypt(jObject.toString(), gainTime.gainDate()));
			// 第一次HTTP请求，获取用户信息
			jObject = testHttPInterface.doPost("http://www.jinshangfoods.com/api/user/show", jObject2);
			if(jObject.get("code").getAsString().equals("0")){
				jObject = (JsonObject) jObject.get("data");
				// 将token存入数据库中
				DatabaseManipulate.saveToken(jObject.get("mobile").getAsString(), jObject.get("remember_token").getAsString(),
						gainTime.gainDateAndTime());
				jObject = new JsonObject();
				jObject2 = new JsonObject();
				jObject.addProperty("time", gainTime.gainDateAndTime());
				jObject.addProperty("mobile", this.userId.getText());
				jObject.addProperty("token", DatabaseManipulate.fenchToken(this.userId.getText()));
				jObject.addProperty("order_id", this.orderId);
				jObject.addProperty("price",
						String.valueOf(toolsClass.yuanToDouble(this.totalLabel.getText().replaceAll("元", ""))
								- Integer.parseInt(this.amount)));
				jObject2.addProperty("contents", Encrypt.encrypt(jObject.toString(), gainTime.gainDate()));
				jObject = testHttPInterface.doPost("http://www.jinshangfoods.com/api/order/payment", jObject2);
				System.out.println(jObject.toString());
				if (jObject.get("code").getAsString().equals("0")) {
					jObject = (JsonObject) jObject.get("data");
					this.userChange.setText(toolsClass.coinToYuan(jObject.get("money").getAsString()));
					this.logsLabel.setText("支付成功");
					this.logsLabel.setVisible(true);
					this.logsLabel.setOpaque(true);
					this.logsLabel.setHorizontalAlignment(JLabel.CENTER);
					this.logsLabel.setFont(new Font("黑体", Font.BOLD, 40));
					this.jcLayeredPane.add(this.logsLabel, JLayeredPane.MODAL_LAYER);
					new Thread(() -> {
						try {
							Thread.sleep(2000);
							wmFrame.this.jcLayeredPane.remove(wmFrame.this.logsLabel);
							wmFrame.this.jcLayeredPane.repaint();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}).start();
				} else {
					if(jObject.get("code").getAsString().equals("1050003")){
						JOptionPane.showMessageDialog(null, "找不到用户", "失败", JOptionPane.ERROR_MESSAGE);
						this.glasspane.stop();
						return;
					}
					if(jObject.get("code").getAsString().equals("1050007")){
						JOptionPane.showMessageDialog(null, "用户金额不足", "失败", JOptionPane.ERROR_MESSAGE);
						this.glasspane.stop();
						return;
					}
					if(jObject.get("code").getAsString().equals("1050005")){
						JOptionPane.showMessageDialog(null, "支付令牌错误", "失败", JOptionPane.ERROR_MESSAGE);
						this.glasspane.stop();
						return;
					}
				}
				new Thread(() -> {
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
			}else {
				JOptionPane.showMessageDialog(null, "获取用户信息失败", "失败", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if (e.getComponent().equals(this.userId)) {
			if (this.strUser.equals("superuser")) {
				String strNum = JOptionPane.showInputDialog(this.wmFrames, "请输入手机号码", "");
				if (strNum.length() == 6) {
					dutiesDo(strNum);
				} else {
					JOptionPane.showMessageDialog(null, "手机号输入有误", "失败", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "当前非特权用户", "失败", JOptionPane.ERROR_MESSAGE);
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

	public void weightDutiesDo(String string) {
		if (string.startsWith("ST")) {
			DecimalFormat df = new DecimalFormat(".##");
			System.out.println(string);
			string = string.substring(9);
			this.weighLabel.setText(this.weighLabel.getText().substring(3) + ":" + string);
			DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
			this.list.remove(String.valueOf(this.table.getSelectedRow()));
			if (((String) dtm.getValueAt(this.table.getSelectedRow(), 3)).equals("称重")) {
				dtm.setValueAt(string, this.table.getSelectedRow(), 2);
			}
			Double price = Double
					.parseDouble(((String) dtm.getValueAt(this.table.getSelectedRow(), 1)).replaceAll("元", ""));
			price = price*2;
			Double num = Double
					.parseDouble(((String) dtm.getValueAt(this.table.getSelectedRow(), 2)).replaceAll("kg", ""));
			dtm.setValueAt(df.format(price * num) + "元", this.table.getSelectedRow(), 3);
			new Thread(() -> {
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

	public void dutiesDo(String str1) {
		String str2;
		String remains = null;
		JsonObject jsonObject = null;
		ImageIcon imagIcon = null;
		if (str1.length() == 6) {
			glasspane.start();
			jsonObject = new JsonObject();
			jsonObject.addProperty("time", gainTime.gainDateAndTime());
			jsonObject.addProperty("mobile", str1);
			str2 = Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate());
			jsonObject = new JsonObject();
			jsonObject.addProperty("contents", str2);
			// 第一次HTTP请求，获取用户信息
			jsonObject = testHttPInterface.doPost("http://www.jinshangfoods.com/api/user/show", jsonObject);
			if(jsonObject.get("code").getAsString().equals("0")){
				jsonObject = (JsonObject) jsonObject.get("data");
				// 将token存入数据库中
				DatabaseManipulate.saveToken(jsonObject.get("mobile").getAsString(), jsonObject.get("remember_token").getAsString(),
						gainTime.gainDateAndTime());
				// 获取订单信息
				jsonObject = new JsonObject();
				jsonObject.addProperty("time", gainTime.gainDateAndTime());
				jsonObject.addProperty("mobile", str1);
				str2 = Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate());
				jsonObject = new JsonObject();
				jsonObject.addProperty("contents", str2);
				jsonObject = testHttPInterface.doPost("http://www.jinshangfoods.com/api/order/takeout", jsonObject);
				System.out.println(jsonObject.toString());
				if (jsonObject.get("code").getAsString().equals("0")) {
					Vector<String> columnNames = new Vector<String>();
					columnNames.add("名称");
					columnNames.add("单价(份|KG)");
					columnNames.add("数量");
					columnNames.add("总价");
					initDataByVector(jsonObject);
					DefaultTableModel dtm = new DefaultTableModel(this.vectorLabel, columnNames);
					this.table.setModel(dtm);
					TableCellRenderer tcr = new MyTableCellRender();
					this.table.setDefaultRenderer(Object.class, tcr);
					DatabaseManipulate.queryUserInfo(str1);
					Image img = Toolkit.getDefaultToolkit().createImage(DatabaseManipulate.getBytes(), 0, DatabaseManipulate.getBytes().length);
					imagIcon = new ImageIcon(img.getScaledInstance(280, 367, 0));
					glasspane.stop();
					// 统一界面显示
					// this.comsuptionLabel.setText(toolsClass.coinToYuan(strComsuption));
					this.userChange.setText(remains);
					this.userImg.setIcon(imagIcon);
					this.userName.setText(DatabaseManipulate.getName());
					this.userId.setText(DatabaseManipulate.getMobile());
				} else if (jsonObject.get("code").getAsString().equals("1050003")) {
					glasspane.stop();
					this.logsLabel.setText("没有查询到外卖订单信息");
					this.logsLabel.setVisible(true);
					this.logsLabel.setOpaque(true);
					this.logsLabel.setHorizontalAlignment(JLabel.CENTER);
					this.logsLabel.setFont(new Font("黑体", Font.BOLD, 40));
					this.jcLayeredPane.add(this.logsLabel, JLayeredPane.MODAL_LAYER);
					new Thread(() -> {
						try {
							Thread.sleep(2000);
							wmFrame.this.jcLayeredPane.remove(wmFrame.this.logsLabel);
							wmFrame.this.jcLayeredPane.repaint();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}).start();
				}else {
					if(jsonObject.get("code").getAsString().equals("1050007")){
						JOptionPane.showMessageDialog(null, "用户金额不足", "失败", JOptionPane.ERROR_MESSAGE);
						this.glasspane.stop();
						return;
					}
				}
			}
			

		}else {
			JOptionPane.showMessageDialog(null, "账号错误", "失败", JOptionPane.ERROR_MESSAGE);
			this.glasspane.stop();
			return;
		}

		System.gc();
	}

	public void dealDuties(String string) {
		String str1;
		String string2;
		if (string.length() > 52) {
			string = string.substring(0, string.length() - 4);
			str1 = toolsClass.toStringHex2(string);
			string2 = Encrypt.decrypt2(str1, gainTime.gainDate()).substring(11,17);
			System.out.println(string2);
			dutiesDo(string2);
		} else if (string.length() == 32) {
			string2 = DatabaseManipulate.searchWithNum(string);
			dutiesDo(string2);
		} else {
			str1 = toolsClass.toStringHex2(string);
			weightDutiesDo(str1);
		}
	}

	public void initTable() {
		this.jp.setBounds(667, 204, 534, 428);
		this.table.setFont(new Font("宋体", Font.PLAIN, 20));
		this.table.addMouseListener(this);
		this.table.setRowHeight(50);

	}

	public void initDataByVector(JsonObject json) {
		this.list.removeAll(list);
		this.vectorLabel.clear();
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
				vectors.add("称重");
			} else {
				vectors.add(toolsClass.coinToYuan(String.valueOf(Integer.parseInt(jsonCache.get("price").getAsString())
						* Integer.parseInt(jsonCache.get("num").getAsString()))));
			}
			this.vectorLabel.add(vectors);
		}

	}

	class MyTableCellRender extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (wmFrame.this.list.contains(String.valueOf(row))) {
				setBackground(Color.red);
				setForeground(Color.white);
			} else {
				setBackground(Color.white);
				setForeground(Color.black);
			}
			setHorizontalAlignment(JLabel.CENTER);
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}

}
