package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.model.Holiday;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest
public class HolidaysRepositoryTest {

    @Autowired
    private HolidaysRepository holidaysRepository;

    @Test
    @Transactional
    @Rollback
    public void testFetchHoliday(){
        String holiday = " Jan 1 ";

        Optional<Holiday> fetchedHolidayOptional = holidaysRepository.findById(holiday);

        Assertions.assertTrue(fetchedHolidayOptional.isPresent());

        Holiday fetchedHoliday = fetchedHolidayOptional.get();

        Assertions.assertEquals(" Jan 1 ", fetchedHoliday.getDay());
        Assertions.assertEquals("New Year's Day", fetchedHoliday.getReason());
        Assertions.assertEquals(Holiday.Type.FESTIVAL, fetchedHoliday.getType());

        System.out.println("Fetched Holiday: " + fetchedHoliday);
    }
}
