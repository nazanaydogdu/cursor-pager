package com.kboxglobal.naydogdu.assignment.repository;

import com.kboxglobal.naydogdu.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findTop20ByIdAfterOrderByIdAsc(Long id);
}
