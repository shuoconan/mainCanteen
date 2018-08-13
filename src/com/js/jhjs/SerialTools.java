package com.js.jhjs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class SerialTools implements Runnable, SerialPortEventListener {
	
	private  boolean isOpen=false;
	static Set<CommPortIdentifier> portList=new HashSet<CommPortIdentifier>();
	final static String appName="MyApp";
	private  InputStream is;
	private  OutputStream os;
	private  SerialPort serialPort;
	byte[] readBuffer=new byte[100];
	
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
					is.read(readBuffer);//�յ��������ٴˣ������������
				}
				System.out.println(new String(readBuffer));
			} catch (IOException e) {
			}
		}
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
