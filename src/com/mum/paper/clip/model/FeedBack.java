package com.mum.paper.clip.model;

import java.io.Serializable;
import java.util.Date;

public class FeedBack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8913573158492034692L;
	private Integer feedBackId;
	private Integer personId;
	private String feedBackType;
	private String message;
	private String name;
	private String email;
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(Integer feedBackId) {
		this.feedBackId = feedBackId;
	}

	public String getFeedBackType() {
		return feedBackType;
	}

	public void setFeedBackType(String feedBackType) {
		this.feedBackType = feedBackType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}