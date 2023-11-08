package com.capstone.vectorvisioninstitute.controller;

import com.capstone.vectorvisioninstitute.model.Person;
import com.capstone.vectorvisioninstitute.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    //fetch details of person registered to get persons information
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session){
        //details of user logged in will fetch by email of user
        //since there might be users with the same name
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        //check if their are any classes assigned to this person
        //by checking the getClassDetails(), if it not null.
        //next check if the class name present inside the class details is not null
        if(null != person.getClassDetails() && null != person.getClassDetails().getName()){
            model.addAttribute("enrolledClass", person.getClassDetails().getName());
        }
        session.setAttribute("loggedInPerson", person); //allows us to get the persons information between different controllers
        return "dashboard.html";
    }
}
