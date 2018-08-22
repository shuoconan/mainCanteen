package com.js.jhjs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class testHttPInterface {
	public static String HttpSendPost(String url,Map<String,String> map){
		if(map==null){
			return null;
		}
		String resultStr = null;
		CloseableHttpClient chc = HttpClients.createDefault();
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		for(Map.Entry<String, String> entry:map.entrySet()){
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(list,Consts.UTF_8);
		HttpPost hp = new HttpPost("http://text.jinshangfoods.com/api/tags/group");
		hp.setEntity(uefe);
		try {
			CloseableHttpResponse chrp = chc.execute(hp);
			HttpEntity entity = chrp.getEntity();
			if (entity != null) {  
                System.out.println("--------------------------------------");  
           //     System.out.println("Response content: " + unicodeToCn(EntityUtils.toString(entity, "utf16")));
                System.out.println("Response content: " + EntityUtils.toString(entity, "utf8"));
                System.out.println("--------------------------------------");  
            }  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultStr;
	}
}
