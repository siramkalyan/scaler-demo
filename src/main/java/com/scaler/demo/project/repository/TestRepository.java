package com.scaler.demo.project.repository;

import com.scaler.demo.project.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, String> {

//    @Query("select t from Test t where t.name like ?1")
//    Optional<Test> findByNameLike(String name);


}
