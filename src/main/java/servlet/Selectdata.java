package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.baidu.aip.util.Util;

import entity.DataUrl;
import entity.DataUser;
import entity.arrayInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Selectdata {


	public static String getgongsijihuoshu() {
		Connection conn=null;
		Statement stmt=null;
		String str="";
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select count(*) as count from customer where USER_FORM_INFO_FLAG_MOBILE is not null";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			arrayInfo today=new arrayInfo();
			if(rs.next())
			{
				str= rs.getString("count");
				
			}
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			rs.close();
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}

	public static String getgongsiguanzhushu() {
		String url="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
		String GET_TOKEN_URL=entity.Util.get("http://cloud.hnjtbf.com/wechat/gettoken");
		String resultString=entity.Util.get(url.replace("ACCESS_TOKEN", GET_TOKEN_URL));
		System.out.println(resultString);
		JSONObject jsonObject=JSONObject.fromObject(resultString);
		System.out.println(jsonObject.toString());
		return jsonObject.getString("total").toString();
	}

	public static String getjihuoshu(String phone, String startime, String endtime) {
		Connection conn=null;
		Statement stmt=null;
		String str="";
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select count(openid) as count from customer where USER_FORM_INFO_FLAG_MOBILE is not null and OuterStr='"+phone+"' and createtime BETWEEN '"+startime+"' and '"+endtime+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				str= rs.getString("count");
				
			}
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			rs.close();
			
			
		}catch (Exception e) {
		}
		
		return str;
	}

	public static int guanzhushu(String phone, String startime, String endtime,JSONArray jsonarray) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select openid from customer where USER_FORM_INFO_FLAG_MOBILE is not null and OuterStr='"+phone+"' and createtime BETWEEN '"+startime+"' and '"+endtime+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址	
		int count=0;
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();		
			while(rs.next())
			{
				for(int i=0;i<jsonarray.size();i++)
				{
					
					if(jsonarray.getString(i).equals(rs.getString("openid")))
					{
						count=count+1;
					}
				}
				
			}
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			rs.close();
			
			
		}catch (Exception e) {
		}
		
		return count;
	}

	public static String getjihuoshu(String startime, String endtime) {
		Connection conn=null;
		Statement stmt=null;
		String str="";
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select count(openid) as count from customer where USER_FORM_INFO_FLAG_MOBILE is not null and createtime BETWEEN '"+startime+"' and '"+endtime+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				str= rs.getString("count");
				
			}
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			rs.close();
			
			
		}catch (Exception e) {
		}
		
		return str;
	}

	public static String guanzhushu(String startime, String endtime, JSONArray jsonarray) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select openid from customer where USER_FORM_INFO_FLAG_MOBILE is not null and createtime BETWEEN '"+startime+"' and '"+endtime+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		
		
		int count=0;
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
		
			while(rs.next())
			{
				for(int i=0;i<jsonarray.size();i++)
				{
					
					if(jsonarray.getString(i).equals(rs.getString("openid")))
					{
						count=count+1;
					}
				}
				
			}
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			rs.close();
			
			
		}catch (Exception e) {
		}
		
		return count+"";
	}

}
