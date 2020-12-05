package com.park.parking.controller;
import com.park.parking.entity.IdNotFoundException;
import com.park.parking.entity.ParkingInfo;
import com.park.parking.model.Availability;
import com.park.parking.config.Utility;
import com.park.parking.model.ParkingInfoDTO;
import com.park.parking.repo.ParkingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;



    @RestController
    public class ParkingController {

        private static  Logger logger =   LoggerFactory.getLogger(ParkingController.class.getName());

        private final ParkingRepo repo;

        @Autowired
        Utility utl;

        public ParkingController(ParkingRepo repo) {
            this.repo = repo;
        }

//        @ResponseBody
        @GetMapping("/getavailability")
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


        @GetMapping("/getavailability/{id}")

        Availability ocpyLot(@PathVariable Integer id) {

            Availability avblSpot = new Availability();

            utl.prq.add(id);
            logger.info("Parking Lot: L-" + id + " now available");

            avblSpot.setLotNum(id);

            return avblSpot;
        }



        @GetMapping("/parking")

        public Iterable<ParkingInfo> showAll(){

            Iterable<ParkingInfo> pi=repo.findAll();
            return pi;
        }

        @PostMapping("/parking")
//        ParkingInfo chkIn(@RequestParam (value="vcleRegdNum",required=true)String vcleRegdNum ) {
//            http://localhost:8080/parking/?vcleRegdNum=5XYL169
       ResponseEntity chkIn( @RequestBody ParkingInfoDTO pi ) {


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
            logger.info("Saving parking info: "+pi);
            logger.info("Regd Num: "+pi.getVcleRegdNum());
            try {
                repo.save(po);
            }
            catch( Exception e){
                e.printStackTrace();
                return new ResponseEntity(new String( "Error"),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(po, HttpStatus.OK);

        }

        @PutMapping("/parking")
        public ResponseEntity chkOut(@RequestBody ParkingInfoDTO dl, Errors errors) {

            ParkingInfo po = new ParkingInfo();

            po.setExitTime(new Timestamp(System.currentTimeMillis()));

            po.setCardNum(dl.getCardNum());
            po.setId(dl.getId());
            logger.info("Updating parking Exit info: "+dl);

            try {
                repo.extUpd(po.getId(),po.getExitTime(),po.getCardNum());

                utl.prq.add(po.getId());
            }
            catch( Exception e){
                e.printStackTrace();
                return new ResponseEntity(new String( "Error during update"),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(po, HttpStatus.OK);

        }


        @DeleteMapping("/parking")

        public ResponseEntity delById(@RequestBody ParkingInfoDTO pi) {
            Optional<ParkingInfo> pr = repo.findById(pi.getId());

            logger.info("Deleting ID: " + pr.get().getId());
            try {
                if (pr.isPresent()) {
                    repo.deleteById(pi.getId());
                } else {
                    throw new IdNotFoundException(pi.getId());
                }

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity(new String("No ID Found for delete"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(pi, HttpStatus.OK);

        }


    }







