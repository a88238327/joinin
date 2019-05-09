package entity;

public class wx_config {
	private String appid;// 必填，公众号的唯一标识
	private String timestamp;// 必填，生成签名的时间戳
	private String noncestr; // 必填，生成签名的随机串
	private String signature;//签名
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appId) {
		this.appid = appId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNonceStr(String nonceStr) {
		this.noncestr = noncestr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public wx_config(String appId,String timestamp,String nonceStr,String signature) {
		this.appid=appId;
		this.timestamp=timestamp;
		this.noncestr=nonceStr;
		this.signature=signature;
	}
}
