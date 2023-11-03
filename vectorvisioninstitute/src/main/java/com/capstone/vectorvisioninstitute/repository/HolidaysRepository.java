package com.capstone.vectorvisioninstitute.repository;

import com.capstone.vectorvisioninstitute.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//CrudRepository<Class, Data Type of primary key>
@Repository
public interface HolidaysRepository  extends CrudRepository<Holiday, String> {

}
