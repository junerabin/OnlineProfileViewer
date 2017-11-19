package com.mum.paper.clip.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mum.paper.clip.daoimpl.CustomerDaoImpl;
import com.mum.paper.clip.daoimpl.PersonDaoImpl;
import com.mum.paper.clip.model.Admin;
import com.mum.paper.clip.model.Customer;
import com.mum.paper.clip.model.Education;
import com.mum.paper.clip.model.Person;
import com.mum.paper.clip.model.Skill;
import com.mum.paper.clip.model.SpeakingLanguage;
import com.mum.paper.clip.model.StandardTemplate;
import com.mum.paper.clip.model.WorkExperience;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String CURRENT_PERSON_IN_SESSION = "currentUser";
	public static final String LOGIN_LOG = LoginServlet.class.getName();
	public static final String LOGIN_STATUS_TAG = "loginStatus";
	public static final int LOGIN_STATUS_SUCCESS = 1;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute("error");
		//session.removeAttribute(CURRENT_PERSON_IN_SESSION);
		//session.removeAttribute("currentResume");
		request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Person person = new Person();
		person.setUserName(username);
		person.setPassword(password);
		Person value = PersonDaoImpl.getInstance().login(person);

		if (value.getClass() == Customer.class) {

			session.setAttribute(CURRENT_PERSON_IN_SESSION, value);
			
			session.setAttribute(LOGIN_STATUS_TAG, LOGIN_STATUS_SUCCESS);
			session.removeAttribute("error");
			
			/** education list **/
			List<Education> educations = CustomerDaoImpl.getInstance().getEducations(value.getPersonId());
			
			System.out.println(educations.size());
			
			session.setAttribute("educations", educations);			
			
			/** skill list **/
			List<Skill> skills = CustomerDaoImpl.getInstance().getSkills(value.getPersonId());
			
			System.out.println(skills.size());
			
			session.setAttribute("skills", skills);	
			
			/** language list **/
			List<SpeakingLanguage> languages = CustomerDaoImpl.getInstance().getSpeakingLanguage(value.getPersonId());
			
			System.out.println(languages.size());
			
			session.setAttribute("languages", languages);	
			
			/** standard template **/
			StandardTemplate template = CustomerDaoImpl.getInstance().getStandardTemplate(value.getPersonId());
			session.setAttribute("standard", template);
			
			/** workexperiences list **/
			List<WorkExperience> workExperiences = CustomerDaoImpl.getInstance().getWorkExperiences(value.getPersonId());
			session.setAttribute("works", workExperiences);
			
			request.getRequestDispatcher("profile.jsp").forward(request, response);
			System.out.println(LOGIN_LOG + " : Login successful as Customer");
			
			

		} else if (value.getClass() == Admin.class) {

			session.setAttribute(CURRENT_PERSON_IN_SESSION, value);
			session.removeAttribute("error");
			session.setAttribute(LOGIN_STATUS_TAG, LOGIN_STATUS_SUCCESS);
			response.sendRedirect("adminFunctions");
			System.out.println(LOGIN_LOG + " : Login successful as Admin");

		} else {

			session.setAttribute("error", "Please provide valid username and password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
