package ch.belsoft.hrassistant.attachment.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.belsoft.hrassistant.attachment.model.Attachment;
import ch.belsoft.hrassistant.attachment.model.AttachmentHolder;
import ch.belsoft.hrassistant.dao.BaseDAO;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.tools.Logging;

public class AttachmentDAO extends BaseDAO implements ICrudDAO<AttachmentHolder, String>,
Serializable {
    
    private static final long serialVersionUID = 1L;
    /*private static final String DESIGN_DOC = "attachment";
    private static final String VIEW_NAME = "attachments";
    private static final String DESIGN_DOC_KEYS = "attachmentkey";
    private static final String VIEW_NAME_KEYS = "attachmentskeys";
    private static final String SEARCH_PATTERN = "name:{QUERY} OR street:{QUERY} OR streetnumber:{QUERY} OR zip:{QUERY} OR city:{QUERY} OR country:{QUERY}";
    private static final String SEARCH_INDEX = "attachment/ftsearchAttachments";
    private static final int VIEW_LIMIT = 1000;
    private static final int SEARCH_COUNT = 100;*/
    
    public void create(AttachmentHolder t) {
        connectToService();
        super.handleResponse(cloudantService.saveDocument(t), t);
    }
    
    public void delete(AttachmentHolder t) {
        connectToService();
        cloudantService.removeDocument(t);
    }
    public AttachmentHolder read(String id) {
        connectToService();
        return (AttachmentHolder) cloudantService.findDocumentByID(AttachmentHolder.class, id);
    }
    public List<AttachmentHolder> read() {
        //TODO not yet implemented
        connectToService();
        /*return (List<Attachment>) cloudantService.findAllDocumentFromView(
                Attachment.class, DESIGN_DOC, VIEW_NAME, "STRING", VIEW_LIMIT);*/
        return null;
    }
    public List<AttachmentHolder> readWithKeys(String startKey, String endKey) {
        //TODO not yet implemented
        connectToService();
        /* return (List<Attachment>) cloudantService.findAllDocumentFromViewKeys(
                Company.class, DESIGN_DOC_KEYS, VIEW_NAME_KEYS, "STRING",
                VIEW_LIMIT, startKey, endKey);*/
        return null;
    }
    public List<AttachmentHolder> search(String query) {
        //TODO not yet implemented
        List<Attachment> result = new ArrayList<Attachment>();
        try {
            /*connectToService();
            String queryFinal = SEARCH_PATTERN.replace(SEARCH_QUERYREPLACE,
                    query);
            result = (List<Attachment>) cloudantService.search(SEARCH_INDEX,
                    Attachment.class, SEARCH_COUNT, queryFinal);*/
            
        } catch (Exception e) {
            Logging.logError(e);
        }
        
        return null;
    }
    public void update(AttachmentHolder t) {
        connectToService();
        super.updateModifiedDate(t);
        super.handleResponse(cloudantService.updateDocument(t), t);
    }
    
}
