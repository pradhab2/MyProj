package com.park.parking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "parking_info")
public class ParkingInfo {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="lot_num")
    private Integer lotNum;
    @Column(name="entry_time")
    private Date entryTime;
    @Column(name="exit_time")
    private Date exitTime;
    @Column(name="card_num")
    private Integer cardNum ;
    @Column(name="vcle_regd_num")
    private String vcleRegdNum;

    public ParkingInfo() {
    }

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




}
