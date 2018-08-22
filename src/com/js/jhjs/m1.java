package com.js.jhjs;

import java.nio.charset.Charset;

import com.sun.jna.Native;

public class m1
{
	/**
	 * @param args
	 */
	short st=1;
	int icdev ;
	byte Snr[]=new byte[5];
	
	public void DevConnect(Declare.mwrf epen)
	{
		byte[] ver=new byte[20];
		icdev =epen.rf_init((short)0, 9600);
		st=epen.rf_get_status(icdev, ver);
		if(st==0)
		{
			String str=new String(ver,0,18);
			System.out.println("�豸��ʼ���ɹ���" + str); 
		}
		else
		{
			System.out.println("�豸����ʧ��!");
		}
		for(short i=0;i<16;i++)
		{
			byte[] key=new byte[]{(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff};
			st=epen.rf_load_key(icdev, (short)0, i, key);
			System.out.println("���� "+i+" ��������ɹ�!");
			if(st!=0)
			{
				System.out.println("���� "+i+" ��������ʧ��!");
			}
		}
		epen.rf_beep(icdev, (short)30);
	}
	public void mifareOne(Declare.mwrf epen)
	{
		short sector=4;
		st=epen.rf_card(icdev,(short)1,Snr);
		if(st==0)
		{
			byte[] Snrhex=new byte[9];
			epen.hex_a(Snr,Snrhex,(short)4);
			String str=new String(Snrhex,0,8);
			System.out.println(str);
		}
		else
		{
			System.out.println("Ѱ��ʧ�ܣ�");
		}
		st=epen.rf_authentication(icdev, (short)0, sector);
		if(st!=0)
		{
			System.out.println(sector+"����������֤ ����!");
		}
		String str="���������ĺ��Ƽ�";
//		byte[] wdata=str.getBytes();
//		st=epen.rf_write(icdev, (short)(sector*4), wdata);
//		if(st!=0)
//		{
//			System.out.println(sector+"����д����ʧ��");
//		}
		byte[] rdata=new byte[17];
		st=epen.rf_read_hex(icdev, (short)(sector*4), rdata);
		if(st==0)
		{
			String stri=new String(rdata,Charset.forName("utf-8"));
			System.out.println("�����ݳɹ������ݣ� "+stri);
		}
		else
		{
			System.out.println("������ʧ�ܣ�");
		}
//		String key="ffffffffffff";
//		byte[] wkey=key.getBytes();
//		byte[] wkeyhex=new byte[7];
//		epen.a_hex(wkey, wkeyhex, (short)wkey.length);
//		st=epen.rf_changeb3(icdev, (short)sector, wkeyhex, (short)0, (short)0, (short)0, (short)1, (short)0, wkeyhex);
//		if(st!=0)
//		{
//			System.out.println("������ʧ�ܣ�");
//		}	
//		else
//		{
//			System.out.println("������ɹ���");
//		}
	}
	public void disconnectDev(Declare.mwrf epen)
	{
		epen.rf_exit(icdev);
		//System.out.println("�Ͽ��豸");
	}
	public void MifarePROCpu(Declare.mwrf epen)
	{
		byte[] resetData=new byte[50];
		st=epen.rf_card(icdev,(short)1,Snr);
		if(st==0)
		{
			byte[] Snrhex=new byte[9];
			epen.hex_a(Snr,Snrhex,(short)4);
			String str=new String(Snrhex,0,8);
			System.out.println(str);
		}
		else
		{
			System.out.println("Ѱ��ʧ�ܣ�");
		}
		st=epen.rf_pro_rst(icdev, resetData);
		if(st!=0)
		{
			System.out.println("��λʧ�ܣ�"+st);
		}
		else
		{
			byte[] resetDataHex=new byte[100];
			epen.hex_a(resetData, resetDataHex, (short)resetData.length);
			String str=new String(resetDataHex,0,resetData[0]*2);
			System.out.println("��λ�ɹ���"+str);
		}
		byte[] cmd=new byte[]{0x00,0x00,0x00,0x05,0x00,(byte)0x84,0x00,0x00,0x08};
		byte[] returnData=new byte[50];
		byte[] returnDataHex=new byte[100];
		st=epen.rf_pro_trn(icdev, cmd, returnData);
		if(st!=0)
		{
			System.out.println("��������ʧ�ܣ�");
		}
		else
		{
			epen.hex_a(returnData, returnDataHex, (short)(returnData[3]+4));
			String strData=new String(returnDataHex,0,(short)(returnData[3]+4)*2);
			System.out.println(strData);
		}
	}
//	public static void main(String[] args)
//	{
//		// TODO Auto-generated method stub
//		Declare.mwrf epen = (Declare.mwrf) Native.loadLibrary("mwrf32", Declare.mwrf.class);   
//		if (epen != null)   
//			System.out.println("DLL���سɹ���"); 
//		else
//			System.out.println("DLL����ʧ�ܣ�");	
//		m1 con=new m1();
//		con.DevConnect(epen);
////		con.mifareOne(epen);
//		con.mifareOne(epen);
//		con.disconnectDev(epen);
//	}   
}
