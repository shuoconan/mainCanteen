package com.js.jhjs;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class Encrypt {
	public static String encrypt2(String content, String password) {  
        try {  
                SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");  
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
                byte[] byteContent = content.getBytes("utf-8");  
                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化 
                byte[] result = cipher.doFinal(byteContent);  
                return parseByte2HexStr(result); // 加密  
        } catch (NoSuchAlgorithmException e) {  
                e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
                e.printStackTrace();  
        } catch (InvalidKeyException e) {  
                e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
                e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
                e.printStackTrace();  
        } catch (BadPaddingException e) {  
                e.printStackTrace();  
        }  
        return null;  
	}  
	public static String encrypt(String content, String password) {  
        try {  
                SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");  
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
                byte[] byteContent = content.getBytes("utf-8");  
                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化 
                byte[] result = cipher.doFinal(byteContent);  
                return parseByte2Base64(result); // 加密  
        } catch (NoSuchAlgorithmException e) {  
                e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
                e.printStackTrace();  
        } catch (InvalidKeyException e) {  
                e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
                e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
                e.printStackTrace();  
        } catch (BadPaddingException e) {  
                e.printStackTrace();  
        }  
        return null;  
	}  
	public static String parseByte2HexStr(byte buf[]) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < buf.length; i++) {  
                String hex = Integer.toHexString(buf[i] & 0xFF);  
                if (hex.length() == 1) {  
                        hex = '0' + hex;  
                }  
                sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
	}
	 public static byte[] parseHexStr2Byte(String hexStr) {  
         if (hexStr.length() < 1)  
                 return null;  
         byte[] result = new byte[hexStr.length()/2];  
         for (int i = 0;i< hexStr.length()/2; i++) {  
                 int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                 int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                 result[i] = (byte) (high * 16 + low);  
         }  
         return result;  
	 }
	 public static byte[] parseBase642Byte(String base64Str){
		 final Base64.Decoder base64Decoder = Base64.getDecoder();
		 if(base64Str == null){
			 System.out.println("12345");
			 return null;
		 }
		 System.out.println("123456");
		 System.out.println(base64Str);
		 byte[] result = base64Decoder.decode(base64Str);
		 System.out.println("1234567");
		 return result;
	 }
	 public static String parseByte2Base64(byte[] bytes){
		 final Base64.Encoder base64Encoder = Base64.getEncoder();
		 if(base64Encoder == null){
			 return null;
		 }
		 String string = base64Encoder.encodeToString(bytes);
		 return string;
	 }
	 public static String decrypt2(String content, String password){ 
		 byte[] toDecrypt = parseBase642Byte(content);
         try {
    		 SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES"); 
			 Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			 cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			 System.out.println("123");
			 byte[]  result = cipher.doFinal(toDecrypt);//result = cipher.update(toDecrypt);
			 String string = new String(result);
			 return string;
			 
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "二维码已失效！", "失败", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return null;
         
	 }
}

