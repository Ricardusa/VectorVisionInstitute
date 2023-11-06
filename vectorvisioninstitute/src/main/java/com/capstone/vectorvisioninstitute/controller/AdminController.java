package com.capstone.vectorvisioninstitute.controller;

import com.capstone.vectorvisioninstitute.model.ClassDetails;
import com.capstone.vectorvisioninstitute.model.Person;
import com.capstone.vectorvisioninstitute.repository.ClassDetailsRepository;
import com.capstone.vectorvisioninstitute.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    ClassDetailsRepository classDetailsRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        //fetch all classes with the class details
        List<ClassDetails> cDetails = classDetailsRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("cDetails", cDetails);
        modelAndView.addObject("classDetails", new ClassDetails());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("classDetails") ClassDetails classDetails){
        classDetailsRepository.save(classDetails);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    /**
     * Handles the deletion of a class based on the provided class ID.
     * Ensures that associated persons' foreign key references to the class are set to null
     * before deleting the class to prevent the deletion of people.
     *
     * @param model The Model object for adding attributes.
     * @param id The class ID to be deleted.
     * @return ModelAndView representing the redirection to "/admin/displayClasses".
     */
    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id){
        // Retrieve the ClassDetails object associated with the provided ID
        Optional<ClassDetails> classDetails = classDetailsRepository.findById(id);

        // Ensure all persons associated with the class have their foreign key references set to null
        for(Person person : classDetails.get().getPersons()){
            person.setClassDetails(null);
            personRepository.save(person); // Save the updated person details
        }

        // Delete the class record from the database
        classDetailsRepository.deleteById(id);

        // Redirect to the "/admin/displayClasses" path
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    /**
     * Handles the display of students associated with a specific class based on the provided class ID.
     * This method accepts a classId parameter and directs the admin to a view named students.html.
     * Future enhancements to the functionality can be implemented here.
     *
     * @param model The Model object for adding attributes.
     * @param classId The ID of the class for which students are to be displayed.
     * @return ModelAndView representing the view "students.html" for displaying associated students.
     */
    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId){
        // Return ModelAndView for the "students.html" view
        ModelAndView modelAndView = new ModelAndView("students.html");
        return modelAndView;
    }
}
