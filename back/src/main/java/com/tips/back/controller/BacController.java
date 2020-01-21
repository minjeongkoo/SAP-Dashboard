package com.tips.back.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tips.back.common.RestResponseEntity;
import com.tips.back.model.json.TableOneJson;
import com.tips.back.repository.TableOneEntityRepository;
import com.tips.back.service.TableOneEntityService;

@RestController
@RequestMapping("/")
public class BacController
{
    @Autowired
    private TableOneEntityService tableOneEntityService;
    
    @GetMapping("/list")
    public RestResponseEntity<TableOneJson> findTableOneAll()
    {
        RestResponseEntity<TableOneJson> result = null;
        
        result = new RestResponseEntity<TableOneJson>(this.tableOneEntityService.findTableOneEntity());
        
        return result;
    }
}