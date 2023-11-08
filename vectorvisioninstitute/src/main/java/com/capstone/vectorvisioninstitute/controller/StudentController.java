package com.capstone.vectorvisioninstitute.controller;

import com.capstone.vectorvisioninstitute.model.Courses;
import com.capstone.vectorvisioninstitute.model.Person;
import com.capstone.vectorvisioninstitute.repository.ClassDetailsRepository;
import com.capstone.vectorvisioninstitute.repository.CoursesRepository;
import com.capstone.vectorvisioninstitute.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model, HttpSession session){
        //get current logged in user person details (dashboard controller)
        Person person = (Person) session.getAttribute("loggedInPerson");
        //take student to courses_enrolled.html
        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
        modelAndView.addObject("person", person);
        return modelAndView;
    }
}
