package com.tips.backend2.service;

import java.security.InvalidParameterException;

import com.tips.backend2.model.json.TableOneJson;

public interface TableOneEntityService
{
    public TableOneJson findTableOneEntity() throws InvalidParameterException;
}
