package com.park.parking.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
    @Configuration

    public class Utility {



//    public HashMap<String, Boolean> avMap = new HashMap<String, Boolean>();

//@Bean

//    public  HashMap<String,Boolean> initMap() {

//

//

//        int len = 5;

//

//        for (int i = 0; i < len; i++) {

//            avMap.put("L1-" + i, false);

//        }

//

//    return avMap;

//    }



        public Integer occupancyCounter = 1;

        public PriorityQueue<Integer> prq = new PriorityQueue<Integer>();



        @Bean

        public PriorityQueue<Integer> initMap() {

            for (int i = 1; i <= 500; i++) {

                prq.add(i);

            }

            return prq;

        }

    }





