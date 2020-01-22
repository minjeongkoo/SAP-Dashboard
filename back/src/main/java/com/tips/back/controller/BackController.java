package com.tips.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tips.back.common.RestResponseEntity;
import com.tips.back.model.json.MeasureInfoRealJsonList;
import com.tips.back.service.MeasureInfoRealEntityService;

@RestController
@RequestMapping("/")
public class BackController
{
    @Autowired
    private MeasureInfoRealEntityService measureInfoRealEntityService;
    
    @GetMapping("/list")
    public RestResponseEntity<MeasureInfoRealJsonList> findMeasureInfoRealAll()
    {
        RestResponseEntity<MeasureInfoRealJsonList> result = null;
        
        result = new RestResponseEntity<MeasureInfoRealJsonList>(this.measureInfoRealEntityService.findMeasureInfoRealEntity());
        
        return result;
    }
    
    /*
     * URL : /list/page?page=<page>&size=<size>&sort=<property>,<asc|desc>
     * Parameter
     * - page : sequence of page that start from 0
     * - size : num of data
     * - property
     * 		# id : pk
     * 		# datatime
     * 		# ...
     */   
    @GetMapping("/list/page")
    public RestResponseEntity<MeasureInfoRealJsonList> findMeasureInfoRealPage(Pageable pageable)
    {
    	RestResponseEntity<MeasureInfoRealJsonList> result = null;
    	
    	result = new RestResponseEntity<MeasureInfoRealJsonList>(this.measureInfoRealEntityService.findMeasureInfoRealEntity(pageable));
    	
    	return result;
    }
}