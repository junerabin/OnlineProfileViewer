package com.mum.paper.clip.model;

import java.io.Serializable;

public class Skill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4848413868279294013L;
	private String skill;
	private String level;

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
