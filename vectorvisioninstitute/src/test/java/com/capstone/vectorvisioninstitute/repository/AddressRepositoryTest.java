package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.model.Address;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Transactional
    @Rollback
    public void testSaveAddress(){
        Address address = new Address();
        address.setAddress1("123 Main St");
        address.setCity("Peoria");
        address.setState("Arizona");
        address.setZipCode("12345");

        address.setCreatedAt(LocalDateTime.now());
        address.setCreatedBy("testUser");

        Address savedAddress = addressRepository.save(address);

        Address fetchAddress = addressRepository.findById(savedAddress.getAddressId()).orElse(null);

        Assertions.assertNotNull(fetchAddress);
        Assertions.assertEquals("123 Main St", fetchAddress.getAddress1());


    }
}
