package com.js.jhjs;

import java.awt.Color;
import java.awt.Dimension;
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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.TooManyListenersException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.JsonObject;
import com.sun.jna.platform.win32.Ddeml;
import com.sun.org.apache.bcel.internal.generic.NEW;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import jdk.internal.util.xml.impl.ReaderUTF8;

public class jcFrame implements MouseListener {
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
	private JLabel num1 = new JLabel("1", JLabel.CENTER);
	private JLabel num2 = new JLabel("2", JLabel.CENTER);
	private JLabel num3 = new JLabel("3", JLabel.CENTER);
	private JLabel num4 = new JLabel("4", JLabel.CENTER);
	private JLabel num5 = new JLabel("5", JLabel.CENTER);
	private JLabel num6 = new JLabel("6", JLabel.CENTER);
	private JLabel num7 = new JLabel("7", JLabel.CENTER);
	private JLabel num8 = new JLabel("8", JLabel.CENTER);
	private JLabel num9 = new JLabel("9", JLabel.CENTER);
	private DatabaseManipulate dbm_charge = new DatabaseManipulate();
	private DatabaseManipulate dm = new DatabaseManipulate();
	private InfiniteProgressPanel glasspane = new InfiniteProgressPanel();
	private SerialTools st = null;
	private settingFrame sf = null;
	private boolean flags = true;
	private Thread threads = null;

	public jcFrame(String str) {
		this.strUser = str;
		this.initJcFrame("img/3.jpg");
	}
	public void setUser(String str){
		this.strUser = str;
	}
	
	public boolean getVisible(){
		return this.jcFrames.isVisible();
	}
	public void setVisible(boolean status,String strname){
		this.jcFrames.setVisible(status);
		this.strUser = strname;
		this.userEcho.setText("当前操作员:" + this.strUser);
	}
	public void setOtherFrame(settingFrame sf){
		this.sf = sf;
	}
	public void initJcFrame(String pathName) {
		// 初始化frame
		this.jcFrames = new JFrame("就餐");
		this.jcFrames.setUndecorated(true);
		this.jcFrames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jcFrames.setBounds(0, 0, 1280, 1024);
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
		this.userId.addMouseListener(this);
		// 用户余额显示
		this.userChange = new JLabel("");
		this.userChange.setBounds(251, 811, 222, 50);
		this.userChange.setFont(new Font("黑体", Font.BOLD, 30));
		// 早中晚餐显示
		this.mealsKind = new JLabel();
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
		// 当前操作员用户名显示
		this.userEcho = new JLabel();
		this.userEcho.setBounds(500, 22, 500, 50);
		this.userEcho.setFont(new Font("黑体", Font.BOLD, 30));
		this.userEcho.setForeground(Color.white);
		this.userEcho.setText("当前操作员:" + this.strUser);
		// 消费金额显示
		this.comsuptionLabel = new JLabel();
		this.comsuptionLabel.setBounds(731, 746, 400, 100);
		this.comsuptionLabel.setFont(new Font("黑体", Font.BOLD, 40));
		this.comsuptionLabel.setHorizontalAlignment(JLabel.CENTER);
		// 就餐人数显示
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
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		glasspane.setBounds(100, 100, (dimension.width) / 2, (dimension.height) / 2);
		jcFrames.setGlassPane(glasspane);
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

		this.jcLayeredPane.add(this.jcPanel, 1);
		this.jcLayeredPane.add(this.jcPanel2, 0);

		this.jcFrames.add(this.jcLayeredPane);
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
		
	}

	private void setNums(JLabel label) {
		label.setFont(new Font("黑体", Font.BOLD, 30));
		label.setOpaque(false);
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
					if ((Integer.parseInt(gTime.gainTime()) >= 0) && ((Integer.parseInt(gTime.gainTime()) <= 900))) {
						jcFrame.this.mealsKind.setText("早餐");
					}
					if ((Integer.parseInt(gTime.gainTime()) > 900) && ((Integer.parseInt(gTime.gainTime()) < 915))) {
						jcFrame.this.mealsKind.setText("午餐");
					}
					if ((Integer.parseInt(gTime.gainTime()) > 915) && ((Integer.parseInt(gTime.gainTime()) < 2400))) {
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
		if(e.getSource().equals(this.settingBackJLabel)){
			this.sf.setVisibles(true,this.strUser);
		}
		if(e.getSource().equals(this.userId)){
			if (this.strUser.equals("superuser")) {
				String strNum = JOptionPane.showInputDialog(this.jcFrames, "请输入手机号码", "");
				if (strNum.length() == 11) {
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
	public void dutiesDo(String str1){
		String str2;
		String strComsuption = null;
		String strnums = null;
		String remains = null;
		JsonObject jsonObject = null;
		JsonObject jsonObject2 = null;
		ImageIcon imagIcon = null;
		if (str1.length() == 11) {
			this.glasspane.start();
			jsonObject = new JsonObject();
			jsonObject.addProperty("time", gainTime.gainDateAndTime());
			jsonObject.addProperty("mobile", str1);
			str2 = Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate());
			jsonObject = new JsonObject();
			jsonObject.addProperty("contents", str2);
			// 第一次HTTP请求，获取用户信息
			jsonObject = testHttPInterface.doPost("http://www.jinshangfoods.com/api/user/show", jsonObject);
			System.out.println(jsonObject.toString());
			if(jsonObject.get("code").getAsString().equals("0")){
				jsonObject = (JsonObject) jsonObject.get("data");
				// 将token存入数据库中
				dm.saveToken(jsonObject.get("mobile").getAsString(), jsonObject.get("remember_token").getAsString(),
						gainTime.gainDateAndTime());
				System.out.println(jsonObject.toString());
				jsonObject = new JsonObject();
				jsonObject.addProperty("time", gainTime.gainDateAndTime());
				jsonObject.addProperty("mobile", str1);
				jsonObject.addProperty("type", mealsType());
				jsonObject2 = new JsonObject();
				jsonObject2.addProperty("contents", Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate()));
				// 第二次HTTP请求，获取用户订餐信息.
				jsonObject = testHttPInterface.doPost("http://www.jinshangfoods.com/api/order/meal", jsonObject2);
				//如果存在订单信息
				if (jsonObject.get("code").getAsString().equals("0")) {
					System.out.println("存在订单信息");
					jsonObject2 = (JsonObject) jsonObject.get("data");
					jsonObject2 = (JsonObject) jsonObject2.get("order_meal");
					//获得订单中的就餐数量
					String strnum = jsonObject2.get("num").getAsString();
					System.out.println("订餐数量为："+strnum);
					dm.setFoodsNums(str1, setNumsTime(), strnum);
					setBookNumLabelBackground(strnum);
					jsonObject2 = (JsonObject) jsonObject.get("data");
					System.out.println("订单的data数据为:"+jsonObject2.toString());
					strComsuption = jsonObject2.get("amount").getAsString();
					System.out.println("订单的消费总额为："+strComsuption);
					String orderId = jsonObject2.get("id").getAsString();
					// 第三次HTTP请求，进行订单支付
					jsonObject = new JsonObject();
					jsonObject.addProperty("time", gainTime.gainDateAndTime());
					jsonObject.addProperty("mobile", str1);
					jsonObject.addProperty("token", dm.fenchToken(str1));
					jsonObject.addProperty("order_id", orderId);
					jsonObject2 = new JsonObject();
					jsonObject2.addProperty("contents", Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate()));
					jsonObject = testHttPInterface.doPost("http://www.jinshangfoods.com/api/order/payment", jsonObject2);
					System.out.println(jsonObject.toString());
					if (jsonObject.get("code").getAsString().equals("0")) {
						jsonObject = (JsonObject) jsonObject.get("data");
						remains =toolsClass.coinToYuan(jsonObject.get("money").getAsString());
						System.out.println("用户余额为："+remains);
						dm.putMoneyUpdate(jsonObject.get("money").getAsString(), jsonObject.get("mobile").getAsString());
						dm.setFoodsNums(jsonObject.get("mobile").getAsString(), setNumsTime(), strnum);
					}
					// 没有订单信息就走现场支付流程
				} else if (jsonObject.get("code").getAsString().equals("1050003")) {
					strnums = dm.returnNumsByMobile(str1, setNumsTime());
					strComsuption = gainComsuption(String.valueOf(Integer.parseInt(strnums) + 1));
					dm.setFoodsNums(str1, setNumsTime(), String.valueOf(Integer.parseInt(strnums) + 1));
					jsonObject = new JsonObject();
					jsonObject.addProperty("time", gainTime.gainDateAndTime());
					jsonObject.addProperty("mobile", str1);
					jsonObject.addProperty("amount", strComsuption);
					jsonObject.addProperty("num", "1");
					jsonObject.addProperty("token", dm.fenchToken(str1));
					jsonObject.addProperty("type", mealsType());
					jsonObject2 = new JsonObject();
					jsonObject2.addProperty("contents", Encrypt.encrypt(jsonObject.toString(), gainTime.gainDate()));
					jsonObject = testHttPInterface.doPost("http://www.jinshangfoods.com/api/site/payment", jsonObject2);
					if (jsonObject.get("code").getAsString().equals("0")) {
						jsonObject = (JsonObject) jsonObject.get("data");
						remains = toolsClass.coinToYuan(jsonObject.get("money").getAsString());
						dm.putMoneyUpdate(jsonObject.get("money").getAsString(), str1);
					}else {
						if(jsonObject.get("code").getAsString().equals("1050003")){
							JOptionPane.showMessageDialog(null, "找不到用户", "失败", JOptionPane.ERROR_MESSAGE);
							this.glasspane.stop();
							return;
						}
						if(jsonObject.get("code").getAsString().equals("1050007")){
							JOptionPane.showMessageDialog(null, "用户金额不足", "失败", JOptionPane.ERROR_MESSAGE);
							this.glasspane.stop();
							return;
						}
						if(jsonObject.get("code").getAsString().equals("1050005")){
							JOptionPane.showMessageDialog(null, "支付令牌错误", "失败", JOptionPane.ERROR_MESSAGE);
							this.glasspane.stop();
							return;
						}
					}
					setNumLabelBackground(String.valueOf(Integer.parseInt(strnums) + 1));
					
				}
				dm.queryUserInfo(str1);
				Image img = Toolkit.getDefaultToolkit().createImage(dm.getBytes(), 0, dm.getBytes().length);
				imagIcon = new ImageIcon(img.getScaledInstance(280, 367, 0));
				glasspane.stop();
				if(this.threads != null){
					if(this.threads.isAlive()){
						this.threads.interrupt();
					}
				}
				
				//统一界面显示
				this.comsuptionLabel.setText(toolsClass.coinToYuan(strComsuption));
				this.userChange.setText(remains);
				this.userImg.setIcon(imagIcon);
				this.userName.setText(dm.getName());
				this.userId.setText(dm.getMobile());
				// 3秒后界面恢复
				this.threads = new Thread(() -> {
					try {
						Thread.sleep(2000);
						jcFrame.this.userImg.setIcon(null);
						jcFrame.this.userName.setText("");
						jcFrame.this.userId.setText("");
						jcFrame.this.comsuptionLabel.setText("");
						jcFrame.this.userChange.setText("");
						setNumback();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				this.threads.start();
			}else {
				if(jsonObject.get("code").getAsString().equals("1050003")){
					JOptionPane.showMessageDialog(null, "找不到用户", "失败", JOptionPane.ERROR_MESSAGE);
					this.glasspane.stop();
				}
				if(jsonObject.get("code").getAsString().equals("1050007")){
					JOptionPane.showMessageDialog(null, "用户金额不足", "失败", JOptionPane.ERROR_MESSAGE);
					this.glasspane.stop();
				}
				if(jsonObject.get("code").getAsString().equals("1050005")){
					JOptionPane.showMessageDialog(null, "支付令牌错误", "失败", JOptionPane.ERROR_MESSAGE);
					this.glasspane.stop();
				}
			}
			
			

		}
		System.gc();
	}
	public void dealDuties(String string) {
		String str1;
		String string2;
		if(string.length() == 52){
			string = string.substring(0,string.length()-4);
			str1 = toolsClass.toStringHex2(string);
			string2 = Encrypt.decrypt2(str1, gainTime.gainDate()).substring(3);
			System.out.println(string2);
			dutiesDo(string2);
		}else if(string.length() == 32){
			string2 = dm.searchWithNum(string);
			dutiesDo(string2);
		}
	}
	public void setNumback(){
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
	}

	public String setNumsTime() {
		String string = gainTime.gainFullDate();
		if (this.mealsKind.getText().equals("早餐")) {
			string = "z" + string;
		}
		if (this.mealsKind.getText().equals("午餐")) {
			string = "w" + string;
		}
		if (this.mealsKind.getText().equals("晚餐")) {
			string = "n" + string;
		}
		return string;
	}

	public void setNumLabelBackground(String str) {
		if (str.equals("1")) {
			this.setNumback();
			this.num1.setOpaque(true);
			this.num1.setBackground(Color.DARK_GRAY);
			this.num1.setForeground(Color.white);
		}
		if (str.equals("2")) {
			this.setNumback();
			this.num2.setOpaque(true);
			this.num2.setBackground(Color.DARK_GRAY);
			this.num2.setForeground(Color.white);
		}
		if (str.equals("3")) {
			this.setNumback();
			this.num3.setOpaque(true);
			this.num3.setBackground(Color.DARK_GRAY);
			this.num3.setForeground(Color.white);
		}
		if (str.equals("4")) {
			this.setNumback();
			this.num4.setOpaque(true);
			this.num4.setBackground(Color.DARK_GRAY);
			this.num4.setForeground(Color.white);
		}
		if (str.equals("5")) {
			this.setNumback();
			this.num5.setOpaque(true);
			this.num5.setBackground(Color.DARK_GRAY);
			this.num5.setForeground(Color.white);
		}
		if (str.equals("6")) {
			this.setNumback();
			this.num6.setOpaque(true);
			this.num6.setBackground(Color.DARK_GRAY);
			this.num6.setForeground(Color.white);
		}
		if (str.equals("7")) {
			this.setNumback();
			this.num7.setOpaque(true);
			this.num7.setBackground(Color.DARK_GRAY);
			this.num7.setForeground(Color.white);
		}
		if (str.equals("8")) {
			this.setNumback();
			this.num8.setOpaque(true);
			this.num8.setBackground(Color.DARK_GRAY);
			this.num8.setForeground(Color.white);
		}
		if (str.equals("9")) {
			this.setNumback();
			this.num9.setOpaque(true);
			this.num9.setBackground(Color.DARK_GRAY);
			this.num9.setForeground(Color.white);
		}
	}

	public void setBookNumLabelBackground(String str) {
		if (str.equals("1")) {
			this.num1.setOpaque(true);
			this.num1.setBackground(Color.red);
			this.num1.setForeground(Color.white);
		}
		if (str.equals("2")) {
			this.num2.setOpaque(true);
			this.num2.setBackground(Color.red);
			this.num2.setForeground(Color.white);
		}
		if (str.equals("3")) {
			this.num3.setOpaque(true);
			this.num3.setBackground(Color.red);
			this.num3.setForeground(Color.white);
		}
		if (str.equals("4")) {
			this.num4.setOpaque(true);
			this.num4.setBackground(Color.red);
			this.num4.setForeground(Color.white);
		}
		if (str.equals("5")) {
			this.num5.setOpaque(true);
			this.num5.setBackground(Color.red);
			this.num5.setForeground(Color.white);
		}
		if (str.equals("6")) {
			this.num6.setOpaque(true);
			this.num6.setBackground(Color.red);
			this.num6.setForeground(Color.white);
		}
		if (str.equals("7")) {
			this.num7.setOpaque(true);
			this.num7.setBackground(Color.red);
			this.num7.setForeground(Color.white);
		}
		if (str.equals("8")) {
			this.num8.setOpaque(true);
			this.num8.setBackground(Color.red);
			this.num8.setForeground(Color.white);
		}
		if (str.equals("9")) {
			this.num9.setOpaque(true);
			this.num9.setBackground(Color.red);
			this.num9.setForeground(Color.white);
		}
	}
	//从数据库获取当餐价格并计算
	public String gainComsuption(String strnum) {
		String returnComsuption = null;
		if (this.mealsKind.getText().equals("早餐")) {
			if (Integer.parseInt(strnum) > 1) {
				returnComsuption = String.valueOf(Integer.parseInt(dm.getBreakfastPrice("breakfast")) * 2);
			} else {
				returnComsuption = dm.getBreakfastPrice("breakfast");
			}
		} else if (this.mealsKind.getText().equals("午餐")) {
			if (Integer.parseInt(strnum) > 1) {
				returnComsuption = String.valueOf(Integer.parseInt(dm.getBreakfastPrice("lunch")) * 2);
			} else {
				returnComsuption = dm.getBreakfastPrice("lunch");
			}
		} else if (this.mealsKind.getText().equals("晚餐")) {
			if (Integer.parseInt(strnum) > 1) {
				returnComsuption = String.valueOf(Integer.parseInt(dm.getBreakfastPrice("dinner")) * 2);
			} else {
				returnComsuption = dm.getBreakfastPrice("dinner");
			}
		}
		return returnComsuption;
	}

	public int mealsType() {
		if (jcFrame.this.mealsKind.getText().equals("早餐")) {
			return 1;
		}
		if (jcFrame.this.mealsKind.getText().equals("午餐")) {
			return 2;
		}
		if (jcFrame.this.mealsKind.getText().equals("晚餐")) {
			return 3;
		}
		return 0;
	}

}
