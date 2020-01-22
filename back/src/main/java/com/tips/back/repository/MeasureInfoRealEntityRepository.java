package com.tips.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tips.back.model.MeasureInfoReal;

@Repository
public interface MeasureInfoRealEntityRepository extends JpaRepository<MeasureInfoReal, Long>
{
	
}
