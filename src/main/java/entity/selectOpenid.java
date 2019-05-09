package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class selectOpenid {

	public static boolean selectopenid(String openid) {
		Connection conn=null;
		ResultSet rs= null ;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String password=new DataUser().getPassword();//数据库密码
		String sql="select * from customer where openid=\'"+openid+"\'" ;//查询语句
		System.out.println(sql);
		String url=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(url,username,password);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			rs=pstmt.executeQuery();//插入数据库
			if(rs.next())
			{
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				rs.close();
				return true;
			}		
			else {
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				rs.close();
				return false;
			}
			
		
		}catch (Exception e) {
		// TODO: handle exception
	}
		return false;
	}
}
