package com.tips.backend2.model;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Data
@Entity
@Table(name = "TABLE_ONE")
public class TableOne implements Serializable
{
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private String id; 
    
    @Column private String returntype;
    @Column private String cograde;
    @Column private String covalue;
    @Column private String dataterm;
    @Column private String datatime;
    @Column private String khaigrade;
    @Column private String khaivalue;
    @Column private String mangname;
    @Column private String no2grade;
    @Column private String no2value;
    @Column private String numofrows;
    @Column private String o3grade;
    @Column private String o3value;
    @Column private String pageno;
    @Column private String pm10grade;
    @Column private String pm10grade1h;
    @Column private String pm10value;
    @Column private String pm10value24;
    @Column private String pm25grade;
    @Column private String pm25grade1h;
    @Column private String pm25value;
    @Column private String pm25value24;
    @Column private String resultcode;
    @Column private String resultmsg;
    @Column private String rnum;
    @Column private String servicekey;
    @Column private String sidoname;
    @Column private String so2grade;
    @Column private String so2value;
    @Column private String stationcode;
    @Column private String stationname;
    @Column private String totalcount;
    @Column private String ver;
}
