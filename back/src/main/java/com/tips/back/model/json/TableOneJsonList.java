package com.tips.back.model.json;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TableOneJsonList {
    @JsonProperty("list")
    private ArrayList<TableOneJson> tableOneJsonList;	
}