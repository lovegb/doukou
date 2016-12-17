package com.doukou.Tuling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

/**
 * 调用图灵机器人api接口，获取只能回复内容         .<br>
 *
 * @author sunkai
 * @version 2016年12月12日
 */
public class TulingProcess {
	
	private static final String APIKEY = "3c1f07bca9334784a611c7b52b6167a6";
	
	public static String getTulingResult(String content) {
		StringBuffer strBuffer = new StringBuffer();
		BufferedReader reader = null;
		HttpURLConnection urlConnection = null;
		String result = null;
		try {
			String info = URLEncoder.encode(content, "UTF-8");
			String url = "http://www.tuling123.com/openapi/api?key="
						+ APIKEY + "&info=" + info;
			URL getUrl = new URL(url);
			urlConnection = (HttpURLConnection) getUrl.openConnection();
			urlConnection.connect();
			reader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream(), "utf-8"));

			String line = "";
			while ((line = reader.readLine()) != null) {
				strBuffer.append(line);
			}
			JSONObject json = new JSONObject(strBuffer.toString());
			result = json.getString("text");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return result;
	}
}
