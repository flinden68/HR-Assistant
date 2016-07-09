package ch.belsoft.hrassistant.servlet.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServletUtils {
    public static List<String> findRouteParameters(String pathInfo){
        System.out.println("pathInfo: " + pathInfo);
        List<String> param = Arrays.asList(pathInfo.split("/(.*?)"));
        List<String> routeParm = new ArrayList<String>();
        if(param.size() > 3) {
            // /nsf/xsp/servletname is the base, so the fourth is the first routeParm
            for( int i=3; i<param.size(); i++ ) {
                routeParm.add(param.get(i));
            }
        }
        
        return routeParm;
    }
}
