package com.Lbs.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Servlet implementation class DataToYuntu
 */
// @WebServlet("/DataToYuntu")
public class DataToYuntu {
	private static final long serialVersionUID = 1L;

	public static final String key = "fb132e1723bf9e8ec0967391ab6eb6f4";
	public static final String tableid = "54c48b0ae4b0ff22e1f393f1";
	public static final String loctype = "2";
	public static final String URL_DATA_CREATE = "http://yuntuapi.amap.com/datamanage/data/create";
	public String tt;
	// httpclient
	private static HttpClient client = HttpClients.createDefault();
	// HashMap<String, String> data = new HashMap<String, String>();
	public ObjData data;
	// String name = new String();
	// String address = new String();
	// String dt =
	// "{\"_name\":\"停车场\",\"_address\":\"江苏省苏州市吴中区仁爱路208号\"}";
	// jackson
	public static ObjectMapper objectMapper = new ObjectMapper();
	private JsonGenerator jsonGenerator = null;

	public DataToYuntu() throws Exception {

	}

	public DataToYuntu(String name, String addr, String phone, String oneTen,
			String oneTri, String oneTwe, String payHalf, String payOne,
			String payMore, String parkSum) throws Exception {
		data = new ObjData(name, addr, phone, oneTen, oneTri, oneTwe, payHalf,
				payOne, payMore, parkSum);
	}

	public int create() throws Exception {

		jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(
				System.out, JsonEncoding.UTF8);

		// http://yuntuapi.amap.com/datamanage/data/create
		HttpPost post = new HttpPost(this.URL_DATA_CREATE);
		post.addHeader("Content-Type", "application/x-www-form-urluncoded");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", key));
		params.add(new BasicNameValuePair("tableid", tableid));
		params.add(new BasicNameValuePair("loctype", loctype));
		params.add(new BasicNameValuePair("data", tt));

		// httpentity
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		HttpResponse resp = client.execute(post);// 这是云图返回的response可以从中取值

		// jsonΪJava
		ResInfo resinfo = objectMapper.readValue(resp.getEntity().getContent(),
				ResInfo.class);

		//
		if (resinfo.getStatus() == 1) {
			System.out.println("sucess create item__" + resinfo.get_id());
			return resinfo.getStatus();
		} else {
			return -1;
			// throw new RuntimeException(resinfo.getInfo());
		}

		// return 0;
	}

}
