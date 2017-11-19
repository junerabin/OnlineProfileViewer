package com.mum.paper.clip.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mum.paper.clip.daoimpl.CustomerDaoImpl;
import com.mum.paper.clip.model.FeedBack;

/**
 * Servlet implementation class SendFeedBackServlet
 */
@WebServlet("/sendFeedBack")
public class SendFeedBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendFeedBackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/** delete session **/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FeedBack feedBack = new FeedBack();
		feedBack.setEmail(request.getParameter("email"));
		feedBack.setName(request.getParameter("name"));
		feedBack.setFeedBackType(request.getParameter("type"));
		feedBack.setMessage(request.getParameter("message"));
		
		CustomerDaoImpl.getInstance().sendFeedBack(feedBack);
		
		HttpSession session = request.getSession();
		
		if(feedBack.getFeedBackId() == null) {
			
			session.setAttribute("error", "Sorry!");
		} else {
			
			session.setAttribute("success", "Thank you for your feedback.");
		}
		
		request.getRequestDispatcher("feedback.jsp").forward(request, response);
		
	}

}
