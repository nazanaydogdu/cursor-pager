package com.kboxglobal.naydogdu.assignment.repository;

import com.kboxglobal.naydogdu.assignment.entity.ViewArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewArchiveRepository extends JpaRepository<ViewArchive, Long> {
}
