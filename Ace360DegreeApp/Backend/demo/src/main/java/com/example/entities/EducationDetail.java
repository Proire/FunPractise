package com.example.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "EducationDetails")
public class EducationDetail {
	 	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String education;

	    @Temporal(TemporalType.DATE)
	    private Date yearOfCompletion;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private UserProfile user;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getEducation() {
			return education;
		}

		public void setEducation(String education) {
			this.education = education;
		}

		public Date getYearOfCompletion() {
			return yearOfCompletion;
		}

		public void setYearOfCompletion(Date yearOfCompletion) {
			this.yearOfCompletion = yearOfCompletion;
		}

		public UserProfile getUser() {
			return user;
		}

		public void setUser(UserProfile user) {
			this.user = user;
		}
}
