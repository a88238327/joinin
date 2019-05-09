package entity;


import net.sf.json.JSONObject;

public class GetTicket {
	private static final String GET_TOKEN_URL="http://cloud2.hnjtbf.com/wechat/gettoken";
	private static Jsapi_Ticket jt;
	private static void getTicket() {
		String tokenStr = Util.get(GET_TOKEN_URL);
		
		String ticketStr=Util.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+tokenStr+"&type=jsapi");
		
		JSONObject jsonObject = JSONObject.fromObject(ticketStr);
		
		String ticket = jsonObject.getString("ticket");
		String expireIn = jsonObject.getString("expires_in");
		//创建token对象,并存起来。
		
		jt = new Jsapi_Ticket(ticket, expireIn);
	}
	public static String getjsapi_Ticket() {
		if(jt==null||jt.isExpired()) {
			getTicket();
		}
		return jt.getjsapi_Ticket();
	}
}
