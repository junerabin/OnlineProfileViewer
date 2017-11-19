<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="components/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-8 login">
			<div class="col-md-12">
				<h3>Feedback</h3>

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

				<form action="sendFeedBack" method="post">
					<div class="form-group">
						<label>Your name:</label> <input type="text" name="name"
							class="form-control" placeholder="Type your name" required>
					</div>
					<div class="form-group">

						<label>Your email:</label> <input type="email" name="email"
							class="form-control" placeholder="Type your email" required>
					</div>
					<div class="form-group">

						<label>Type:</label> <select class="form-control" name="type">
							<option>Feedback</option>
							<option>New feature request</option>
						</select>
					</div>
					<div class="form-group">

						<label>Message:</label>
						<textarea name="message" class="form-control"
							placeholder="Type your message"></textarea>
					</div>
					<button type="submit" class="btn btn-block btn-primary ">
						Send</button>

				</form>
			</div>
		</div>
	</div>
</div>
</header>
<%@ include file="components/footer.html"%>