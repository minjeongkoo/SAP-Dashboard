package com.tips.batch.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.springframework.data.jpa.repository.Temporal;

import lombok.Data;

@Data
@Entity
@Table
public class TableOneStage implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private String id; 

    @Column private String column1 ;    
    @Column private String column2 ;
    @Column private String column3 ;
    @Column private String column4 ;
    @Column private String column5 ;
    @Column private String column6 ;
    @Column private String column7 ;
    @Column private String column8 ;
    @Column private String column9 ;
    @Column private String column10;
    @Column private String column11;
    @Column private String column12;
    @Column private String column13;
    @Column private String column14;
    @Column private String column15;
    @Column private String column16;
    @Column private String column17;
    @Column private String column18;
    @Column private String column19;
    @Column private String column20;
    @Column private String column21;
    @Column private String column22;
    @Column private String column23;
    @Column private String column24;
    @Column private String column25;
    @Column private String column26;
    @Column private String column27;
    @Column private String column28;
    @Column private String column29;
    @Column private String column30;
    @Column private String column31;
    @Column private String column32;
    
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String createData;
}
    