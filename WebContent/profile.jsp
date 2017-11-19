<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="components/header.jsp"%>

<div class="container">
	<div class="col-md-6">
		<p class="highlight-s">Profile</p>
	</div>
</div>
</header>

<div class="container-fluid">

	<div class="container rel">
		<div class="loader">
			<div class="logo">
				<i class="fa fa-paperclip"></i>Paperclip<br>
				<h6>Loading...</h6>
			</div>
		</div>

		<div class="row margin-30 ">
			<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link active"
						id="btn-profile">Profile</a></li>
					<li class="nav-item"><a class="nav-link" id="btn-education">Education</a>
					</li>
					<li class="nav-item"><a class="nav-link" id="btn-work">Experience</a>
					</li>
					<li class="nav-item"><a class="nav-link" id="btn-skills">Skills
							/ Languages</a></li>
					<li class="nav-item"><a class="nav-link" id="btn-other">Other</a>
					</li>
				</ul>
			</div>
		</div>


		<!-- UPDATE PROFILE -->
		<form action="updatePersonProfile" method="post" id="updateprofile">
			<div id="edit-profile" class="row margin-30">
				<div class="col-md-12">

					<h4>Personal Profile</h4>

					<c:choose>
						<c:when test="${not empty error}">
							<div class="alert alert-danger" role="alert">${error}</div>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${not empty success}">
							<div class="alert alert-success" role="alert">${success}</div>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>

				</div>


				<div class="col-md-6">
					<div class="form-group bottom-blue">
						<h5>Personal information</h5>
						<input type="hidden" name="tab" value="1">
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">First Name:
							${currentUser.firstName}</label> <input type="text" class="form-control"
							value="${currentUser.firstName}" name="firstname"
							placeholder="Your first name" required>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Middle Name:</label> <input
							type="text" class="form-control"
							value="${currentUser.middleName}" name="middlename"
							placeholder="Your middle name">
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Last Name:</label> <input
							type="text" class="form-control" value="${currentUser.lastName}"
							name="lastname" placeholder="Your last name" required>
					</div>
					<div class="form-group bottom-blue">
						<h5>Contact information</h5>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Email address:</label> <input
							type="text" class="form-control" name="email"
							value="${currentUser.email}" pattern="[A-Za-z0-9._%+-]+@mum.edu"
							title="You must create your account with an @mum.edu email account"
							placeholder="youremail@mum.edu" required> <small>Please
							use your @mum.edu email</small>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Phone Number:</label> <input
							type="phone" class="form-control"
							value="${currentUser.contactNo}" name="phone"
							placeholder="(000) 000-0000" required>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group bottom-blue">
						<h5>Account information</h5>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Username:</label> <input type="text"
							class="form-control" value="${currentUser.userName}"
							name="username" placeholder="Username" disabled>
					</div>
					<!--  <div class="form-group">
                            <label class="hidden-sm-down">Password</label>                            
                            <input type="password" class="form-control" name="password" placeholder="Password" required>
                        </div> -->
					<div class="form-group bottom-blue">
						<h5>Address</h5>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Address:</label> <input type="text"
							class="form-control" value="${currentUser.street1}"
							name="street1" placeholder="123 Address St" required>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Address 2:</label> <input
							type="text" class="form-control" value="${currentUser.street2}"
							name="street2" placeholder="Building / Apt" required>
					</div>
					<div class="row">
						<div class="form-group col-sm-6">
							<label class="hidden-sm-down">City:</label> <input type="text"
								class="form-control" value="${currentUser.city}" name="city"
								placeholder="City">
						</div>

						<div class="form-group col-sm-6">
							<label class="hidden-sm-down">State:</label> <input type="text"
								class="form-control" value="${currentUser.state}" name="state"
								placeholder="State">
						</div>

					</div>
					<div class="row">
						<div class="form-group col-sm-6">
							<label class="hidden-sm-down">Zip Code:</label> <input
								type="text" pattern="\d{5}" class="form-control"
								value="${currentUser.zipCode}" name="zipcode"
								title="Please type a 5 digits Zipcode" placeholder="Zipcode">
						</div>

						<div class=" form-group col-sm-6">
							<label class="hidden-sm-down">Country:</label> <input type="text"
								class="form-control" name="country" value="United States"
								placeholder="country" disabled>
						</div>

					</div>

				</div>
				<div class="col-md-12">
					<button type="submit" class="btn btn-block btn-primary">Update</button>
				</div>
			</div>
		</form>


		<!-- EDUCATION TAB -->
		<div id="edit-education" class="row margin-30">
			<form id="addEducationForm" action="manageEducation" method="post">
				<div id="addEducation">
					<div class="col-md-12">
						<h4>Add new education</h4>
						<button class="btn btn-outline-danger btn-default float-right"
							id="cancelEducation">Cancel</button>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="hidden-sm-down">School:</label> <input type="text"
								class="form-control" name="school" placeholder="School name"
								required>
						</div>
						<div class="form-group">
							<label class="hidden-sm-down">Field of Study:</label> <input
								type="text" class="form-control" name="fieldofstudy"
								placeholder="Computer Science" required>
						</div>
						<div class="form-group">
							<label class="hidden-sm-down">GPA:</label> <input type="text"
								class="form-control" name="gpa" placeholder="3.75">
						</div>

					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="hidden-sm-down">Degre:</label> <input type="text"
								class="form-control" name="degree" placeholder="Master" required>
						</div>
						<div class="form-group">
							<label class="hidden-sm-down">Year:</label> <input type="text"
								class="form-control" pattern="\d{4}" name="year"
								placeholder="Year" required>
						</div>

					</div>
					<div class="col-md-12">
						<button type="submit" class="btn btn-block btn-primary">Save</button>
					</div>

				</div>
			</form>
			<div class="col-md-12">
				<h4>Education</h4>
				<c:choose>
					<c:when test="${not empty error}">
						<div class="alert alert-danger" role="alert">${error}</div>
					</c:when>
					<c:otherwise>

					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${not empty success}">
						<div class="alert alert-success" role="alert">${success}</div>
					</c:when>
					<c:otherwise>

					</c:otherwise>
				</c:choose>
				<button class="btn btn-default btn-primary float-right"
					id="add-newEducation-template">Add New</button>
			</div>
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
						<c:forEach items="${educations}" var="edu">

							<tr>
								<th scope="row"><a class="btn btn-sm btn-outline-danger"
									href="manageEducation?id=${edu.educationId}&tab=2"><i
										class="fa fa-trash-o" aria-hidden="true"></i></a> <c:out
										value="${edu.year}" /></th>
								<td><c:out value="${edu.school}" /></td>
								<td><c:out value="${edu.degree}" /></td>
								<td><c:out value="${edu.studyField}" /></td>
								<td><c:out value="${edu.grade}" /></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>


		<!-- WORK EXPERIENCE TAB -->

		<div id="edit-work" class="row margin-30">
			<form id="addWorkForm" action="addWorkForm" method="post">
				<div id="addWork">
					<div class="col-md-12">
						<h4>Add new work experience</h4>
						<button type="button"
							class="btn btn-outline-danger btn-default float-right"
							id="cancelWork">Cancel</button>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="hidden-sm-down">Title:</label> <input type="text"
								class="form-control" name="title" placeholder="Title" required>
						</div>
						<div class="form-group">
							<label class="hidden-sm-down">Company:</label> <input type="text"
								class="form-control" name="company" placeholder="Company"
								required>
						</div>
						<div class="form-group">
							<label class="hidden-sm-down">Location:</label> <input
								type="text" class="form-control" name="location"
								placeholder="Mars">
						</div>
					</div>
					<div class="col-md-6">

						<div class="form-group">
							<label class="hidden-sm-down">From:</label> <input type="date"
								class="form-control" name="fromDate" required>
						</div>

						<div class="form-group">
							<label class="hidden-sm-down">To:</label> <input type="date"
								class="form-control" name="toDate" required>
						</div>
						<div class="form-group">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox" value="working"
								name="currentWorking"> Current employer
							</label>
						</div>
					</div>
					<div class="col-md-12">
						<h6 class="float-left">Description</h6>
						<textarea class="form-control" name="description" rows="3"></textarea>
					</div>
					<div class="col-md-12 margin-30">
						<button type="submit" class="btn btn-block btn-primary">Save</button>
					</div>

				</div>
			</form>


			<div class="col-md-12">
				<h4 class="float-left">Work Experience</h4>

				<button class="btn btn-default btn-primary float-right"
					id="add-newWork-template">Add New</button>
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

						<c:forEach items="${works}" var="experience">
							<tr class="job-row">
								<!-- // This is the one to repeat -->
								<td colspan="5">
									<table>
										<tr>
											<th scope="row" class="t-s"><a
												class="btn btn-sm btn-outline-danger"
												href="addWorkForm?expId=${experience.experienceId}"><i
													class="fa fa-trash-o" aria-hidden="true"></i></a> <c:out
													value="${experience.title}" /></th>
											<td class="t-s"><c:out value="${experience.company}" /></td>
											<td class="t-s"><c:out value="${experience.location}" /></td>
											<td class="t-d"><c:out value="${experience.fromDate}" /></td>
											<td class="t-d"><c:out value="${experience.toDate}" /></td>
										</tr>
										<tr>
											<td colspan="5"><c:out value="${experience.description}" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>



		<!-- SKILLS TAB -->

		<div id="edit-skills" class="row margin-30">
			<!-- Skills -->
			<div class="col-md-6">
				<h4>Skills</h4>

				<c:choose>
					<c:when test="${not empty error}">
						<div class="alert alert-danger" role="alert">${error}</div>
					</c:when>
					<c:otherwise>

					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${not empty success}">
						<div class="alert alert-success" role="alert">${success}</div>
					</c:when>
					<c:otherwise>

					</c:otherwise>
				</c:choose>

				<div class="row">
					<div class="col-md-12">
						<form id="formAddSkill" action="manageSkill" method="post">
							<div class="form-group bottom-blue">
								<h5>Add</h5>
							</div>
							<div class="form-group">
								<label class="hidden-sm-down">Skill:</label> <input type="text"
									class="form-control" name="skill"
									placeholder="E Java, JS, Angular, Node " required> <small>Add
									one by one</small>
							</div>
							<div class="form-group">
								<label class="hidden-sm-down">Level:</label> <select
									class="form-control" id="skillLevel" name="level">
									<option>Less than 1 year</option>
									<option>1 to 3 years</option>
									<option>3 to 5 years</option>
									<option>I own it!</option>
								</select>
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-block btn-primary">Add</button>
							</div>
						</form>

					</div>
					<div class="col-md-12">
						<div class="form-group bottom-blue">
							<h5>My Skills</h5>

						</div>
						<table class="table">
							<thead class="thead-default">
								<tr>
									<th><i class="fa fa-trash-o" aria-hidden="true"></i></th>
									<th>Skill</th>
									<th>Level</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${skills}" var="skill">
									<tr>
										<th scope="row"><a class="btn btn-sm btn-outline-danger"
											href="manageSkill?skill=${skill.skill}&tab=4"><i
												class="fa fa-trash-o" aria-hidden="true"></i></a></th>
										<td><c:out value="${skill.skill}" /></td>
										<td><c:out value="${skill.level}" /></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- LANGUAGES -->
			<div class="col-md-6">
				<h4>Languages</h4>
				<div class="row">
					<div class="col-md-12">
						<form id="formAddLang" action="manageLanguage" method="post">
							<div class="form-group bottom-blue">
								<h5>Add</h5>
							</div>
							<div class="form-group">
								<label class="hidden-sm-down">Language:</label> <input
									type="text" class="form-control" name="languageName"
									placeholder="English" required> <small>Add one
									by one</small>
							</div>
							<div class="form-group">
								<label class="hidden-sm-down">Proeficiency:</label> <select
									class="form-control" id="profi" name="proficiency">
									<option>Beginner</option>
									<option>Intermediate</option>
									<option>Fluent</option>
									<option>Advanced</option>
								</select>
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-block btn-primary">Add</button>
							</div>
						</form>

					</div>
					<div class="col-md-12">
						<div class="form-group bottom-blue">
							<h5>My Languages</h5>

						</div>

						<table class="table">
							<thead class="thead-default">
								<tr>
									<th><i class="fa fa-trash-o" aria-hidden="true"></i></th>
									<th>Skill</th>
									<th>Level</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${languages}" var="language">
									<tr>
										<th scope="row"><a class="btn btn-sm btn-outline-danger"
											href="manageLanguage?languageName=${language.languageName}&tab=4"><i
												class="fa fa-trash-o" aria-hidden="true"></i></a></th>
										<td><c:out value="${language.languageName}" /></td>
										<td><c:out value="${language.proficiency}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<!-- OTHER TAB -->
		<form action="otherProfile" method="post" id="updateOther"
			enctype="multipart/form-data">
			<div id="edit-other" class="row margin-30">
				<div class="col-md-12">
					<h4 class="float-left">Other Information</h4>
				</div>
				<div class="col-md-6">
					<div class="form-group bottom-blue">
						<h5>Info for your profile</h5>
						<input type="hidden" name="tab" value="5">
					</div>

					<div class="form-group">
						<label class="hidden-sm-down">Title:</label> <input type="text"
							value="${standard.appliedPosition}" class="form-control"
							name="title" placeholder="Title of the position you want to fill">
						<small>This is the title of the position you are looking
							to fill</small>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Status:</label> <input type="text"
							class="form-control" value="${standard.status}" name="status"
							placeholder="Studying Ms. in CS"> <small>Just a
							short sentence to let them know what's up with you.</small>
					</div>
					<div class="form-group bottom-blue">
						<h5>Social Networks</h5>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Linkedin:</label> <input type="text"
							class="form-control" value="${standard.linkedInURL}"
							name="linkedin" placeholder="https://linkedin.com/in/yourprofile">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group bottom-blue">
						<h5>Resume</h5>
					</div>

					<div class="form-group">
						<label class="custom-file"> <input type="file" id="resume"
							accept="application/pdf" name="resume" class="custom-file-input">
							<span class="custom-file-control"></span>

						</label>

						<c:choose>
							<c:when test="${not empty standard.attachmentURL}">
									<a
									href="getResumeById?type=profile"
									class="btn btn-outline-primary btn-default btn-download">
							Download <i class="fa fa-file-text-o"></i></a>

							</c:when>
							<c:otherwise>

							</c:otherwise>
						</c:choose>


					</div>
					<div class="form-group bottom-blue">
						<h5>Picture</h5>
					</div>
					<div class="form-group">
						<img src="<%=request.getContextPath() %>${standard.photoURL}"
							class="loading-avatar" /> <label class="custom-file"> <c:choose>
								<c:when test="${not empty standard.photoURL}">
									<input type="file" id="resume" name="picture"
										class="custom-file-input">
									<span class="custom-file-control"></span>

								</c:when>
								<c:otherwise>
									<input type="file" id="resume" name="picture"
										class="custom-file-input" required>
									<span class="custom-file-control">Picture</span>

								</c:otherwise>
							</c:choose>

						</label> <br> <small>This is not necessary to apply for a
							job.</small>
					</div>


				</div>

				<div class="col-md-12">
					<div class="form-group bottom-blue">
						<h5>About yourself</h5>
					</div>
					<textarea name="about" class="form-control" rows="5"
						placeholder="This should be an introduction about yourself">${standard.aboutYourself}</textarea>
				</div>
				<div class="col-md-12 margin-30">
					<button type="submit" class="btn btn-block btn-primary">Save</button>
				</div>
				<input type="hidden" name="templateId"
					value="${standard.templateId}" />

			</div>
		</form>
	</div>
</div>

<%@ include file="components/footer.html"%>