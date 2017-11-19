<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="components/header.jsp" %>
                <div class="container">
            <div class="col-md-6">
                <p class="highlight-s">Results</p>
            </div>
        </div>
    </header>

    <div class="container-fluid">

        <div class="container rel">
           <div class="loader"><div class="logo"><i class="fa fa-paperclip"></i>Paperclip<br><h6>Loading...</h6></div></div>
            <div class="row margin-30 ">
                <div class="col-md-8">
                    <h2>Profiles</h2>
                    <p><small>Use the search function to narrow results.</small></p>
                </div>
                <div class="col-md-4 text-center"> 
                    <button type="button" class="btn btn-outline-primary btn-block" id="clearSearch">Clear Search / Filters</button>
                </div>
            </div>

            <div class="row margin-30 filters animate ">
                <div class="col-md-12"> 
                    <h4 class="float-left"> Search</h4> 
                   
                </div>

                <div class="col-md-12 " id="">
                    <div class="form-check">
                       <h6>Search by typing anything</h6>
                    </div>
                    <div class="form-check">
                        <input class="form-control form-control-sm" type="text" id="searchInput" placeholder="Type anything" value="">
                    </div>
                    <div class="form-check">
                       <button id="searchAction" class="btn btn-outline-primary btn-block">Search</button>
                    </div>
                </div> 
            </div>
             <div class="row margin-30 line-bt card-deck" id="list-results">
            </div>
        </div>
    </div>
<%@ include file="components/footer.html" %>