package com.capstone.vectorvisioninstitute.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Data
@Entity
@Table(name = "class")
public class ClassDetails extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int classId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    /**
     * Represents a bidirectional one-to-many relationship between Class and Person entities.
     * The FetchType is set to LAZY to avoid potential performance issues.
     * For example, in a scenario where a Class has a large number of people/students,
     * using LAZY loading prevents loading all students at once, improving performance.
     * CascadeType.PERSIST ensures that when saving a Class object, associated Person details
     * are persisted within the same transaction, preventing cascade effects for delete or update operations.
     * The targetEntity specifies the type of entities in the relationship, in this case, Person.
     */
    @OneToMany(mappedBy = "classDetails", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private Set<Person> persons;

}
