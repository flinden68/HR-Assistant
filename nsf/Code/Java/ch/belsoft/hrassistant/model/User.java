package ch.belsoft.hrassistant.model;

import java.io.Serializable;

public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String userName = "";
    private String password = "";
    private String passwordGenerated = "";
    private boolean authenticated = false;
    
    public User(){
        
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
    
    public void setPasswordGenerated(String passwordGenerated) {
        this.passwordGenerated = passwordGenerated;
    }
    
    public String getPasswordGenerated() {
        return passwordGenerated;
    }
}
