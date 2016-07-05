package com.ibm.xsp.bluemix.services.data;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;

import com.ibm.commons.util.StringUtil;
import com.ibm.commons.util.io.json.JsonException;
import com.ibm.commons.util.io.json.JsonJavaArray;
import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.xsp.bluemix.util.BluemixContextUtil;
import com.ibm.xsp.bluemix.util.RestUtil;

/**
 * Utility bean for using the Bluemix Cloudant database service
 * 
 * @author Brian Gleeson - brian.gleeson@ie.ibm.com
 */
public class Cloudant {
    
    public static final String SERVICE_NAME = "cloudantNoSQLDB";
    private final BluemixContextUtil bluemixUtil;
    private final RestUtil rest;
    
    public Cloudant() {
        // Hardcoded Watson url/credentials for local testing
        String baseUrl  = "https://f44a2403-965e-4345-bc84-4fd485047735-bluemix:e0ab6592f8d6a0192d8d819fbe656a112f54fc82464a46ca9005cdc63b396d4a@f44a2403-965e-4345-bc84-4fd485047735-bluemix.cloudant.com";
        String username = "f44a2403-965e-4345-bc84-4fd485047735-bluemix";
        String password = "e0ab6592f8d6a0192d8d819fbe656a112f54fc82464a46ca9005cdc63b396d4a";
        bluemixUtil = new BluemixContextUtil(SERVICE_NAME, username, password, baseUrl);
        rest = new RestUtil();
    }
    
    public void testSearch() throws JsonException, URISyntaxException, IOException{
        String postUrl = bluemixUtil.getBaseUrl() + "/hrassistant/_design/configuration/_search/ftsearchConfigurations?q=m";
        Response response = rest.post(postUrl, bluemixUtil.getAuthorizationHeader(), new JsonJavaObject());
        System.out.println("Response STATUS = "+ response.returnResponse().getStatusLine().getStatusCode());
    }
    
    public void insertDoc(String dbName, String title, String author, boolean read, String filePath, String contentType) throws ClientProtocolException, URISyntaxException, IOException, JsonException{
        JsonJavaObject postData = new JsonJavaObject();
        postData.put("title", title);
        postData.put("author", author);
        postData.put("read", read);
        
        String postUrl = bluemixUtil.getBaseUrl() + "/" + dbName;
        
        if(StringUtil.isNotEmpty(filePath) && StringUtil.isNotEmpty(contentType)) {
            JsonJavaObject imageAttachment = createAttachmentData(filePath, contentType);
            postData.put("_attachments", imageAttachment);
        }
        
        rest.post(postUrl, bluemixUtil.getAuthorizationHeader(), postData);
    }
    
    public void updateDoc(String dbName, String id, String title, String author, boolean read, String revision) throws ClientProtocolException, URISyntaxException, IOException, JsonException{
        JsonJavaObject postData = new JsonJavaObject();
        postData.put("_id", id);
        postData.put("title", title);
        postData.put("_rev", revision);
        postData.put("author", author);
        postData.put("read", read);
        
        String postUrl = bluemixUtil.getBaseUrl() + "/" + dbName;
        rest.post(postUrl, bluemixUtil.getAuthorizationHeader(), postData);
    }
    
    public void deleteDoc(String dbName, String id, String revision) throws ClientProtocolException, URISyntaxException, IOException, JsonException{
        JsonJavaObject postData = new JsonJavaObject();
        postData.put("_id", id);
        postData.put("_rev", revision);
        postData.put("_deleted", true);
        
        String postUrl = bluemixUtil.getBaseUrl() + "/" + dbName;
        rest.post(postUrl, bluemixUtil.getAuthorizationHeader(), postData);
    }
    
    /******************
     * Helper methods *
     ******************/
    
    /**
     * Create the database, a view and one document
     */
    public String initDefaultDatabase() throws ClientProtocolException, IOException, URISyntaxException, JsonException{
        int dbCreation = createDefaultDatabase();
        
        if(dbCreation == 201 || dbCreation == 202 || dbCreation == 412) {
            int viewCreation = createDefaultView();
            if(viewCreation == 201 || viewCreation == 409) {
                List<JsonJavaObject> rows = getView("booklist", "documents", "all");
                
                if(null == rows || rows.size() < 2) {
                    //XSPContext context = XSPContext.getXSPContext(FacesContext.getCurrentInstance());
                    //String filePath = context.getUrl().getAddress().replace("cloudant.xsp", "") + "catcherintherye.jpg";
                    //String contentType = "image/jpeg";
                    insertDoc("booklist", "Catcher in the Rye", "J.D. Salinger", false);
                }
                return "success";
            }
        }else if(dbCreation == 403){
            return "invalid database name";
        }
        return "unknown error";
    }
    
    private int createDefaultDatabase() throws ClientProtocolException, URISyntaxException, IOException, JsonException {
        String putUrl = bluemixUtil.getBaseUrl() + "/booklist";
        Response response = rest.put(putUrl, bluemixUtil.getAuthorizationHeader());
        int responseStatus = response.returnResponse().getStatusLine().getStatusCode();
        return responseStatus;
    }
    
    private int createDefaultView() throws ClientProtocolException, URISyntaxException, IOException, JsonException {
        String putUrl = bluemixUtil.getBaseUrl() + "/booklist/_design/documents";
        JsonJavaObject putData = new JsonJavaObject();
        JsonJavaObject viewsData = new JsonJavaObject();
        JsonJavaObject allViewData = new JsonJavaObject();
        
        allViewData.put("map", "function(doc){emit(doc);}");
        viewsData.put("all", allViewData);
        putData.put("_id", "_design/documents");
        putData.put("views", viewsData);
        
        Response response = rest.put(putUrl, bluemixUtil.getAuthorizationHeader(), putData);
        int responseStatus = response.returnResponse().getStatusLine().getStatusCode();
        return responseStatus;
    }
    
    public List<JsonJavaObject> getView(String dbName, String designName, String viewName) throws ClientProtocolException, URISyntaxException, IOException {
        String getUrl = bluemixUtil.getBaseUrl() + "/" + dbName + "/_design/" + designName + "/_view/" + viewName;
        Response response = rest.get(getUrl, bluemixUtil.getAuthorizationHeader());
        
        HttpResponse httpResponse = response.returnResponse();
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if(statusCode == 404) {
            return null;
        }else{
            String content = EntityUtils.toString(httpResponse.getEntity());
            JsonJavaObject jsonData = rest.parse(content);
            List<JsonJavaObject> rows = getRows(jsonData);
            return rows;
        }
    }
    
    public String insertDoc(String dbName, String fileName, File file, String contentType) throws ClientProtocolException, URISyntaxException, IOException, JsonException{
        JsonJavaObject postData = new JsonJavaObject();
        postData.put("title", fileName);
        postData.put("author", "XPages");
        postData.put("read", true);
        
        String postUrl = bluemixUtil.getBaseUrl() + "/" + dbName;
        
        if(file != null) {
            try {
                byte[] imageBytes = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
                
                JsonJavaObject imageAttachment = new JsonJavaObject();
                JsonJavaObject imageData = new JsonJavaObject();
                imageData.put("Content-Type", contentType);
                imageData.put("data", imageBytes);
                imageAttachment.put(fileName, imageData);
                postData.put("_attachments", imageAttachment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        Response insertResponse = rest.post(postUrl, bluemixUtil.getAuthorizationHeader(), postData);
        String content = insertResponse.returnContent().asString();
        
        JsonJavaObject jsonResponseData = rest.parse(content);
        String id = (String)jsonResponseData.get("id");
        return id;
    }
    
    public String[] getDocDetailsFromResponse(Response response) throws ClientProtocolException, IOException {
        String insertResponseData = response.returnContent().asString();
        JsonJavaObject jsonResponseData = rest.parse(insertResponseData);
        String revision = (String)jsonResponseData.get("rev");
        String id = (String)jsonResponseData.get("id");
        
        String[] docDetails = new String[2];
        docDetails[0] = id;
        docDetails[1] = revision;
        return docDetails;
    }
    
    public List<JsonJavaObject> getRows(JsonJavaObject data) {
        JsonJavaArray rows = data.getAsArray("rows");
        List<JsonJavaObject> docList = new ArrayList<JsonJavaObject>();
        
        if (rows != null && rows.size() > 0) {
            for(int i = 0; i < rows.size(); i++) {
                JsonJavaObject row = (JsonJavaObject)rows.get(i);
                JsonJavaObject key = (JsonJavaObject)row.get("key");
                docList.add(key);
            }
        }
        return docList;
    }
    
    public JsonJavaObject createAttachmentData(String filePath, String contentType) throws IOException {
        String fileExt = contentType.replace('/', '.');
        File imageFile = null;
        if(filePath.contains("http")) {
            String tempFileName = new SimpleDateFormat("MMddhhmmss").format(new Date());
            imageFile = new File(System.getProperty("java.io.tmpdir") + tempFileName + ".jpg");
            imageFile.deleteOnExit();
            URL imageURL = new URL(filePath);
            FileUtils.copyURLToFile(imageURL, imageFile);
        }else{
            imageFile = new File(filePath);
        }
        byte[] imageBytes = Base64.encodeBase64(FileUtils.readFileToByteArray(imageFile));
        
        JsonJavaObject imageAttachment = new JsonJavaObject();
        JsonJavaObject imageData = new JsonJavaObject();
        imageData.put("Content-Type", contentType);
        imageData.put("data", imageBytes);
        imageAttachment.put(fileExt, imageData);
        
        return imageAttachment;
    }
    
    public void insertDoc(String dbName, String title, String author, boolean read) throws ClientProtocolException, URISyntaxException, IOException, JsonException{
        insertDoc(dbName, title, author, read, null, null);
    }
    
    public String getBaseUrl() {
        return bluemixUtil.getBaseUrl();
    }
}
