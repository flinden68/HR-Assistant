package ch.belsoft.tools;

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

import com.ibm.commons.util.StringUtil;

/**
 * Utility class for sending REST requests
 * 
 * @author Brian Gleeson - brian.gleeson@ie.ibm.com
 */
public class RestUtil {
	private final Executor executor = Executor.newInstance();
	private static final String HEADER_AUTHORIZATION = "Authorization";

	/**
	 * Send basic GET request with authorization header
	 */
	public Response get(String url, String auth) throws URISyntaxException,
			IOException {
		URI normUri = new URI(url).normalize();
		Request getRequest = Request.Get(normUri);
		if (StringUtil.isNotEmpty(auth)) {
			getRequest.addHeader(HEADER_AUTHORIZATION, auth);
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
	 * 
	 * @param url
	 *            - The url of the POST request
	 * @param auth
	 *            - String for authorization header
	 * @param headers
	 *            - Hashmap of headers to add to the request
	 * @param postData
	 *            - The body of the POST
	 * @return the Response to the POST request
	 */
	public Response post(String url, String auth,
			HashMap<String, String> headers, String postDataString)
			throws IOException, URISyntaxException {
		URI normUri = new URI(url).normalize();
		Request postRequest = Request.Post(normUri);

		// Add all headers
		if (StringUtil.isNotEmpty(auth)) {
			postRequest.addHeader(HEADER_AUTHORIZATION, auth);
		}
		if (headers != null && headers.size() > 0) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				postRequest.addHeader(entry.getKey(), entry.getValue());
			}
		}

		Response response = executor.execute(postRequest.bodyString(
				postDataString, ContentType.APPLICATION_JSON));
		return response;
	}

	public Response post(String url, String auth, String postDataString,
			File fileUpload) throws IOException, URISyntaxException {
		URI normUri = new URI(url).normalize();
		Request postRequest = Request.Post(normUri);

		// Add all headers
		if (StringUtil.isNotEmpty(auth)) {
			postRequest.addHeader(HEADER_AUTHORIZATION, auth);
		}

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addBinaryBody("images_file", fileUpload,
				ContentType.APPLICATION_OCTET_STREAM, fileUpload.getName());

		builder.addTextBody("classifier_ids", postDataString,
				ContentType.MULTIPART_FORM_DATA);

		HttpEntity multipart = builder.build();
		postRequest.body(multipart);

		Response response = executor.execute(postRequest);
		return response;
	}

	/**
	 * Send basic POST request
	 * 
	 * @param url
	 *            - The url of the POST request
	 * @param postData
	 *            - The body of the POST
	 * @return the Response to the POST request
	 */
	public Response post(String url, String postDataString)
			throws URISyntaxException, IOException {
		return post(url, null, null, postDataString);
	}

	/**
	 * Send basic POST request with authorization header
	 * 
	 * @param url
	 *            - The url of the POST request
	 * @param auth
	 *            - String for authorization header
	 * @param postData
	 *            - The body of the POST
	 * @return the Response to the POST request
	 */
	public Response post(String url, String auth, String postDataString)
			throws URISyntaxException, IOException {
		return post(url, auth, null, postDataString);
	}

	/**
	 * Send PUT request with authorization header
	 * 
	 * @param url
	 *            - The url of the POST request
	 * @param auth
	 *            - String for authorization header
	 * @param putData
	 *            - The body of the PUT
	 */
	public Response put(String url, String auth, String putDataString)
			throws URISyntaxException, IOException {
		URI normUri = new URI(url).normalize();
		Request putRequest = Request.Put(normUri);

		// Add auth header
		if (StringUtil.isNotEmpty(auth)) {
			putRequest.addHeader(HEADER_AUTHORIZATION, auth);
		}

		putRequest = putRequest.bodyString(putDataString,
				ContentType.APPLICATION_JSON);

		Response response = executor.execute(putRequest);
		return response;
	}

	public Response put(String url, String auth) throws URISyntaxException,
			IOException {
		return put(url, auth, null);
	}

}
