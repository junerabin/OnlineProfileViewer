<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Paperclip - Online Profile Viewer</title>
<link type="text/css"href="css/bootstrap.css" rel="stylesheet">
<link type="text/css"href="css/animate.css" rel="stylesheet">
<link type="text/css"href="css/font-awesome.min.css" rel="stylesheet">
<link type="text/css" href="<c:url value='./css/custom.css' />" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800"
	rel="stylesheet">
</head>
<body>
	<div id="loader">
		<div class="logo">
			<i class="fa fa-paperclip"></i>Paperclip
		</div>
	</div>
	<header class="container-fluid nopadding">
		<div class="container-fluid menu">
			<div class="row">
				<div class="col-sm-4 logo">
					<i class="fa fa-paperclip"></i>Paperclip
				</div>
				<div class="col-sm-8">
					<ul class="float-right">
						<li><a href="index.jsp">Home</a></li>
						<li><a href="feedback.jsp">Feedback</a></li>

						<c:choose>
							<c:when test="${not empty loginStatus}">
								<li><a href="logout"
									class="btn btn-outline-secondary btn-sm">Logout</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="login"
									class="btn btn-outline-secondary btn-sm">Login</a></li>
							</c:otherwise>
						</c:choose>

					</ul>

				</div>
			</div>
		</div>