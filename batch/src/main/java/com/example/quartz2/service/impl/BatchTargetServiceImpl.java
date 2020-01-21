package com.example.quartz2.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quartz2.model.ProcessorReceiveDTO;
import com.example.quartz2.model.entity.TableOneStage;
import com.example.quartz2.repository.BatchTargetRepository;
import com.example.quartz2.service.BatchTargetService;



@Transactional
@Service
public class BatchTargetServiceImpl implements BatchTargetService
{
    @Autowired
    BatchTargetRepository batchTargetRepository;

    public List<TableOneStage> findAll() throws InvalidParameterException 
    {
        List<TableOneStage> batchTargetList = this.batchTargetRepository.findAll();

        return batchTargetList;
    }
    
    public void saveAll(List<TableOneStage> batchTargetList) throws InvalidParameterException 
    {
        this.batchTargetRepository.saveAll(batchTargetList);
    }
}
