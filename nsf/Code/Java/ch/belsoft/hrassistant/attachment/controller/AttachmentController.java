package ch.belsoft.hrassistant.attachment.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;

import ch.belsoft.hrassistant.attachment.dao.AttachmentDAO;
import ch.belsoft.hrassistant.attachment.model.Attachment;
import ch.belsoft.hrassistant.attachment.model.AttachmentHolder;
import ch.belsoft.hrassistant.attachment.model.Upload;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.tools.Logging;

import com.ibm.xsp.component.UIFileuploadEx.UploadedFile;
import com.ibm.xsp.http.IUploadedFile;

public class AttachmentController extends ControllerBase implements IGuiController<Attachment>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private AttachmentDAO attachmentDAO;
    
    public AttachmentController(){
        
    }
    
    public Attachment getDataContext() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public String getPageTitle() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public AttachmentHolder findAttachment(String id){
        return attachmentDAO.read(id);
    }
    
    public void create(AttachmentHolder attachmentHolder){
        try {
            this.attachmentDAO.create(attachmentHolder);
        } catch (Exception e) {
            handleException(e);
            Logging.logError(e);
        }
    }
    
    public void update(AttachmentHolder attachmentHolder){
        try {
            this.attachmentDAO.update(attachmentHolder);
            
        } catch (Exception e) {
            handleException(e);
            Logging.logError(e);
        }
    }
    
    public void remove(AttachmentHolder attachmentHolder) {
        try {
            this.attachmentDAO.delete(attachmentHolder);
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    public AttachmentHolder createNewAttachmentHolderFromUpload(Upload upload, String id){
        AttachmentHolder attachmentHolder = new AttachmentHolder();
        try {
            if(id != null){
                attachmentHolder.setId(id);
            }
            
            Attachment attachment = createAttachmentFromUpload(upload);
            attachmentHolder.addAttachment(attachment.getName(), attachment);
            
        } catch (Exception e) {
            Logging.logError(e);
        }
        return attachmentHolder;
    }
    
    public Attachment createAttachmentFromUpload(Upload upload){
        Attachment attachment = new Attachment();
        attachment.setName(upload.getUploadFile().getUploadedFile().getClientFileName());
        attachment.setContent_type(upload.getUploadFile().getUploadedFile().getContentType());
        attachment.setData(Base64.encodeBase64String(convertUploadFileToBytes(upload.getUploadFile())));
        attachment.setLength(attachment.getData().length());
        
        return attachment;
    }
    
    
    
    private byte[] convertUploadFileToBytes(UploadedFile uploadFile){
        //get the uploaded file
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        String line;
        
        IUploadedFile iUploadedFile = uploadFile.getUploadedFile();
        //get the server file (with a cryptic filename)
        File serverFile = iUploadedFile.getServerFile();
        
        try {
            inputStream = new FileInputStream(serverFile);
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            
        } catch (FileNotFoundException e) {
            Logging.logError(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString().getBytes();
    }
    
    /*
     * GETTERS AND SETTERS
     */
    
    public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
        this.attachmentDAO = attachmentDAO;
    }
    
    public AttachmentDAO getAttachmentDAO() {
        return attachmentDAO;
    }
}
