package com.mum.paper.clip.model;

import java.io.Serializable;

public class SpeakingLanguage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6535295338693404688L;
	private String languageName;
	private String proficiency;

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}

}
