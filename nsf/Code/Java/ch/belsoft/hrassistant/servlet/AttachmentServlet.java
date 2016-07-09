package ch.belsoft.hrassistant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ch.belsoft.hrassistant.attachment.controller.AttachmentController;
import ch.belsoft.hrassistant.attachment.model.Attachment;
import ch.belsoft.hrassistant.attachment.model.AttachmentHolder;

public class AttachmentServlet extends BaseServlet {
    
    
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        super.service(servletRequest, servletResponse);
        try {
            
            AttachmentHolder attachmentHolder = null;
            Attachment attachment = null;
            
            System.out.println("attachmentController="+AttachmentController.get());
            System.out.println("getRouteParam()="+getRouteParam());
            if(getRouteParam().size()==2){
                //route param 1 = attachmentId
                //route param 2 = attachment name
                if( "GET".equals(getRequestMethod())) {
                    //get the attachment from the
                    String attachmentId = getRouteParam().get(0);
                    System.out.println("attachmentId = "+ attachmentId);
                    attachmentHolder = AttachmentController.get().findAttachment(attachmentId);
                    System.out.println("attachmentHolder="+attachmentHolder);
                }
            }
            
            if(attachmentHolder != null){
                if(attachmentHolder.getAttachments().containsKey(getRouteParam().get(1))){
                    attachment = attachmentHolder.getAttachments().get(getRouteParam().get(1));
                    if(attachment != null){
                        //we have the attachment
                        getResponse().reset();
                        
                        getResponse().setHeader("Cache-Control", "no-cache");
                        getResponse().setHeader("Access-Control-Allow-Origin", "*");
                        getResponse().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                        getResponse().setContentLength((int) attachment.getLength());
                        
                        getResponse().setContentType(attachment.getContent_type());
                        getResponse().setHeader("Content-disposition", "inline; filename=\""+attachment.getName()+"\"");
                        
                        getOut().write(AttachmentController.get().decodeBase64Attachment(attachment));
                        
                        
                    }
                }
                
            }else{
                getOut().println("the url is not correct (/attachment/1234444/test.pdf)");
            }
            
        } catch (Exception e) {
            getOut().println(e.toString());
            
        } finally {
            getOut().close();
            
            // It shouldn't be null if things are going well, but a check never hurt
            if(getFacesContext() != null) {
                //complete the response and release the handle on the FacesContext instance
                getFacesContext().responseComplete();
                getFacesContext().release();
            }
        }
    }
    
}
