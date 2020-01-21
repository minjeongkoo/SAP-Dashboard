package com.tips.backend2.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tips.backend2.model.TableOne;
import com.tips.backend2.model.json.TableOneJson;
import com.tips.backend2.repository.TableOneEntityRepository;
import com.tips.backend2.service.TableOneEntityService;


@Transactional
@Service
public class TableOneEntityServiceImpl implements TableOneEntityService
{
    @Autowired
    TableOneEntityRepository TableOneRepository;

    @Override
    public TableOneJson findTableOneEntity() throws InvalidParameterException 
    {
        TableOneJson TableOneJson = new TableOneJson();
        
        // Retune value
        ArrayList <TableOneJson> TableOneJsonList = new ArrayList<TableOneJson>();
        
        List<TableOne> tableOneAllByJPA = this.TableOneRepository.findAll();
        
        // JPA return value
        for(TableOne tableOneEntity : tableOneAllByJPA)  
        {
            TableOneJson tableOneJsonObj = new TableOneJson();
            
            tableOneJsonObj.setId            (tableOneEntity.getId         ());
            tableOneJsonObj.setReturntype    (tableOneEntity.getDatatime   ());
            tableOneJsonObj.setCograde       (tableOneEntity.getCograde    ());
            tableOneJsonObj.setCovalue       (tableOneEntity.getCovalue    ());
            tableOneJsonObj.setDataterm      (tableOneEntity.getDataterm   ());
            tableOneJsonObj.setDatatime      (tableOneEntity.getDatatime   ());
            tableOneJsonObj.setKhaigrade     (tableOneEntity.getKhaigrade  ());
            tableOneJsonObj.setKhaivalue     (tableOneEntity.getKhaivalue  ());
            tableOneJsonObj.setMangname      (tableOneEntity.getMangname   ());
            tableOneJsonObj.setNo2grade      (tableOneEntity.getNo2grade   ());
            tableOneJsonObj.setNo2value      (tableOneEntity.getNo2value   ());
            tableOneJsonObj.setNumofrows     (tableOneEntity.getNumofrows  ());
            tableOneJsonObj.setO3grade       (tableOneEntity.getO3grade    ());
            tableOneJsonObj.setO3value       (tableOneEntity.getO3value    ());
            tableOneJsonObj.setPageno        (tableOneEntity.getPageno     ());
            tableOneJsonObj.setPm10grade     (tableOneEntity.getPm10grade  ());
            tableOneJsonObj.setPm10grade1h   (tableOneEntity.getPm10grade1h());
            tableOneJsonObj.setPm10value     (tableOneEntity.getPm10value  ());
            tableOneJsonObj.setPm10value24   (tableOneEntity.getPm10value24());
            tableOneJsonObj.setPm25grade     (tableOneEntity.getPm25grade  ());
            tableOneJsonObj.setPm25grade1h   (tableOneEntity.getPm25grade1h());
            tableOneJsonObj.setPm25value     (tableOneEntity.getPm25value  ());
            tableOneJsonObj.setPm25value24   (tableOneEntity.getPm25value24());
            tableOneJsonObj.setResultcode    (tableOneEntity.getResultcode ());
            tableOneJsonObj.setResultmsg     (tableOneEntity.getResultmsg  ());
            tableOneJsonObj.setRnum          (tableOneEntity.getRnum       ());
            tableOneJsonObj.setServicekey    (tableOneEntity.getServicekey ());
            tableOneJsonObj.setSidoname      (tableOneEntity.getSidoname   ());
            tableOneJsonObj.setSo2grade      (tableOneEntity.getSo2grade   ());
            tableOneJsonObj.setSo2value      (tableOneEntity.getSo2value   ());
            tableOneJsonObj.setStationcode   (tableOneEntity.getStationcode());
            tableOneJsonObj.setStationname   (tableOneEntity.getStationname());
            tableOneJsonObj.setTotalcount    (tableOneEntity.getTotalcount ());
            tableOneJsonObj.setVer           (tableOneEntity.getVer        ());
            
            TableOneJsonList.add(tableOneJsonObj);
        }
          
        TableOneJson.setTableOneList(TableOneJsonList);
        
        return TableOneJson;
    }
}
