package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    /*
    spring will find inside the class Contact a field of status
     */
    List<Contact> findByStatus(String status); //Derived Query Method

}
