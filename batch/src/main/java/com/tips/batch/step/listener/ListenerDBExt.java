package com.tips.batch.step.listener;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.tips.batch.model.FileWriteDTO;
import com.tips.batch.model.entity.MeasureInfoReal;
import com.tips.batch.model.entity.MeasureInfoRealStage;
import com.tips.batch.model.vo.BizVO;
import com.tips.batch.model.vo.MeasureInfoVO;
import com.tips.batch.repository.MeasureInfoRealRepository;
import com.tips.batch.service.MeasureInfoRealService;


public class ListenerDBExt extends JobExecutionListenerSupport
{
    private static final Logger log  = LoggerFactory.getLogger(ListenerDBExt.class);
    
    @Autowired
    MeasureInfoRealService measureInfoRealService;
    
    @Autowired
    MeasureInfoVO measureInfoVO;

    @Override
    public void afterJob(JobExecution jobExecution)
    {
    	log.info("[JobListener] afterJob() BatchStatus.COMPLETED : " + BatchStatus.COMPLETED);
    	
        if (jobExecution.getStatus() == BatchStatus.COMPLETED)
        {
        	for (MeasureInfoReal measureInfoReal : measureInfoVO.values())
        	{
        		measureInfoRealService.save(measureInfoReal);
        	}
        }
    }
}
