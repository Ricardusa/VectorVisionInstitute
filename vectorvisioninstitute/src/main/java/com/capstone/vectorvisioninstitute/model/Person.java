package com.capstone.vectorvisioninstitute.model;

import com.capstone.vectorvisioninstitute.annotation.FieldsValueMatch;
import com.capstone.vectorvisioninstitute.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@FieldsValueMatch.List({ //perform comparison between fields from FieldsValueMatch class
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "ConfirmPwd",
                message = "Passwords don't match!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email addresses don't match!"
        )
})
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int personId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile Number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide valid email address")
    private String email;

    @NotBlank(message = "Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address")
    @Transient //no database related operations like save
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank")
    @Size(min=5, message = "Password must be at least 5 characters long")
    @PasswordValidator //our own validation
    private String pwd;

    @NotBlank(message = "Confirm Password must not be blank")
    @Size(min=5, message = "Confirm Password must be at least 5 characters long")
    @Transient //no database related operations like save
    private String confirmPwd;

    /**
     * When performing operations (other than Persist) on a Person, we want to avoid applying the same operation to roles.
     * To keep roles unchanged, CascadeType.PERSIST is used.
     *
     * This means that when persisting a Person, the associated roles will not be affected by other operations.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name ="role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    /** Our Person Pojo class is the parent and the Address class is the child
     *      in our case we are establishing a 1 to 1 relationship since
     *      One person can only have one address and vise versa.
     *      And since the foreign key relationship between the person
     *      and the Address tables is an optional, or it is a nullable = true
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name ="address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    /**
     * Represents a many-to-one relationship between Person and Class entities.
     * We use FetchType.LAZY to optimize performance, especially in scenarios where a large number
     * of persons are registered, preventing the unnecessary loading of class details.
     * Setting Optional to true acknowledges that the foreign key relationship is nullable.
     * In our context, during user registration, a person may not be associated with any class by default,
     * and the class assignment typically occurs later through administrative actions in the web application.
     * No cascade configurations are specified since there's no use case for saving class information
     * with the help of a Person entity. Class records are created separately by administrators,
     * and persons are added to existing classes afterward.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private ClassDetails classDetails;


    /**
     * The 'courses' field is a Set of Courses, representing a collection since a person can enroll in multiple courses.
     * For the ManyToMany relationship, we use a @JoinTable annotation to specify details about the intermediate table.
     * In this case, the intermediate table is named 'person_courses'.
     *
     * - joinColumns: Configures columns belonging to this entity (Person).
     *   - name: The column name in the database, which is "person_id".
     *   - referencedColumnName: Matches the field name in the Person Entity class, which is "personId".
     *
     * - inverseJoinColumns: Configures details about the other entity class (Courses).
     *   - name: The column name in the database, which is "course_id".
     *   - referencedColumnName: Matches the field name in the Courses Entity class, which is "courseId".
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_courses", // spring is smart enough to make this personCourses entity class itself which belong to the person_courses
            joinColumns = {
                @JoinColumn(name = "person_id", referencedColumnName = "personId")},
            inverseJoinColumns = {
                @JoinColumn(name = "course_id", referencedColumnName = "courseId")})
    private Set<Courses> courses = new HashSet<>();


}
