package com.tips.batch.bean.writer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.tips.batch.model.BizVO;
import com.tips.batch.model.FileWriteDTO;
import com.tips.batch.model.ProcessorReceiveDTO;
import com.tips.batch.model.entity.TableOneStage;
import com.tips.batch.repository.BatchTargetRepository;
import com.tips.batch.service.BatchTargetService;
import com.tips.batch.service.impl.BatchTargetServiceImpl;

@StepScope
public class WriterDBImpl implements ItemWriter<List<ProcessorReceiveDTO>>
{
    private static final Logger log = LoggerFactory.getLogger(WriterDBImpl.class);
    
    private JdbcBatchItemWriter<TableOneStage> batchTargetWriter;
    
    BatchTargetServiceImpl batchTargetServiceImpl;
    
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

    private static final String sql = "insert into public.table_one_stage                        "
                                    + "(                                                         "
                                    + "    column1 , column2 , column3 , column4 , column5       "
                                    + "  , column6 , column7 , column8 , column9 , column10      "
                                    + "  , column11, column12, column13, column14, column15      "
                                    + "  , column16, column17, column18, column19, column20      "
                                    + "  , column21, column22, column23, column24, column25      "
                                    + "  , column26, column27, column28, column29, column30      "
                                    + "  , column31, column32                                    "
                                    + ")                                                         "
                                    + "values                                                    "
                                    + "(                                                         "
                                    + "    :column1 , :column2 , :column3 , :column4 , :column5  "
                                    + "  , :column6 , :column7 , :column8 , :column9 , :column10 "
                                    + "  , :column11, :column12, :column13, :column14, :column15 "
                                    + "  , :column16, :column17, :column18, :column19, :column20 "
                                    + "  , :column21, :column22, :column23, :column24, :column25 "
                                    + "  , :column26, :column27, :column28, :column29, :column30 "
                                    + "  , :column31, :column32                                  "
                                    + ")                                                         ";
    
    public WriterDBImpl()
    {
        dataSource.setDriver  (new org.postgresql.Driver());
        dataSource.setUrl     ("jdbc:postgresql://localhost:5432/tipsdb");
        dataSource.setUsername("tipsuser");
        dataSource.setPassword("tipsuser");
    }

    @BeforeStep
    public void prepareForWriter()
    {
        this.batchTargetWriter = new JdbcBatchItemWriter<TableOneStage>();
        
        this.batchTargetWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<TableOneStage>());
        this.batchTargetWriter.setDataSource(dataSource);
        this.batchTargetWriter.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
        this.batchTargetWriter.setSql(sql);
        this.batchTargetWriter.afterPropertiesSet();
    }
    
    @Override
    public void write(List<? extends List<ProcessorReceiveDTO>> items) throws Exception
    {
        log.info("[WriterImplJpa] write() items : " + items.toString());
        
        ArrayList <TableOneStage> batchTargetList = new ArrayList<TableOneStage>();
        
        for (List<ProcessorReceiveDTO> list : items)
        {
            log.info("[WriterImplJpa] write() list : " + list.toString());
            
            list.forEach(record ->
            {
                log.info("[WriterImplJpa] write() record : " + record.toString());
                
                TableOneStage batchTarget = new TableOneStage();
                
                batchTarget.setColumn1 (record.getColumn1 ());
                batchTarget.setColumn2 (record.getColumn2 ());
                batchTarget.setColumn3 (record.getColumn3 ());
                batchTarget.setColumn4 (record.getColumn4 ());
                batchTarget.setColumn5 (record.getColumn5 ());
                batchTarget.setColumn6 (record.getColumn6 ());
                batchTarget.setColumn7 (record.getColumn7 ());
                batchTarget.setColumn8 (record.getColumn8 ());
                batchTarget.setColumn9 (record.getColumn9 ());
                batchTarget.setColumn10(record.getColumn10());
                batchTarget.setColumn11(record.getColumn11());
                batchTarget.setColumn12(record.getColumn12());
                batchTarget.setColumn13(record.getColumn13());
                batchTarget.setColumn14(record.getColumn14());
                batchTarget.setColumn15(record.getColumn15());
                batchTarget.setColumn16(record.getColumn16());
                batchTarget.setColumn17(record.getColumn17());
                batchTarget.setColumn18(record.getColumn18());
                batchTarget.setColumn19(record.getColumn19());
                batchTarget.setColumn20(record.getColumn20());
                batchTarget.setColumn25(record.getColumn21());
                batchTarget.setColumn25(record.getColumn22());
                batchTarget.setColumn25(record.getColumn23());
                batchTarget.setColumn25(record.getColumn24());
                batchTarget.setColumn25(record.getColumn25());
                batchTarget.setColumn25(record.getColumn26());
                batchTarget.setColumn25(record.getColumn27());
                batchTarget.setColumn25(record.getColumn28());
                batchTarget.setColumn25(record.getColumn29());
                batchTarget.setColumn25(record.getColumn30());
                batchTarget.setColumn31(record.getColumn31());
                batchTarget.setColumn32(record.getColumn32());
                
                batchTargetList.add(batchTarget);
            });
            
            this.batchTargetWriter.write(batchTargetList);
        }
    }
}