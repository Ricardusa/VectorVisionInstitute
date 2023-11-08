package com.capstone.vectorvisioninstitute.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Courses extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int courseId;

    private String name;

    private String fees;

    /**
     * Set<Person> persons >> these fields should be a collection object of set because multiple persons
     * enroll to the same course, that is why we should always maintain this as a set of persons.
     * fetch = FetchType.EAGER >> since when we want to fetch the courses we also fetch the persons associated
     * with those courses
     * cascade = CascadeType.PERSIST >> since if we delete a course we don't want to delete the persons
     * associated with that course as entity.
     */
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Person> persons = new HashSet<>();
}
