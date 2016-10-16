package com.usercrud.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
 
@Entity
@Table(name="users")
public class User extends BaseEntity {
    
    @NotNull
    @Size(min=3, max=50)
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @NotNull
    @Size(min=3, max=50)
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @NotNull
    @Size(min=3, max=50)
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private Date birthDate;
    
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
