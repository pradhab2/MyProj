package com.park.parking.controller;

import com.park.parking.config.Utility;
import com.park.parking.entity.ParkingInfo;
import com.park.parking.model.Availability;
import com.park.parking.model.ParkingInfoDTO;
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
import java.util.Calendar;
import java.util.Date;
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
    ResponseEntity enq() {
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

        return new ResponseEntity(avblSpot,HttpStatus.OK);

    }


    @GetMapping("/availability/{id}")

    ResponseEntity ocpyLot(@PathVariable Integer id) {

        Availability avblSpot = new Availability();

        utl.prq.add(id);
        logger.info("Parking Lot: L-" + id + " now available");

        avblSpot.setLotNum(id);

        return new ResponseEntity(avblSpot,HttpStatus.OK);
    }


    @PostMapping("/park")
    @ResponseStatus(HttpStatus.CREATED)
    public String chkIn(@Valid ParkingInfoDTO pi, Errors errors, Model model ) {


        ParkingInfo po = new ParkingInfo();
        PriorityQueue<Integer> vprq=utl.prq;
        Integer pqLen=vprq.size();

        logger.info("Length of PQ is "+pqLen);
        if (utl.initMap().size() != 0) {
            Integer lotNum = utl.initMap().peek();
            utl.prq.poll();
            po.setLotNum(lotNum);
        } else {
            logger.info("Parking Lot is full");
            po.setLotNum(-1);
        }
        po.setEntryTime(new Timestamp(System.currentTimeMillis()));
        logger.info("Car num: "+pi.getVcleRegdNum());
        po.setVcleRegdNum(pi.getVcleRegdNum());
        logger.info("Saving parking entry time: "+po.getEntryTime());
       // logger.info("Regd Num: "+pi.getVcleRegdNum());

        if (errors.hasErrors()) {
            logger.info("Errors: " + errors);
            return "parkingall";
        }
        try {
            repo.save(po);
            model.addAttribute("vcleRegdNum",pi);
         // return "redirect:/parkingall";
            return "parkingall";
        }
        catch( Exception e){
            e.printStackTrace();
            return new  String( "Error");
        }
        //return new ResponseEntity(po, HttpStatus.OK);

    }


    @PutMapping("/park")
    public String chkOut(@Valid ParkingInfoDTO dl, Errors errors, Model model ) {

        ParkingInfo po = new ParkingInfo();

        po.setExitTime(new Timestamp(System.currentTimeMillis()));

        po.setCardNum(dl.getCardNum());
        po.setId(dl.getId());
        logger.info("Updating parking info(Exit): "+dl);

        try {
            repo.extUpd(po.getId(),po.getExitTime(),po.getCardNum());
            model.addAttribute("id",dl);
            model.addAttribute("cardNum",dl);
            // return "redirect:/parkingall";
            return "parkingall";



        }
        catch( Exception e){
            e.printStackTrace();
            return new  String( "Error during update");
        }
        //return new ResponseEntity(po, HttpStatus.OK);

    }

//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public String addProduct(@Valid Product product, Errors errors) {
//        if (errors.hasErrors()) {
//            log.info("Errors: " + errors);
//            return "products";
//        }
//
//        log.info("Saving product: " + product);
//        repository.save(product);
//        return "redirect:/products";
//    }

    @GetMapping("/park")

public String showAll(Model model) {
//    model.addAttribute("parking", new ParkingInfo());
//    model.addAttribute("parkings", repo.findAll());
        Date stDate= new Date(System.currentTimeMillis());
        Date endDate= new Date(stDate.getTime()-24*60*60*1000);
        model.addAttribute("parkings", repo.getTodayDtl(endDate,stDate));
        return "parkingall";
}







}







