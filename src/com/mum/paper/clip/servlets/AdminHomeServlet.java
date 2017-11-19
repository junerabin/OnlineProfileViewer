package com.mum.paper.clip.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mum.paper.clip.daoimpl.AdminDaoImpl;
import com.mum.paper.clip.model.DynamicTemplate;
import com.mum.paper.clip.model.FeedBack;

/**
 * Servlet implementation class GetFeedBackListServlet
 */
@WebServlet("/adminFunctions")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<DynamicTemplate> dynamicList = AdminDaoImpl.getInstance().getDynamicTemplateList();
		
		List<FeedBack> feedBackList = AdminDaoImpl.getInstance().getFeedBackList(null, null);
		
		HttpSession session = request.getSession();
		session.setAttribute("dynamicList", dynamicList);
		session.setAttribute("feedBackList", feedBackList);
		
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
}