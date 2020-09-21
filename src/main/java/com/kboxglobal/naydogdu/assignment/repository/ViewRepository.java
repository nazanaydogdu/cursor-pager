package com.kboxglobal.naydogdu.assignment.repository;

import com.kboxglobal.naydogdu.assignment.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    List<View> findTop20ByDateBeforeOrderByDateDesc (Date next);

    @Query(value = "SELECT * FROM View v WHERE v.viewee_id = :vieweeId AND v.viewer_id <> :vieweeId AND v.date < :next ORDER BY v.date DESC LIMIT 20", nativeQuery = true )
    List<View> findViews(@Param("vieweeId") Long vieweeId, Date next);

    List<View> findAllByDateBefore(Date date);
}
