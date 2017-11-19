package com.mum.paper.clip.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mum.paper.clip.daoimpl.CustomerDaoImpl;
import com.mum.paper.clip.model.Customer;
import com.mum.paper.clip.model.StandardTemplate;

/**
 * Servlet implementation class OtherProfileServlet
 */
@WebServlet("/otherProfile")
@MultipartConfig(maxFileSize = 16177215)
public class OtherProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OtherProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("profile.jsp?tab=6").forward(request, response);
		
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("currentUser");

		Integer personId = customer.getPersonId();

		String templateId = request.getParameter("templateId");
		
		String title = request.getParameter("title");
		String status = request.getParameter("status");
		String linkedin = request.getParameter("linkedin");
		String about = request.getParameter("about");
	
		String filePathResume = getServletContext().getRealPath("/") + "\\resume";
		String filePathPhoto = getServletContext().getRealPath("/") + "\\photo";

		System.out.println(filePathResume + ": " + filePathPhoto);

		try {
		Part filePart = request.getPart("resume");
		if (filePart != null) {

			InputStream inputStreamResume = filePart.getInputStream();

			File file = new File(filePathResume);
			file.mkdir();

			File f = new File(file, "resume_" + personId + ".pdf");

			OutputStream outputStreamResume = new FileOutputStream(f);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStreamResume.read(bytes)) != -1) {
				outputStreamResume.write(bytes, 0, read);
			}
		}

		Part filePartPhoto = request.getPart("picture");
		if (filePartPhoto != null) {
		
			InputStream inputStreamPhoto = filePartPhoto.getInputStream();

			BufferedImage image = ImageIO.read(inputStreamPhoto);

			File file = new File(filePathPhoto);
			file.mkdir();
			File f = new File(file, "profilePhoto_" + personId + ".jpeg");

			ImageIO.write(image, "jpeg", f);

		} 
		
		} catch(Exception e) {
			
		}

		StandardTemplate st = new StandardTemplate();

		if (templateId.equals("")) {

			st.setAppliedPosition(title);
			st.setStatus(status);
			st.setLinkedInURL(linkedin);
			st.setAboutYourself(about);
			st.setPhotoURL("/photo/profilePhoto_" + personId + ".jpeg");
			st.setAttachmentURL(filePathResume + "\\resume_" + personId + ".pdf");
			
			CustomerDaoImpl.getInstance().insertStandardTemplate(st, personId);
		} else {

			st.setAppliedPosition(title);
			st.setStatus(status);
			st.setLinkedInURL(linkedin);
			st.setAboutYourself(about);
			st.setPhotoURL("/photo/profilePhoto_" + personId + ".jpeg");
			st.setAttachmentURL(filePathResume + "\\resume_" + personId + ".pdf");

			CustomerDaoImpl.getInstance().updateStandardTemplate(st, personId);
		}

		StandardTemplate template = CustomerDaoImpl.getInstance().getStandardTemplate(personId);
		session.setAttribute("standard", template);

		response.sendRedirect("profile.jsp?tab=6");

	}

}
