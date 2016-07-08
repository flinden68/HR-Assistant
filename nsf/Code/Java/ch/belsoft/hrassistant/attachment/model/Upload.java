package ch.belsoft.hrassistant.attachment.model;

import java.io.Serializable;

import ch.belsoft.hrassistant.model.DataItem;

import com.ibm.xsp.component.UIFileuploadEx.UploadedFile;

public class Upload extends DataItem  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private final DataType dataType = DataType.UPLOAD;
    private UploadedFile uploadFile;
    
    public Upload(){
        
    }
    
    public DataType getDataType() {
        return dataType;
    }
    
    public void setUploadFile(UploadedFile uploadFile) {
        this.uploadFile = uploadFile;
    }
    
    public UploadedFile getUploadFile() {
        return uploadFile;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("fileName: " + getUploadFile().getUploadedFile().getClientFileName());
        sb.append("ContentType: " + getUploadFile().getUploadedFile().getContentType() + ",");
        sb.append("Length: " + getUploadFile().getUploadedFile().getContentLength());
        
        return sb.toString();
    }
}
