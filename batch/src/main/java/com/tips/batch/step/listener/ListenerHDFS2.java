package com.tips.batch.step.listener;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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
import com.tips.batch.model.vo.MeasureInfoRealListVO;

public class ListenerHDFS2 extends JobExecutionListenerSupport
{
    private static final Logger log       = LoggerFactory.getLogger(ListenerHDFS2.class);
    private static final String HEADER    = "id,data_time,sido_name,station_name,mang_name,so2_value,co_value,o3_value,no2_value,pm10_value,pm10_value_24h,pm25_value,pm25_value_24h,khai_value,so2_grade,co_grade,o3_grade,no2_grade,pm10_grade,pm10_grade_1h,pm25_grade,pm25_grade_1h,khai_grade";
    private static final String LINE_DILM = ",";
   
    @Autowired
    private MeasureInfoRealListVO measureInfoRealListVO;

    @Override
    public void afterJob(JobExecution jobExecution)
    {
        log.info("[JobListener] afterJob() start : {}", jobExecution.getStatus());
        
        if (jobExecution.getStatus() == BatchStatus.COMPLETED)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date             date             = new Date();
            
            String createTime = simpleDateFormat.format(date);
            
          //Path path = Paths.get("measure_info_real_" + createTime + ".csv");
            Path path = Paths.get("measure_info_real" + ".csv");
            
            try 
            {
                FileOutputStream fileOutputStream = new FileOutputStream(path.toString());
                
                OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
                
                BufferedWriter fileWriter = new BufferedWriter(OutputStreamWriter);

                //BufferedWriter fileWriter = new BufferedWriter(Files.newBufferedWriter(path));
                
                fileWriter.write(HEADER);
                
                fileWriter.newLine();
                
                log.info("[JobListener] afterJob() measureInfoRealListVO.size() : " + measureInfoRealListVO.size());
                
                for (MeasureInfoReal record : measureInfoRealListVO.get())
                {
                    fileWriter.write(new StringBuilder().append(record.getColumnA1()).append(LINE_DILM)
                                                        .append(record.getColumnA3()).append(LINE_DILM)
                                                        .append(record.getColumnA5()).append(LINE_DILM)
                                                        .append(record.getColumnA6()).append(LINE_DILM)
                                                        .append(record.getColumnA7()).append(LINE_DILM)
                                                        .append(record.getColumnA8()).append(LINE_DILM)
                                                        .append(record.getColumnA9()).append(LINE_DILM)
                                                        .append(record.getColumnB1()).append(LINE_DILM)
                                                        .append(record.getColumnB2()).append(LINE_DILM)
                                                        .append(record.getColumnB3()).append(LINE_DILM)
                                                        .append(record.getColumnB4()).append(LINE_DILM)
                                                        .append(record.getColumnB5()).append(LINE_DILM)
                                                        .append(record.getColumnB6()).append(LINE_DILM)
                                                        .append(record.getColumnB7()).append(LINE_DILM)
                                                        .append(record.getColumnB8()).append(LINE_DILM)
                                                        .append(record.getColumnB9()).append(LINE_DILM)
                                                        .append(record.getColumnC1()).append(LINE_DILM)
                                                        .append(record.getColumnC2()).append(LINE_DILM)
                                                        .append(record.getColumnC3()).append(LINE_DILM)
                                                        .append(record.getColumnC4()).append(LINE_DILM)
                                                        .append(record.getColumnC5()).append(LINE_DILM)
                                                        .append(record.getColumnC6()).toString());
                    
                    fileWriter.newLine();
                }
                
                fileWriter.close();
            }
            catch (Exception e)
            {
                log.error("[JobListener] afterJob() Exception : " + path.getFileName());
            }
        }
    }
}
