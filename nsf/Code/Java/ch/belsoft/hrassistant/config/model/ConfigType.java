package ch.belsoft.hrassistant.config.model;

import java.util.Arrays;

public enum ConfigType {
    JOB_TYPE, JOB_INDUSTRY, GENDER, SALUTATION, CAREERLEVEL, MENU_ITEM, COUNTRY;
    
    public static String[] types() {
        ConfigType[] configs = values();
        String[] names = new String[configs.length];
        
        for (int i = 0; i < configs.length; i++) {
            names[i] = configs[i].name();
        }
        
        Arrays.sort(names);
        
        return names;
    }
}
