package ch.belsoft.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.el.VariableResolver;

import lotus.domino.Database;
import lotus.domino.Name;
import lotus.domino.NotesException;
import lotus.domino.Session;

import com.ibm.domino.xsp.module.nsf.NotesContext;
import com.ibm.xsp.application.DesignerApplicationEx;
import com.ibm.xsp.component.UIViewRootEx;
import com.ibm.xsp.designer.context.XSPContext;
import com.ibm.xsp.designer.context.XSPUserAgent;

@SuppressWarnings("unchecked")
public class XPagesUtil {
    
    public static Object getBindingValue(String ref) {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        return application.createValueBinding(ref).getValue(context);
    }
    
    public static ValueBinding getValueBinding() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        String value = "#document1.subject";
        ValueBinding binding = application.createValueBinding(value);
        
        return binding;
    }
    
    /**
     * The method creates a {@link javax.faces.el.ValueBinding} from the
     * specified value binding expression and sets a new value for it.<br>
     * <br>
     * If the expression references a managed bean and the bean has not been
     * created yet, it gets created by the JSF runtime.
     * 
     * @param ref
     *            value binding expression, e.g. #{Bean1.property}
     * @param newObject
     *            new value for the ValueBinding throws
     *            javax.faces.el.ReferenceSyntaxException if the specified
     *            <code>ref</code> has invalid syntax
     */
    public static void setBindingValue(String ref, Object newObject) {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ValueBinding binding = application.createValueBinding(ref);
        binding.setValue(context, newObject);
    }
    
    /**
     * The method returns the value of a global JavaScript variable.
     * 
     * @param varName
     *            variable name
     * @return value
     * @throws javax.faces.el.EvaluationException
     *             if an exception is thrown while resolving the variable name
     */
    public static Object getVariableValue(String varName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getVariableResolver().resolveVariable(
                context, varName);
    }
    
 
    
    /**
     * Finds an UIComponent by its component identifier in the current component
     * tree.
     * 
     * @param compId
     *            the component identifier to search for
     * @return found UIComponent or null
     * 
     * @throws NullPointerException
     *             if <code>compId</code> is null
     */
    public static UIComponent findComponent(String compId) {
        return findComponent(FacesContext.getCurrentInstance().getViewRoot(),
                compId);
    }
    
    /**
     * Finds an UIComponent by its component identifier in the component tree
     * below the specified <code>topComponent</code> top component.
     * 
     * @param topComponent
     *            first component to be checked
     * @param compId
     *            the component identifier to search for
     * @return found UIComponent or null
     * 
     * @throws NullPointerException
     *             if <code>compId</code> is null
     */
    public static UIComponent findComponent(UIComponent topComponent,
            String compId) {
        if (compId == null) {
            throw new NullPointerException(
            "Component identifier cannot be null");
        }
        
        if (compId.equals(topComponent.getId())) {
            return topComponent;
        }
        
        if (topComponent.getChildCount() > 0) {
            List<UIComponent> childComponents = topComponent.getChildren();
            
            for (UIComponent currChildComponent : childComponents) {
                UIComponent foundComponent = findComponent(currChildComponent,
                        compId);
                if (foundComponent != null) {
                    return foundComponent;
                }
            }
        }
        return null;
    }
    
    private static Session _signerSess;
    
    public static DesignerApplicationEx getApplication() {
        return (DesignerApplicationEx) getFacesContext().getApplication();
    }
    
    public static Map<String, Object> getApplicationScope() {
        return getServletContext().getApplicationMap();
    }
    
    public static Map<String, Object> getCompositeData() {
        return (Map<String, Object>) getVariableResolver().resolveVariable(
                getFacesContext(), "compositeData");
    }
    
    public static Database getCurrentDatabase() {
        return (Database) getVariableResolver().resolveVariable(
                getFacesContext(), "database");
    }
    
    public static Session getCurrentSession() {
        return (Session) getVariableResolver().resolveVariable(
                getFacesContext(), "session");
    }
    
    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    
    public static Map<String, Object> getRequestScope() {
        return getServletContext().getRequestMap();
    }
    
    public static ExternalContext getServletContext() {
        return getFacesContext().getExternalContext();
    }
    
    public static Map<String, Object> getSessionScope() {
        return getServletContext().getSessionMap();
    }
    
    private static VariableResolver getVariableResolver() {
        return getApplication().getVariableResolver();
    }
    
    public static UIViewRootEx getViewRoot() {
        return (UIViewRootEx) getFacesContext().getViewRoot();
    }
    
    public static Map<String, Object> getViewScope() {
        return getViewRoot().getViewMap();
    }
    
    public static XSPContext getXSPContext() {
        return XSPContext.getXSPContext(getFacesContext());
    }
    
    public static String getCurrentUrl() {
        return getXSPContext().getUrl().toString();
    }
    
    /**
     * @return the current xpage name, without .xsp ending
     */
    public static String getCurrentPageName() {
        String pageName = getViewRoot().getPageName();
        pageName = pageName.replace("/", "");
        pageName = pageName.replace(".xsp", "");
        return pageName;
    }
    
    public static String getHostName() {
        return getXSPContext().getUrl().getHost();
    }
    
    public static XSPUserAgent getXSPUserAgent() {
        return getXSPContext().getUserAgent();
    }
    
    public static Object resolveVariable(String variable) {
        return FacesContext.getCurrentInstance().getApplication()
        .getVariableResolver().resolveVariable(
                FacesContext.getCurrentInstance(), variable);
    }
    
    public static String getCommonUserName(String userName) {
        String sResult = "";
        try {
            Name nam = getCurrentSession().createName(userName);
            sResult = nam.getCommon();
            nam.recycle();
        } catch (Exception e) {
            Util.logError(e);
        }
        
        return sResult;
        
    }
    
    /**
     * @since 3.0.0 Added by Paul Withers to get current session as signer
     */
    public static Session getSessionAsSigner() {
        if (_signerSess == null) {
            _signerSess = NotesContext.getCurrent().getSessionAsSigner();
        } else {
            try {
                @SuppressWarnings("unused")
                boolean pointless = _signerSess.isOnServer();
            } catch (NotesException recycleSucks) {
                // our database object was recycled so we'll need to get it
                // again
                try {
                    _signerSess = NotesContext.getCurrent()
                    .getSessionAsSigner();
                } catch (Exception e) {
                    
                }
            }
        }
        return _signerSess;
    }
    
    /*
     * public static DateTime getDominoDate(Date dt) { DateTime result = null;
     * try { result = JSFUtil.getCurrentSession().createDateTime(dt); } catch
     * (Exception e) { Util.logError(e); }
     * 
     * return result; }
     */
    
    /**
     * @return the Current User Name in Canonical Format
     */
    public static String getUserNameCanonical() {
        String sResult = "";
        
        try {
            Session s = getCurrentSession();
            Name nam = s.createName(s.getEffectiveUserName());
            sResult = nam.getCanonical();
            nam.recycle();
        } catch (Exception e) {
            Util.logError(e);
        }
        return sResult;
    }
    
    /**
     * @param UserName
     * @return the Given User Name in Canonical Format
     */
    public static String getUserNameCanonical(String userName) {
        String sResult = "";
        
        try {
            Session s = getCurrentSession();
            Name nam = s.createName(userName);
            sResult = nam.getCanonical();
            nam.recycle();
        } catch (Exception e) {
            Util.logError(e);
        }
        return sResult;
    }
    
    /**
     * @return the Current User Name in Abbreviated Format
     */
    public static String getUserNameAbbreviated() {
        String sResult = "";
        
        try {
            Session s = getCurrentSession();
            Name nam = s.createName(s.getEffectiveUserName());
            sResult = nam.getAbbreviated();
            nam.recycle();
        } catch (Exception e) {
            Util.logError(e);
        }
        return sResult;
    }
    
    public static String getUserNameCommon() {
        String sResult = "";
        
        try {
            Session s = getCurrentSession();
            Name nam = s.createName(s.getEffectiveUserName());
            sResult = nam.getCommon();
            nam.recycle();
        } catch (Exception e) {
            Util.logError(e);
        }
        return sResult;
    }
    
    /**
     * @param UserName
     * @return the Given User Name in Abbreviaated Format
     */
    public static String getUserNameAbbreviated(String userName) {
        String sResult = "";
        
        try {
            Session s = getCurrentSession();
            Name nam = s.createName(userName);
            sResult = nam.getAbbreviated();
            nam.recycle();
        } catch (Exception e) {
            Util.logError(e);
        }
        return sResult;
    }
    
    private static List<String> getUserRoles() {
        List<String> lstResult = new ArrayList<String>();
        
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            XSPContext context = XSPContext.getXSPContext(facesContext);
            lstResult = context.getUser().getRoles();
            
        } catch (Exception e) {
            Util.logError(e);
        }
        
        return lstResult;
    }
    
    public static boolean isRole(String role) {
        boolean bResult = false;
        
        try {
            List<String> roles = getUserRoles();
            if (roles.contains(role)) {
                bResult = true;
            } else {
                bResult = false;
            }
        } catch (Exception e) {
            Util.logError(e);
        }
        
        return bResult;
    }
    
    public static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("[?]");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }
    
    public static String getQueryString(String paramName) {
        String result = getXSPContext().getUrlParameter(paramName);
        if (result == null) {
            result = "";
        }
        
        return result;
    }
    
    public static void showErrorMessage(String errorMessage) {
        showErrorMessage(errorMessage, "");
    }
    
    public static void showErrorMessage(String errorMessage, String componentId) {
        try {
            FacesContext facesContext = getFacesContext();
            UIComponent component = null;
            if (!componentId.equals("")) {
                component = findComponent(componentId);
                facesContext.addMessage(component.getClientId(facesContext),
                        new FacesMessage(errorMessage));
            } else {
                facesContext.addMessage("", new FacesMessage(errorMessage));
            }
            
        } catch (Exception e) {
            Util.logError(e);
        }
    }
    
    public static void redirect(String url) {
        try {
            getServletContext().redirect(url);
        } catch (Exception e) {
            Util.logError(e);
        }
    }
    
    public static String getUniqueID(){
        return UUID.randomUUID().toString();
    }
    
    /*
     * public static ControllerConfiguration getControllerConfiguration() {
     * ControllerConfiguration result = null;
     * 
     * try {
     * 
     * result = (ControllerConfiguration) JSFUtil.getApplicationScope()
     * .get(MANAGEDBEAN_CONFIGURATION); if (result == null) { result = new
     * ControllerConfiguration();
     * JSFUtil.getApplicationScope().put(MANAGEDBEAN_CONFIGURATION, new
     * ControllerConfiguration()); } //
     * System.out.println("inside getControllerConfiguration:" + // result);
     * 
     * } catch (Exception e) { Util.logError(e); } return result; }
     */
}