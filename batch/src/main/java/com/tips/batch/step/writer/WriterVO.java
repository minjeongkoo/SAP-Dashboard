package com.tips.batch.step.writer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.tips.batch.model.FileWriteDTO;
import com.tips.batch.model.ProcessorReceiveDTO;
import com.tips.batch.model.entity.MeasureInfoRealStage;
import com.tips.batch.model.vo.MeasureInfoRealListVO;


public class WriterVO implements ItemWriter<ProcessorReceiveDTO>
{
	private static final Logger log = LoggerFactory.getLogger(WriterVO.class);
	
    @Autowired
    MeasureInfoRealListVO measureInfoRealListVO;

    @Override
    public void write(List<? extends ProcessorReceiveDTO> items) throws Exception
    {
    	log.info("[WriterImpl] write() items : " + items.toString());
    	
        // Transfer to VO
    	items.forEach(t ->
        {
//            if (bizVO.containsKey(t.getStock())) 
//            {
//                String column1 = t.getColumn1();
//                
//                FileWriteDTO priceDetails = bizVO.get(t.getColumn1());
//                
//                // Set highest price
//                if (tradePrice > priceDetails.getHigh())
//                {
//                    priceDetails.setHigh(tradePrice);
//                }
//                
//                // Set lowest price
//                if (tradePrice < priceDetails.getLow())
//                {
//                    priceDetails.setLow(tradePrice);
//                }
//                
//                // Set close price
//                priceDetails.setClose(tradePrice);
//                
//                bizVO.put(t.g.getStock(), 
//                    new FileWriteDTO(t.getStock(), t.getPrice(), t.getPrice(), t.getPrice(), t.getPrice()));
//            }
//            else
//            {
//            	bizVO.put(t.getStock(),
//                    new FileWriteDTO(t.getStock(), t.getPrice(), t.getPrice(), t.getPrice(), t.getPrice()));
//            }
        });
    }
}
