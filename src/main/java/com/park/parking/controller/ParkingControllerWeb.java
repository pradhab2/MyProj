package com.park.parking.controller;

import com.park.parking.config.Utility;
import com.park.parking.entity.ParkingInfo;
import com.park.parking.model.Availability;
import com.park.parking.repo.ParkingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.PriorityQueue;


@Controller
public class ParkingControllerWeb {

    private static  Logger logger =   LoggerFactory.getLogger(ParkingControllerWeb.class.getName());

    private final ParkingRepo repo;

    @Autowired
    Utility utl;

    public ParkingControllerWeb(ParkingRepo repo) {
        this.repo = repo;
    }

//        @ResponseBody
    @GetMapping("/availability")
    Availability enq() {
        Availability avblSpot = new Availability();
        PriorityQueue<Integer> vprq=utl.prq;
        Integer pqLen=vprq.size();
        logger.info("Length of PQ is "+pqLen);

        if (utl.initMap().size() != 0) {
            Integer lotNum = utl.initMap().peek();
            utl.prq.poll();

            avblSpot.setLotNum(lotNum);
        } else {
            logger.info("Parking Lot is full");
            avblSpot.setLotNum(-1);
        }

        return avblSpot;

    }


    @GetMapping("/availability/{id}")

    Availability ocpyLot(@PathVariable Integer id) {

        Availability avblSpot = new Availability();

        utl.prq.add(id);
        logger.info("Parking Lot: L-" + id + " now available");

        avblSpot.setLotNum(id);

        return avblSpot;
    }

//        @ResponseBody
    @PostMapping("/park")
    ParkingInfo chkIn( ) {
        ParkingInfo pi = new ParkingInfo();
        PriorityQueue<Integer> vprq=utl.prq;
        Integer pqLen=vprq.size();

        logger.info("Length of PQ is "+pqLen);
        if (utl.initMap().size() != 0) {
            Integer lotNum = utl.initMap().peek();
            utl.prq.poll();
            pi.setLotNum(lotNum);
        } else {
            logger.info("Parking Lot is full");
            pi.setLotNum(-1);
        }
        pi.setEntryTime(new Timestamp(System.currentTimeMillis()));
        logger.info("Saving parking info"+pi);
         repo.save(pi);
        return pi;

    }

    @GetMapping("/park")

public String showAll(Model model) {
    //model.addAttribute("parking", new ParkingInfo());
    model.addAttribute("parkings", repo.findAll());
    return "parkingall";
}





}







