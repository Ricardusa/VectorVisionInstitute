package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.constants.VectorVisionConstants;
import com.capstone.vectorvisioninstitute.model.Contact;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

@SpringBootTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    @Transactional
    @Rollback
    public void testSaveContact(){
        Contact contact = new Contact();
        contact.setName("John Doe");
        contact.setMobileNum("1234567890");
        contact.setEmail("john@email.com");
        contact.setSubject("Test Subject");
        contact.setMessage("This is a test message!");
        contact.setStatus(VectorVisionConstants.OPEN);

        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy("testUser"); //Anonymous user

        Contact savedContact = contactRepository.save(contact);

        Contact fetchedContact = contactRepository.findById(savedContact.getContactId()).orElse(null);

        Assertions.assertNotNull(fetchedContact);
        Assertions.assertEquals("John Doe", fetchedContact.getName());


    }
}
