package ch.belsoft.charts.factory;

import java.io.Serializable;

import com.ibm.bluemix.services.watson.alchemylanguage.model.AlchemyLanguageExtractTypes;

public class ChartFactoryProducer implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public ChartFactoryToneAnalyzer getChartFactoryToneAnalyzerEmotionTone() {
        return new ChartFactoryToneAnalyzer(
                ChartFactoryToneAnalyzer.ToneCategoryEnum.EMOTION_TONE);
    }
    
    public ChartFactoryToneAnalyzer getChartFactoryToneAnalyzerLanguageTone() {
        return new ChartFactoryToneAnalyzer(
                ChartFactoryToneAnalyzer.ToneCategoryEnum.LANGUAGE_TONE);
    }
    
    public ChartFactoryToneAnalyzer getChartFactoryToneAnalyzerSocialTone() {
        return new ChartFactoryToneAnalyzer(
                ChartFactoryToneAnalyzer.ToneCategoryEnum.SOCIAL_TONE);
    }
    
    public ChartFactoryJobApplications getChartFactoryApplicationsByJob() {
        return new ChartFactoryJobApplications(
                ChartFactoryJobApplications.ChartAlias.JOB_APPLICATIONS_BY_JOB);
    }
    
    public ChartFactoryAlchemyLanguage getChartFactoryAlchemyLanguageKeywords() {
        return new ChartFactoryAlchemyLanguage(
                AlchemyLanguageExtractTypes.KEYWORDS);
    }
    
    public ChartFactoryAlchemyLanguage getChartFactoryAlchemyLanguageTaxanomies() {
        return new ChartFactoryAlchemyLanguage(
                AlchemyLanguageExtractTypes.TAXONOMY);
    }
    
}
