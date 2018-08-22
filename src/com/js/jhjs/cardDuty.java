package com.js.jhjs;

import java.awt.Toolkit;
import java.nio.charset.Charset;

import javax.swing.JOptionPane;

import com.google.gson.JsonObject;
import com.sun.jna.Native;

public class cardDuty {
	private Declare.mwrf epen = null;
	private short st=1;
	private short st2 = 1;
	private	int icdev ;
	private	byte Snr[]=new byte[5];
	private	byte[] ver=new byte[20];
	private	short sector=1;
	
	private byte[] Snrhex=new byte[9];
	public cardDuty() {
		// TODO Auto-generated constructor stub
		this.epen = (Declare.mwrf) Native.loadLibrary("img/mwrf32", Declare.mwrf.class); 
		if (this.epen != null){
			this.icdev = this.epen.rf_init((short)0, 9600);
			this.st= this.epen.rf_get_status(this.icdev, this.ver);
			if(this.st==0)
			{
				String str=new String(this.ver,0,18);
				for(short i=0;i<16;i++)
				{
					byte[] key=new byte[]{(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff};
					st=epen.rf_load_key(this.icdev, (short)0, i, key);
					if(st!=0)
					{
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "第"+i+"扇区密码失败，请检查！","密码加载失败" ,JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
	
	public String openICcardPort(){
		this.st2=epen.rf_card(icdev,(short)1,Snr);
		if(this.st2==0){
			epen.hex_a(Snr,Snrhex,(short)4);
			String str1=new String(Snrhex,0,8);
			System.out.println(str1);
			st=epen.rf_authentication(icdev, (short)0, sector);
			byte[] rdata=new byte[17];
			this.st2=epen.rf_read_hex(icdev, (short)(sector*4), rdata);
			if(this.st2==0)
			{
				epen.rf_beep(icdev, (short)30);
				String stri=new String(rdata,Charset.forName("utf-8"));
				System.out.println("读数据成功，数据： "+stri);
				return stri;
			}else{
			return "ERROR";	
			}
		}else {
			return "ERROR";
		}
	}
	
	
}
