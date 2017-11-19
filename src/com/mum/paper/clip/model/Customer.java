package com.mum.paper.clip.model;

import java.io.Serializable;
import java.util.List;

public class Customer extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3559728256110465166L;
	private String firstName;
	private String lastName;
	private String middleName;
	private String contactNo;
	private String email;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private StandardTemplate standardTemplate;
	private List<WorkExperience> workExps;
	private List<Education> educations;
	private List<DynamicData> dynamicData;
	private List<SpeakingLanguage> speakingLanguages;
	private List<Skill> skills;

	public StandardTemplate getStandardTemplate() {
		return standardTemplate;
	}

	public void setStandardTemplate(StandardTemplate standardTemplate) {
		this.standardTemplate = standardTemplate;
	}

	public List<WorkExperience> getWorkExps() {
		return workExps;
	}

	public void setWorkExps(List<WorkExperience> workExps) {
		this.workExps = workExps;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public List<DynamicData> getDynamicData() {
		return dynamicData;
	}

	public void setDynamicData(List<DynamicData> dynamicData) {
		this.dynamicData = dynamicData;
	}

	public List<SpeakingLanguage> getSpeakingLanguages() {
		return speakingLanguages;
	}

	public void setSpeakingLanguages(List<SpeakingLanguage> speakingLanguages) {
		this.speakingLanguages = speakingLanguages;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
