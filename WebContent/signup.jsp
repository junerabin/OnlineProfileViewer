<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="components/header.jsp"%>
<div class="container">
	<div class="col-md-6">
		<p class="highlight-s">Create your account</p>
	</div>
</div>
</header>
<div class="container-fluid">
	<div class="container">

		<div class="row margin-30 ">
			<div class="col-md-8">
				<h2>Sign up</h2>
				<p>
					<small>Please use your @mum.edu account</small>
				</p>
			</div>
		</div>
		<form action="signup" method="post">
			<div class="row margin-30 line-bt card-deck">


				<div class="col-md-6">
					<div class="form-group bottom-blue">
						<h5>Personal information</h5>
					</div>
					<c:choose>
						<c:when test="${not empty error}">
							<div class="alert alert-danger" role="alert">${error}</div>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>

					<div class="form-group">
						<label class="hidden-sm-down">First Name:</label> <input
							type="text" class="form-control" name="firstname"
							placeholder="Your first name" required>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Middle Name:</label> <input
							type="text" class="form-control" name="middlename"
							placeholder="Your middle name">
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Last Name:</label> <input
							type="text" class="form-control" name="lastname"
							placeholder="Your last name" required>
					</div>
					<div class="form-group bottom-blue">
						<h5>Contact information</h5>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Email address:</label> <input
							type="text" class="form-control" name="email"
							pattern="[A-Za-z0-9]+@mum.edu"
							title="You must create your account with an @mum.edu email account"
							placeholder="youremail@mum.edu" required> <small>Please
							use your @mum.edu email</small>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Phone Number:</label> <input
							type="phone" class="form-control" name="phone"
							placeholder="(000) 000-0000" required>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group bottom-blue">
						<h5>Account information</h5>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Username:</label> <input type="text"
							class="form-control" name="username" placeholder="Username"
							required>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Password</label> <input
							type="password" class="form-control" name="password"
							placeholder="Password" required>
					</div>
					<div class="form-group bottom-blue">
						<h5>Address</h5>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Address:</label> <input type="text"
							class="form-control" name="street1" placeholder="123 Address St"
							required>
					</div>
					<div class="form-group">
						<label class="hidden-sm-down">Address 2:</label> <input
							type="text" class="form-control" name="street2"
							placeholder="Building / Apt" required>
					</div>
					<div class="row">
						<div class="form-group col-sm-6">
							<label class="hidden-sm-down">City:</label> <input type="text"
								class="form-control" name="city" placeholder="City">
						</div>

						<div class="form-group col-sm-6">
							<label class="hidden-sm-down">State:</label> <input type="text"
								class="form-control" name="state" placeholder="State">
						</div>
					</div>
					<div class="row">
						<div class="form-group col-sm-6">
							<label class="hidden-sm-down">Zip Code:</label> <input
								type="text" class="form-control" name="zipcode"
								placeholder="Zipcode">
						</div>

						<div class=" form-group col-sm-6">
							<label class="hidden-sm-down">Country:</label> <input type="text"
								class="form-control" name="country" value="United States"
								placeholder="country" disabled>
						</div>
					</div>

				</div>
				<div class="col-md-12">
					<button type="submit" class="btn btn-block btn-primary">Sign
						up</button>
				</div>

			</div>
		</form>
	</div>
</div>
<%@ include file="components/footer.html"%>