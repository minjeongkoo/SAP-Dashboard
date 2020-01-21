package com.tips.batch.service;

import java.security.InvalidParameterException;
import java.util.List;

import com.tips.batch.model.entity.TableOneStage;

public interface BatchTargetService
{
	List<TableOneStage> findAll() throws InvalidParameterException;
	void              saveAll(List<TableOneStage> batchTargetList) throws InvalidParameterException;
}
