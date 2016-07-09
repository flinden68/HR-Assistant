package ch.belsoft.hrassistant.attachment.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;

import ch.belsoft.hrassistant.attachment.dao.AttachmentDAO;
import ch.belsoft.hrassistant.attachment.model.Attachment;
import ch.belsoft.hrassistant.attachment.model.AttachmentHolder;
import ch.belsoft.hrassistant.attachment.model.Upload;
import ch.belsoft.hrassistant.controller.ControllerBase;
import ch.belsoft.hrassistant.controller.IGuiController;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.XPagesUtil;

import com.ibm.xsp.component.UIFileuploadEx.UploadedFile;
import com.ibm.xsp.http.IUploadedFile;

public class AttachmentController extends ControllerBase implements IGuiController<Attachment>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private AttachmentDAO attachmentDAO;
    private static final String BEAN_NAME = "attachmentController";
    
    public AttachmentController(){
        
    }
    public static AttachmentController get() {
        return (AttachmentController) XPagesUtil.resolveVariable(BEAN_NAME);
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
    
    
    
    public byte[] convertUploadFileToBytes(UploadedFile uploadFile){
        
        IUploadedFile iUploadedFile = uploadFile.getUploadedFile();
        File serverFile = iUploadedFile.getServerFile();
        byte[] bFile = new byte[(int) serverFile.length()];
        
        try {
            FileInputStream fileInputStream = new FileInputStream(serverFile);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            Logging.logError(e);
        } catch (Exception e) {
            Logging.logError(e);
        }
        return bFile;
        
    }
    
    public InputStream convertAttachmentInputstream(Attachment attachment){
        return new ByteArrayInputStream(decodeBase64Attachment(attachment));
    }
    
    public byte[] decodeBase64Attachment(Attachment attachment){
        return Base64.decodeBase64(attachment.getData());
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
