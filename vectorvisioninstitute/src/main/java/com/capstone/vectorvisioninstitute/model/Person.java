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

    /*since whenever we do an operation(other than Persist) to the person we don't
    want that same operation to be done to the roles as well, since
    we want to keep roles unchanged, CascadeType.PERSIST is used*/
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name ="role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    /** Our Person Pojo class is the parent and the Address class is the child
     *      in our case we are establishing a 1 to 1 relationship since
     *      One person can only have one address and vise versa.
     *      And since the foreign key relationship between the person
     *      and the Address tables is an optional, or it is a nullable = true
     */
    /* @OneToOne
     * EAGER = when we fetch the person record tables, spring data JPA will
     *   automatically fetch the child table records.
     * LAZY = when we fetch the person record tables, spring data JPA will
     *   not fetch the child table records automatically. it will only load the person related records.
     * CascadeType.ALL = if we are doing Insert, Update or Delete operations on the parent
     *   the same operations we want spring data JPA to do to the child entity class.
     * 1. First we must establish a FetchType so do we want to fetch the child
     *    entities either eagerly or lazily
     * 2. Next alongside with our fetch configurations we must also make cascade
     *    configurations.
     * 3. Alongside with our CascadeType configurations we mention the optional
     *    targetEntity which indirectly tells to spring data JPA that the targetEntity
     *    is of Address table (done for readability purposes).
     *
     * @JoinColumn
     * Without JoinColumn annotation our Spring Data JPA would be clueless to which
     *   column of person entity has a 1to1 relationship with which column Address entity.
     * 1. First we need to tell what is the column name that is present
     *    inside the person table which is address_id.
     * 2. Using referencedColumnName we have to communicate to spring data JPA which field
     *    inside the Address entity pojo class that this address_id column is present inside
     *    Person has a link to or a 1to1 relationship with that name is addressId.
     * 3. Last we mention whether this relationship is nullable or not, And since
     *    the Person and Address relationship that we have right now inside the tables is nullable.
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
     * Set<Courses> courses = new HashSet<>() >> courses field is also of type collection since a person can enroll
     * into multiple courses.
     * @JoinTable >> we use jointable since we are going to have an intermediate table inside our ManyToMany relationship,
     * we need to tell the details of the intermediate table to my spring JPA framework with the help of the @JoinTable annotation.
     * First we need to tell what is the name of the intermediate table name, which is person_courses.
     * joinColumns >> we need to configure two configurations first we have joinColumns which we are going to configure
     * that the columns belong to this entity which Person. so what is the column name in the DataBase which is
     * "person_id" and for the referenceColumnName we have to make sure we match the field name with the field name
     * that we have in the Person Entity class which is "personId".
     * inverseJoinColumns >> here we need to provide the details about the other entity class which is Courses.
     * we do the same name = "course_id", referencedColumnName = "courseId".
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_courses", // spring is smart enough to make this personCourses entity class itself which belong to the person_courses
            joinColumns = {
                @JoinColumn(name = "person_id", referencedColumnName = "personId")},
            inverseJoinColumns = {
                @JoinColumn(name = "course_id", referencedColumnName = "courseId")})
    private Set<Courses> courses = new HashSet<>();


}
