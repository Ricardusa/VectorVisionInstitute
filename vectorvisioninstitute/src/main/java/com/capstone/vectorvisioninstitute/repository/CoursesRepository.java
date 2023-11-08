package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    //static sorting using order by keyword
    List<Courses> findByOrderByNameDesc();

    List<Courses> findByOrderByName();
}
