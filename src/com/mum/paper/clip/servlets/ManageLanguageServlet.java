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
import com.mum.paper.clip.model.SpeakingLanguage;

/**
 * Servlet implementation class ManageLanguageServlet
 */
@WebServlet("/manageLanguage")
public class ManageLanguageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageLanguageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("languageName");
		HttpSession session = request.getSession();

		SpeakingLanguage sp = new SpeakingLanguage();
		sp.setLanguageName(name);

		Customer customer = (Customer) session.getAttribute("currentUser");
		
		System.out.println(customer.getPersonId());

		if (CustomerDaoImpl.getInstance().deleteSpeakingLanguage(sp, customer.getPersonId())) {

			session.removeAttribute("error");
			session.setAttribute("success", "Language successfully deleted");
		} else {

			session.removeAttribute("success");
			session.setAttribute("error", "Not successfully deleted");
		}

		List<SpeakingLanguage> languages = CustomerDaoImpl.getInstance().getSpeakingLanguage(customer.getPersonId());

		System.out.println(languages.size());

		session.setAttribute("languages", languages);
		request.getRequestDispatcher("profile.jsp?tab=4").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String proficiency = request.getParameter("proficiency");
		String name = request.getParameter("languageName");

		SpeakingLanguage sp = new SpeakingLanguage();
		sp.setLanguageName(name);
		sp.setProficiency(proficiency);

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("currentUser");

		if (CustomerDaoImpl.getInstance().insertSpeakingLanguage(sp, customer.getPersonId())) {

			session.removeAttribute("error");
			session.setAttribute("success", "Language successfully added");
		} else {

			session.removeAttribute("success");
			session.setAttribute("error", "Not successfully added");
		}

		List<SpeakingLanguage> languages = CustomerDaoImpl.getInstance().getSpeakingLanguage(customer.getPersonId());

		System.out.println(languages.size());

		session.setAttribute("languages", languages);
		// request.getRequestDispatcher("profile.jsp?tab=2").forward(request,
		// response);
		response.sendRedirect("profile.jsp?tab=4");
	}
}
