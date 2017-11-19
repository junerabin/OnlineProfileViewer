package com.mum.paper.clip.dao;

import java.util.List;

import com.mum.paper.clip.model.Customer;
import com.mum.paper.clip.model.DynamicData;
import com.mum.paper.clip.model.Education;
import com.mum.paper.clip.model.FeedBack;
import com.mum.paper.clip.model.Skill;
import com.mum.paper.clip.model.SpeakingLanguage;
import com.mum.paper.clip.model.StandardTemplate;
import com.mum.paper.clip.model.WorkExperience;

public interface CustomerDao {

	/**
	 * searched by applied position, zip code, state and get data from standard
	 * template without login
	 * 
	 * @param keyword
	 * @return
	 */
	public List<Customer> getStandardTemplateList(String keyword);

	public StandardTemplate getStandardTemplate(Integer customerId);

	public void insertStandardTemplate(StandardTemplate standard, Integer customerId);

	public void updateStandardTemplate(StandardTemplate standard, Integer customerId);

	public List<SpeakingLanguage> getSpeakingLanguage(Integer customerId);

	public List<DynamicData> getDynamicData(Integer customerId);

	public List<Skill> getSkills(Integer customerId);

	public void sendFeedBack(FeedBack feedBack);

	public void insertWorkExperiences(Customer customer);

	public Boolean insertSpeakingLanguage(SpeakingLanguage sp, Integer customerId);

	public void insertDynamicData(Customer customer);

	public Boolean insertSkill(Skill skill, Integer customerId);

	public void insertEducation(Education education, Integer personId);

	public Boolean deleteSpeakingLanguage(SpeakingLanguage sp, Integer customerId);

	public Boolean deleteSkill(Skill skill, Integer customerId);

	public Boolean deleteEducation(Integer educationId);

	public List<WorkExperience> getWorkExperiences(Integer customerId);

	public List<Education> getEducations(Integer customerId);
	
	public void deleteWorkExperiences(Customer customer);
	
	public void deleteSingleWorkingExperience(int personId, int experienceId);
	
	public void insertSingleWorkExperience(int personId, WorkExperience experience);

}
