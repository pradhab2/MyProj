package com.park.parking.repo;

import com.park.parking.entity.ParkingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepo extends JpaRepository<ParkingInfo,Integer> {

   // public interface ParkingRepo extends CrudRepository<ParkingInfo,Integer> {

    Optional<ParkingInfo> findById (Integer id);
    Optional<ParkingInfo> findByVcleRegdNum (String vcleRegdNum);

   @Query(value="select x from ParkingInfo x where date(x.entryTime) between :stDate and :endDate order by x.entryTime desc")
    List<ParkingInfo> getTodayDtl(@Param("stDate") Date stDate,@Param("endDate") Date endDate);


    @Query(value="update ParkingInfo  set exitTime=:exitTime , cardNum=:cardNum  where id=:id")
    @Modifying
    @Transactional
    void extUpd(@Param("id") Integer id,@Param("exitTime") Date extTime,@Param("cardNum") Integer cardNum);





    //select * from parking.parking_info where exit_time is null
}
