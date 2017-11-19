package com.mum.paper.clip.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mum.paper.clip.daoimpl.PersonDaoImpl;
import com.mum.paper.clip.model.Customer;

/**
 * Servlet implementation class UpdatePersonProfileServlet
 */
@WebServlet("/updatePersonProfile")
public class UpdatePersonProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePersonProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		
		Customer customer = (Customer) session.getAttribute("currentUser");
		customer.setFirstName(request.getParameter("firstname"));
		customer.setMiddleName(request.getParameter("middlename"));
		customer.setLastName(request.getParameter("lastname"));
		customer.setEmail(request.getParameter("email"));
		customer.setContactNo(request.getParameter("phone"));
		customer.setStreet1(request.getParameter("street1"));
		customer.setStreet2(request.getParameter("street2"));
		customer.setCity(request.getParameter("city"));
		customer.setState(request.getParameter("state"));
		customer.setZipCode(request.getParameter("zipcode"));
		
		if(PersonDaoImpl.getInstance().updatePersonalProfile(customer)) {
			
			session.setAttribute("success", "Personal profile successfully updated");
		} else {
			
			session.setAttribute("error", "Not successfully updated");			
		}
		
		request.getRequestDispatcher("profile.jsp").forward(request, response);
		
	}

}
