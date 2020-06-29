package com.park.parking.repo;

import com.park.parking.entity.ParkingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
//public interface ParkingRepo extends JpaRepository<ParkingInfo,Integer> {

    public interface ParkingRepo extends CrudRepository<ParkingInfo,Integer> {
}
