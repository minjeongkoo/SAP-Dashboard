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
	
	@Query(value="SELECT m.data_time as datatime, m.sido_name as sidoname, m.station_name as stationname, m.mang_name as mangname, m.so2_value as so2value " + 
				 "FROM(SELECT data_time, sido_name, station_name, mang_name, so2_value, row_number() over (partition by sido_name, station_name, mang_name order by data_time desc) rn " + 
				 "	   FROM public.measure_info_real) m " + 
				 "WHERE m.rn = 1;",
		   nativeQuery=true)
	List<SO2ValueInfoReal> findSO2ValueInfoReal();
	
	@Query(value="SELECT m.data_time as datatime, m.sido_name as sidoname, m.station_name as stationname, m.mang_name as mangname, m.so2_value as so2value " + 
				 "FROM(SELECT data_time, sido_name, station_name, mang_name, so2_value, row_number() over (partition by sido_name, station_name, mang_name order by data_time desc) rn " + 
				 "	   FROM public.measure_info_real " + 
				 "	   WHERE sido_name = ?1) m " + 
				 "WHERE m.rn = 1;", 
		   nativeQuery=true)
	List<SO2ValueInfoReal> findSO2ValueInfoRealBySidoname(String sidoName);
	
	@Query(value="SELECT m.data_time as datatime, m.sido_name as sidoname, m.station_name as stationname, m.mang_name as mangname, m.so2_value as so2value " + 
			 	 "FROM(SELECT data_time, sido_name, station_name, mang_name, so2_value, row_number() over (partition by sido_name, station_name, mang_name order by data_time desc) rn " + 
			 	 "	   FROM public.measure_info_real " + 
			 	 "	   WHERE mang_name = ?1) m " + 
			 	 "WHERE m.rn = 1;", 
	       nativeQuery=true)
	List<SO2ValueInfoReal> findSO2ValueInfoRealByMangname(String mangName);
	
	@Query(value="SELECT m.data_time as datatime, m.sido_name as sidoname, m.station_name as stationname, m.mang_name as mangname, m.so2_value as so2value " + 
		 	 	 "FROM(SELECT data_time, sido_name, station_name, mang_name, so2_value, row_number() over (partition by sido_name, station_name, mang_name order by data_time desc) rn " + 
		 	 	 "	   FROM public.measure_info_real " + 
		 	 	 "	   WHERE sido_name = ?1 and mang_name = ?2) m " + 
		 	 	 "WHERE m.rn = 1;", 
           nativeQuery=true)
	List<SO2ValueInfoReal> findSO2ValueInfoRealBySidonameAndMangname(String sidoName, String mangName);

	@Query(value="SELECT m.data_time as datatime, m.sido_name as sidoname, m.station_name as stationname, m.mang_name as mangname, m.khai_value as khaivalue " + 
			 	 "FROM(SELECT data_time, sido_name, station_name, mang_name, khai_value, row_number() over (partition by sido_name, station_name, mang_name order by data_time desc) rn " + 
			 	 "	   FROM public.measure_info_real) m " + 
			 	 "WHERE m.rn = 1;",
		   nativeQuery=true)	
	List<KhaiValueInfoReal> findKhaiValueInfoReal();
	
	@Query(value="SELECT m.data_time as datatime, m.sido_name as sidoname, m.station_name as stationname, m.mang_name as mangname, m.khai_value as khaivalue " + 
			 	 "FROM(SELECT data_time, sido_name, station_name, mang_name, khai_value, row_number() over (partition by sido_name, station_name, mang_name order by data_time desc) rn " + 
			 	 "	   FROM public.measure_info_real " + 
			 	 "	   WHERE sido_name = ?1) m " + 
			 	 "WHERE m.rn = 1;", 
	       nativeQuery=true)
	List<KhaiValueInfoReal> findKhaiValueInfoRealBySidoname(String sidoName);
	
	@Query(value="SELECT m.data_time as datatime, m.sido_name as sidoname, m.station_name as stationname, m.mang_name as mangname, m.khai_value as khaivalue " + 
		 	 	 "FROM(SELECT data_time, sido_name, station_name, mang_name, khai_value, row_number() over (partition by sido_name, station_name, mang_name order by data_time desc) rn " + 
		 	 	 "	   FROM public.measure_info_real " + 
		 	 	 "	   WHERE mang_name = ?1) m " + 
		 	 	 "WHERE m.rn = 1;", 
           nativeQuery=true)
	List<KhaiValueInfoReal> findKhaiValueInfoRealByMangname(String mangName);
	
	@Query(value="SELECT m.data_time as datatime, m.sido_name as sidoname, m.station_name as stationname, m.mang_name as mangname, m.khai_value as khaivalue " + 
	 	 	 	 "FROM(SELECT data_time, sido_name, station_name, mang_name, khai_value, row_number() over (partition by sido_name, station_name, mang_name order by data_time desc) rn " + 
	 	 	 	 "	   FROM public.measure_info_real " + 
	 	 	 	 "	   WHERE sido_name = ?1 and mang_name = ?2) m " + 
	 	 	 	 "WHERE m.rn = 1;", 
           nativeQuery=true)
	List<KhaiValueInfoReal> findKhaiValueInfoRealBySidonameAndMangname(String sidoName, String mangName);
}
