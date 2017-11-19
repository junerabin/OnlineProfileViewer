package com.mum.paper.clip.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mum.paper.clip.model.DynamicTemplate;

/**
 * Servlet implementation class EditTemplateServlet
 */
@WebServlet("/editTemplate")
public class EditTemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTemplateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<DynamicTemplate> list = (List<DynamicTemplate>) session.getAttribute("dynamicList");
		DynamicTemplate object = null;
		
		for(DynamicTemplate dt : list) {
			
			if(dt.getDynamicId() == Integer.valueOf(request.getParameter("id"))) {
				
				object = dt;
				break;
			}
		}
		
		session.setAttribute("dynamicTemplate", object);
		request.getRequestDispatcher("dynamicTemplate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
