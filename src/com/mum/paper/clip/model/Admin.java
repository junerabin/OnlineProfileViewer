package com.mum.paper.clip.model;

import java.io.Serializable;
import java.util.List;

public class Admin extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2594392900633522639L;
	private List<DynamicTemplate> dynamicTemplates;
	private List<FeedBack> feedBacks;

	public List<DynamicTemplate> getDynamicTemplates() {
		return dynamicTemplates;
	}

	public void setDynamicTemplates(List<DynamicTemplate> dynamicTemplates) {
		this.dynamicTemplates = dynamicTemplates;
	}

	public List<FeedBack> getFeedBacks() {
		return feedBacks;
	}

	public void setFeedBacks(List<FeedBack> feedBacks) {
		this.feedBacks = feedBacks;
	}

}
