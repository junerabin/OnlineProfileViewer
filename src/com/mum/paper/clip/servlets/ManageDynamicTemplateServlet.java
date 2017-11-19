package com.mum.paper.clip.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mum.paper.clip.daoimpl.AdminDaoImpl;
import com.mum.paper.clip.model.DynamicTemplate;

/**
 * Servlet implementation class ManageDynamicTemplateServlet
 */
@WebServlet("/manageTemplate")
public class ManageDynamicTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageDynamicTemplateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String dynamicId = request.getParameter("dynamicId");
		
		System.out.println(dynamicId + "id");
		String fieldName = request.getParameter("fieldname");
		String inputType = request.getParameter("fieldtype");
		String description = request.getParameter("description");
		String labelName = request.getParameter("labelname");
		String active = request.getParameter("isActive");
		System.out.println(active + " active");
		
		DynamicTemplate dt = new DynamicTemplate();
		
		if (dynamicId == null || dynamicId.equals("")) {
			
			dt.setFieldName(fieldName);
			dt.setInputType(inputType);
			dt.setDescription(description);
			dt.setLabelName(labelName);
			dt.setIsActive(active == null ? false : true);
			
		} else {
			dt.setDynamicId(Integer.valueOf(dynamicId));
			dt.setFieldName(fieldName);
			dt.setInputType(inputType);
			dt.setDescription(description);
			dt.setLabelName(labelName);
			dt.setIsActive(active == null ? false : true);
		}
		
		DynamicTemplate dynamicTemplate = AdminDaoImpl.getInstance().manageDynamicTemplate(dt);
		
		HttpSession session = request.getSession();
		session.setAttribute("dynamicTemplate", dynamicTemplate);
		
		session.removeAttribute("dynamicTemplate");
		request.getRequestDispatcher("adminFunctions").forward(request, response);
		
	}

}
