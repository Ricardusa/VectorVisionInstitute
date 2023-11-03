package com.capstone.vectorvisioninstitute.model;

import com.capstone.vectorvisioninstitute.annotation.FieldsValueMatch;
import com.capstone.vectorvisioninstitute.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
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



}
