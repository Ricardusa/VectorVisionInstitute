package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.constants.VectorVisionConstants;
import com.capstone.vectorvisioninstitute.model.Person;
import com.capstone.vectorvisioninstitute.model.Roles;
import com.capstone.vectorvisioninstitute.service.PersonService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    @Rollback
    public void testSaveStudentPerson() {
        // Create a sample Person object
        Person person = new Person();
        person.setName("John Doe");
        person.setMobileNumber("1234567890");
        person.setEmail("john.doe@example.com");
        person.setPwd(passwordEncoder.encode("password12345"));
        person.setConfirmPwd(passwordEncoder.encode("password12345"));

        // Retrieve the student role
        Roles studentRole = rolesRepository.getByRoleName(VectorVisionConstants.STUDENT_ROLE);
        person.setRoles(studentRole); //set role of user to Student
        // Ensure the student role is not null before setting it in the Person entity
        if (studentRole != null) {
            person.setRoles(studentRole); // Set student role
        } else {
            // Handle the case where the student role is not found
            // You might want to log an error or throw an exception
            return;
        }

        // Set auditing fields
        person.setCreatedAt(LocalDateTime.now());
        person.setCreatedBy("testUser"); // Set the user who created the entity

        // Save the person to the repository
        Person savedPerson = personRepository.save(person);

        // Retrieve the saved person from the repository
        Person retrievedPerson = personRepository.findById(savedPerson.getPersonId()).orElse(null);

        // Assert that the retrieved person is not null and has the same name
        assertEquals("John Doe", retrievedPerson.getName());
        // Add more specific assertions if needed
    }

}