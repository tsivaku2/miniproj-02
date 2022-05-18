package in.ashokit.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "USER_ACCOUNT")
@Data
public class UserAccountEntity {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "FIRST_NAME")
	private String fname;
	
	@Column(name = "LAST_NAME")
	private String lname;
	
	@Column(name = "USER_EMAIL",unique = true)
	private String email;
	
	@Column(name = "USER_PWD")
	private String password;
	
	@Column(name = "USER_MOBILE")
	private Long phno;
	
	@Column(name = "DOB")
	private LocalDate dob;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "CITY_ID")
	private Integer cityid;
	
	@Column(name = "STATE_ID")
	private Integer stateid;
	
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "ACC_STATUS")
	private String accstatus;
	
	@Column(name = "CREATED_DATE",updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name = "UPDATED_DATE",insertable = false)
	@CreationTimestamp
	private LocalDate updatedDate;

		
	}
	

	
	
