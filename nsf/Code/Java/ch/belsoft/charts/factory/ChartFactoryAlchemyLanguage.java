package ch.belsoft.charts.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.charts.model.DataSet;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.Util;

import com.ibm.bluemix.services.watson.alchemylanguage.interfaces.AlchemyLanguageAnalyzable;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageChartCategory;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageExtractTypes;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageKeyword;

public class ChartFactoryAlchemyLanguage extends ChartFactory<AlchemyLanguageAnalyzable>
implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private final AlchemyLanguageExtractTypes extract;
    
    public ChartFactoryAlchemyLanguage(AlchemyLanguageExtractTypes extract, String alias) {
        this.chartAlias = extract.toString()+"_"+alias;
        this.setDefaultChartType(this.chartAlias, ChartTypeSelection.BAR.toString());
        this.extract = extract;
    }
    
    protected void fillChartDataSets(Chart chart, List<AlchemyLanguageChartCategory> chartCategories,
            String dataSetName, String dataSetLabelName) {
        
        try {
            
            DataSet dataSet = new DataSet();
            dataSet.setLabel(dataSetLabelName);
            dataSet.addBackgroundColor(Util.getRgbaColorOfString(dataSetName,
                    opacity));
            for (AlchemyLanguageChartCategory category : chartCategories) {
                chart.addLabel(category.getName());
                dataSet.addData(category.getScore());
                //	dataSet.setLabel(dataSetLabelName);
            }
            chart.addDataSet(dataSet);
            
        } catch (Exception e) {
            Logging.logError(e);
        }
    }
    
    @Override
    public Chart createChart(AlchemyLanguageAnalyzable alchemyLanguageAnalyzable) {
        Chart chart = new Chart(this.chartAlias);
        this.setDefaultChartType(this.chartAlias, ChartTypeSelection.BAR.toString());
        List<AlchemyLanguageChartCategory> chartCategories = new ArrayList<AlchemyLanguageChartCategory>();
        try {
            if(alchemyLanguageAnalyzable.getAlchemyLanguageResult() != null){
                if(extract == AlchemyLanguageExtractTypes.KEYWORDS){
                    for(AlchemyLanguageKeyword keyword : alchemyLanguageAnalyzable.getAlchemyLanguageResult().getKeywords()){
                        AlchemyLanguageChartCategory chartCategory = new AlchemyLanguageChartCategory();
                        chartCategory.setName(keyword.getText());
                        chartCategory.setScore(keyword.getRelevance());
                        
                        chartCategories.add(chartCategory);
                    }
                    this.fillChartDataSets(chart, chartCategories, alchemyLanguageAnalyzable.getName(),
                            alchemyLanguageAnalyzable.getName());
                }
                
            }
        } catch (Exception e) {
            Logging.logError(e);
        }
        return chart;
    }
    
    @Override
    public Chart createChart(List<AlchemyLanguageAnalyzable> alchemyLanguageAnalyzableList) {
        Chart chart = new Chart(this.chartAlias);
        this.setDefaultChartType(this.chartAlias, ChartTypeSelection.BAR.toString());
        
        List<AlchemyLanguageChartCategory> chartCategories = new ArrayList<AlchemyLanguageChartCategory>();
        try {
            for (AlchemyLanguageAnalyzable alchemyLanguageAnalyzable : alchemyLanguageAnalyzableList) {
                if(alchemyLanguageAnalyzable.getAlchemyLanguageResult() != null){
                    if(extract == AlchemyLanguageExtractTypes.KEYWORDS){
                        for(AlchemyLanguageKeyword keyword : alchemyLanguageAnalyzable.getAlchemyLanguageResult().getKeywords()){
                            AlchemyLanguageChartCategory chartCategory = new AlchemyLanguageChartCategory();
                            chartCategory.setName(keyword.getText());
                            chartCategory.setScore(keyword.getRelevance());
                            
                            chartCategories.add(chartCategory);
                        }
                        this.fillChartDataSets(chart, chartCategories, alchemyLanguageAnalyzable.getName(),
                                alchemyLanguageAnalyzable.getName());
                    }
                    
                }
            }
            
        } catch (Exception e) {
            Logging.logError(e);
        }
        return chart;
    }
}
