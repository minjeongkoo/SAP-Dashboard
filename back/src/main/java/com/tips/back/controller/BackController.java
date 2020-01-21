package com.tips.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tips.back.common.RestResponseEntity;
import com.tips.back.model.json.TableOneJsonList;
import com.tips.back.service.TableOneEntityService;

@RestController
@RequestMapping("/")
public class BackController
{
    @Autowired
    private TableOneEntityService tableOneEntityService;
    
    @GetMapping("/list")
    public RestResponseEntity<TableOneJsonList> findTableOneAll()
    {
        RestResponseEntity<TableOneJsonList> result = null;
        
        result = new RestResponseEntity<TableOneJsonList>(this.tableOneEntityService.findTableOneEntity());
        
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
    public RestResponseEntity<TableOneJsonList> findTableOnePage(Pageable pageable)
    {
    	RestResponseEntity<TableOneJsonList> result = null;
    	
    	result = new RestResponseEntity<TableOneJsonList>(this.tableOneEntityService.findTableOneEntity(pageable));
    	
    	return result;
    }
}