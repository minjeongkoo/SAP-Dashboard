package com.tips.back.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.back.model.KhaiValueInfoReal;
import com.tips.back.model.MeasureInfoReal;
import com.tips.back.model.SO2ValueInfoReal;
import com.tips.back.model.json.MeasureInfoRealJson;
import com.tips.back.model.json.MeasureInfoRealJsonList;
import com.tips.back.repository.MeasureInfoRealEntityRepository;
import com.tips.back.service.MeasureInfoRealEntityService;


@Transactional
@Service
public class MeasureInfoRealEntityServiceImpl implements MeasureInfoRealEntityService
{
    @Autowired
	MeasureInfoRealEntityRepository measureInfoRealRepository;

    @Override
    public MeasureInfoRealJsonList findMeasureInfoRealEntity() throws InvalidParameterException 
    {
        MeasureInfoRealJsonList measureInfoRealJsonList = new MeasureInfoRealJsonList();
        
        // Retune value
        ArrayList<MeasureInfoRealJson> measureInfoRealJsonListObj = new ArrayList<MeasureInfoRealJson>();
        
        List<MeasureInfoReal> measureInfoRealAllByJPA = this.measureInfoRealRepository.findAll();
        
        // JPA return value
        for(MeasureInfoReal measureInfoRealEntity : measureInfoRealAllByJPA)  
        {
            MeasureInfoRealJson measureInfoRealJsonObj = new MeasureInfoRealJson();
            
            measureInfoRealJsonObj.setId            (measureInfoRealEntity.getId         ());
            measureInfoRealJsonObj.setDatatime      (measureInfoRealEntity.getDatatime   ());
            measureInfoRealJsonObj.setSidoname      (measureInfoRealEntity.getSidoname   ());
            measureInfoRealJsonObj.setStationname   (measureInfoRealEntity.getStationname());
            measureInfoRealJsonObj.setMangname      (measureInfoRealEntity.getMangname   ());
            measureInfoRealJsonObj.setSo2value      (measureInfoRealEntity.getSo2value   ());
            measureInfoRealJsonObj.setCovalue       (measureInfoRealEntity.getCovalue    ());
            measureInfoRealJsonObj.setO3value       (measureInfoRealEntity.getO3value    ());
            measureInfoRealJsonObj.setNo2value      (measureInfoRealEntity.getNo2value   ());
            measureInfoRealJsonObj.setPm10value     (measureInfoRealEntity.getPm10value  ());
            measureInfoRealJsonObj.setPm10value24   (measureInfoRealEntity.getPm10value24());
            measureInfoRealJsonObj.setPm25value     (measureInfoRealEntity.getPm25value  ());
            measureInfoRealJsonObj.setPm25value24   (measureInfoRealEntity.getPm25value24());
            measureInfoRealJsonObj.setKhaivalue     (measureInfoRealEntity.getKhaivalue  ());
            measureInfoRealJsonObj.setSo2grade      (measureInfoRealEntity.getSo2grade   ());
            measureInfoRealJsonObj.setCograde       (measureInfoRealEntity.getCograde    ());
            measureInfoRealJsonObj.setO3grade       (measureInfoRealEntity.getO3grade    ());
            measureInfoRealJsonObj.setNo2grade      (measureInfoRealEntity.getNo2grade   ());
            measureInfoRealJsonObj.setPm10grade     (measureInfoRealEntity.getPm10grade  ());
            measureInfoRealJsonObj.setPm10grade1h   (measureInfoRealEntity.getPm10grade1h());
            measureInfoRealJsonObj.setPm25grade     (measureInfoRealEntity.getPm25grade  ());
            measureInfoRealJsonObj.setPm25grade1h   (measureInfoRealEntity.getPm25grade1h());
            measureInfoRealJsonObj.setKhaigrade     (measureInfoRealEntity.getKhaigrade  ());
            
            measureInfoRealJsonListObj.add(measureInfoRealJsonObj);
        }
          
        measureInfoRealJsonList.setMeasureInfoRealJsonList(measureInfoRealJsonListObj);
        
        return measureInfoRealJsonList;
    }
    
    @Override
    public MeasureInfoRealJsonList findMeasureInfoRealEntity(String sidoName, String mangName) throws InvalidParameterException 
    {
        MeasureInfoRealJsonList measureInfoRealJsonList = new MeasureInfoRealJsonList();
        
        // Retune value
        ArrayList<MeasureInfoRealJson> measureInfoRealJsonListObj = new ArrayList<MeasureInfoRealJson>();
        
        List<MeasureInfoReal> measureInfoRealByJPA = null;
        
        if(sidoName != null && mangName != null) {
        	measureInfoRealByJPA = this.measureInfoRealRepository.findBySidonameAndMangname(sidoName, mangName);
        }else if(sidoName != null) {
        	measureInfoRealByJPA = this.measureInfoRealRepository.findBySidoname(sidoName);
        }else if(mangName != null) {
        	measureInfoRealByJPA = this.measureInfoRealRepository.findBySidoname(sidoName);
        }else {
        	measureInfoRealByJPA = this.measureInfoRealRepository.findAll();
        }
        
        // JPA return value
        for(MeasureInfoReal measureInfoRealEntity : measureInfoRealByJPA)  
        {
            MeasureInfoRealJson measureInfoRealJsonObj = new MeasureInfoRealJson();
            
            measureInfoRealJsonObj.setId            (measureInfoRealEntity.getId         ());
            measureInfoRealJsonObj.setDatatime      (measureInfoRealEntity.getDatatime   ());
            measureInfoRealJsonObj.setSidoname      (measureInfoRealEntity.getSidoname   ());
            measureInfoRealJsonObj.setStationname   (measureInfoRealEntity.getStationname());
            measureInfoRealJsonObj.setMangname      (measureInfoRealEntity.getMangname   ());
            measureInfoRealJsonObj.setSo2value      (measureInfoRealEntity.getSo2value   ());
            measureInfoRealJsonObj.setCovalue       (measureInfoRealEntity.getCovalue    ());
            measureInfoRealJsonObj.setO3value       (measureInfoRealEntity.getO3value    ());
            measureInfoRealJsonObj.setNo2value      (measureInfoRealEntity.getNo2value   ());
            measureInfoRealJsonObj.setPm10value     (measureInfoRealEntity.getPm10value  ());
            measureInfoRealJsonObj.setPm10value24   (measureInfoRealEntity.getPm10value24());
            measureInfoRealJsonObj.setPm25value     (measureInfoRealEntity.getPm25value  ());
            measureInfoRealJsonObj.setPm25value24   (measureInfoRealEntity.getPm25value24());
            measureInfoRealJsonObj.setKhaivalue     (measureInfoRealEntity.getKhaivalue  ());
            measureInfoRealJsonObj.setSo2grade      (measureInfoRealEntity.getSo2grade   ());
            measureInfoRealJsonObj.setCograde       (measureInfoRealEntity.getCograde    ());
            measureInfoRealJsonObj.setO3grade       (measureInfoRealEntity.getO3grade    ());
            measureInfoRealJsonObj.setNo2grade      (measureInfoRealEntity.getNo2grade   ());
            measureInfoRealJsonObj.setPm10grade     (measureInfoRealEntity.getPm10grade  ());
            measureInfoRealJsonObj.setPm10grade1h   (measureInfoRealEntity.getPm10grade1h());
            measureInfoRealJsonObj.setPm25grade     (measureInfoRealEntity.getPm25grade  ());
            measureInfoRealJsonObj.setPm25grade1h   (measureInfoRealEntity.getPm25grade1h());
            measureInfoRealJsonObj.setKhaigrade     (measureInfoRealEntity.getKhaigrade  ());
            
            measureInfoRealJsonListObj.add(measureInfoRealJsonObj);
        }
          
        measureInfoRealJsonList.setMeasureInfoRealJsonList(measureInfoRealJsonListObj);
        
        return measureInfoRealJsonList;
    }
    
    @Override
    public MeasureInfoRealJsonList findMeasureInfoRealEntity(Pageable pageable) throws InvalidParameterException
    {
    	MeasureInfoRealJsonList measureInfoRealJsonList = new MeasureInfoRealJsonList();
        
        // Retune value
        ArrayList <MeasureInfoRealJson> measureInfoRealJsonListObj = new ArrayList<MeasureInfoRealJson>();
        
        Page<MeasureInfoReal> measureInfoRealPageByJPA = this.measureInfoRealRepository.findAll(pageable);
        List<MeasureInfoReal> measureInfoRealList = measureInfoRealPageByJPA.getContent();
        
        // JPA return value
        for(MeasureInfoReal measureInfoRealEntity : measureInfoRealList)  
        {
            MeasureInfoRealJson measureInfoRealJsonObj = new MeasureInfoRealJson();
            
            measureInfoRealJsonObj.setId            (measureInfoRealEntity.getId         ());
            measureInfoRealJsonObj.setDatatime      (measureInfoRealEntity.getDatatime   ());
            measureInfoRealJsonObj.setSidoname      (measureInfoRealEntity.getSidoname   ());
            measureInfoRealJsonObj.setStationname   (measureInfoRealEntity.getStationname());
            measureInfoRealJsonObj.setMangname      (measureInfoRealEntity.getMangname   ());
            measureInfoRealJsonObj.setSo2value      (measureInfoRealEntity.getSo2value   ());
            measureInfoRealJsonObj.setCovalue       (measureInfoRealEntity.getCovalue    ());
            measureInfoRealJsonObj.setO3value       (measureInfoRealEntity.getO3value    ());
            measureInfoRealJsonObj.setNo2value      (measureInfoRealEntity.getNo2value   ());
            measureInfoRealJsonObj.setPm10value     (measureInfoRealEntity.getPm10value  ());
            measureInfoRealJsonObj.setPm10value24   (measureInfoRealEntity.getPm10value24());
            measureInfoRealJsonObj.setPm25value     (measureInfoRealEntity.getPm25value  ());
            measureInfoRealJsonObj.setPm25value24   (measureInfoRealEntity.getPm25value24());
            measureInfoRealJsonObj.setKhaivalue     (measureInfoRealEntity.getKhaivalue  ());
            measureInfoRealJsonObj.setSo2grade      (measureInfoRealEntity.getSo2grade   ());
            measureInfoRealJsonObj.setCograde       (measureInfoRealEntity.getCograde    ());
            measureInfoRealJsonObj.setO3grade       (measureInfoRealEntity.getO3grade    ());
            measureInfoRealJsonObj.setNo2grade      (measureInfoRealEntity.getNo2grade   ());
            measureInfoRealJsonObj.setPm10grade     (measureInfoRealEntity.getPm10grade  ());
            measureInfoRealJsonObj.setPm10grade1h   (measureInfoRealEntity.getPm10grade1h());
            measureInfoRealJsonObj.setPm25grade     (measureInfoRealEntity.getPm25grade  ());
            measureInfoRealJsonObj.setPm25grade1h   (measureInfoRealEntity.getPm25grade1h());
            measureInfoRealJsonObj.setKhaigrade     (measureInfoRealEntity.getKhaigrade  ());
            
            measureInfoRealJsonListObj.add(measureInfoRealJsonObj);
        }
          
        measureInfoRealJsonList.setMeasureInfoRealJsonList(measureInfoRealJsonListObj);;
        
        return measureInfoRealJsonList;
    }
    
    public MeasureInfoRealJsonList findSO2ValueInfoRealEntity(String sidoName, String mangName) throws InvalidParameterException{
        MeasureInfoRealJsonList measureInfoRealJsonList = new MeasureInfoRealJsonList();
        
        // Retune value
        ArrayList<MeasureInfoRealJson> measureInfoRealJsonListObj = new ArrayList<MeasureInfoRealJson>();

        List<SO2ValueInfoReal> so2ValueInfoRealByJPA = null;
        
        if(sidoName != null && mangName != null) {
        	so2ValueInfoRealByJPA = this.measureInfoRealRepository.findSO2ValueInfoRealBySidonameAndMangname(sidoName, mangName);
        }else if(sidoName != null) {
        	so2ValueInfoRealByJPA = this.measureInfoRealRepository.findSO2ValueInfoRealBySidoname(sidoName);
        }else if(mangName != null) {
        	so2ValueInfoRealByJPA = this.measureInfoRealRepository.findSO2ValueInfoRealByMangname(mangName);
        }else {
        	so2ValueInfoRealByJPA = this.measureInfoRealRepository.findSO2ValueInfoReal();
        }
        
        // JPA return value
        for(SO2ValueInfoReal so2ValueInfoRealEntity : so2ValueInfoRealByJPA)
        {
            MeasureInfoRealJson measureInfoRealJsonObj = new MeasureInfoRealJson();
            
            measureInfoRealJsonObj.setDatatime      (so2ValueInfoRealEntity.getDatatime   ());
            measureInfoRealJsonObj.setSidoname      (so2ValueInfoRealEntity.getSidoname   ());
            measureInfoRealJsonObj.setStationname   (so2ValueInfoRealEntity.getStationname());
            measureInfoRealJsonObj.setMangname      (so2ValueInfoRealEntity.getMangname   ());
            measureInfoRealJsonObj.setSo2value      (so2ValueInfoRealEntity.getSo2value   ());
            
            measureInfoRealJsonListObj.add(measureInfoRealJsonObj);
        }
          
        measureInfoRealJsonList.setMeasureInfoRealJsonList(measureInfoRealJsonListObj);
        
        return measureInfoRealJsonList;    	
    }
    
    public MeasureInfoRealJsonList findKhaiValueInfoRealEntity(String sidoName, String mangName) throws InvalidParameterException{
        MeasureInfoRealJsonList measureInfoRealJsonList = new MeasureInfoRealJsonList();
        
        // Retune value
        ArrayList<MeasureInfoRealJson> measureInfoRealJsonListObj = new ArrayList<MeasureInfoRealJson>();

        List<KhaiValueInfoReal> khaiValueInfoRealByJPA = null;
        
        if(sidoName != null && mangName != null) {
        	khaiValueInfoRealByJPA = this.measureInfoRealRepository.findKhaiValueInfoRealBySidonameAndMangname(sidoName, mangName);;
        }else if(sidoName != null) {
        	khaiValueInfoRealByJPA = this.measureInfoRealRepository.findKhaiValueInfoRealBySidoname(sidoName);
        }else if(mangName != null) {
        	khaiValueInfoRealByJPA = this.measureInfoRealRepository.findKhaiValueInfoRealByMangname(mangName);
        }else {
        	khaiValueInfoRealByJPA = this.measureInfoRealRepository.findKhaiValueInfoReal();
        }
        
        // JPA return value
        for(KhaiValueInfoReal khaiValueInfoRealEntity : khaiValueInfoRealByJPA)
        {
            MeasureInfoRealJson measureInfoRealJsonObj = new MeasureInfoRealJson();
            
            measureInfoRealJsonObj.setDatatime      (khaiValueInfoRealEntity.getDatatime   ());
            measureInfoRealJsonObj.setSidoname      (khaiValueInfoRealEntity.getSidoname   ());
            measureInfoRealJsonObj.setStationname   (khaiValueInfoRealEntity.getStationname());
            measureInfoRealJsonObj.setMangname      (khaiValueInfoRealEntity.getMangname   ());
            measureInfoRealJsonObj.setKhaivalue      (khaiValueInfoRealEntity.getKhaivalue  ());
            
            measureInfoRealJsonListObj.add(measureInfoRealJsonObj);
        }
          
        measureInfoRealJsonList.setMeasureInfoRealJsonList(measureInfoRealJsonListObj);
        
        return measureInfoRealJsonList;
    }
}
