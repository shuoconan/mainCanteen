package com.js.jhjs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.TooManyListenersException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.generic.NEW;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import jdk.internal.util.xml.impl.ReaderUTF8;

public class MainClass {
	private jcFrame jcframe = null;
	private wmFrame wmframe = null;
	private settingFrame settingframe = null;
	private LoginFrame lfs = null;
	private CardCheck cc = null;
	private DatabaseManipulate dm = new DatabaseManipulate();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainClass();
	}

	public MainClass() {
		// TODO Auto-generated constructor stub
		this.jcframe = new jcFrame("");
		this.wmframe = new wmFrame("");
		this.cc = new CardCheck();
		this.settingframe = new settingFrame("");
		this.jcframe.setOtherFrame(this.settingframe);
		this.wmframe.setOtherFrame(this.settingframe);
		System.out.println("窗口对象建立完毕");
		SerialTools sTools = this.new SerialTools();
		Set<CommPortIdentifier> portList = sTools.getPortList();
		if(portList.size() == 0){
			this.lfs = new LoginFrame(this.jcframe, this.wmframe);
			this.settingframe.setOtherFrame(this.jcframe, this.wmframe, this.lfs, this.cc);
			for (CommPortIdentifier cpif : portList) {
				// 处理串口发来数据的线程
				new Thread(() -> {
					System.out.println("串口打开成功！");
					SerialTools st = this.new SerialTools();
					st.openSerialPort(cpif, 20);
				}).start();

			}
		}else{
			JOptionPane.showMessageDialog(null,"串口打开失败，请重新启动程序!" ,"错误" , JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
	}

	public void setSettingframe(boolean b) {
		this.settingframe.setVisibles(b);
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
			while (tempPortList.hasMoreElements()) {

				portIp = (CommPortIdentifier) tempPortList.nextElement();
				portList.add(portIp);
			}
			return portList;
		}

		public boolean openSerialPort(CommPortIdentifier portIp, int delay) {
			try {
				serialPort = (SerialPort) portIp.open(appName, delay);
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
			isOpen = true;
			return true;
		}

		public boolean getOPen() {
			return isOpen;
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

		public String howtoHexStrings(String s) {
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < s.length(); i++) {
				int j = (int) s.charAt(i);
				sb.append(Integer.toHexString(j));
			}
			String string = new String(sb);
			return string;
		}

		@Override
		public void serialEvent(SerialPortEvent event) {
			String string = "";
			int b;
			if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
				try {
					while (is.available() > 0) {
						while ((b = is.read()) != -1) {
							if (b <= 0xf) {
								string += "0";
							}
							string += Integer.toHexString(b & 0xff);
						}
						System.out.println(string);
						if (MainClass.this.lfs.getVisibles()) {
							if (string.length() == 32) {
								if(MainClass.this.dm.searchWithSuperNum(string)){
									MainClass.this.wmframe.setVisible(true, "superuser");
									MainClass.this.lfs.setVisible(false);
								}
							}
						} else {
							if ((MainClass.this.jcframe.getVisible()) && (!MainClass.this.wmframe.getVisible())) {
								System.out.println("就餐界面");
								MainClass.this.jcframe.dealDuties(string);
							}
							if ((!MainClass.this.jcframe.getVisible()) && (MainClass.this.wmframe.getVisible())) {
								System.out.println("外卖界面");
								MainClass.this.wmframe.dealDuties(string);
							}
						}
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
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
