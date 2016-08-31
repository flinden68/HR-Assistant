package ch.belsoft.charts.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ch.belsoft.tools.XPagesUtil;

public class ChartTypeSelection implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public static final String RADAR = "radar";
    public static final String LINE = "line";
    public static final String BAR = "bar";
    public static final String POLAR = "polarArea";
    public static final String PIE = "pie";
    public static final String DOUGHNUT = "doughnut";
    
    private HashMap<String, String> chartTypeByChartAlias = new HashMap<String, String>();
    private static final String BEAN_NAME = "chartTypeSelection";
    private List<String> chartTypeSelection;
    
    public static ChartTypeSelection getBean() {
        return (ChartTypeSelection) XPagesUtil.resolveVariable(BEAN_NAME);
    }
    
    public HashMap<String, String> getChartTypeByChartAlias() {
        return chartTypeByChartAlias;
    }
    
    public void setChartTypeByChartAlias(
            HashMap<String, String> chartTypeByChartAlias) {
        this.chartTypeByChartAlias = chartTypeByChartAlias;
    }
    
    public ChartTypeSelection() {
        chartTypeSelection = new ArrayList<String>();
        chartTypeSelection.add("Radar Chart|" + RADAR);
        chartTypeSelection.add("Line Chart|" + LINE);
        chartTypeSelection.add("Bar Chart|" + BAR);
        chartTypeSelection.add("Polar Area Chart|" + POLAR);
        chartTypeSelection.add("Pie Chart|" + PIE);
        chartTypeSelection.add("doughnut Chart|" + DOUGHNUT);
    }
    
    public List<String> getChartTypeSelection() {
        return chartTypeSelection;
    }
    
    public void setChartTypeSelection(List<String> chartTypeSelection) {
        this.chartTypeSelection = chartTypeSelection;
    }
    
    public String getChartType(String alias) {
        
        //	System.out.println("getting the chart type by alias: "+alias+" and im bean id: "+this);
        
        if (chartTypeByChartAlias.containsKey(alias)) {
            return chartTypeByChartAlias.get(alias);
        } else {
            //	System.out.println("the default chart type is not there yet: alias:"+alias);
            return "";
        }
        
    }
    
    public void setChartType(String alias, String chartType) {
        //	System.out.println("inside setChartType: "+alias+ " type: "+chartType+" bean id: "+this);
        this.chartTypeByChartAlias.put(alias, chartType);
        //	System.out.println("so now: "+ getChartType(alias));
        
    }
    
    @Override
    public String toString(){
        return "ChartTypeSelection [alias- " + chartTypeByChartAlias + ", chartTypeSelection - "+chartTypeSelection+"]";
    }
}
