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
	public static final String loctype = "1";
	public static final String URL_DATA_CREATE = "http://yuntuapi.amap.com/datamanage/data/create";
	public static final String URL_DATA_UPDATE = "http://yuntuapi.amap.com/datamanage/data/update";

	public String tt;
	// httpclient
	private static HttpClient client = HttpClients.createDefault();
	// HashMap<String, String> data = new HashMap<String, String>();
	public ObjData data;
	//String name = new String();
	//String address = new String();
	//String dt = "{\"_name\":\"停车场\",\"_address\":\"江苏省苏州市吴中区仁爱路208号\"}";
	// jackson
	public static ObjectMapper objectMapper = new ObjectMapper();
	private JsonGenerator jsonGenerator = null;

	//��ͼ�����ı��id
	public int id;
	public DataToYuntu() throws Exception {
	
	}
	

	public int create(String data) throws Exception {

		jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(
				System.out, JsonEncoding.UTF8);

		//String tt = objectMapper.writeValueAsString(data);		
		
		// http://yuntuapi.amap.com/datamanage/data/create
		HttpPost post = new HttpPost(this.URL_DATA_CREATE);
		post.addHeader("Content-Type", "application/x-www-form-urluncoded");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", key));
		params.add(new BasicNameValuePair("tableid", tableid));
		params.add(new BasicNameValuePair("loctype", loctype));		
		params.add(new BasicNameValuePair("data", data));

		// httpentity
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		HttpResponse resp = client.execute(post);

		// jsonΪJava
		ResInfo resinfo = objectMapper.readValue(resp.getEntity().getContent(),
				ResInfo.class);

		//
		if (resinfo.getStatus() == 1) {
			System.out.println("sucess create item__" + resinfo.get_id());
			this.id=resinfo.get_id();
			return resinfo.getStatus();
		} else {
			System.out.println(resinfo.getInfo());
			return -1;
		}

	}

	
	public int update(String data) throws Exception
	{
		jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(
				System.out, JsonEncoding.UTF8);
		HttpPost post = new HttpPost(this.URL_DATA_UPDATE);
		post.addHeader("Content-Type", "application/x-www-form-urluncoded");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", key));
		params.add(new BasicNameValuePair("tableid", tableid));
		params.add(new BasicNameValuePair("loctype", loctype));		
		params.add(new BasicNameValuePair("data", data));

		// httpentity
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		HttpResponse resp = client.execute(post);

		// jsonΪJava
		ResInfo resinfo = objectMapper.readValue(resp.getEntity().getContent(),
				ResInfo.class);

		//
		if (resinfo.getStatus() == 1) {
			System.out.println("sucess create item__" + resinfo.get_id());
			return resinfo.getStatus();
		} else {
			System.out.println(resinfo.getInfo());
			return -1;
		}
		
	}
}
