package com.yliu.crawler.core.request;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;
import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yliu.crawler.core.bean.UriParams;
import com.yliu.crawler.core.constant.Decode;
import com.yliu.crawler.core.utils.HttpConnectionManager;
import com.yliu.crawler.core.utils.JsonUtil;




public class Request {
	
	private static Logger logger = LoggerFactory.getLogger(Request.class);  

	private static CloseableHttpResponse execute(HttpRequestBase request,Header[] headers){
		
		CloseableHttpClient client = HttpConnectionManager.getHttpClient();
		CloseableHttpResponse response = null;
		if(headers!=null){
			request.setHeaders(headers);
			}
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static String get(String url,Header[] headers,String charset){
		HttpRequestBase get = new HttpGet(url);
		CloseableHttpResponse response = execute(get,headers);
		String body = null;
		try {
			body = EntityUtils.toString(response.getEntity(),charset);
			response.close();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return body;
	}
	public static String get(String url,Header[] headers,String charset,UriParams params){
		String uri = url+"?"+params.getParams();
		logger.info("请求地址 {}",uri);
		HttpGet get = new HttpGet(uri);
		CloseableHttpResponse response = execute(get,headers);
		String body = null;
		try {
			body = EntityUtils.toString(response.getEntity(),charset);
			response.close();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return body;
	}
	public static String get(String url,Header header){
			Header[] headers = new Header[1];
			headers[0] = header;
			String charset = Decode.UTF8.getCode();
			return get(url,headers,charset);
			
	}
	
	public static String get(String url){
		String charset = Decode.UTF8.getCode();
		return get(url,null,charset);
		
	}
	
	public static ByteArrayOutputStream getOutPutStream(String url,String suffix){
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = execute(get, null);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			response.getEntity().writeTo(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	public static String post(String url,Map<String,Object> params,Header[] headers,String charset){
		return post(url,JsonUtil.toJson(params),headers,charset);
	}
	public static String post(String url,UriParams params,Header[] headers,String charset){
		return post(url,params.getParams(),headers,charset);
	}
	public static String post(String url,String params,Header[] headers,String charset){
		CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(params, Charset.forName(charset));
		post.setEntity(entity);
		CloseableHttpResponse response = null;
		String result = null;
		try {
			response = httpClient.execute(post);
			result = EntityUtils.toString(response.getEntity(),charset);
			logger.info("请求状态{}",response.getStatusLine().getStatusCode());
			response.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String post(String url,Map<String,Object> params,Header[] headers){
		return post(url,params,headers,Decode.UTF8.getCode());
	}
	// 构建唯一会话Id
	public static String buildSessionId(){
	    UUID uuid = UUID.randomUUID();
	    String str = uuid.toString();
	    return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
	}
	
}
