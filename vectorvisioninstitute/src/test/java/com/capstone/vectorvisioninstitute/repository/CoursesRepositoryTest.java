package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.model.Courses;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

@SpringBootTest
public class CoursesRepositoryTest {

    @Autowired
    private CoursesRepository coursesRepository;

    @Test
    @Transactional
    @Rollback
    public void testSaveCourse(){
        Courses course = new Courses();
        course.setName("Test Course");
        course.setFees("$ 1000");

        course.setCreatedAt(LocalDateTime.now());
        course.setCreatedBy("testUser");

        Courses savedCourse = coursesRepository.save(course);

        Courses fetchCourse = coursesRepository.findById(savedCourse.getCourseId()).orElse(null);

        Assertions.assertNotNull(fetchCourse);
        Assertions.assertEquals("Test Course", fetchCourse.getName());

    }
}
