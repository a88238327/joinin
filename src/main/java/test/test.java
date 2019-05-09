package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.ID;
import entity.Util;
import entity.getToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import servlet.Selectdata;

public class test {
	public static void main(String[] args) {
//		String dataString="{\"membership_number\":\"17508910598\",\"code\":\""+ID.getOrderId()+"\",\"card_id\":\"pqnJv0gAEGqEe_CLTHhq06M-Gqa8\"}";
//		String urlString=getToken.get_ACCESSTOKEN();
//		String url="https://api.weixin.qq.com/card/membercard/activate?access_token=TOKEN";
//		String reString=Util.post(url.replace("TOKEN", urlString), dataString);
//		System.out.println(reString);
		
//		String dataString="{\"card_id\": \"pqnJv0hpaqK_k2Odtyodq7elr71s\",\"required_form\":{\"can_modify\":false,\"common_field_id_list\": [\"USER_FORM_INFO_FLAG_MOBILE\"]}}";
//		String urlString=getToken.get_ACCESSTOKEN();
//		String url="https://api.weixin.qq.com/card/membercard/activateuserform/set?access_token=TOKEN";
//		String reString=Util.post(url.replace("TOKEN", urlString), dataString);
//		System.out.println(reString);
		
		String dataString="{\"card_id\": \"pqnJv0lFQcvwypV6kBIMXzVx4Hag\",\"member_card\": {\"base_info\": {\"need_push_on_view\": true},\"wx_activate\": true,\"wx_activate_after_submit\": true,\"wx_activate_after_submit_url\": \"http://cloud.hnjtbf.com/Activity_Data/joinin\"}}";
		String urlString=getToken.get_ACCESSTOKEN();
		String url="https://api.weixin.qq.com/card/update?access_token=TOKEN";
		String reString=Util.post(url.replace("TOKEN", urlString), dataString);
		System.out.println(reString);
		System.out.println(getToken.get_ACCESSTOKEN());
		
		
	}
}
