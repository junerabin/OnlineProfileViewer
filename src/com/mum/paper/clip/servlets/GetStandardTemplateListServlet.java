package com.mum.paper.clip.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mum.paper.clip.daoimpl.CustomerDaoImpl;
import com.mum.paper.clip.model.Customer;

/**
 * Servlet implementation class GetStandardTemplateListServlet
 */
@WebServlet("/getStandardCVs")
public class GetStandardTemplateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public GetStandardTemplateListServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		String keyword = request.getParameter("keyword");

		List<Customer> customerList = CustomerDaoImpl.getInstance().getStandardTemplateList(keyword);

		JSONArray jsonArray = new JSONArray();

		for (Customer customer : customerList) {

			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(customer);
			JSONObject obj = null;
			try {
				obj = new JSONObject(jsonInString);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			jsonArray.put(obj);
		}

		out.print(jsonArray);

	}

}
