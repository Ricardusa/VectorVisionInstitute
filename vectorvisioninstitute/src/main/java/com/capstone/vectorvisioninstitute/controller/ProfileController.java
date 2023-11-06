package com.capstone.vectorvisioninstitute.controller;

import com.capstone.vectorvisioninstitute.model.Address;
import com.capstone.vectorvisioninstitute.model.Person;
import com.capstone.vectorvisioninstitute.model.Profile;
import com.capstone.vectorvisioninstitute.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    PersonRepository personRepository;

    /**
     * Displays the user profile on the profile.html page.
     * Retrieves user information from the session-stored Person object and populates a Profile object.
     *
     * @param model   The model to hold data for the view.
     * @param session HttpSession for managing session data.
     * @return ModelAndView for displaying the profile.html page.
     */
    @RequestMapping("/displayProfile")
    public ModelAndView displayMessages(Model model, HttpSession session){
        Person person = (Person) session.getAttribute("loggedInPerson");

        //Populate person information into a Profile object.
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        profile.setEmail(person.getEmail());

        //Check whether address details are available inside Person object
        if(person.getAddress() != null && person.getAddress().getAddressId() > 0){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        ModelAndView modelAndView = new ModelAndView("profile.html"); //display profile page
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    /**
     * Handles the POST request from profile.html to update user profile information.
     * Validates the form data using Spring MVC annotations (@Valid, @ModelAttribute) and captures errors.
     * Utilizes HttpSession for session management.
     *
     * @param profile The Profile object with updated user information from the UI.
     * @param errors  Holds validation errors based on annotations in the Profile object.
     * @param session HttpSession for managing session data.
     * @return Redirects to profile.html in case of validation errors.
     *         Updates user details in the session-stored Person object if successful.
     */
    @PostMapping(value = "/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors,
                                HttpSession session){
        if(errors.hasErrors()){
            return "profile.html";
        }
        Person person = (Person) session.getAttribute("loggedInPerson");
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());

        /* Checks if the person already has an address record in the database.
         * If no address information is found, initializes an empty Address object.
         * This step is crucial for setting address-related fields (address1, address2,
         * city, state, zipCode) using the getAddress() method to avoid NullPointerException.*/
        if(person.getAddress() == null || !(person.getAddress().getAddressId() > 0)){
            person.setAddress(new Address());
        }

        // Copies address details from the Profile to the Person object.
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());

        // Saves information into the database.
        personRepository.save(person);
        session.setAttribute("loggedInPerson", person); //reset to ensure latest Person pojo class.
        return "redirect:/displayProfile";
    }
}
