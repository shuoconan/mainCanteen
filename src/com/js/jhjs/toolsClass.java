package com.js.jhjs;

import java.io.ByteArrayOutputStream;

public class toolsClass {
	private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5','6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	private static String hexString = "0123456789ABCDEF";
	public static String coinToYuan(String string){
		int i = Integer.parseInt(string);
		String str = String.valueOf(i/100)+"."+String.valueOf(i%100)+"元";
		return str;
	}
	public static int yuanToDouble(String string){
		return (int)(Double.parseDouble(string.substring(0,string.length()-1))*100);
	}
	public static String howtoHexStrings(String s){
		StringBuffer sb = new StringBuffer("");
		for(int i = 0;i<=s.length();i++){
			sb.append(Integer.toHexString((int)s.charAt(i)));
			System.out.println(sb);
		}
		System.out.println(sb);
		String string = new String(sb);
		return string;
	}
	public static String bytesToHexFun1(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for(byte b : bytes) { // 使用除与取余进行转换
            if(b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }
	public static String decodeUTF8(String bytes) {
	       ByteArrayOutputStream baos = new ByteArrayOutputStream(
	         bytes.length() / 2);
	       // 将每2位16进制整数组装成一个字节
	       for (int i = 0; i < bytes.length(); i += 2)
	        baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
	          .indexOf(bytes.charAt(i + 1))));
	       return new String(baos.toByteArray());
	    }
	 // 转化十六进制编码为字符串
    public static String toStringHex2(String s) {
       byte[] baKeyword = new byte[s.length() / 2];
       for (int i = 0; i < baKeyword.length; i++) {
        try {
         baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
           i * 2, i * 2 + 2), 16));
        } catch (Exception e) {
         e.printStackTrace();
        }
       }
       try {
        s = new String(baKeyword, "utf-8");// UTF-16le:Not
       } catch (Exception e1) {
        e1.printStackTrace();
       }
       return s;
    }
}
