package com.capstone.vectorvisioninstitute.service;

import com.capstone.vectorvisioninstitute.constants.VectorVisionConstants;
import com.capstone.vectorvisioninstitute.model.Person;
import com.capstone.vectorvisioninstitute.model.Roles;
import com.capstone.vectorvisioninstitute.repository.PersonRepository;
import com.capstone.vectorvisioninstitute.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(VectorVisionConstants.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd())); //encode users password
        person = personRepository.save(person);
        if(null != person && person.getPersonId() > 0){ //person record successfully inserted into the DB
            isSaved = true;
        }
        return isSaved;
    }
}
