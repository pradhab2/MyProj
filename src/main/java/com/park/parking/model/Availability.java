package com.park.parking.model;

import org.springframework.stereotype.Component;
    @Component

    public class Availability {
        private Integer lotNum;
        public Integer getLotNum() {
            return lotNum;

        }



        public void setLotNum(Integer lotNum) {

            this.lotNum = lotNum;

        }


}
