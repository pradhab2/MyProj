package com.park.parking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


public class ParkingInfoDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("lotNum")
    private Integer lotNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLotNum() {
        return lotNum;
    }

    public void setLotNum(Integer lotNum) {
        this.lotNum = lotNum;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public String getVcleRegdNum() {
        return vcleRegdNum;
    }

    public void setVcleRegdNum(String vcleRegdNum) {
        this.vcleRegdNum = vcleRegdNum;
    }

    @JsonProperty("entryTime")
    private Date entryTime;
    @JsonProperty("exitTime")
    private Date exitTime;
    @JsonProperty("cardNum")
    private Integer cardNum ;
    @JsonProperty("vcleRegdNum")
    @NotBlank(message ="Veichle Regd Number can not be blank")
    private String vcleRegdNum;
}

