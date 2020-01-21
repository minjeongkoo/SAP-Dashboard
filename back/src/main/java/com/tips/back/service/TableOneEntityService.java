package com.tips.back.service;

import java.security.InvalidParameterException;

import com.tips.back.model.json.TableOneJson;

public interface TableOneEntityService
{
    public TableOneJson findTableOneEntity() throws InvalidParameterException;
}
