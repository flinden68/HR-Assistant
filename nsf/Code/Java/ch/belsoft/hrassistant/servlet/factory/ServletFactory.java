package ch.belsoft.hrassistant.servlet.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.ibm.designer.runtime.domino.adapter.ComponentModule;
import com.ibm.designer.runtime.domino.adapter.IServletFactory;
import com.ibm.designer.runtime.domino.adapter.ServletMatch;

public class ServletFactory implements IServletFactory {
    private static final Map<String, String> servletClasses = new HashMap<String, String>();
    private static final Map<String, String> servletNames = new HashMap<String, String>();
    private ComponentModule module;
    
    
    public void init(ComponentModule module) {
        servletClasses.put("attachment", "ch.belsoft.hrassistant.servlet.AttachmentServlet");
        servletNames.put("attachment", "Attachment servlet");
        
        servletClasses.put("image", "ch.belsoft.hrassistant.servlet.ImageServlet");
        servletNames.put("image", "Image servlet");
        
        this.module = module;
    }
    
    /**
     * The ServletMatch matches the path to the correctly identified servlet;
     * by the routed key.
     */
    public ServletMatch getServletMatch(String contextPath, String path)
    throws ServletException {
        try {
            String servletPath = "";
            // iterate the servletNames map
            Iterator<Map.Entry<String, String>> it = servletNames.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> pairs = it.next();
                if (path.contains("/" + pairs.getKey())) {
                    String pathInfo = path;
                    return new ServletMatch(getWidgetServlet(pairs.getKey()),
                            servletPath, pathInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Servlet getWidgetServlet(String key) throws ServletException {
        return module.createServlet(servletClasses.get(key), servletNames
                .get(key), null);
    }
    
}
