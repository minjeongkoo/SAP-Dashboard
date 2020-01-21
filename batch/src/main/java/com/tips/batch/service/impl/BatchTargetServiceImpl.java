package com.tips.batch.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.batch.model.ProcessorReceiveDTO;
import com.tips.batch.model.entity.MeasureInfoRealStage;
import com.tips.batch.repository.BatchTargetRepository;
import com.tips.batch.service.BatchTargetService;



@Transactional
@Service
public class BatchTargetServiceImpl implements BatchTargetService
{
    @Autowired
    BatchTargetRepository batchTargetRepository;

    public List<MeasureInfoRealStage> findAll() throws InvalidParameterException 
    {
        List<MeasureInfoRealStage> batchTargetList = this.batchTargetRepository.findAll();

        return batchTargetList;
    }
    
    public void saveAll(List<MeasureInfoRealStage> batchTargetList) throws InvalidParameterException 
    {
        this.batchTargetRepository.saveAll(batchTargetList);
    }
}
