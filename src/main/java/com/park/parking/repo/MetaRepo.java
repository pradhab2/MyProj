package com.park.parking.repo;

import com.park.parking.entity.MetaInfo;
import com.park.parking.entity.ParkingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MetaRepo extends JpaRepository<MetaInfo,Integer> {


//    Optional<MetaInfo> findByInpFldNm (String InpFldNm);

//   @Query(value="select x from MetaInfo x where x.inpFldNm = :inpFldNm ",nativeQuery = true)

   @Query(value="select * FROM parking.Meta_Info where inp_fld_nm = :inpFldNm ",nativeQuery = true)
    List<MetaInfo> getMetaDtl(@Param("inpFldNm") String inpFldNm);







}
