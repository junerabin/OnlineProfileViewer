package com.mum.paper.clip.model;

import java.io.Serializable;

public class Education implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4775552524174779940L;
	private Integer educationId;
	private String school;
	private String degree;
	private String studyField;
	private String grade;
	private Integer year;

	public Integer getEducationId() {
		return educationId;
	}

	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getStudyField() {
		return studyField;
	}

	public void setStudyField(String studyField) {
		this.studyField = studyField;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
