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
	List<MeasureInfoReal> findBySidoname(String sidoName);
	
	List<MeasureInfoReal> findByMangname(String mangName);
	
	List<MeasureInfoReal> findBySidonameAndMangname(String sidoName, String mangName);
	
	@Query(name="measureInfoRealEntity.findSO2ValueInfoReal", nativeQuery=true)
	List<SO2ValueInfoReal> findSO2ValueInfoReal();
	
	@Query(name="measureInfoRealEntity.findSO2ValueInfoRealBySidoname", nativeQuery=true)
	List<SO2ValueInfoReal> findSO2ValueInfoRealBySidoname(String sidoName);
	
	@Query(name="measureInfoRealEntity.findSO2ValueInfoRealByMangname", nativeQuery=true)
	List<SO2ValueInfoReal> findSO2ValueInfoRealByMangname(String mangName);
	
	@Query(name="measureInfoRealEntity.findSO2ValueInfoRealBySidonameAndMangname", nativeQuery=true)
	List<SO2ValueInfoReal> findSO2ValueInfoRealBySidonameAndMangname(String sidoName, String mangName);

	@Query(name="measureInfoRealEntity.findKhaiValueInfoReal", nativeQuery=true)
	List<KhaiValueInfoReal> findKhaiValueInfoReal();
	
	@Query(name="measureInfoRealEntity.findKhaiValueInfoRealBySidoname", nativeQuery=true)
	List<KhaiValueInfoReal> findKhaiValueInfoRealBySidoname(String sidoName);
	
	@Query(name="measureInfoRealEntity.findKhaiValueInfoRealByMangname", nativeQuery=true)
	List<KhaiValueInfoReal> findKhaiValueInfoRealByMangname(String mangName);
	
	@Query(name="measureInfoRealEntity.findKhaiValueInfoRealBySidonameAndMangname", nativeQuery=true)
	List<KhaiValueInfoReal> findKhaiValueInfoRealBySidonameAndMangname(String sidoName, String mangName);
}
