package ch.belsoft.hrassistant.attachment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.belsoft.hrassistant.model.DataItem;

public class AttachmentHolder extends DataItem implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private final DataType dataType = DataType.ATTACHMENT;
    private Map<String, Attachment> attachments = new HashMap<String, Attachment>();
    
    public AttachmentHolder(){
        
    }
    
    public DataType getDataType() {
        return dataType;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Attachments: " + getAttachments());
        
        return sb.toString();
    }
    
    public void addAttachment(String name, Attachment attachment){
        attachments.put(name, attachment);
    }
    
    public void setAttachments(Map<String, Attachment> attachments) {
        this.attachments = attachments;
    }
    
    public Map<String, Attachment> getAttachments() {
        return attachments;
    }
    
    public List<Attachment> getAttachmentsAsList(){
        return new ArrayList<Attachment>(attachments.values());
    }
    
    public boolean isShowAttachments(){
        return attachments.isEmpty();
    }
    
    public String getAttachmentUrl(String attachmentName){
        return "/xsp/attachment/"+getId()+ "/"+ attachmentName;
    }
}
