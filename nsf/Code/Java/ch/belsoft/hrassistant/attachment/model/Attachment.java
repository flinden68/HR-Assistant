package ch.belsoft.hrassistant.attachment.model;

import java.io.Serializable;

public class Attachment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String name;
    private String data;
    private String digest;
    private String content_type;
    private long length;
    private boolean stub;
    
    public Attachment(){
        
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDigest() {
        return digest;
    }
    
    public void setDigest(String digest) {
        this.digest = digest;
    }
    
    public String getContent_type() {
        return content_type;
    }
    
    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }
    
    public long getLength() {
        return length;
    }
    
    public void setLength(long length) {
        this.length = length;
    }
    
    public boolean isStub() {
        return stub;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public String getData() {
        return data;
    }
    
    public void setStub(boolean stub) {
        this.stub = stub;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getName() + ",");
        sb.append("ContentType: " + getContent_type() + ",");
        sb.append("Digest: " + getDigest() + ",");
        //sb.append("Data: " + getData() + ",");
        sb.append("length: " + getLength());
        
        return sb.toString();
    }
    
    public String getNameOnly(){
        return getName().split("\\.(?=[^\\.]+$)")[0];
    }
    
    public String getExtension(){
        return getName().split("\\.(?=[^\\.]+$)")[1];
    }
    
    public String getSrcForImageTag(){
        return "data:"+getContent_type()+";base64,"+getData();
    }
    
}
