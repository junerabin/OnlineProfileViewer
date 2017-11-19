package com.mum.paper.clip.model;

import java.io.Serializable;

public class StandardTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1689554515460764087L;
	private Integer templateId;
	private String linkedInURL;
	private String appliedPosition;
	private String aboutYourself;
	private String photoURL;
	private String attachmentURL;
	private String status;

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getLinkedInURL() {
		return linkedInURL;
	}

	public void setLinkedInURL(String linkedInURL) {
		this.linkedInURL = linkedInURL;
	}

	public String getAppliedPosition() {
		return appliedPosition;
	}

	public void setAppliedPosition(String appliedPosition) {
		this.appliedPosition = appliedPosition;
	}

	public String getAboutYourself() {
		return aboutYourself;
	}

	public void setAboutYourself(String aboutYourself) {
		this.aboutYourself = aboutYourself;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public String getAttachmentURL() {
		return attachmentURL;
	}

	public void setAttachmentURL(String attachmentURL) {
		this.attachmentURL = attachmentURL;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
