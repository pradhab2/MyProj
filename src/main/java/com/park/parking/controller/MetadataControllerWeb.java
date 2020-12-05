package com.park.parking.controller;

import com.park.parking.config.Utility;
import com.park.parking.entity.ParkingInfo;
import com.park.parking.model.Availability;
import com.park.parking.model.MetaInfoDTO;
import com.park.parking.model.ParkingInfoDTO;
import com.park.parking.repo.MetaRepo;
import com.park.parking.repo.ParkingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.PriorityQueue;


@Controller
public class MetadataControllerWeb {

    private static  Logger logger =   LoggerFactory.getLogger(MetadataControllerWeb.class.getName());

//    private final MetaRepo repo;

    @Autowired
    Utility utl;
    @Autowired
    MetaRepo repo;

    public MetadataControllerWeb(MetaRepo repo) {
        this.repo = repo;
    }


    @GetMapping("/meta")

    public String showAll( Model model) {

        return "DataMetadata";
}

    @RequestMapping(value = "/meta", params = "inpFldNm")
    @ResponseStatus(HttpStatus.CREATED)
    public String getData(@Valid MetaInfoDTO mi, Errors errors, Model model ) {
        model.addAttribute("inpFldNm",mi);

        model.addAttribute("metadata", repo.getMetaDtl(mi.getInpFldNm()));
        return "DataMetadata";

    }

    @RequestMapping(value = "/meta", params = "inpFld2")
    @ResponseStatus(HttpStatus.CREATED)
    public String validateData(@Valid MetaInfoDTO pi, Errors errors, Model model ) {
        model.addAttribute("inpFldNm",pi);

        model.addAttribute("metadata2", repo.getMetaDtl(pi.getInpFldNm()));
        return "DataMetadata";

    }



}







