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

import entity.DataUrl;
import entity.DataUser;
import entity.info;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class getdata2
 */
@WebServlet("/getdata2")
public class getdata2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getdata2() {
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
			ArrayList<HashMap<String , String >> list1=new ArrayList<HashMap<String,String>>();
			list1=Selectdata2.getjihuoshu(startime+" 00:00:00",endtime+" 24:00:00");
			ArrayList<HashMap<String , String >> list2=new ArrayList<HashMap<String,String>>();
			list2=Selectdata2.guanzhushu(list1,startime+" 00:00:00",endtime+" 24:00:00");
			HashMap<String , String > map1=new HashMap<String, String>();
			map1=Selectdata2.getgongsijihuoshu(startime+" 00:00:00",endtime+" 24:00:00");
			list2.add(Selectdata2.gongsiguanzhushu(map1,startime+" 00:00:00",endtime+" 24:00:00"));
			HashMap<String , String > map2=new HashMap<String, String>();
			map2=Selectdata2.getgongsijihuoshu1(startime+" 00:00:00",endtime+" 24:00:00");
			list2.add(Selectdata2.gongsiguanzhushu1(map2,startime+" 00:00:00",endtime+" 24:00:00"));	
			JSONArray jsonArray=JSONArray.fromObject(list2);
			response.getWriter().write(jsonArray.toString());
		}
	}

}
