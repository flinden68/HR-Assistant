package com.ibm.bluemix.services;


public interface ICloudService<T> {
    
    public void connect();
    
    public String getPassword();
    
    public void setPassword(String password);
    
    public String getUsername();
    
    public void setUsername(String username);
    
    public String getApiKey();
    
    public void setApiKey(String apiKey);
}
