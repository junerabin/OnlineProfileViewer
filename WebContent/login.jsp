<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="components/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 login">
			<div class="col-md-12">
				<h3>Login</h3>
				<c:choose>
					<c:when test="${not empty error}">
						<div class="alert alert-danger" role="alert">${error}</div>
					</c:when>
					<c:otherwise>

					</c:otherwise>
				</c:choose>

				<form action="login" method="post">
					<div class="form-group">
						<label>Username:</label> <input type="text" name="username"
							class="form-control" placeholder="Username" required>
					</div>
					<div class="form-group">
						<label>Password:</label> <input type="password" name="password"
							class="form-control" placeholder="Password" required>
					</div>
					<button type="submit" class="btn btn-block btn-primary ">
						Login</button>
				</form>
			</div>
			<div class="col-md-12 text-center">
				<p>Don't have an account?</p>
				<a href="signup" class="btn btn-outline-primary btn-block"> Sign up
					here </a> <small>using yout @mum.edu account</small>
			</div>
		</div>
	</div>
</div>
</header>
<%@ include file="components/footer.html"%>