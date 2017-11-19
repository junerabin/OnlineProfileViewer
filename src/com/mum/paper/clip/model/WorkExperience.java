package com.mum.paper.clip.model;

import java.io.Serializable;
import java.util.Date;

public class WorkExperience implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8170194324324975004L;
	private Integer experienceId;
	private String title;
	private String company;
	private String location;
	private Date fromDate;
	private Date toDate;
	private String description;
	private Boolean IsCurrent;

	public Integer getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(Integer experienceId) {
		this.experienceId = experienceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsCurrent() {
		return IsCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		IsCurrent = isCurrent;
	}

}
