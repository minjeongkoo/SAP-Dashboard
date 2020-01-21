package com.example.quartz2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quartz2.model.ProcessorReceiveDTO;
import com.example.quartz2.model.entity.TableOneStage;


@Repository
public interface BatchTargetRepository extends JpaRepository<TableOneStage, String>
{
}
