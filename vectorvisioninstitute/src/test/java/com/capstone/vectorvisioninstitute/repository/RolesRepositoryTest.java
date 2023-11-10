package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.model.Roles;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest
public class RolesRepositoryTest {

    @Autowired
    private RolesRepository rolesRepository;

    @Test
    @Transactional
    @Rollback
    public void testFetchRole() {
        // Fetching role from DB
        // roleId 1 = ADMIN
        // roleId 2 = STUDENT
        int roleId = 1;

        // Retrieve the role from the repository
        Optional<Roles> fetchedRoleOptional = rolesRepository.findById(roleId);

        // Assert that the fetched role is present
        Assertions.assertTrue(fetchedRoleOptional.isPresent());

        // Get the actual Roles object
        Roles fetchedRole = fetchedRoleOptional.get();

        // Assert that the fetchedRole has the expected values
        Assertions.assertEquals(roleId, fetchedRole.getRoleId());
        Assertions.assertEquals("ADMIN", fetchedRole.getRoleName());

        // Print the fetchedRole for verification
        System.out.println("Fetched Role: " + fetchedRole);
    }
}
