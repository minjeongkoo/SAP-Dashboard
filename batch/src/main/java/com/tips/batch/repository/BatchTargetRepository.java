package com.tips.batch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tips.batch.model.ProcessorReceiveDTO;
import com.tips.batch.model.entity.MeasureInfoRealStage;


@Repository
public interface BatchTargetRepository extends JpaRepository<MeasureInfoRealStage, String>
{
}
