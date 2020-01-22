package com.tips.back.service;

import java.security.InvalidParameterException;

import org.springframework.data.domain.Pageable;

import com.tips.back.model.json.MeasureInfoRealJsonList;

public interface MeasureInfoRealEntityService
{
    public MeasureInfoRealJsonList findMeasureInfoRealEntity() throws InvalidParameterException;
    
    public MeasureInfoRealJsonList findMeasureInfoRealEntity(Pageable pageable) throws InvalidParameterException;
    
    public MeasureInfoRealJsonList findSO2ValuesOnlyEntity(String sidoName, String mangName) throws InvalidParameterException;
}
