    package com.tips.batch.config;

import java.util.List;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.tips.batch.model.ProcessorReceiveDTO;
import com.tips.batch.model.ReaderReturnDTO;
import com.tips.batch.model.entity.MeasureInfoRealStage;
import com.tips.batch.model.vo.BizVO;
import com.tips.batch.model.vo.MeasureInfoVO;
import com.tips.batch.step.listener.ListenerDB;
import com.tips.batch.step.listener.ListenerFlatFile;
import com.tips.batch.step.processor.Processor;
import com.tips.batch.step.reader.ReaderDummy;
import com.tips.batch.step.reader.ReaderFlatFile;
import com.tips.batch.step.reader.ReaderRestApi;
import com.tips.batch.step.writer.WriterDB;
import com.tips.batch.step.writer.WriterDTO;

@Configuration
@EnableBatchProcessing
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

    @Bean
    public MeasureInfoVO measureInfoVO()
    {
        return new MeasureInfoVO();
    }
    
    // Reader ----------------------------------------------------------------
    @Bean
    @StepScope
    public ReaderRestApi readerRestApiImpl()
    {
        return new ReaderRestApi();
    }
    
    @Bean
    public ReaderFlatFile readerFlatFileExt()
    {
        return new ReaderFlatFile();
    }

    @Bean
    public ReaderDummy readerDummyImpl()
    {
        return new ReaderDummy();
    }
    
    // Processor -------------------------------------------------------------
    @Bean
    public Processor processorImpl()
    {
        return new Processor();
    }
    
    // Writer--- -------------------------------------------------------------    
    @Bean
    public WriterDB writerDBImpl()
    {
        return new WriterDB();
    }
   
    @Bean
    public WriterDTO writerDTOImpl()
    {
        return new WriterDTO();
    }

    // Listener --------------------------------------------------------------
    @Bean
    public ListenerFlatFile listenerFlatFileExt()
    {
        return new ListenerFlatFile();
    }

    @Bean
    public ListenerDB listenerDBExt()
    {
        return new ListenerDB();
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
                                .listener   (listenerDBExt      ())
                                .flow       (stepBean           ())
                                .end()
                                .build();
    }

    @Bean
    public Step stepBean()
    {
        return stepBuilderFactory.get("ETLStep")
                                 .allowStartIfComplete(true)                                      // allows step re-runnig although there is job that success
                               //.<     ReaderReturnDTO,       ProcessorReceiveDTO>  chunk(1000)  // First:Reader return type. Second:Writer receive type
                                 .<List<ReaderReturnDTO>, List<ProcessorReceiveDTO>> chunk(1000)     // First:Reader return type. Second:Writer receive type
                               //.reader   (readerFlatFileExt())
                               //.reader   (readerDummyImpl  ())
                                 .reader   (readerRestApiImpl())
                                 .processor(processorImpl    ())
                               //.writer   (writerDTOImpl    ())
                                 .writer   (writerDBImpl     ())
                                 .build();
    }
}
