package com.tips.batch.service;

import java.security.InvalidParameterException;
import java.util.List;

import com.tips.batch.model.entity.MeasureInfoRealStage;

public interface BatchTargetService
{
	List<MeasureInfoRealStage> findAll() throws InvalidParameterException;
	void              saveAll(List<MeasureInfoRealStage> batchTargetList) throws InvalidParameterException;
}
