package ch.belsoft.hrassistant.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.belsoft.hrassistant.servlet.factory.ServletUtils;

import com.ibm.xsp.webapp.DesignerFacesServlet;

public class BaseServlet extends DesignerFacesServlet {
    
    private static final long serialVersionUID = 1L;
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;
    private FacesContext facesContext = null;
    private ServletOutputStream out = null;
    private String requestMethod = null;
    private Map<String, String[]> requestParams;
    private List<String> routeParam;
    
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        request = (HttpServletRequest)servletRequest;
        response = (HttpServletResponse)servletResponse;
        facesContext = this.getFacesContext(request, response);
        out = response.getOutputStream();
        
        requestMethod = request.getMethod();
        requestParams = request.getParameterMap();
        
        routeParam = ServletUtils.findRouteParameters(request.getPathInfo());
    }
    
    public HttpServletRequest getRequest() {
        return request;
    }
    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public HttpServletResponse getResponse() {
        return response;
    }
    
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    
    public FacesContext getFacesContext() {
        return facesContext;
    }
    
    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }
    
    public ServletOutputStream getOut() {
        return out;
    }
    
    public void setOut(ServletOutputStream out) {
        this.out = out;
    }
    
    public String getRequestMethod() {
        return requestMethod;
    }
    
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
    
    public Map<String, String[]> getRequestParams() {
        return requestParams;
    }
    
    public void setRequestParams(Map<String, String[]> requestParams) {
        this.requestParams = requestParams;
    }
    
    public void setRouteParam(List<String> routeParam) {
        this.routeParam = routeParam;
    }
    
    public List<String> getRouteParam() {
        return routeParam;
    }
    
}
