package com.tips.batch.config;

import java.util.List;

import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.tips.batch.bean.listener.ListenerFlatFileExt;
import com.tips.batch.bean.processor.ProcessorImpl;
import com.tips.batch.bean.reader.ReaderDummyImpl;
import com.tips.batch.bean.reader.ReaderFlatFileExt;
import com.tips.batch.bean.reader.ReaderRestApiImpl;
import com.tips.batch.bean.writer.WriterDBImpl;
import com.tips.batch.bean.writer.WriterDTOImpl;
import com.tips.batch.model.BizVO;
import com.tips.batch.model.ProcessorReceiveDTO;
import com.tips.batch.model.ReaderReturnDTO;
import com.tips.batch.model.entity.MeasureInfoRealStage;

/**
 * 
 * @author ashraf
 */
@Configuration
@EnableBatchProcessing
@Import({QuartzConfiguration.class})
public class BatchConfiguration
{
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // VO --------------------------------------------------------------------
    @Bean
    public BizVO bizVO()
    {
        return new BizVO();
    }
    
    // Reader ----------------------------------------------------------------
    @Bean
    public ReaderRestApiImpl readerRestApiImpl()
    {
        return new ReaderRestApiImpl();
    }
    
    @Bean
    public ReaderFlatFileExt readerFlatFileExt()
    {
        return new ReaderFlatFileExt();
    }

    @Bean
    public ReaderDummyImpl readerDummyImpl()
    {
        return new ReaderDummyImpl();
    }
    
    // Processor -------------------------------------------------------------
    @Bean
    public ProcessorImpl processorImpl()
    {
        return new ProcessorImpl();
    }
    
    // Writer--- -------------------------------------------------------------    
    @Bean
    public WriterDBImpl writerDBImpl()
    {
        return new WriterDBImpl();
    }
   
    @Bean
    public WriterDTOImpl writerDTOImpl()
    {
        return new WriterDTOImpl();
    }

    // Listener --------------------------------------------------------------
    @Bean
    public ListenerFlatFileExt listenerFlatFileExt()
    {
        return new ListenerFlatFileExt();
    }
    
    // RunIncreamenter -------------------------------------------------------
    @Bean
    public RunIdIncrementer runIdIncrementer()
    {
    	return new RunIdIncrementer();
    }
    
    // Job Step Configuration ------------------------------------------------
    // Configure job step
    @Bean
    public Job jobBean()
    {
        return jobBuilderFactory.get("ETLJob")                       // Share Quartz Configuration
                                .incrementer(runIdIncrementer   ())  // Automatically parameter increase
                              //.listener   (listenerFlatFileExt())  // Must be Bean
                                .flow       (stepBean())
                                .end()
                                .build();
    }

    @Bean
    public Step stepBean()
    {
        // The job is thus scheduled to run every 2 minute. In fact it should
        // be successful on the first attempt, so the second and subsequent
        // attempts should through a JobInstanceAlreadyCompleteException, so you have to set allowStartIfComplete to true
        return stepBuilderFactory.get("ETLStep")
                                 .allowStartIfComplete(true)                                      // allows step re-runnig although there is job that success
                               //.<     ReaderReturnDTO,       ProcessorReceiveDTO>  chunk(1000)  // First:Reader return type. Second:Writer receive type
                                 .<List<ReaderReturnDTO>, List<ProcessorReceiveDTO>> chunk(1)     // First:Reader return type. Second:Writer receive type
                               //.reader   (readerFlatFileExt())
                               //.reader   (readerDummyImpl  ())
                                 .reader   (readerRestApiImpl())
                                 .processor(processorImpl    ())
                               //.writer   (writerDTOImpl    ())
                                 .writer   (writerDBImpl     ())
                                 .build();
    }
}
