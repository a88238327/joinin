package entity;

public class Jsapi_Ticket {

	private String jsapi_Ticket;
	private long expireTime;

	public String getjsapi_Ticket() {
		return jsapi_Ticket;
	}

	public void setjsapi_Ticket(String jsapi_Ticket) {
		this.jsapi_Ticket = jsapi_Ticket;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public Jsapi_Ticket(String jsapi_Ticket, String expireIn) {
		super();
		this.jsapi_Ticket = jsapi_Ticket;
		expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
	}
	
	/**
	 * 判断token是否过期
	 */
	public boolean isExpired() {
		return System.currentTimeMillis()>expireTime;
	}

}
