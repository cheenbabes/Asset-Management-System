<%-- 
    Document   : header
    Created on : Oct 29, 2014, 9:25:27 AM
    Author     : apprentice
--%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Outdoor Outfitters</title>

        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <!--        <link rel="stylesheet" href="bootstrapvalidator-0.5.2/dist/css/bootstrapValidator.min.css"/>-->
        <link href="css/bootstrap.min.css" rel="stylesheet"/>

        <!--        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
               <script src="js/bootstrap.min.js" type="text/javascript"></script>
                <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>-->


        <script src="http://imsky.github.com/holder/holder.js"></script>

    </head>

    <body>
        <div class="container">
            <div class="page-header">
                <div class="text-center">
                    <a href="home"><img src="img/Meadow-Of-Flowers-1080x1920.jpg" alt="" /></a>
                    <br>
                </div>
            </div>


            <nav class="navbar navbar-default" role="navigation">

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">

                        <li class="dropdown">
                            <a href="#events" class="dropdown-toggle" data-toggle="dropdown">Events<span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="home" role="button">Events</a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="browseAssets.jsp" class="dropdown-toggle" data-toggle="dropdown">Assets <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="assets">Browse Assets</a>
                                </li>
                                <li><a href="manage_assets">Manage Assets</a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#users" class="dropdown-toggle" data-toggle="dropdown">Users <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="viewAllUsers">View All Users</a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="../reports.html" class="dropdown-toggle" data-toggle="dropdown">Reports <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="reports">View All Reports</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <sec:authorize access="isAuthenticated()">
                        <p class="navbar-text navbar-right">
                            Signed in as <sec:authentication property="principal.username" /> <%--only works if you're already logged in--%>
                        </p>
                    </sec:authorize>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#account" class="dropdown-toggle" data-toggle="dropdown">Account <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="login">Sign In</a>
                                </li>
                                <%-- functionality pending...
                                
                                <li class="divider"></li>
                                <li><a href="#manageMyEventsModal" role="button" data-toggle="modal">View My Events</a>
                                </li>
                                --%>
                                <li class="disabled"><a href="#">Manage My Account</a>
                                </li>
                                <li class="disabled">
                                    <a href="#"></a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="<c:url value="j_spring_security_logout" />">Log Out</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </body>
</html>