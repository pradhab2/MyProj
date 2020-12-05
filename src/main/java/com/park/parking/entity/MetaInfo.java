package com.park.parking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meta_info")
public class MetaInfo {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inp_fld_nm")
    private String inpFldNm;
    @Column(name="db_fld_nm")
    private String dbFldNm;
    @Column(name="recm_fld_nm")
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

    public MetaInfo() {
    }





}
