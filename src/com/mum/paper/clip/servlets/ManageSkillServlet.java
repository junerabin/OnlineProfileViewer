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
import com.mum.paper.clip.model.Skill;

/**
 * Servlet implementation class ManageSkillServlet
 */
@WebServlet("/manageSkill")
public class ManageSkillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageSkillServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String s = request.getParameter("skill");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("currentUser");

		Skill skill = new Skill();
		skill.setSkill(s);

		if (CustomerDaoImpl.getInstance().deleteSkill(skill, customer.getPersonId())) {

			session.removeAttribute("error");
			session.setAttribute("success", "Skill successfully deleted");
		} else {

			session.removeAttribute("success");
			session.setAttribute("error", "Not successfully deleted");
		}

		List<Skill> skills = CustomerDaoImpl.getInstance().getSkills(customer.getPersonId());

		session.setAttribute("skills", skills);
		request.getRequestDispatcher("profile.jsp?tab=4").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String skill = request.getParameter("skill");
		String level = request.getParameter("level");

		Skill s = new Skill();
		s.setSkill(skill);
		s.setLevel(level);

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("currentUser");

		if (CustomerDaoImpl.getInstance().insertSkill(s, customer.getPersonId())) {

			session.removeAttribute("error");
			session.setAttribute("success", "Skill successfully added");
		} else {

			session.removeAttribute("success");
			session.setAttribute("error", "Not successfully added");
		}

		List<Skill> skills = CustomerDaoImpl.getInstance().getSkills(customer.getPersonId());

		session.setAttribute("skills", skills);
		// request.getRequestDispatcher("profile.jsp?tab=2").forward(request,
		// response);
		response.sendRedirect("profile.jsp?tab=4");
	}

}
