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
import com.tips.batch.model.entity.MeasureInfoRealStage;
import com.tips.batch.model.vo.BizVO;

public class ListenerFlatFile extends JobExecutionListenerSupport
{
    private static final Logger log       = LoggerFactory.getLogger(ListenerFlatFile.class);
    private static final String HEADER    = "stock,open,close,low,high";
    private static final String LINE_DILM = ",";
   
    @Autowired
    private BizVO bizVO;

    @Override
    public void afterJob(JobExecution jobExecution)
    {
    	log.info("[JobListener] afterJob()");
    	
        if (jobExecution.getStatus() == BatchStatus.COMPLETED)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date             date             = new Date();
            
            String createTime = simpleDateFormat.format(date);
            
          //Path path = Paths.get("prices_" + createTime + ".csv");
            Path path = Paths.get("prices" + ".csv");
            
            try (BufferedWriter fileWriter = Files.newBufferedWriter(path))
            {
                fileWriter.write(HEADER);
                
                fileWriter.newLine();
                
                log.info("[JobListener] afterJob() PricesStore : " + bizVO.values().toString());
                
                for (FileWriteDTO pd : bizVO.values())
                {
                    fileWriter.write(new StringBuilder().append(pd.getStock())
                              .append(LINE_DILM).append(pd.getOpen())
                              .append(LINE_DILM).append(pd.getClose())
                              .append(LINE_DILM).append(pd.getLow())
                              .append(LINE_DILM).append(pd.getHigh()).toString());
                    
                    fileWriter.newLine();
                }
            }
            catch (Exception e)
            {
                log.error("[JobListener] afterJob() Exception : " + path.getFileName());
            }
        }
    }
}
