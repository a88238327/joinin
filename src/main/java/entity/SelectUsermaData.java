package entity;

import entity.cardidcode;
import net.sf.json.JSONObject;

public class SelectUsermaData {

	//查询激活会员卡用户的激活信息
	public static String getUserInfo(String cardid,String code) {
		
		//转为json
		cardidcode ao=new cardidcode(cardid, code);
		JSONObject jsonObject = JSONObject.fromObject(ao);
		//System.out.println(jsonObject.toString());
		//准备url
		String url = "https://api.weixin.qq.com/card/membercard/userinfo/get?access_token=TOKEN";
		url = url.replace("TOKEN", getToken.get_ACCESSTOKEN());
		//发送请求
		String result = Util.post(url, jsonObject.toString());
		System.out.println(jsonObject.toString());
		System.out.println(result);
		return result;
		
		
	}
	//System.out.println(entity.getUserInfo("pR0B71Ums67ft8xRVFzuIMaYFBCI","415034363298"));
}
