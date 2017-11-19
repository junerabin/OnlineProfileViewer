package com.mum.paper.clip.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mum.paper.clip.daoimpl.CustomerDaoImpl;
import com.mum.paper.clip.model.Customer;
import com.mum.paper.clip.model.WorkExperience;

/**
 * Servlet implementation class UpdatePersonExperience
 */
@WebServlet("/addWorkForm")
public class UpdatePersonExperienceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePersonExperienceServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		if (request.getParameter("expId") != null) {

			HttpSession session = request.getSession();
			Customer customer = (Customer) session.getAttribute("currentUser");
			
			CustomerDaoImpl.getInstance().deleteSingleWorkingExperience(
					customer.getPersonId(), Integer.valueOf(request.getParameter("expId")));
			
			/** workexperiences list **/
			List<WorkExperience> workExperiences = CustomerDaoImpl.getInstance().getWorkExperiences(customer.getPersonId());
			session.setAttribute("works", workExperiences);
			

			response.sendRedirect("profile.jsp?tab=3");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("currentUser");

		if (customer != null) {

			WorkExperience experience = new WorkExperience();
			experience.setTitle(request.getParameter("title"));
			experience.setCompany(request.getParameter("company"));
			experience.setLocation(request.getParameter("location"));

			Date fromDate = null;
			Date toDate = null;
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				fromDate = df.parse(request.getParameter("fromDate"));
				toDate = df.parse(request.getParameter("toDate"));
			} catch (ParseException e) {
				fromDate = new Date();
				toDate = new Date();
			}
			experience.setFromDate(fromDate);
			experience.setToDate(toDate);
			experience.setDescription(request.getParameter("description"));
			experience.setIsCurrent(request.getParameter("currentWorking") == null ? false : true);

			/*customer.getWorkExps().add(experience);*/
			CustomerDaoImpl.getInstance().insertSingleWorkExperience(customer.getPersonId(), experience);
			
			/** workexperiences list **/
			List<WorkExperience> workExperiences = CustomerDaoImpl.getInstance().getWorkExperiences(customer.getPersonId());
			session.setAttribute("works", workExperiences);

			response.sendRedirect("profile.jsp?tab=3");

		} else {
			System.out.println("The customer object is null");
		}

	}

}
