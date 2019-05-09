package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.GetTicket;
import entity.check;
import entity.wx_config;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String type=request.getParameter("type");
		if(type.equals("getSignature"))
		{
			String url=request.getParameter("url");
			String appId="wxafe6999b4d77754a";// 必填，公众号的唯一标识
		    String timestamp=String.valueOf(System.currentTimeMillis()/1000);// 必填，生成签名的时间戳
		    
		    String nonceStr=UUID.randomUUID().toString().replace("-", "").substring(0, 16);// 必填，生成签名的随机串
		    
		    String signature=check.getSignature(nonceStr, GetTicket.getjsapi_Ticket(), timestamp, url);// 必填，签名
		    //返回json
		    wx_config config=new wx_config(appId, timestamp, nonceStr, signature);
		    JSONObject jsonObject=JSONObject.fromObject(config);
		    System.out.println(jsonObject.toString());
		    response.getWriter().write(jsonObject.toString());
		}
	}

}
