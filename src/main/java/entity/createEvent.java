package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class createEvent {

	public static boolean createEvent(String openid,String event) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs= null ;
		PreparedStatement pstmt	= null ;
		String createtime="\'"+sysDate.getsysDate()+"\'";
		openid=','+"\'"+openid+"\'";
		event=','+"\'"+event+"\'";
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String password=new DataUser().getPassword();//数据库密码
		String sql="insert into  customer_event (createtime,openid,event) values("+createtime+openid+event+")" ;//查询语句
		System.out.println(sql);
		String url=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(url,username,password);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			if(pstmt.executeUpdate()>0)//插入数据库
			{
				System.out.println("创建事件成功");
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return true;
			}			
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接	
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return false;
	}
}
