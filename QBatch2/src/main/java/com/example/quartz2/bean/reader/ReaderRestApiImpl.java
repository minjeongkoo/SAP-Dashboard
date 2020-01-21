package com.example.quartz2.bean.reader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.quartz2.model.ReaderReturnDTO;
import com.example.quartz2.model.reader.ReaderSourceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.batch.item.ItemReader;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;

public class ReaderRestApiImpl implements ItemReader<List<ReaderReturnDTO>>
{
    private static final Logger log = LoggerFactory.getLogger(ReaderRestApiImpl.class);
    
    public ReaderRestApiImpl() {};
    
    private static int runningCount = 0;
    
  //List<ReaderSourceDTO> readerSourceDTOList = new ArrayList<ReaderSourceDTO>();  // REST API 리턴 형식
    List<ReaderReturnDTO> readerReturnDTOList = new ArrayList<ReaderReturnDTO>();  // Processor 로 넘길 형식
    
    // Get Rest Api Data
    public List<ReaderReturnDTO> getResource(int runningCount)
    {
        log.info("[ReaderRestApiImpl] getResource() runningCount : " + runningCount);
        
        try
        {
            // Local -------------------------------------------------------------------------------------------------------------------
            /** **
            String uri = "http://localhost:8081/list";
            
            RestTemplate restTemplate = new RestTemplate();
            
            String response = restTemplate.getForObject(uri, String.class);
            ** **/
            
            // 미세먼지 공공 REST API ------------------------------------------------------------------------------------------------------
            /** **/
            String url           = "http://openapi.airkorea.or.kr/openapi/services/rest/";
            String serviceId     = "ArpltnInforInqireSvc"                                ;  // 대기오염정보 조회 서비스
            String operationName = "getCtprvnRltmMesureDnsty"                            ;  // 시도별 실시간 측정정보 조회
          //String serviceKey    = "bg9choiwFZX5JYcIIF76jFiVYe0VwiWdxdpCUldbALWxzJLNZA4Ipq2Z1SVqkZyWSW88og%2Bt8EiOCX9J%2BB3ZUw%3D%3D";
            String serviceKey    = "2%2Bxy%2FDG9FLV3s9hUtwRXX1%2F4KjR92LJqXblaoGqWPzs2u4s4ZxqgXnYQiEUNIAaoXjy66zBIafygmX8ayFFgRw%3D%3D";
            String numOfRows     = "100"                            ;
            String pageNo        = "1"                              ;
            String sidoName      = URLEncoder.encode("인천", "UTF-8");
            String version       = "1.3"                            ;
            String returnType    = "json"                           ;
            
            String uri = url
                       + serviceId
                       + "/"
                       + operationName
                       + "?" + "serviceKey="   + serviceKey
                       + "&" + "numOfRows="    + numOfRows
                       + "&" + "pageNo="       + pageNo
                       + "&" + "sidoName="     + sidoName
                       + "&" + "ver="          + version
                       + "&" +  "_returnType=" + returnType;
            
            log.info("[ReaderRestApiImpl] getResource() uri : " + uri);
            
            URL urlObj = new URL(uri.toString());
            
            HttpURLConnection urlconnection = (HttpURLConnection) urlObj.openConnection();
            
            urlconnection.setRequestMethod("GET");
            urlconnection.setRequestProperty("Content-type", "application/json");
            
            BufferedReader response;
            
            if (urlconnection.getResponseCode() >= 200 && urlconnection.getResponseCode() <= 300)
            {
                response = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
            }
            else
            {
                response = new BufferedReader(new InputStreamReader(urlconnection.getErrorStream()));
            };
            /** **/
            
            log.info("[ReaderRestApiImpl] getResource() response : " + response.toString());
            
            // ------------------------------------------------------------------------------------------------------------------------
            
            ObjectMapper mapper = new ObjectMapper();
            
          //JsonNode list = mapper.readTree(response).path("result").get("list");  // Local
            JsonNode list = mapper.readTree(response).get("list");  // Public
            
            log.info("[ReaderRestApiImpl] getResource() list : " + list.toString());
            
            String[] columns = 
                {
                                 "coGrade"    , "coValue"    , "dataTerm" , "dataTime"
                , "khaiGrade"  , "khaiValue"  , "mangName"   , "no2Grade" , "no2Value"   
                , "numOfRows"  , "o3Grade"    , "o3Value"    , "pageNo"   , "pm10Grade"
                , "pm10Grade1h", "pm10Value"  , "pm10Value24", "pm25Grade", "pm25Grade1h"
                , "pm25Value"  , "pm25Value24", "resultCode" , "resultMsg", "rnum"
                , "serviceKey" , "sidoName"   , "so2Grade"   , "so2Value" , "stationCode"
                , "stationName", "totalCount" , "ver"
                };
            
            // for Local Test
            for (int i = 0; i < columns.length; i++)
            {
                //columns[i] = columns[i].toLowerCase();
                
                log.info("[ReaderRestApiImpl] getResource() columns[i] : " + columns[i]);
            }
            
            for (int i = 0; i < list.size(); i++)
            {
                ReaderReturnDTO readerReturnObj = new ReaderReturnDTO();
                
                readerReturnObj.setColumn1 (list.get(i).get(columns[ 0]).textValue());
                readerReturnObj.setColumn2 (list.get(i).get(columns[ 1]).textValue());
                readerReturnObj.setColumn3 (list.get(i).get(columns[ 2]).textValue());
                readerReturnObj.setColumn4 (list.get(i).get(columns[ 3]).textValue());
                readerReturnObj.setColumn5 (list.get(i).get(columns[ 4]).textValue());
                readerReturnObj.setColumn6 (list.get(i).get(columns[ 5]).textValue());
                readerReturnObj.setColumn7 (list.get(i).get(columns[ 6]).textValue());
                readerReturnObj.setColumn8 (list.get(i).get(columns[ 7]).textValue());
                readerReturnObj.setColumn9 (list.get(i).get(columns[ 8]).textValue());
                readerReturnObj.setColumn10(list.get(i).get(columns[ 9]).textValue());
                readerReturnObj.setColumn11(list.get(i).get(columns[10]).textValue());
                readerReturnObj.setColumn12(list.get(i).get(columns[11]).textValue());
                readerReturnObj.setColumn13(list.get(i).get(columns[12]).textValue());
                readerReturnObj.setColumn14(list.get(i).get(columns[13]).textValue());
                readerReturnObj.setColumn15(list.get(i).get(columns[14]).textValue());
                readerReturnObj.setColumn16(list.get(i).get(columns[15]).textValue());
                readerReturnObj.setColumn17(list.get(i).get(columns[16]).textValue());
                readerReturnObj.setColumn18(list.get(i).get(columns[17]).textValue());
                readerReturnObj.setColumn19(list.get(i).get(columns[18]).textValue());
                readerReturnObj.setColumn20(list.get(i).get(columns[19]).textValue());
                readerReturnObj.setColumn21(list.get(i).get(columns[20]).textValue());
                readerReturnObj.setColumn22(list.get(i).get(columns[21]).textValue());
                readerReturnObj.setColumn23(list.get(i).get(columns[22]).textValue());
                readerReturnObj.setColumn24(list.get(i).get(columns[23]).textValue());
                readerReturnObj.setColumn25(list.get(i).get(columns[24]).textValue());
                readerReturnObj.setColumn26(list.get(i).get(columns[25]).textValue());
                readerReturnObj.setColumn27(list.get(i).get(columns[26]).textValue());
                readerReturnObj.setColumn28(list.get(i).get(columns[27]).textValue());
                readerReturnObj.setColumn29(list.get(i).get(columns[28]).textValue());
                readerReturnObj.setColumn30(list.get(i).get(columns[29]).textValue());
                readerReturnObj.setColumn31(list.get(i).get(columns[30]).textValue());
                readerReturnObj.setColumn32(list.get(i).get(columns[31]).textValue());
                
                log.info("[ReaderRestApiImpl] getResource() readerReturnObj : " + readerReturnObj.toString());
                
                readerReturnDTOList.add(readerReturnObj);
            }
            
//            readerSourceDTOList = new ObjectMapper().readerFor(new TypeReference<ArrayList<ReaderSourceDTO>>() {}).readValue(list);
//           
//            for (ReaderSourceDTO readerSourceDTO : readerSourceDTOList)
//            {
//                ReaderReturnDTO readerReturnObj = new ReaderReturnDTO();
//                
//                readerReturnObj.setColumn1(readerSourceDTO.getDatatime   ());
//                readerReturnObj.setColumn2(readerSourceDTO.getStationname());
//                readerReturnObj.setColumn3(readerSourceDTO.getMangname   ());
//                readerReturnObj.setColumn4(readerSourceDTO.getPm10grade  ());
//                readerReturnObj.setColumn5(readerSourceDTO.getPm10value  ());
//                
//                readerReturnDTOList.add(readerReturnObj);
//            }

            response.close();
            
            urlconnection.disconnect();
            
            return readerReturnDTOList;
        }
        catch(Exception e)
        {
            log.info("[ReaderRestApiImpl]");
            
            e.printStackTrace();

            return null;
        }
    }
    
    @Override
    public List<ReaderReturnDTO> read()
    {
        List<ReaderReturnDTO> readerReturnDTOList = null;
        
        if (runningCount < 1)
        {
            readerReturnDTOList = this.getResource(runningCount);
            
            runningCount++;
        }

        return readerReturnDTOList;
    }
}
