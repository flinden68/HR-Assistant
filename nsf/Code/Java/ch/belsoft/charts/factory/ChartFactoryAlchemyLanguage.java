package ch.belsoft.charts.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import ch.belsoft.charts.model.Chart;
import ch.belsoft.charts.model.DataSet;
import ch.belsoft.tools.Logging;
import ch.belsoft.tools.Util;

import com.ibm.bluemix.services.watson.alchemylanguage.interfaces.AlchemyLanguageAnalyzable;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageChartCategory;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageExtractTypes;
import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageKeyword;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneAnalyzerResult;
import com.ibm.bluemix.services.watson.toneanalyzer.model.ToneCategory;

public class ChartFactoryAlchemyLanguage extends ChartFactory<AlchemyLanguageAnalyzable>
implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private final AlchemyLanguageExtractTypes extract;
    
    public ChartFactoryAlchemyLanguage(AlchemyLanguageExtractTypes extract) {
        this.chartAlias = extract.toString();
        this.setDefaultChartType(this.chartAlias, ChartTypeSelection.BAR.toString());
        this.extract = extract;
    }
    
    public static enum ToneCategoryEnum {
        EMOTION_TONE("emotion_tone"), LANGUAGE_TONE("language_tone"), SOCIAL_TONE(
        "social_tone");
        
        private final String tone;
        
        private ToneCategoryEnum(final String tone) {
            this.tone = tone;
        }
        
        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return tone;
        }
    }
    
    protected ToneCategory getToneCategoryByTone(ToneCategoryEnum tone,
            ToneAnalyzerResult toneAnalyzerResult)
    throws NoSuchElementException {
        
        this.setDefaultChartType(this.chartAlias, ChartTypeSelection.RADAR);
        ToneCategory result = null;
        
        if (toneAnalyzerResult == null) {
            return null;
        }
        
        for (ToneCategory t : toneAnalyzerResult.getToneCategories()) {
            if (t.getCategory_id().equals(tone.toString())) {
                result = t;
                return result;
            }
        }
        
        throw new NoSuchElementException(tone.toString()
                + " not found in the tone analyzer result");
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
        this.setDefaultChartType(this.chartAlias, ChartTypeSelection.BAR);
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
                }
                this.fillChartDataSets(chart, chartCategories, alchemyLanguageAnalyzable.getName(),
                        extract.toString());
            }
        } catch (Exception e) {
            Logging.logError(e);
        }
        return chart;
    }
    
    @Override
    public Chart createChart(List<AlchemyLanguageAnalyzable> alchemyLanguageAnalyzableList) {
        Chart chart = new Chart(this.chartAlias);
        List<AlchemyLanguageChartCategory> chartCategories = new ArrayList<AlchemyLanguageChartCategory>();
        try {
            for (AlchemyLanguageAnalyzable alchemyLanguageAnalyzable : alchemyLanguageAnalyzableList) {
                if(extract == AlchemyLanguageExtractTypes.KEYWORDS){
                    for(AlchemyLanguageKeyword keyword : alchemyLanguageAnalyzable.getAlchemyLanguageResult().getKeywords()){
                        AlchemyLanguageChartCategory chartCategory = new AlchemyLanguageChartCategory();
                        chartCategory.setName(keyword.getText());
                        chartCategory.setScore(keyword.getRelevance());
                        
                        chartCategories.add(chartCategory);
                    }
                }
                this.fillChartDataSets(chart, chartCategories, alchemyLanguageAnalyzable.getName(),
                        extract.toString());
            }
        } catch (Exception e) {
            Logging.logError(e);
        }
        return chart;
    }
}
