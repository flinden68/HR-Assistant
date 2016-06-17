package com.ibm.xsp.bluemix.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import com.ibm.commons.Platform;
import com.ibm.commons.util.StringUtil;
import com.ibm.commons.util.io.json.JsonException;
import com.ibm.commons.util.io.json.JsonGenerator;
import com.ibm.commons.util.io.json.JsonJavaFactory;
import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.commons.util.io.json.JsonParser;

/**
 * Utility class for sending REST requests
 * 
 * @author Brian Gleeson - brian.gleeson@ie.ibm.com
 */
public class RestUtil {
	Executor executor = Executor.newInstance();
	
	/**
	 * Send basic GET request with authorization header
	 */
	public Response get(String url, String auth) throws URISyntaxException, IOException {
		URI normUri = new URI(url).normalize();
		Request getRequest = Request.Get(normUri);
		if(StringUtil.isNotEmpty(auth)) {
			getRequest.addHeader("Authorization", auth);
		}
		Response response = executor.execute(getRequest);
		return response;
	}
	
	/**
	 * Send basic GET request
	 */
	public Response get(String url) throws URISyntaxException, IOException {
		return get(url, null);
	}
	
	/**
	 * Send POST request with authorization header and additional headers
	 * @param url - The url of the POST request
	 * @param auth - String for authorization header
	 * @param headers - Hashmap of headers to add to the request
	 * @param postData - The body of the POST
	 * @return the Response to the POST request
	 */
	public Response post(String url, String auth, HashMap<String, String> headers, JsonJavaObject postData) throws JsonException, IOException, URISyntaxException {
		URI normUri = new URI(url).normalize();
		Request postRequest = Request.Post(normUri);
		
		//Add all headers
		if(StringUtil.isNotEmpty(auth)) {
			postRequest.addHeader("Authorization", auth);
		}
		if(headers != null && headers.size() > 0){
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				postRequest.addHeader(entry.getKey(), entry.getValue());
			}
		}

		String postDataString = JsonGenerator.toJson(JsonJavaFactory.instanceEx, postData);
		Response response = executor.execute(postRequest.bodyString(postDataString, ContentType.APPLICATION_JSON));
		return response;
	}
	

	public Response post(String url, String auth, JsonJavaObject postData, File fileUpload) throws JsonException, IOException, URISyntaxException {
		URI normUri = new URI(url).normalize();
		Request postRequest = Request.Post(normUri);
		
		//Add all headers
		if(StringUtil.isNotEmpty(auth)) {
			postRequest.addHeader("Authorization", auth);
		}
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addBinaryBody("images_file", fileUpload, ContentType.APPLICATION_OCTET_STREAM, fileUpload.getName());
		
		if(postData != null) {
			String postDataString = JsonGenerator.toJson(JsonJavaFactory.instanceEx, postData);
			builder.addTextBody("classifier_ids", postDataString, ContentType.MULTIPART_FORM_DATA);
		}
		
		HttpEntity multipart = builder.build();
		postRequest.body(multipart);
		
		Response response = executor.execute(postRequest);
		return response;
	}
	
	/**
	 * Send basic POST request
	 * @param url - The url of the POST request
	 * @param postData - The body of the POST
	 * @return the Response to the POST request
	 */
	public Response post(String url, JsonJavaObject postData) throws URISyntaxException, IOException, JsonException {
		return post(url, null, null, postData);
	}
	
	/**
	 * Send basic POST request with authorization header
	 * @param url - The url of the POST request
	 * @param auth - String for authorization header
	 * @param postData - The body of the POST
	 * @return the Response to the POST request
	 */
	public Response post(String url, String auth, JsonJavaObject postData) throws URISyntaxException, IOException, JsonException {
		return post(url, auth, null, postData);
	}
	
	/**
	 * Send PUT request with authorization header
	 * @param url - The url of the POST request
	 * @param auth - String for authorization header
	 * @param putData - The body of the PUT
	 */
	public Response put(String url, String auth, JsonJavaObject putData) throws URISyntaxException, IOException, JsonException {
		URI normUri = new URI(url).normalize();
		Request putRequest = Request.Put(normUri);
		
		//Add auth header
		if(StringUtil.isNotEmpty(auth)) {
			putRequest.addHeader("Authorization", auth);
		}
		
		//Add put data
		String putDataString = JsonGenerator.toJson(JsonJavaFactory.instanceEx, putData);
		if(putData != null) {
			putRequest = putRequest.bodyString(putDataString, ContentType.APPLICATION_JSON);
		}
		
		Response response = executor.execute(putRequest);
		return response;
	}

	public Response put(String url, String auth) throws URISyntaxException, IOException, JsonException {
		return put(url, auth, null);
	}
	
	/**
	 * Convert a JSON String into a JsonJavaObject
	 * @param data - The JSON data in String format
	 * @return JsonJavaObject containing the JSON data
	 */
	public JsonJavaObject parse(String data) {
		JsonJavaObject jsonData = null;
		try {
			jsonData = (JsonJavaObject) JsonParser.fromJson(JsonJavaFactory.instanceEx, data);
		} catch (JsonException e) {
			Platform.getInstance().log(e);
		}
		return jsonData;
	}
}
