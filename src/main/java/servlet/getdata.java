package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.Select;

import entity.DataUrl;
import entity.DataUser;
import entity.arrayInfo;
import entity.info;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class getdata
 */
@WebServlet("/getdata")
public class getdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getdata() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String type=request.getParameter("type");
		
		HttpSession session = request.getSession();
		//获取手机号并获取公司数据
		if(type.equals("getphone")) {
				HashMap<String , String > map=new HashMap<String, String>();
				map.put("gongsijihuoshu", Selectdata.getgongsijihuoshu());
				map.put("gongsiguanzhushu", Selectdata.getgongsiguanzhushu());
				JSONObject jsonObject=JSONObject.fromObject(map);
				response.getWriter().write(jsonObject.toString());
		}
		//获取数据
		if(type.equals("getdata"))
		{
			System.out.println(request.getParameter("startime"));
			String startime=request.getParameter("startime").replace("-", "/");
			String endtime=request.getParameter("endtime").replace("-", "/");
			//获取手机号
			//查询今日每人的
			Connection conn=null;
			Statement stmt=null;
			PreparedStatement pstmt	= null ;
			String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
			String username=new DataUser().getUsername();//数据库用户名
			String DBpassword=new DataUser().getPassword();//数据库密码
			String sql="select * from manager";//查询语句
			System.out.println(sql);
			String DBurl=new DataUrl().getUrl();//连接数据库的地址
			List list = new ArrayList<info>();
			JSONArray jsonArray=new JSONArray();
			//List<info> map=new List<info>() ;
			String url="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
			String GET_TOKEN_URL=entity.Util.get("http://cloud2.hnjtbf.com/wechat/gettoken");
			String resultString=entity.Util.get(url.replace("ACCESS_TOKEN", GET_TOKEN_URL));
			JSONObject jsonObject=JSONObject.fromObject(resultString);
			String a=JSONObject.fromObject(jsonObject.getString("data")).getString("openid");
			JSONArray jsonarray=JSONArray.fromObject(a);
			int i=0;
			try{
				Class.forName(driver);//加载驱动器类
				conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
				//建立处理的SQL语句
				pstmt = conn.prepareStatement(sql) ;
				System.out.println(pstmt.toString());
				ResultSet rs=pstmt.executeQuery();
				
				while(rs.next())
				{
					//今日情况
					//个人情况
					i=i+1;									
					//公司情况
					String aString=rs.getString("username");
					long start=System.currentTimeMillis(); //获取开始时间		
					String bString=Selectdata.getjihuoshu(rs.getString("phone_number"),startime+" 00:00:00",endtime+" 60:00:00");
					long end=System.currentTimeMillis(); //获取结束时间
					System.out.println("程序运行时间： "+(end-start)+"ms");
					if(!bString.equals("0"))
					{
						long start1=System.currentTimeMillis(); //获取开始时间
						String cString=Selectdata.guanzhushu(rs.getString("phone_number"),startime+" 00:00:00",endtime+" 60:00:00",jsonarray)+"";				
						info f=new info(aString, bString,cString);				
						list.add(f);
						long end1=System.currentTimeMillis(); //获取结束时间
						System.out.println("程序运行时间： "+(end1-start1)+"ms");
					}
					
					//arryinfo.getarrayinfo().add(new info(aString,bString,cString));

	
				}
				String aString="公司";
				String bString=Selectdata.getjihuoshu(startime+" 00:00:00",endtime+" 60:00:00");
				String cString=Selectdata.guanzhushu(startime+" 00:00:00",endtime+" 60:00:00",jsonarray);
				info f=new info(aString, bString,cString);			
				list.add(f);
				String a1="三亚安保";
				String b1=Selectdata.getjihuoshu("null",startime+" 00:00:00",endtime+" 60:00:00");
				String c1=Selectdata.guanzhushu("null",startime+" 00:00:00",endtime+" 60:00:00",jsonarray)+"";				
				info d=new info(a1, b1,c1);			
				list.add(d);
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				rs.close();
			}catch (Exception e) {
			}
			jsonArray=jsonArray.fromObject(list);
			response.getWriter().write(jsonArray.toString());
		}
	}

}
