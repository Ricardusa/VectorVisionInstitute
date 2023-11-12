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
     * The 'persons' field represents a collection of Person objects. It is a Set because multiple persons
     * can enroll in the same course, and maintaining this as a set ensures uniqueness.
     *
     * - fetch = FetchType.EAGER: When fetching courses, we also fetch the associated persons for immediate access.
     *
     * - cascade = CascadeType.PERSIST: If we delete a course, we don't want to delete the associated persons.
     *   This cascade type is used to prevent unwanted deletions of persons when deleting a course entity.
     */
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Person> persons = new HashSet<>();
}
