package com.tips.batch.service;

import java.security.InvalidParameterException;
import java.util.List;

import com.tips.batch.model.entity.MeasureInfoReal;
import com.tips.batch.model.entity.MeasureInfoRealStage;

public interface MeasureInfoRealService
{
	void save(MeasureInfoReal measureInfoReal) throws InvalidParameterException;
}
