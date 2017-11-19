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
import com.mum.paper.clip.model.Person;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String SIGNUP_LOG = LoginServlet.class.getName();
	public static final String SIGNUP_STATUS_TAG = "signup_status";
	public static final int SIGNUP_STATUS_SUCCESS = 1;
	public static final int SIGNUP_STATUS_ERROR = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if (!PersonDaoImpl.getInstance().isUserAvailable(request.getParameter("username"))) {
			
			session.setAttribute(SIGNUP_STATUS_TAG, SIGNUP_STATUS_ERROR);
			session.setAttribute("error", "Duplicate username");
			request.getRequestDispatcher("profile.jsp").forward(request, response);
			System.out.println(SIGNUP_LOG + " : Username aready exists");
			
		} else {
			
			Customer customer = new Customer();
			customer.setFirstName(request.getParameter("firstname"));
			customer.setMiddleName(request.getParameter("middlename"));
			customer.setLastName(request.getParameter("lastname"));
			customer.setEmail(request.getParameter("email"));
			customer.setContactNo(request.getParameter("phone"));
			customer.setUserName(request.getParameter("username"));
			customer.setPassword(request.getParameter("password"));
			customer.setStreet1(request.getParameter("street1"));
			customer.setStreet2(request.getParameter("street2"));
			customer.setCity(request.getParameter("city"));
			customer.setState(request.getParameter("state"));
			customer.setZipCode(request.getParameter("zipcode"));
			customer.setCountry("United States");
			
			Person person = PersonDaoImpl.getInstance().createCustomer(customer);
			if (person != null) {
				
				session.setAttribute(SIGNUP_STATUS_TAG, SIGNUP_STATUS_SUCCESS);
				request.getRequestDispatcher("login.jsp").forward(request, response);
				System.out.println(SIGNUP_LOG + " : Signup successful");
				
			} else {
				
				session.setAttribute(SIGNUP_STATUS_TAG, SIGNUP_STATUS_ERROR);
				session.setAttribute("error", "Sorry!");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
				System.out.println(SIGNUP_LOG + " : Invalid fields");
				
			}
			
		}

	}

}
