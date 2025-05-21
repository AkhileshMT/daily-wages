package com.realworld.wages.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class userDto extends RepresentationModel<userDto>{

    private Long userId; // Long // Id or Primary Key

    /** The firstName. */
    // private Date createdate; // Date
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName; // String

    /** The lastName. */
    @NotNull
    @Size(min = 1, max = 50)
    private String lastName; // String

    /** The userName. */
    @NotNull
    @Size(min = 8, max = 50)
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /** The type. */
    @NotNull
    @Size(min = 1, max = 50)
    private String type; // String

    /** The email. */
    @NotNull
    @Email(message = "Email should be valid")
    @Size(min = 6, max = 50)
    private String email; // String

    /** The isActive. */
    @NotNull
    @Size(min = 1, max = 10)
    private String active; // String


}
