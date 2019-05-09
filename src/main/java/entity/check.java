package entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class check {
	public static String getSignature(String noncestr,String jsapi_ticket,String timestamp,String url)
	{
		//String[] strs=new String[] {noncestr,jsapi_ticket,timestamp,url};
		//Arrays.sort(strs);
		//2）将三个参数字符串拼接成一个字符串进行sha1加密 
		String str = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
		//String str=strs[0]+strs[1]+strs[2]+strs[3];
		String mysig=sha1(str);
		
		return mysig;
	}
	public static String sha1(String src) {
		try {
			//获取一个加密对象
			MessageDigest md=MessageDigest.getInstance("sha1");
			//加密
			byte[] digest=md.digest(src.getBytes());
			char[] chars= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
			StringBuilder sb=new StringBuilder();
			//处理加密结果
			for(byte b:digest) {
				sb.append(chars[(b>>4)&15]);
				sb.append(chars[b&15]);
			}
			return sb.toString();
		}catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
		}
		return null;
		
	}
}
