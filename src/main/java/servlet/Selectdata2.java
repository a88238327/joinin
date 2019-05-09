package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.baidu.aip.util.Util;

import entity.DataUrl;
import entity.DataUser;
import entity.arrayInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Selectdata2 {


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

	public static ArrayList<HashMap<String, String>> getjihuoshu(String startime,String endtime) {
		ArrayList<HashMap<String , String >> list=new ArrayList<HashMap<String,String>>();
		Connection conn=null;
		Statement stmt=null;
		String str="";
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select count(openid) as 激活数,manager.username from customer,manager where customer.OuterStr=manager.phone_number and USER_FORM_INFO_FLAG_MOBILE is not null and customer.createtime between '"+startime+"' and '"+endtime+"' group by manager.username";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				HashMap<String, String > map=new HashMap<String, String>();
				map.put("name", rs.getString("username"));
				map.put("jihuoshu", rs.getString("激活数"));
				list.add(map);
			}
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			rs.close();
			
			
		}catch (Exception e) {
		}
		
		return list;
	}

	public static ArrayList<HashMap<String, String>> guanzhushu(ArrayList<HashMap<String, String>> list1,
			String startime, String endtime) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select count(openid) as 关注数,manager.username from customer,manager where customer.OuterStr=manager.phone_number and USER_FORM_INFO_FLAG_MOBILE is not null and openid in(select openid from customer_event where createtime between '"+startime+"' and '"+endtime+"' and event in('subscribe','unsubscribe') group by openid having count(event)% 2 = 1) group by manager.username";//查询语句
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
				for(int i=0;i<list1.size();i++)
				{
					if(rs.getString("username").equals(list1.get(i).get("name")))
					{
						list1.get(i).put("guanzhushu", rs.getString("关注数"));
					}
				}
				
			}
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			rs.close();
			
			
		}catch (Exception e) {
		}
		
		return list1;
	}

	public static HashMap<String , String> getgongsijihuoshu(String startime, String endtime) {
		HashMap<String , String> map=new HashMap<String, String>();
		Connection conn=null;
		Statement stmt=null;
		String str="";
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select count(openid) as 激活数  from customer where USER_FORM_INFO_FLAG_MOBILE is not null and createtime between '"+startime+"' and '"+endtime+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				map.put("name", "公司");
				map.put("jihuoshu", rs.getString("激活数"));
			}
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			rs.close();
			
			
		}catch (Exception e) {
		}
		
		return map;
	}

	public static HashMap<String, String> gongsiguanzhushu(HashMap<String, String> map1, String startime,
			String endtime) {
			Connection conn=null;
			Statement stmt=null;
			PreparedStatement pstmt	= null ;
			String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
			String username=new DataUser().getUsername();//数据库用户名
			String DBpassword=new DataUser().getPassword();//数据库密码
			String sql="select count(openid) as 关注数  from customer where USER_FORM_INFO_FLAG_MOBILE is not null and openid in(select openid from customer_event where createtime between '"+startime+"' and '"+endtime+"' and event in('subscribe','unsubscribe') group by openid having count(event)% 2 = 1)";//查询语句
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
					map1.put("guanzhushu", rs.getString("关注数"));
				}
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				rs.close();
				
				
			}catch (Exception e) {
			}
			
			return map1;
	}

	public static HashMap<String, String> getgongsijihuoshu1(String startime, String endtime) {
		HashMap<String , String> map=new HashMap<String, String>();
		Connection conn=null;
		Statement stmt=null;
		String str="";
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select count(openid) as 激活数  from customer where USER_FORM_INFO_FLAG_MOBILE is not null and OuterStr is null and createtime between '"+startime+"' and '"+endtime+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				map.put("name", "三亚安保");
				map.put("jihuoshu", rs.getString("激活数"));
			}
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			rs.close();
			
			
		}catch (Exception e) {
		}
		
		return map;
	}

	public static HashMap<String, String> gongsiguanzhushu1(HashMap<String, String> map2, String startime,
			String endtime) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select count(openid) as 关注数  from customer where USER_FORM_INFO_FLAG_MOBILE is not null and OuterStr is null and openid in(select openid from customer_event where createtime between '"+startime+"' and '"+endtime+"' and event in('subscribe','unsubscribe') group by openid having count(event)% 2 = 1)";//查询语句
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
				map2.put("guanzhushu", rs.getString("关注数"));
			}
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			rs.close();
			
			
		}catch (Exception e) {
		}
		
		return map2;
	}

}
