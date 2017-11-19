package com.mum.paper.clip.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mum.paper.clip.daoimpl.CustomerDaoImpl;
import com.mum.paper.clip.daoimpl.PersonDaoImpl;
import com.mum.paper.clip.model.Customer;
import com.mum.paper.clip.model.StandardTemplate;

/**
 * Servlet implementation class GetResumeByIdServlet
 */
@WebServlet("/getResumeById")
public class GetResumeByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetResumeByIdServlet() {
		super();
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

		HttpSession session = request.getSession();
		String type = request.getParameter("type");

		if (type != null) {

			String url = null;

			if (type.equals("profile")) {

				StandardTemplate st = (StandardTemplate) session.getAttribute("standard");
				url = st.getAttachmentURL();
			} else {

				Customer customer = (Customer) session.getAttribute("resume");
				url = customer.getStandardTemplate().getAttachmentURL();

			}

			/* String filePath = "D:\\photo\\a.jpg"; */
			File downloadFile = new File(url);
			FileInputStream inStream = new FileInputStream(downloadFile);

			ServletContext context = getServletContext();

			String mimeType = context.getMimeType(url);
			if (mimeType == null) {

				mimeType = "application/octet-stream";
			}
			System.out.println("MIME type: " + mimeType);

			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());

			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inStream.close();
			outStream.close();

		} else {

			String id = request.getParameter("id");

			Integer personId = Integer.valueOf(id);

			Customer customer = PersonDaoImpl.getInstance().getPersonalData(personId);

			customer.setStandardTemplate(CustomerDaoImpl.getInstance().getStandardTemplate(personId));
			customer.setWorkExps(CustomerDaoImpl.getInstance().getWorkExperiences(personId));
			customer.setEducations(CustomerDaoImpl.getInstance().getEducations(personId));
			customer.setSpeakingLanguages(CustomerDaoImpl.getInstance().getSpeakingLanguage(personId));
			customer.setSkills(CustomerDaoImpl.getInstance().getSkills(personId));

			session.setAttribute("resume", customer);
			request.getRequestDispatcher("resume.jsp").forward(request, response);

		}

	}

}
