package com.tips.back.service;

import java.security.InvalidParameterException;

import org.springframework.data.domain.Pageable;

import com.tips.back.model.json.TableOneJsonList;

public interface TableOneEntityService
{
    public TableOneJsonList findTableOneEntity() throws InvalidParameterException;
    
    public TableOneJsonList findTableOneEntity(Pageable pageable) throws InvalidParameterException;
}
