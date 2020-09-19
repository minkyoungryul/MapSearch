package com.kakaobank.project.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtils {
	
	public ApiUtils() {}
	
	/**
	 * Http Get방식 호출
	 * @param strUrl
	 * @param key
	 * @return
	 */
	public static String requestHttpGet(String strUrl, String key) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
			con.setConnectTimeout(10000); //서버에 연결되는 Timeout 시간 설정
			con.setReadTimeout(10000); // InputStream 읽어 오는 Timeout 시간 설정
			con.setRequestProperty("Authorization", key);
			con.setRequestProperty("Content-Type", "multipart/form-data;boundary=*****;charset=utf-8");
			con.setRequestMethod("GET");
            con.setDoOutput(false); 
            

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}

			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				br.close();
			}catch (IOException e) {
				throw e;
			}
		}
		
		return sb.toString();
	}
	
	public static Map<String, Object> jsonStringToMap(String json) throws IOException{
		ObjectMapper mapper = new ObjectMapper(); 
		Map<String, Object> map = new HashMap<>();
		
		try { // convert JSON string to Map 
			map = mapper.readValue(json, Map.class); 
		} catch (IOException e) { 
			throw e; 
		}
		
		return map;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static <T>T unmarshallingJson(String json, Class<T> valueType) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return (T)objectMapper.readValue(json, valueType);
	}

}
