package com.crm2.payload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

//@Getter@Settera
@Data
public class EmployeeDto {
    private Long id;

    @NotBlank
    @Size(min = 3, message = "At least 3 character requred..." )
    private String name;

    @Email
    private String emailId;

    @Size(min = 10, max = 10, message = "Should be 10 digits/characters")
    private String mobile;

    //here i have created date bcs i don't want to save the date in database.
    private Date date;

}
