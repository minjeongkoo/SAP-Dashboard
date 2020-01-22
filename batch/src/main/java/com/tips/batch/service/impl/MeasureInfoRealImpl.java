package com.tips.batch.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.batch.model.ProcessorReceiveDTO;
import com.tips.batch.model.entity.MeasureInfoReal;
import com.tips.batch.model.entity.MeasureInfoRealStage;
import com.tips.batch.repository.MeasureInfoRealRepository;
import com.tips.batch.service.MeasureInfoRealService;



@Transactional
@Service
public class MeasureInfoRealImpl implements MeasureInfoRealService
{
	@Autowired
	MeasureInfoRealRepository measureInfoRealRepository;

	@Override
	public void save(MeasureInfoReal measureInfoReal) throws InvalidParameterException
	{
		measureInfoRealRepository.save(measureInfoReal);	
	}
}
