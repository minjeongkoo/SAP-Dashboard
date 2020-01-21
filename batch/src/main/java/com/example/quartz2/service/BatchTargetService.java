package com.example.quartz2.service;

import java.security.InvalidParameterException;
import java.util.List;

import com.example.quartz2.model.entity.TableOneStage;

public interface BatchTargetService
{
	List<TableOneStage> findAll() throws InvalidParameterException;
	void              saveAll(List<TableOneStage> batchTargetList) throws InvalidParameterException;
}
