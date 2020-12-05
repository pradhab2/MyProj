package com.park.parking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class MetaInfoDTO {
    @JsonProperty("inpFldNm")
    private String inpFldNm;
    @JsonProperty("dbFldNm")
    private String dbFldNm;
    @JsonProperty("recmFldNm")
    private String recmFldNm;

    public String getInpFldNm() {
        return inpFldNm;
    }

    public void setInpFldNm(String inpFldNm) {
        this.inpFldNm = inpFldNm;
    }

    public String getDbFldNm() {
        return dbFldNm;
    }

    public void setDbFldNm(String dbFldNm) {
        this.dbFldNm = dbFldNm;
    }

    public String getRecmFldNm() {
        return recmFldNm;
    }

    public void setRecmFldNm(String recmFldNm) {
        this.recmFldNm = recmFldNm;
    }
}

