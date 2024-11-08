package com.example.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "userProfiles")
public class UserProfile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Gender is required")
	private String gender;
	
	@NotBlank(message = "City is required")
    @Size(max = 100, message = "City must be less than 100 characters")
	private String city;
	
	@NotNull(message = "State is required")
	private String state;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<EducationDetail> educationDetails;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] profilePhoto;
	
	@NotEmpty(message = "Skills are required")
    @ElementCollection
	private List<String> skills;
	
	@ElementCollection
	private List<byte[]> certificates;
	
	@NotBlank(message = "Profession is required")
	private String profession;

	@NotBlank(message = "companyName is required")
    private String companyName;
    
	@Temporal(TemporalType.DATE)
    private Date jobStartedDate;
    
    @NotBlank(message = "Mobile number is required")
    private String businessName;
    
    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp="\\d{10}", message="Mobile number must be 10 digits")
    private String mobileNo;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<EducationDetail> getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(List<EducationDetail> educationDetails) {
		this.educationDetails = educationDetails;
	}

	public byte[] getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(byte[] profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<byte[]> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<byte[]> certificates) {
		this.certificates = certificates;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getJobStartedDate() {
		return jobStartedDate;
	}

	public void setJobStartedDate(Date jobStartedDate) {
		this.jobStartedDate = jobStartedDate;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
}
