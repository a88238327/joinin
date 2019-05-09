package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Tuil.Util;
import entity.SelectUsermaData;
import entity.createEvent;
import entity.createopenidouterstr;
import entity.customer;
import entity.getToken;
import entity.selectOpenid;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class joinin
 */
@WebServlet("/joinin")
public class joinin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joinin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		//System.out.println(wxservice.getAccessToken());
		//处理消息和事件推送
		String activate_ticket=request.getParameter("activate_ticket");
		String card_id=request.getParameter("card_id");
		System.out.println(card_id);
		String encrypt_code=request.getParameter("encrypt_code");
		System.out.println(encrypt_code);
		String outer_str=request.getParameter("outer_str");
		System.out.println(outer_str);
		String openid=request.getParameter("openid");
		System.out.println(openid);
		if(!selectOpenid.selectopenid(openid))
		{
			String url="https://api.weixin.qq.com/card/membercard/activatetempinfo/get?access_token=TOKEN";
			HashMap<String , String > map=new HashMap<String, String>();
			map.put("activate_ticket", activate_ticket);
			JSONObject jsonObject=JSONObject.fromObject(map);
			String getinfo=Util.post(url.replace("TOKEN", getToken.get_ACCESSTOKEN()), jsonObject.toString());
			JSONObject jsonObject2=JSONObject.fromObject(getinfo);
			JSONObject jsonObject3=JSONObject.fromObject(jsonObject2.getString("info"));
			JSONArray jsonArray=JSONArray.fromObject(jsonObject3.getString("common_field_list"));
			String phone="";
			for(int i=0;i<jsonArray.size();i++)
			{
				System.out.println(jsonArray.get(i).toString());
				JSONObject jsonObject4=JSONObject.fromObject(jsonArray.get(i).toString());
				System.out.println(jsonObject4.getString("name"));
				if(jsonObject4.getString("name").equals("USER_FORM_INFO_FLAG_MOBILE"))
				{
					phone=jsonObject4.getString("value");
				}
			}
			String url3="https://api.weixin.qq.com/card/code/decrypt?access_token=TOKEN";
			String codedata="{\"encrypt_code\":\""+encrypt_code+"\"}";
			String code=Util.post(url3.replace("TOKEN", getToken.get_ACCESSTOKEN()),codedata);
			JSONObject code2=JSONObject.fromObject(code);
			customer.insert(openid,phone,outer_str);
			String jihuodata="{\"membership_number\":\""+phone+"\",\"code\":\""+code2.getString("code")+"\",\"card_id\":\""+card_id+"\"}";
			System.out.println(jihuodata);
			String url1="https://api.weixin.qq.com/card/membercard/activate?access_token=TOKEN";
			String str=Util.post(url1.replace("TOKEN", getToken.get_ACCESSTOKEN()), jihuodata);
			System.out.println(str);
			
			response.sendRedirect("http://cloud2.hnjtbf.com/joinin/closewindows.html");
		}
		else {
			response.sendRedirect("http://cloud2.hnjtbf.com/joinin/closewindows.html");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		//System.out.println(wxservice.getAccessToken());
		//处理消息和事件推送
		String activate_ticket=request.getParameter("activate_ticket");
		String card_id=request.getParameter("card_id");
		System.out.println(card_id);
		String encrypt_code=request.getParameter("encrypt_code");
		System.out.println(encrypt_code);
		String outer_str=request.getParameter("outer_str");
		System.out.println(outer_str);
		String openid=request.getParameter("openid");
		System.out.println(openid);
		String url="https://api.weixin.qq.com/card/membercard/activatetempinfo/get?access_token=TOKEN";
		HashMap<String , String > map=new HashMap<String, String>();
		map.put("activate_ticket", activate_ticket);
		JSONObject jsonObject=JSONObject.fromObject(map);
		String getinfo=Util.post(url.replace("TOKEN", getToken.get_ACCESSTOKEN()), jsonObject.toString());
		System.out.println(getinfo);
		//如果是event执行下列
//				//查询数据库是否存在该openid
//				if(!selectOpenid.selectopenid(requestMap.get("FromUserName")))
//				{
//					if(createopenidouterstr.createopenidouterstr(requestMap.get("FromUserName"), requestMap.get("OuterStr")))
//					{
//						System.out.println("创建会员成功");
//					}
//				}
			
			//Event=submit_membercard_user_info事件处理
//			if(requestMap.get("Event").equals("submit_membercard_user_info"))
//			{
//				//获取客户信息
//				String customerinfo=SelectUsermaData.getUserInfo(requestMap.get("CardId"), requestMap.get("UserCardCode"));
//				if(updateCustomer_info.updateinfo(customerinfo))
//				{
//					System.out.println("用户资料更新成功");
//				}
//				
//				
//			}
			
			//记录用户事件
//			if(createEvent.createEvent(requestMap.get("FromUserName"), requestMap.get("Event")))
//			{
//				System.out.println("创建事件成功");
//			}
//			
			
	}
		

}
