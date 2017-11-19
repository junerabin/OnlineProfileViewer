package com.mum.paper.clip.dao;

import java.util.Date;
import java.util.List;

import com.mum.paper.clip.model.DynamicTemplate;
import com.mum.paper.clip.model.FeedBack;

public interface AdminDao {
	
	public DynamicTemplate manageDynamicTemplate(DynamicTemplate dynamicTemplate); // save and edit (Edit for IsActive)
	public List<FeedBack> getFeedBackList(Date fromDate, Date toDate);
	public List<DynamicTemplate> getDynamicTemplateList();

}
