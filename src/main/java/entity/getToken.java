package entity;

import Tuil.Util;

public class getToken {

	public static String get_ACCESSTOKEN()
	{
		String GET_TOKEN_URL="http://cloud2.hnjtbf.com/wechat/gettoken";
		String ACCESS_TOKEN= Util.get(GET_TOKEN_URL);
		return ACCESS_TOKEN;
	}
}
