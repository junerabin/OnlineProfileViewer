package com.mum.paper.clip.model;

import java.io.Serializable;

public class DynamicData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4073590498620241499L;
	private Integer dynamicId;
	private String fieldName; // no space data
	private String inputType;
	private String description;
	private String labelName; // space data
	private String value;

	public Integer getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

}
