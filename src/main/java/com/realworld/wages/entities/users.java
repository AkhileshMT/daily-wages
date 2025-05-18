package com.realworld.wages.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", nullable = false, updatable = false)
	private Long userId;
	
	@Column(name = "firstName", nullable = false, length = 50)
	private String firstName;

	/** The lastName. */
	@Column(name = "lastName", nullable = false, length = 50)
	private String lastName;

	/** The userName. */
	@Column(name = "userName", nullable = false, unique = true, columnDefinition = "varchar(50) default 'userName'")
	private String userName = "userName";

	/** The password. */
	@Column(name = "password", nullable = false, length = 100)
	private String password;

	/** The type. */
	@Column(name = "type", nullable = false, length = 50)
	private String type;

	/** The email. */
	@Email(message = "Email should be valid")
	@Column(name = "email", nullable = false, length = 50, unique = true)
	private String email;

	/** The isActive. */
	@Column(name = "isActive", nullable = false, length = 10)
	private String active;

	/** The createdBy. */
	// @JsonBackReference
	@Column(name = "createdBy")
	private Long createdBy;

	/** The created date. */
	// @Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "createdDate", nullable = false, updatable = false)
	private Date createdDate;

	/** The modifiedBy. */
	// @JsonBackReference
	@Column(name = "modifiedBy")
	private Long modifiedBy;

	/** The modified date. */
	// @Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "modifiedDate", nullable = false)
	private Date modifiedDate;

	/** The Users1 */

}
