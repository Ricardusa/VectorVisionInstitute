package com.capstone.vectorvisioninstitute.controller;

import com.capstone.vectorvisioninstitute.model.ClassDetails;
import com.capstone.vectorvisioninstitute.model.Person;
import com.capstone.vectorvisioninstitute.repository.ClassDetailsRepository;
import com.capstone.vectorvisioninstitute.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
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
     * Additionally, it accepts an optional error parameter to handle error messages when adding a student.
     * Future enhancements to the functionality can be implemented here.
     *
     * @param model The Model object for adding attributes.
     * @param classId The ID of the class for which students are to be displayed.
     * @param session HttpSession for handling session-related operations.
     * @param error Optional query parameter indicating a failed student addition with "error=true".
     * @return ModelAndView representing the view "students.html" for displaying associated students.
     */
    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error){

        // Return ModelAndView for the "students.html" view
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<ClassDetails> classDetails = classDetailsRepository.findById(classId);
        //.get() to get the actual object that is stored inside Optional
        modelAndView.addObject("classDetails", classDetails.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("classDetails", classDetails.get());

        // Check if there is an error and add an error message to the ModelAndView if applicable
        String errorMessage = null;
        if(error != null){
            errorMessage = "Invalid Email entered!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    /**
     * Handles the addition of a student. Accepts a POST request at the path "/addStudent".
     *
     * @param model The Model object for adding attributes.
     * @param person An object annotated with @ModelAttribute representing the details of the student.
     * @param session HttpSession for handling session-related operations.
     * @return ModelAndView representing the appropriate view based on the outcome of the student addition.
     */
    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        // Retrieve the classDetails from the session attribute
        ClassDetails classDetails = (ClassDetails) session.getAttribute("classDetails");
        Person personEntity = personRepository.readByEmail(person.getEmail());

        // Check if the retrieved personEntity is null or has an invalid personId
        if(personEntity == null || !(personEntity.getPersonId()> 0)){
            // Redirect to the "admin/displayStudents" view with error=true if the personEntity is not valid
            // This condition handles scenarios where the personEntity is not found in the database or has an invalid personId.
            // In such cases, the admin is redirected to the "displayStudents" view with an error flag indicating a failed student addition.
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+ classDetails.getClassId()
                + "&error=true");
            return modelAndView;
        }
        personEntity.setClassDetails(classDetails);
        personRepository.save(personEntity);

        // Add the personEntity to the classDetails and save the updated classDetails
        // Establishes a relationship between the classDetails and the newly added personEntity.
        // The personEntity is added to the list of persons in classDetails.
        classDetails.getPersons().add(personEntity);
        classDetailsRepository.save(classDetails);

        // Redirect to the "admin/displayStudents" view after successful addition
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+classDetails.getClassId());
        return modelAndView;
    }

    /**
     * Handles the deletion of a student. Accepts a GET request at the path "/deleteStudent".
     * Retrieves the person with the specified personId from the database and breaks the relationship
     * between the person and the class.
     *
     * @param model The Model object for adding attributes.
     * @param personId The ID of the person to be deleted, received as a query parameter.
     * @param session HttpSession for handling session-related operations.
     * @return ModelAndView representing the redirect view to "admin/displayStudents" with the current classId.
     */
    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session){
        // Retrieve the classDetails from the session attribute
        ClassDetails classDetails = (ClassDetails) session.getAttribute("classDetails");

        // Retrieve the person with the specified personId from the database
        Optional<Person> person = personRepository.findById(personId);

        // Break the relationship between the person and the class by setting classDetails to null
        person.get().setClassDetails(null);

        // Remove the person from the list of associated persons in classDetails
        classDetails.getPersons().remove(person.get());

        // Save the updated classDetails to the database
        ClassDetails classDetailsSaved = classDetailsRepository.save(classDetails);

        // Set the latest classDetailsSaved object into the session attribute
        session.setAttribute("classDetails", classDetailsSaved);

        // Redirect to the "admin/displayStudents" view with the current classId after successful deletion
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+classDetails.getClassId());
        return modelAndView;
    }
}
