package com.tips.back.model.json;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TableOneJson
{
	private String id;
    private String returntype;
    private String cograde;
    private String covalue;
    private String dataterm;
    private String datatime;
    private String khaigrade;
    private String khaivalue;
    private String mangname;
    private String no2grade;
    private String no2value;
    private String numofrows;
    private String o3grade;
    private String o3value;
    private String pageno;
    private String pm10grade;
    private String pm10grade1h;
    private String pm10value;
    private String pm10value24;
    private String pm25grade;
    private String pm25grade1h;
    private String pm25value;
    private String pm25value24;
    private String resultcode;
    private String resultmsg;
    private String rnum;
    private String servicekey;
    private String sidoname;
    private String so2grade;
    private String so2value;
    private String stationcode;
    private String stationname;
    private String totalcount;
    private String ver;
    
    @JsonProperty("list")
    public ArrayList<TableOneJson> TableOneList;
}
