package ch.belsoft.charts.factory;

import java.io.Serializable;
import java.util.List;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.tools.Logging;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ChartFactory<T> implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final ObjectMapper mapper = new ObjectMapper();
    protected static final String opacity = "0.5";
    
    protected String chartAlias;
    
    public void setDefaultChartType(String alias, String defaultChartType) {
        
        ChartTypeSelection chartTypeSelection = ChartTypeSelection.getBean();
        String chartType = chartTypeSelection.getChartType(alias);
        
        if (chartType.equals("")) {
            chartTypeSelection.setChartType(alias, defaultChartType);
        }
    }
    
    public static String getChartAsJson(Chart chart) {
        String result = "";
        
        try {
            if (chart != null) {
                result = mapper.writeValueAsString(chart);
            }
            
        } catch (Exception e) {
            Logging.logError(e);
        }
        
        return result;
    }
    
    public abstract Chart createChart(T chartable);
    
    public abstract Chart createChart(List<T> chartable);
    
    public String getChartAlias() {
        return chartAlias;
    }
    
    public void setChartAlias(String chartAlias) {
        this.chartAlias = chartAlias;
    }
    
}
