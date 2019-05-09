package test;

import com.baidu.aip.util.Util;

public class zidonghuifu {
	
	public static void main(String[] args) {
		String GET_TOKEN_URL="http://cloud.hnjtbf.com/wechat/gettoken";
		String tokenStr = entity.Util.get(GET_TOKEN_URL);
		String url="https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=ACCESS_TOKEN";
		String resultString=entity.Util.get(url.replace("ACCESS_TOKEN", tokenStr));
		System.out.println(resultString);
	}
}
