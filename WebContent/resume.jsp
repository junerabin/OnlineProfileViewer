<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="components/header.jsp"%>
<div class="container">
	<div class="col-md-6">
		<p class="highlight-resume">
			<span>Resume</span><br> <a href="" class=""><i
				class="fa fa-angle-left"></i> Go back to the list </a>
		</p>

	</div>
</div>
</header>

<div class="container-fluid">
	<div class="container">

		<div class="row bottom-blue margin-30">
			<div class="col-md-6">

				<h2 class="float-left">
					<c:out value="${resume.firstName}" />
					<c:out value="${resume.lastName}" />
				</h2>

			</div>
			<div class="col-md-6">
				<span class="float-right"> <a href="mailto:<c:out value='${resume.email}'/>"><i
						class="fa fa-envelope-o"></i> <c:out value="${resume.email}" /> </a> /
					<i class="fa fa-phone"></i> ${resume.contactNo}
				</span>
			</div>
		</div>

		<div class="row bottom-blue margin-30 header-resume">
			<div class="col-md-4">
				<div class="main-image" style="background-image: url('<%=request.getContextPath() %>${resume.standardTemplate.photoURL}');"></div>
				
				
				<a href="getResumeById?type=download" class="btn btn-outline-primary
					btn-block" target="_BLANK"><i class="fa fa-file-text-o"></i>
					Download Resume</a> <a href="${resume.standardTemplate.linkedInURL}" class="btn btn-primary
					btn-block" target="_BLANK"><i class="fa fa-linkedin"></i> Linkedin</a>
				<h5 class="margin-30">Languages</h5>
				<div class="skills-elements">
					<c:forEach items="${resume.speakingLanguages}" var="lang">                   
                   		${lang.languageName}
                   </c:forEach>
				</div>
			</div>
			<div class="col-md-8">
				<h3 class="titleh3"><c:out value="${resume.standardTemplate.appliedPosition}" /></h3>
				<h5>About</h5>
				<p>${resume.standardTemplate.aboutYourself}</p>
				<h5>Skills</h5>
				<div class="skills-elements">

					<c:forEach items="${resume.skills}" var="skill">
						<div class="badge badge-sm badge-success"> <strong>${skill.skill}</strong> - ${skill.level}</div>
					</c:forEach>

				</div>

			</div>
		</div>

		<div class="row bottom-blue margin-30 header-resume">
			<div class="col-md-12">
				<h5 class="margin-30">Work Experience</h5>

			</div>
			<div class="col-md-12">
				<table class="table">
					<thead class="thead-default">
						<tr>
							<th class="t-s">Title</th>
							<th class="t-s">Company</th>
							<th class="t-s">Location</th>
							<th class="t-d">From</th>
							<th class="t-d">To</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${resume.workExps}" var="work">
						<tr class="job-row">
							<!-- // This is the one to repeat -->
							<td colspan="5">
								<table>

									
										<tr>
											<th scope="row" class="t-s">${work.title}</th>
											<td class="t-s">${work.company}</td>
											<td class="t-s">${work.location}</td>
											<td class="t-d">${work.fromDate}</td>
											<td class="t-d">${work.toDate}</td>
										</tr>
										<tr>
										<td colspan="5">${work.description}</td>
										</tr>
									
									
								</table>
							</td>
						</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>

		<div class="row bottom-blue margin-30 header-resume">
			<div class="col-md-12">
				<h5 class="margin-30">Education</h5>
				<div class="col-md-12">
					<table class="table">
						<thead class="thead-default">
							<tr>
								<th>Year</th>
								<th>School</th>
								<th>Degree</th>
								<th>Study Field</th>
								<th>GPA</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${resume.educations}" var="edu">
							<tr>
								<th scope="row">${edu.year}</th>
								<td>${edu.school}</td>
								<td>${edu.degree}</td>
								<td>${edu.studyField}</td>
								<td>${edu.grade}</td>
							</tr>
						</c:forEach>
					</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="components/footer.html"%>