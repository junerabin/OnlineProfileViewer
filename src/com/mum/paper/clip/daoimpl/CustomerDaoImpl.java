package com.mum.paper.clip.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mum.paper.clip.dao.CustomerDao;
import com.mum.paper.clip.db.ConnectionHelper;
import com.mum.paper.clip.model.Customer;
import com.mum.paper.clip.model.DynamicData;
import com.mum.paper.clip.model.Education;
import com.mum.paper.clip.model.FeedBack;
import com.mum.paper.clip.model.Skill;
import com.mum.paper.clip.model.SpeakingLanguage;
import com.mum.paper.clip.model.StandardTemplate;
import com.mum.paper.clip.model.WorkExperience;
import com.mum.paper.clip.util.Utility;

public class CustomerDaoImpl implements CustomerDao {

	private ConnectionHelper connection;
	/* private static CustomerDaoImpl instance = new CustomerDaoImpl(); */

	private CustomerDaoImpl() {
		/* this.connection = ConnectionHelper.getInstance(); */
		this.connection = new ConnectionHelper();
	}

	public static CustomerDaoImpl getInstance() {
		return new CustomerDaoImpl();
	}

	@Override
	public List<Customer> getStandardTemplateList(String keyword) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;
		List<Customer> customerList = new ArrayList<>();

		try {

			String sql = null;

			ResultSet result = null;

			if (keyword != null && keyword.equals("") == false) {
				sql = "SELECT distinct b.PersonId, b.FirstName, b.LastName, b.MiddleName, b.ContactNo, b.Email, b.Street1, b.Street2, b.City, b.State,"
						+ "b.Country, b.ZipCode, a.LinkedInURL, a.AppliedPosition, a.AboutYourself, a.PhotoURL, a.AttachmentURL, a.Status"
						+ " FROM StandardTemplate a, Person b WHERE a.PersonId = b.PersonId AND b.Type='Customer'"
						+ " AND lower(ZipCode) LIKE ? OR lower(State) LIKE ? OR lower(AppliedPosition) LIKE ?";

				pre = con.prepareStatement(sql);
				pre.setString(1, "%" + keyword.toLowerCase() + "%");
				pre.setString(2, "%" + keyword.toLowerCase() + "%");
				pre.setString(3, "%" + keyword.toLowerCase() + "%");

			} else {

				sql = "SELECT distinct b.PersonId, b.FirstName, b.LastName, b.MiddleName, b.ContactNo, b.Email, b.Street1, b.Street2, b.City, b.State,"
						+ "b.Country, b.ZipCode, a.LinkedInURL, a.AppliedPosition, a.AboutYourself, a.PhotoURL, a.AttachmentURL, a.Status"
						+ " FROM StandardTemplate a, Person b WHERE a.PersonId = b.PersonId";

				pre = con.prepareStatement(sql);
			}

			result = pre.executeQuery();

			while (result.next()) {

				Customer customer = new Customer();
				customer.setPersonId(result.getInt(1));
				customer.setFirstName(result.getString(2));
				customer.setLastName(result.getString(3));
				customer.setMiddleName(result.getString(4));
				customer.setContactNo(result.getString(5));
				customer.setEmail(result.getString(6));
				customer.setStreet1(result.getString(7));
				customer.setStreet2(result.getString(8));
				customer.setCity(result.getString(9));
				customer.setState(result.getString(10));
				customer.setCountry(result.getString(11));
				customer.setZipCode(result.getString(12));
				customer.setStandardTemplate(new StandardTemplate());
				customer.getStandardTemplate().setLinkedInURL(result.getString(13));
				customer.getStandardTemplate().setAppliedPosition(result.getString(14));
				customer.getStandardTemplate().setAboutYourself(result.getString(15));
				customer.getStandardTemplate().setPhotoURL(result.getString(16));
				customer.getStandardTemplate().setAttachmentURL(result.getString(17));
				customer.getStandardTemplate().setStatus(result.getString(18));

				customer.setSkills(getSkills(customer.getPersonId()));

				customerList.add(customer);

			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		} finally {
			if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}

		return customerList;
	}

	@Override
	public StandardTemplate getStandardTemplate(Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;
		StandardTemplate standardTemplate = new StandardTemplate();

		try {

			String sql = null;

			ResultSet result = null;

			sql = "SELECT a.LinkedInURL, a.AppliedPosition, a.AboutYourself, a.PhotoURL, a.AttachmentURL, a.Status, a.TemplateId"
					+ " FROM StandardTemplate a WHERE a.PersonId =?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, customerId);

			result = pre.executeQuery();

			if (result.next()) {

				standardTemplate.setLinkedInURL(result.getString(1));
				standardTemplate.setAppliedPosition(result.getString(2));
				standardTemplate.setAboutYourself(result.getString(3));
				standardTemplate.setPhotoURL(result.getString(4));
				standardTemplate.setAttachmentURL(result.getString(5));
				standardTemplate.setStatus(result.getString(6));
				standardTemplate.setTemplateId(result.getInt(7));

			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		} finally {
			if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			/*
			 * if (con != null) try { con.close(); } catch (SQLException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */

		}

		return standardTemplate;
	}
	
	@Override
	public void insertStandardTemplate(StandardTemplate standard, Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;
			ResultSet result = null;

			sql = "INSERT INTO StandardTemplate(PersonId, LinkedInURL, AppliedPosition, AboutYourself, PhotoURL, AttachmentURL, Status) "
					+ "VALUES(?,?,?,?,?,?,?)";
			pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setInt(1, customerId);
			pre.setString(2, standard.getLinkedInURL());
			pre.setString(3, standard.getAppliedPosition());
			pre.setString(4, standard.getAboutYourself());
			pre.setString(5, standard.getPhotoURL());
			pre.setString(6, standard.getAttachmentURL());
			pre.setString(7, standard.getStatus());

			i = pre.executeUpdate();

			if (i < 0) {
				con.rollback();
				System.out.println("Cannot insert standard template");
			}

			result = pre.getGeneratedKeys();
			result.next();
			standard.setTemplateId(result.getInt(1));

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		} finally {
			if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			/*
			 * if (con != null) try { con.close(); } catch (SQLException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */
		}

	}

	@Override
	public void updateStandardTemplate(StandardTemplate standard, Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;

			sql = "UPDATE StandardTemplate SET LinkedInURL =?,AppliedPosition=?,AboutYourself=?,PhotoURL=?,AttachmentURL=?,Status=?"
					+ "WHERE personId = " + customerId;

			pre = con.prepareStatement(sql);
			pre.setString(1, standard.getLinkedInURL());
			pre.setString(2, standard.getAppliedPosition());
			pre.setString(3, standard.getAboutYourself());
			pre.setString(4, standard.getPhotoURL());
			pre.setString(5, standard.getAttachmentURL());
			pre.setString(6, standard.getStatus());

			i = pre.executeUpdate();

			if (i < 0) {
				con.rollback();
				System.out.println("Cannot update Standard template");
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		} finally {
			if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			/*
			 * if (con != null) try { con.close(); } catch (SQLException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */
		}

	}

	@Override
	public void insertWorkExperiences(Customer customer) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;
			ResultSet result = null;
			if (customer.getWorkExps() != null && customer.getWorkExps().size() > 0) {

				for (WorkExperience exp : customer.getWorkExps()) {

					sql = "INSERT INTO WorkExperience(PersonId, Title, Company, Location, FromDate, ToDate, Description, IsCurrent) "
							+ "VALUES(?,?,?,?,?,?,?,?)";
					pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					pre.setInt(1, customer.getPersonId());
					pre.setString(2, exp.getTitle());
					pre.setString(3, exp.getCompany());
					pre.setString(4, exp.getLocation());
					pre.setDate(5, Utility.convertJavaDateToSqlDate(exp.getFromDate()));
					pre.setDate(6, Utility.convertJavaDateToSqlDate(exp.getToDate()));
					pre.setString(7, exp.getDescription());
					pre.setString(8, exp.getIsCurrent() ? "Y" : "N");

					i = pre.executeUpdate();

					if (i <= 0) {
						con.close();
						System.out.println("Cannot insert work template");
					}

					result = pre.getGeneratedKeys();
					result.next();
					exp.setExperienceId(result.getInt(1));

				}

			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		}
	}

	@Override
	public Boolean insertSpeakingLanguage(SpeakingLanguage sp, Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;

			sql = "INSERT INTO SpeakingLanguage(PersonId, LanguageName, Proficiency) " + "VALUES(?,?,?)";
			pre = con.prepareStatement(sql);
			pre.setInt(1, customerId);
			pre.setString(2, sp.getLanguageName());
			pre.setString(3, sp.getProficiency());

			i = pre.executeUpdate();

			if (i <= 0) {

				System.out.println("Cannot insert speaking language template");
				return false;
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

			return false;

		}

		return true;
	}

	@Override
	public void insertDynamicData(Customer customer) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;

			if (customer.getDynamicData() != null && customer.getDynamicData().size() > 0) {

				for (DynamicData dynamicData : customer.getDynamicData()) {

					sql = "INSERT INTO DynamicData(PersonId, DynamicId, Value) " + "VALUES(?,?,?)";
					pre = con.prepareStatement(sql);
					pre.setInt(1, customer.getPersonId());
					pre.setInt(2, dynamicData.getDynamicId());
					pre.setString(3, dynamicData.getValue());

					i = pre.executeUpdate();

					if (i <= 0) {
						con.close();
						System.out.println("Cannot insert dynamic template");
					}

				}

			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		}
	}

	@Override
	public Boolean insertSkill(Skill skill, Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;

			sql = "INSERT INTO Skills(PersonId, Skill, Level) " + "VALUES(?,?,?)";
			pre = con.prepareStatement(sql);
			pre.setInt(1, customerId);
			pre.setString(2, skill.getSkill());
			pre.setString(3, skill.getLevel());

			i = pre.executeUpdate();

			if (i <= 0) {
				con.close();
				System.out.println("Cannot insert skill template");
				return false;
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

			return false;

		}

		return true;
	}

	public void insertEducation(Education edu, Integer personId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;
			ResultSet result = null;

			sql = "INSERT INTO Education(PersonId, School, Degree, StudyField, Grade, Year) " + "VALUES(?,?,?,?,?,?)";
			pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setInt(1, personId);
			pre.setString(2, edu.getSchool());
			pre.setString(3, edu.getDegree());
			pre.setString(4, edu.getStudyField());
			pre.setString(5, edu.getGrade());
			pre.setInt(6, edu.getYear());

			i = pre.executeUpdate();

			if (i <= 0) {
				con.close();
				System.out.println("Cannot insert education template");
			}

			result = pre.getGeneratedKeys();
			result.next();
			edu.setEducationId(result.getInt(1));

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		}
	}

	@Override
	public void sendFeedBack(FeedBack feedBack) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;
			ResultSet result = null;

			sql = "INSERT INTO FeedBack(PersonId, FeedBackType, Message, CreatedDate, Name, Email) "
					+ "VALUES(?,?,?,?,?,?)";
			pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pre.setInt(1, feedBack.getPersonId() == null ? 0 : feedBack.getPersonId());
			pre.setString(2, feedBack.getFeedBackType());
			pre.setString(3, feedBack.getMessage());
			pre.setDate(4, Utility.convertJavaDateToSqlDate(new Date()));
			pre.setString(5, feedBack.getName());
			pre.setString(6, feedBack.getEmail());

			i = pre.executeUpdate();

			if (i <= 0) {
				con.close();
				System.out.println("Cannot insert feedback template");
			}

			result = pre.getGeneratedKeys();
			result.next();
			feedBack.setFeedBackId(result.getInt(1));

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		} finally {
			if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			/*
			 * if (con != null) try { con.close(); } catch (SQLException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */

		}
	}

	@Override
	public Boolean deleteSpeakingLanguage(SpeakingLanguage sp, Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;
			sql = "DELETE FROM SpeakingLanguage WHERE PersonId=? AND LanguageName=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, customerId);
			pre.setString(2, sp.getLanguageName());

			i = pre.executeUpdate();

			if (i <= 0) {

				System.out.println("Cannot delete speaking language template");
				return false;
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

			return false;

		}

		return true;
	}

	public Boolean deleteSkill(Skill skill, Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;

			sql = "DELETE FROM Skills WHERE PersonId=? AND Skill=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, customerId);
			pre.setString(2, skill.getSkill());
			i = pre.executeUpdate();

			if (i <= 0) {
				con.close();
				System.out.println("Cannot insert skill template");
				return false;
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

			return false;

		}

		return true;
	}

	@Override
	public Boolean deleteEducation(Integer educationId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;
			sql = "DELETE FROM Education WHERE EducationId=?";

			pre = con.prepareStatement(sql);
			pre.setInt(1, educationId);

			i = pre.executeUpdate();

			if (i <= 0) {
				con.close();
				System.out.println("Cannot insert education template");
				return false;
			}

			return true;

		} catch (

		SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		}
		return false;
	}

	@Override
	public List<WorkExperience> getWorkExperiences(Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;
		List<WorkExperience> workExps = new ArrayList<>();

		try {

			String sql = null;

			ResultSet result = null;

			sql = "SELECT ExperienceId, Title, Company, Location, FromDate, ToDate, Description, IsCurrent"
					+ " FROM WorkExperience WHERE PersonId =?";
			pre = con.prepareStatement(sql);

			pre.setInt(1, customerId);
			result = pre.executeQuery();

			while (result.next()) {

				WorkExperience work = new WorkExperience();
				work.setExperienceId(result.getInt(1));
				work.setTitle(result.getString(2));
				work.setCompany(result.getString(3));
				work.setLocation(result.getString(4));
				work.setFromDate(Utility.convertSqlDateToUtilDate(result.getDate(5)));
				work.setToDate(Utility.convertSqlDateToUtilDate(result.getDate(6)));
				work.setDescription(result.getString(7));
				work.setIsCurrent(result.getString(8).equals("Y") ? true : false);

				workExps.add(work);
			}

		} catch (

		SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		}

		return workExps;
	}

	@Override
	public List<SpeakingLanguage> getSpeakingLanguage(Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;
		List<SpeakingLanguage> languages = new ArrayList<>();

		try {

			String sql = null;

			ResultSet result = null;

			sql = "SELECT LanguageName, Proficiency" + " FROM SpeakingLanguage WHERE PersonId =?";
			pre = con.prepareStatement(sql);

			pre.setInt(1, customerId);
			result = pre.executeQuery();

			while (result.next()) {

				SpeakingLanguage lang = new SpeakingLanguage();
				lang.setLanguageName(result.getString(1));
				lang.setProficiency(result.getString(2));

				languages.add(lang);
			}

		} catch (

		SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		}

		return languages;
	}

	@Override
	public List<DynamicData> getDynamicData(Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;
		List<DynamicData> datas = new ArrayList<>();

		try {

			String sql = null;

			ResultSet result = null;

			sql = "SELECT a.DynamicId, a.Value, b.FieldName, b.InputType, b.Description, b.LabelName"
					+ " FROM DynamicData a, DynamicTemplate b WHERE a.DynamicId = b.DynamicId AND a.PersonId =? AND b.IsActive='Y'";
			pre = con.prepareStatement(sql);

			pre.setInt(1, customerId);

			result = pre.executeQuery();

			while (result.next()) {

				DynamicData data = new DynamicData();

				data.setDynamicId(result.getInt(1));
				data.setValue(result.getString(2));
				data.setFieldName(result.getString(3));
				data.setInputType(result.getString(4));
				data.setDescription(result.getString(5));
				data.setLabelName(result.getString(6));

				datas.add(data);
			}

		} catch (

		SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		}

		return datas;

	}

	@Override
	public List<Skill> getSkills(Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;
		List<Skill> skills = new ArrayList<>();

		try {

			String sql = null;

			ResultSet result = null;

			sql = "SELECT Skill, Level" + " FROM Skills WHERE PersonId=?";
			pre = con.prepareStatement(sql);

			pre.setInt(1, customerId);

			result = pre.executeQuery();

			while (result.next()) {

				Skill skill = new Skill();
				skill.setSkill(result.getString(1));
				skill.setLevel(result.getString(2));
				skills.add(skill);
			}

		} catch (

		SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		}

		return skills;

	}

	@Override
	public List<Education> getEducations(Integer customerId) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;
		List<Education> educations = new ArrayList<>();

		try {

			String sql = null;

			ResultSet result = null;

			sql = "SELECT EducationId, School, Degree, StudyField, Grade, Year" + " FROM Education WHERE PersonId =?";
			pre = con.prepareStatement(sql);

			pre.setInt(1, customerId);
			result = pre.executeQuery();

			while (result.next()) {

				Education edu = new Education();
				edu.setEducationId(result.getInt(1));
				edu.setSchool(result.getString(2));
				edu.setDegree(result.getString(3));
				edu.setStudyField(result.getString(4));
				edu.setGrade(result.getString(5));
				edu.setYear(result.getInt(6));

				educations.add(edu);
			}

		} catch (

		SQLException ex) {

			ex.printStackTrace();

			try {
				con.close();
			} catch (SQLException e) {

			}

		}

		return educations;

	}
	
	@Override
	public void deleteSingleWorkingExperience(int personId, int experienceId) {
		
		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {
			
			String sql = null;
			int i = 0;

			sql = "DELETE FROM WorkExperience WHERE PersonId=? AND ExperienceId=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, personId);
			pre.setInt(2, experienceId);

			i = pre.executeUpdate();

			if (i < 0) {
				con.rollback();
				System.out.println("Cannot delete work template");
			}
			
		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		}
		
	}

	@Override
	public void deleteWorkExperiences(Customer customer) {

		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {

			String sql = null;
			int i = 0;

			sql = "DELETE FROM WorkExperience WHERE PersonId=?";
			pre = con.prepareStatement(sql);
			pre.setInt(1, customer.getPersonId());

			i = pre.executeUpdate();

			if (i < 0) {
				con.rollback();
				System.out.println("Cannot delete work template");
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		}
	}
	
	@Override
	public void insertSingleWorkExperience(int personId, WorkExperience experience) {
		
		Connection con = connection.getConnection();
		PreparedStatement pre = null;

		try {
			
			String sql = null;
			int i = 0;
			ResultSet result = null;
			if (experience != null) {
				
				sql = "INSERT INTO WorkExperience(PersonId, Title, Company, Location, FromDate, ToDate, Description, IsCurrent) "
						+ "VALUES(?,?,?,?,?,?,?,?)";
				
				pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pre.setInt(1, personId);
				pre.setString(2, experience.getTitle());
				pre.setString(3, experience.getCompany());
				pre.setString(4, experience.getLocation());
				pre.setDate(5, Utility.convertJavaDateToSqlDate(experience.getFromDate()));
				pre.setDate(6, Utility.convertJavaDateToSqlDate(experience.getToDate()));
				pre.setString(7, experience.getDescription());
				pre.setString(8, experience.getIsCurrent() ? "Y" : "N");

				i = pre.executeUpdate();

				if (i < 0) {
					con.rollback();
					System.out.println("Cannot insert work template");
				}
				
				result = pre.getGeneratedKeys();
				result.next();
				experience.setExperienceId(result.getInt(1));
				
			}
			
		} catch (SQLException ex) {

			ex.printStackTrace();

			try {
				con.rollback();
			} catch (SQLException e) {

			}

		}
		
	}


}
