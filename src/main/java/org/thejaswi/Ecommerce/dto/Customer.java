package org.thejaswi.Ecommerce.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Component
@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "* It is Compulsory Field")
	@Size(min = 3, max = 10, message = "* Should be between 3 to 10 charecters")
	private String name;
	@NotEmpty(message = "* It is Compulsory Field")
	@Email(message = "* Enter Proper Email Format")
	private String email;
	@NotEmpty(message = "* It is Compulsory Field")
//	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "* Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
	private String password;
	@NotEmpty(message = "* It is Compulsory Field")
	private String gender;
	@NotNull(message = "* It is Compulsory Field")
	@DecimalMax(value = "9999999999", inclusive = true, message = "* Enter Proper Mobile Number")
	@DecimalMin(value = "6000000000", inclusive = true, message = "* Enter Proper Mobile Number")
	private long mobile;
	@NotNull(message = "* It is Compulsory Field")
	@Past(message = "* Enter proper Dob")
	private LocalDate dob;
	private String role;
	private int otp;
	private boolean verified;
	
}
