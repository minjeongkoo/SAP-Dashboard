package com.tips.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tips.back.model.KhaiValueInfoReal;
import com.tips.back.model.MeasureInfoReal;
import com.tips.back.model.SO2ValueInfoReal;

@Repository
public interface MeasureInfoRealEntityRepository extends JpaRepository<MeasureInfoReal, Long>
{
	@Query(name="measureInfoRealEntity.findSO2ValueInfoRealBySidonameAndMangname", nativeQuery=true)
	List<SO2ValueInfoReal> findSO2ValueInfoRealBySidonameAndMangname(String sidoName, String mangName);
	
	@Query(name="measureInfoRealEntity.findKhaiValueInfoRealBySidonameAndMangname", nativeQuery=true)
	List<KhaiValueInfoReal> findKhaiValueInfoRealBySidonameAndMangname(String sidoName, String mangName);
}
