<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="components/header.jsp"%>
<div class="container">
	<div class="col-md-6">
		<p class="highlight-s">Welcome Admin</p>
	</div>
</div>
</header>

<div class="container-fluid">

	<div class="container">
		<form action="adminFunctions" method="POST">
			<div class="row margin-30 ">
				<div class="col-md-12">
					<ul class="nav nav-tabs">

						<li class="nav-item"><a class="nav-link active"
							id="feedback-button"> Feedback </a></li>
						<li class="nav-item"><a class="nav-link"
							id="dynamictemplate-button">Dynamic Templates</a></li>
					</ul>
				</div>

			</div>

			<div id="feedback-list" class="row margin-30">
				<h4 class="float-left">Feedback</h4>
				<table class="table">
					<thead class="thead-default">
						<tr>
							<th>Date</th>
							<th>Type</th>
							<th>Sent by</th>
							<th>Email</th>
							<th>Message</th>
						</tr>
					</thead>
					<tbody>
						<!-- <tr>
						<th scope="row">09/09/2017</th>
						<td>Feedback</td>
						<td>Andres Cala Rey</td>
						<td>acala@mum.edu</td>
						<td>This is my message that could be a little longet</td>
					</tr>
					<tr>
						<th scope="row">09/09/2017</th>
						<td>Feedback</td>
						<td>Andres Cala Rey</td>
						<td>acala@mum.edu</td>
						<td>This is my message that could be a little longet</td>
					</tr>
					<tr>
						<th scope="row">09/09/2017</th>
						<td>Feedback</td>
						<td>Andres Cala Rey</td>
						<td>acala@mum.edu</td>
						<td>This is my message that could be a little longet</td>
					</tr> -->
						<c:forEach items="${feedBackList}" var="feedBack">
							<tr>
								<td><c:out value="${feedBack.date}" />
									<p></td>
								<td><c:out value="${feedBack.feedBackType}" />
									<p></td>
								<td><c:out value="${feedBack.name}" />
									<p></td>
								<td><c:out value="${feedBack.email}" />
									<p></td>
								<td><c:out value="${feedBack.message}" />
									<p></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

			</div>

			<div id="template-list" class="row margin-30">

				<h4 class="float-left">Dynamic Templates</h4>

				<a class="btn btn-default btn-primary float-right"
					href="dynamicTemplate.jsp" id="add-new-template">Add New</a>

				<table class="table">
					<thead class="thead-default">
						<tr>
							<th>Input Type</th>
							<th>Field Name</th>
							<th>Label Name</th>
							<th>Description</th>
							<th>Is Active</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
						<!--  <tr>
                      <th scope="row">Text</th>
                      <td>some name</td>
                      <td>some label</td>
                      <td>This is the description</td>
                      <td>true</td>
                      <td><button class="btn btn-sm btn-outline-primary">Edit</button></td>
                    </tr> -->
						<c:forEach items="${dynamicList}" var="dynamic">
							<tr>
								<td><c:out value="${dynamic.inputType}" />
									<p></td>
								<td><c:out value="${dynamic.fieldName}" />
									<p></td>
								<td><c:out value="${dynamic.labelName}" />
									<p></td>
								<td><c:out value="${dynamic.description}" />
									<p></td>
								<td><c:out value="${dynamic.isActive}" />
									<p></td>
								<td><a href="editTemplate?id=${dynamic.dynamicId}">Edit</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</div>

<%@ include file="components/footer.html"%>