package entity;

public class DB_Info {
	
		
	private String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
	private String username="sa";//数据库用户名
	private String password="88238327";//数据库密码
	private String url="jdbc:sqlserver://localhost:1433;DatabaseName=chejiacloud";
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
