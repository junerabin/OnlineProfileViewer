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
import com.mum.paper.clip.model.Customer;
import com.mum.paper.clip.model.Education;

/**
 * Servlet implementation class ManageEducationServlet
 */
@WebServlet("/manageEducation")
public class ManageEducationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageEducationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		HttpSession session = request.getSession();

		if (CustomerDaoImpl.getInstance().deleteEducation(Integer.valueOf(id))) {

			session.setAttribute("success", "Education successfully deleted");
		} else {

			session.setAttribute("error", "Not successfully deleted");
		}

		Customer customer = (Customer) session.getAttribute("currentUser");
		List<Education> educations = CustomerDaoImpl.getInstance().getEducations(customer.getPersonId());

		System.out.println(educations.size());

		session.setAttribute("educations", educations);
		request.getRequestDispatcher("profile.jsp?tab=2").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String study = request.getParameter("fieldofstudy");
		String school = request.getParameter("school");
		String gpa = request.getParameter("gpa");
		String year = request.getParameter("year");
		String degree = request.getParameter("degree");

		Education education = new Education();
		education.setDegree(degree);
		education.setStudyField(study);
		education.setGrade(gpa);
		education.setYear(Integer.valueOf(year));
		education.setSchool(school);

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("currentUser");

		CustomerDaoImpl.getInstance().insertEducation(education, customer.getPersonId());

		if (education.getEducationId() != null) {

			session.setAttribute("success", "Education successfully added");
		} else {

			session.setAttribute("error", "Not successfully added");
		}

		List<Education> educations = CustomerDaoImpl.getInstance().getEducations(customer.getPersonId());

		System.out.println(educations.size());

		session.setAttribute("educations", educations);
		//request.getRequestDispatcher("profile.jsp?tab=2").forward(request, response);
		response.sendRedirect("profile.jsp?tab=2");
	}

}
