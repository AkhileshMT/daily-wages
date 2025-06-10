package com.realworld.wages.entities;

import java.util.Date;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false, unique = true, length = 50)
	private String userName;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String type;

	@Email
	@Column(nullable = false, length = 50, unique = true)
	private String email;

	@Column(name = "isActive", nullable = false, length = 10)
	private String active;

	private String role;

	private Long createdBy;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Date createdDate;

	private Long modifiedBy;

	@UpdateTimestamp
	@Column(nullable = false)
	private Date modifiedDate;

}
