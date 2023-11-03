package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    //readBy - findBy - queryBy
    Person readByEmail(String email);
}
