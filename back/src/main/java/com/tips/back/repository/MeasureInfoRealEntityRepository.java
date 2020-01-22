package com.tips.back.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tips.back.model.MeasureInfoReal;

@Repository
public interface MeasureInfoRealEntityRepository extends JpaRepository<MeasureInfoReal, Long>
{
	@Query(value="select m.data_time, m.sido_name, m.station_name, m.mang_name, m.so2_value " + 
				 "from (" + 
				 "	select data_time, sido_name, station_name, mang_name, so2_value," + 
				 "		row_number() over (partition by sido_name, station_name, mang_name order by data_time desc) rn" + 
				 "	from public.measure_info_real" + 
				 "	where sido_name = ?1 and mang_name = ?2) m " + 
				 "where m.rn = 1",
		   nativeQuery=true)
	List<Map<String, String>> findBySindonameAndMangname(String sidoName, String mangName);
}
