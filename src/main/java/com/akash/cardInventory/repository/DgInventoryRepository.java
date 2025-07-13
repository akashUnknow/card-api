package com.akash.cardInventory.repository;
import com.akash.cardInventory.entity.DgInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DgInventoryRepository extends JpaRepository<DgInventory, Long> {
    List<DgInventory> findAllByGdIndia(String gdIndia);

    DgInventory findRowByGdIndia(String gdIndia);

    @Query(value = "SELECT TO_CHAR(TO_DATE(d.order_date, 'YYYY-MM-DD'), 'FMMonth') as monthName, COUNT(*) as count " +
            "FROM dg_inventory d " +
            "WHERE EXTRACT(YEAR FROM TO_DATE(d.order_date, 'YYYY-MM-DD')) = :year " +
            "GROUP BY TO_CHAR(TO_DATE(d.order_date, 'YYYY-MM-DD'), 'FMMonth'), EXTRACT(MONTH FROM TO_DATE(d.order_date, 'YYYY-MM-DD')) " +
            "ORDER BY EXTRACT(MONTH FROM TO_DATE(d.order_date, 'YYYY-MM-DD'))", nativeQuery = true)
    List<Object[]> countByMonthForYearNative(@Param("year") int year);

    @Query(value = "SELECT TO_CHAR(TO_DATE(d.order_date, 'YYYY-MM-DD'), 'Month') as monthName, COUNT(*) as count " +
            "FROM dg_inventory d " +
            "WHERE TO_DATE(d.order_date, 'YYYY-MM-DD') BETWEEN TO_DATE(:startDate, 'YYYY-MM-DD') AND TO_DATE(:endDate, 'YYYY-MM-DD') " +
            "GROUP BY TO_CHAR(TO_DATE(d.order_date, 'YYYY-MM-DD'), 'Month'), EXTRACT(MONTH FROM TO_DATE(d.order_date, 'YYYY-MM-DD')) " +
            "ORDER BY EXTRACT(MONTH FROM TO_DATE(d.order_date, 'YYYY-MM-DD'))", nativeQuery = true)
    List<Object[]> countByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);



}
