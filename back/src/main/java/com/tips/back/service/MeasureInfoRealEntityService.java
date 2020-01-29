package com.tips.back.service;

import java.security.InvalidParameterException;

import org.springframework.data.domain.Pageable;

import com.tips.back.model.json.MeasureInfoRealJsonList;

public interface MeasureInfoRealEntityService
{
    public MeasureInfoRealJsonList findMeasureInfoRealEntity() throws InvalidParameterException;
    
    public MeasureInfoRealJsonList findMeasureInfoRealEntity(String sidoName, String mangName) throws InvalidParameterException;
    
    public MeasureInfoRealJsonList findMeasureInfoRealEntity(Pageable pageable) throws InvalidParameterException;
    
    public MeasureInfoRealJsonList findSO2ValueInfoRealEntity(String sidoName, String mangName) throws InvalidParameterException;
    
    public MeasureInfoRealJsonList findKhaiValueInfoRealEntity(String sidoName, String mangName) throws InvalidParameterException;
}
