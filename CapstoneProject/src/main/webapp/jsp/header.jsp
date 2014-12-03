<%-- 
    Document   : header
    Created on : Oct 29, 2014, 9:25:27 AM
    Author     : apprentice
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Outdoor Outfitters</title>

        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="../bootstrapvalidator-0.5.2/dist/css/bootstrapValidator.min.css"/>
        <link href="../css/bootstrap.min.css" rel="stylesheet"/>

        <script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
        <script src="../js/validateAddEditEvent.js" type="text/javascript"></script>
        <script src="../js/validateAddEditAsset.js" type="text/javascript"></script>
        <script src="../js/validateAddEditUser.js" type="text/javascript"></script>

        <script src="http://imsky.github.com/holder/holder.js"></script>

    </head>

    <body>
        <div class="container">
            <div class="page-header">
                <div class="text-center">
                    <a href="index.jsp"><img src="../img/Meadow-Of-Flowers-1080x1920.jpg" alt="" /></a>
                    <br>
                </div>
            </div>


            <nav class="navbar navbar-default" role="navigation">

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">

                        <li class="dropdown">
                            <a href="#events" class="dropdown-toggle" data-toggle="dropdown">Events<span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="../index.jsp" role="button">Events</a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="browseAssets.jsp" class="dropdown-toggle" data-toggle="dropdown">Assets <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="browseAssets.jsp">Browse Assets</a>
                                </li>
                                <li><a href="../manageAssets.jsp">Manage Assets</a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#users" class="dropdown-toggle" data-toggle="dropdown">Users <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="../viewAllUsers.jsp">View All Users</a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="../reports.html" class="dropdown-toggle" data-toggle="dropdown">Reports <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="../reports.jsp">View All Reports</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <p class="navbar-text navbar-right">Signed in as Eugene</p>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#account" class="dropdown-toggle" data-toggle="dropdown">Account <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="signin">Sign In</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="#manageMyEventsModal" role="button" data-toggle="modal">View My Events</a>
                                </li>
                                <li class="disabled"><a href="#">Manage My Account</a>
                                </li>
                                <li class="disabled">
                                    <a href="#"></a>
                                </li>
                                <li class="divider"></li>
                                <li class="disabled"><a href="#">Log Out</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </body>
</html>