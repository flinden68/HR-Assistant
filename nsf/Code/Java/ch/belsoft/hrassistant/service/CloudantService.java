package ch.belsoft.hrassistant.service;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;

import nl.elstarit.cloudant.connector.CloudantConnector;
import nl.elstarit.cloudant.log.CloudantLogger;
import nl.elstarit.cloudant.model.ConnectorIndex;
import nl.elstarit.cloudant.model.ConnectorResponse;
import ch.belsoft.tools.XPagesUtil;

import com.ibm.xsp.bluemix.util.BluemixContextUtil;


public class CloudantService {
    
    private static final String SERVICE_NAME = "cloudantNoSQLDB";
    private String account;
    private String password;
    private String username;
    private String cloudantDb;
    
    private BluemixContextUtil bluemixUtil;
    
    private boolean connected = false;
    
    private static final String BEAN_NAME = "CloudantService";
    
    private CloudantConnector connector;
    
    public CloudantService(){
        
    }
    
    public void connect(){
        if(bluemixUtil == null){
            bluemixUtil = new BluemixContextUtil(SERVICE_NAME, username, password, "");
        }
        connector = new CloudantConnector(bluemixUtil.getAccount(), bluemixUtil.getUsername(), bluemixUtil.getPassword(), cloudantDb, false);
        connected = true;
    }
    
    public void testConnection(){
        if(!connected){
            connect();
        }
        try{
            List<ConnectorIndex> indices = allIndices();
            setConnected(!indices.isEmpty());
        }catch(Exception e){
            setConnected(false);
        }
    }
    
    public boolean isConnected(){
        return connected;
    }
    
    /*
     * Database connectors
     */
    
    public void switchDatabase(String db, boolean create){
        connector.switchDatabase(db, create);
    }
    
    public void createDatabase(String db){
        connector.switchDatabase(db, true);
    }
    
    /*
     * Document connectors
     */
    
    public Object findDocumentByID(Class<?> cls, String documentId){
        try{
            return connector.getDocumentConnector().find(cls, documentId);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    
    public List<?> findAllDocuments(Class<?> cls){
        try{
            return connector.getDocumentConnector().findAllDocuments(cls);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    
    public void removeDocument(Object obj){
        try{
            connector.getDocumentConnector().delete(obj);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }
    
    public ConnectorResponse saveDocument(Object obj){
        try{
            connector.getDocumentConnector().save(obj);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    
    public ConnectorResponse updateDocument(Object obj){
        try{
            connector.getDocumentConnector().update(obj);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    
    public void saveDocuments(final List<?> docs){
        try{
            connector.getDocumentConnector().createBulk(docs);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void updateDocuments(final List<?> docs){
        try{
            connector.getDocumentConnector().updateBulk(docs);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void deleteDocuments(final List<?> docs){
        try{
            connector.getDocumentConnector().deleteBulk(docs);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }
    
    public ConnectorResponse saveAttachment(final InputStream inputStream, final String name, final String contentType, final String docId, final String docRev){
        try{
            return connector.getDocumentConnector().saveAttachment(inputStream, name, contentType, docId, docRev);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    
    /**
     * 
     * @param cls
     * @param designDoc
     * @param viewName
     * @param keyType
     * @param limit
     * @return
     */
    public List<?> findAllDocumentFromView(final Class<?> cls, final String designDoc, final String viewName, final String keyType, final int limit){
        try{
            return connector.getDocumentConnector().findAllDocumentsFromView(cls, designDoc, viewName, keyType, limit);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    
    /*
     * Query
     */
    
    public List<ConnectorIndex> allIndices(){
        return (List<ConnectorIndex>) connector.getQueryConnector().allIndices();
    }
    
    public List<?> search(final String searchIndexId, final Class<?> cls, final Integer queryLimit, final String query){
        try{
            connector.getQueryConnector().search(searchIndexId, cls, queryLimit, query);
        }catch(Exception e){
            CloudantLogger.CLOUDANT.getLogger().log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    
    /**
     * Getters and Setters
     */
    //access to the bean
    public static CloudantService get() {
        return (CloudantService) XPagesUtil.resolveVariable(BEAN_NAME);
    }
    
    public String getAccount() {
        return account;
    }
    
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    
    public String getPassword() {
        return password;
    }
    
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getUsername() {
        return username;
    }
    
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    
    public String getCloudantDb() {
        return cloudantDb;
    }
    
    
    public void setCloudantDb(String cloudantDb) {
        this.cloudantDb = cloudantDb;
    }
    
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    
}
