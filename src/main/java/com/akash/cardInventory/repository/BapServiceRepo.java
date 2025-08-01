package com.akash.cardInventory.repository;

import com.akash.cardInventory.entity.BapPRofileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BapServiceRepo extends JpaRepository<BapPRofileEntity,Long> {
    List<BapPRofileEntity> findAllRowByorderNo(String orderNo);

    BapPRofileEntity findRowByOrderNo(String orderNo);

    @Query(value = "SELECT \n" +
            "  TO_CHAR(TO_DATE(d.finish_date, 'YYYY-MM-DD'), 'Month') AS monthName, \n" +
            "  COUNT(*) AS count\n" +
            "FROM \n" +
            "  bapprofile_entity d\n" +
            "WHERE \n" +
            "  TO_DATE(d.finish_date, 'YYYY-MM-DD') BETWEEN TO_DATE(:startDate, 'YYYY-MM-DD') AND TO_DATE(:endDate, 'YYYY-MM-DD')\n" +
            "GROUP BY \n" +
            "  TO_CHAR(TO_DATE(d.finish_date, 'YYYY-MM-DD'), 'Month'), \n" +
            "  EXTRACT(MONTH FROM TO_DATE(d.finish_date, 'YYYY-MM-DD'))\n" +
            "ORDER BY \n" +
            "  EXTRACT(MONTH FROM TO_DATE(d.finish_date, 'YYYY-MM-DD'))\n", nativeQuery = true)
    List<Object[]> countByDateRange(LocalDate startDate, LocalDate endDate);

    @Query(value = """
    SELECT 
      TO_CHAR(TO_DATE(d.finish_date, 'YYYY-MM-DD'), 'FMMonth') AS monthName,
      COUNT(*) AS count
    FROM bapprofile_entity d
    WHERE EXTRACT(YEAR FROM TO_DATE(d.finish_date, 'YYYY-MM-DD')) = :year
    GROUP BY 
      TO_CHAR(TO_DATE(d.finish_date, 'YYYY-MM-DD'), 'FMMonth'),
      EXTRACT(MONTH FROM TO_DATE(d.finish_date, 'YYYY-MM-DD'))
    ORDER BY EXTRACT(MONTH FROM TO_DATE(d.finish_date, 'YYYY-MM-DD'))
""", nativeQuery = true)
    List<Object[]> countByMonthForYearNative(@Param("year") int year);



}
