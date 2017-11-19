<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="components/header.jsp"%>
<div class="container">
	<div class="col-md-6">
		<p class="highlight-s">Dynamic Template</p>
	</div>
</div>
</header>

<div class="container-fluid">

	<div class="container">

		<div class="row margin-30 ">
			<div class="col-md-8">
				<h2>Dynamic Input Field</h2>
			</div>

		</div>
		<form action="manageTemplate" method="POST">
			<div class="row margin-30 line-bt card-deck">

				<div class="col-md-12">
					<div class="form-group">
						<label class="hidden-sm-down">Input Type:</label>
					</div>
					<div class="form-group">
						<div class="form-check form-check-inline">
							<label class="form-check-label"> <c:choose>
									<c:when test="${dynamicTemplate.inputType eq 'inputtext'}">
										<input class="form-check-input" type="radio" name="fieldtype"
											id="btn-inputtext" value="inputtext" checked> Text Input
							</c:when>
									<c:otherwise>
										<input class="form-check-input" type="radio" name="fieldtype"
											id="btn-inputtext" value="inputtext"> Text Input
							</c:otherwise>
								</c:choose>
							</label>
						</div>
						<div class="form-check form-check-inline">
							<label class="form-check-label"> <c:choose>
									<c:when test="${dynamicTemplate.inputType eq 'textarea'}">
										<input class="form-check-input" type="radio" name="fieldtype"
											id="btn-textarea" value="textarea" checked> Text Area
							</c:when>
									<c:otherwise>
										<input class="form-check-input" type="radio" name="fieldtype"
											id="btn-textarea" value="textarea"> Text Area
							</c:otherwise>
								</c:choose>


							</label>
						</div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label class="hidden-sm-down">Field Name:</label>

						<c:choose>
							<c:when test="${not empty dynamicTemplate.fieldName}">
								<input type="text" class="form-control"
									value="${dynamicTemplate.fieldName}" name="fieldname" disabled
									placeholder="Field Name" required>
							</c:when>
							<c:otherwise>
								<input type="text" class="form-control"
									value="${dynamicTemplate.fieldName}" name="fieldname"
									placeholder="Field Name" required>
							</c:otherwise>
						</c:choose>


					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Label Name:</label>


						<c:choose>
							<c:when test="${not empty dynamicTemplate.labelName}">
								<input type="text" class="form-control"
									value="${dynamicTemplate.labelName}" name="labelname" disabled
									placeholder="Label Name" required>
							</c:when>
							<c:otherwise>
								<input type="text" class="form-control"
									value="${dynamicTemplate.labelName}" name="labelname"
									placeholder="Label Name" required>
							</c:otherwise>
						</c:choose>


					</div>
				</div>

				<div class="col-md-6">

					<div class="form-check">
						<label class="form-check-label"> <c:choose>
								<c:when test="${dynamicTemplate.isActive eq true}">
									<input class="form-check-input" name="isActive" type="checkbox"
										id="blankCheckbox" aria-label="..." checked> Is Active
							</c:when>
								<c:otherwise>
									<input class="form-check-input" name="isActive" type="checkbox"
										id="blankCheckbox" aria-label="..."> Is Active
							</c:otherwise>
							</c:choose>

						</label>
					</div>
					<div class="form-group">
						<label for="description">Description</label>

						<c:choose>
							<c:when test="${not empty dynamicTemplate.description}">
								<textarea class="form-control" name="description"
									id="description" disabled rows="4">${dynamicTemplate.description}</textarea>
							</c:when>
							<c:otherwise>

								<textarea class="form-control" name="description"
									id="description" rows="4"></textarea>
							</c:otherwise>
						</c:choose>


					</div>


				</div>

				<input type="hidden" name="dynamicId"
					value="${dynamicTemplate.dynamicId}" />

				<div class="col-md-6">
					<a class="btn btn-block btn-outline-danger" href="admin.jsp">Cancel</a>
				</div>
				<div class="col-md-6">
					<button type="submit" class="btn btn-block btn-primary">Save</button>
				</div>

			</div>
		</form>
	</div>
</div>
<%@ include file="components/footer.html"%>