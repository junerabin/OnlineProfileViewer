<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="components/header.jsp" %>

        <div class="container">
            <div class="col-md-6">
                <p class="highlight-s">Find candidates for the tech industry</p>
            </div>
        </div>
    </header>

    <div class="container-fluid">

        <div class="container">
           
            <div class="row margin-30 ">
                <div class="col-md-12">
                    <h2>Search</h2>
                    <p><small>Start by typing the name of the candidate, or browse in the complete database.</small></p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <form action="results.jsp" method="GET" target="_SELF" class="form-inline">
                       <input type="text" name="search" class="form-control p80" placeholder="Type to search..." title="Please type the name of the candidate or browse all of them" required>
                       <button type="submit" class="btn btn-primary p20"> Search</button>
                    </form>
                </div>
                <div class="col-md-4 text-center"> 
                    <a href="results.jsp" class="btn btn-outline-primary btn-block">or Browse all</a>
                </div>
            </div>

             <div class="row margin-30 line-bt">
                <div class="col-md-4"> <h4>Find the<br>right candidate</h4><p>Use this tool to search or browse in our database of candidates.</p></div>
                <div class="col-md-4"> <h4>Capables</h4><p>Our members are graduates of the MsCS program of MUM.</p></div>
                <div class="col-md-4"> <h4>Ready to work</h4><p>All our candidates have the legal permission to work in US at least for 1 year (CPT) while some of then can extend their permission up to 4 years.</p></div>
            </div>
            
        </div>
    </div>
<%@ include file="components/footer.html" %>