package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.model.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Integer> {


}
