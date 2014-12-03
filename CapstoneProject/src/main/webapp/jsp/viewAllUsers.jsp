<%-- 
    Document   : viewAllUsers
    Created on : Oct 29, 2014, 11:21:26 AM
    Author     : apprentice
--%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Users</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="include/header.jsp"/>



            <div class="container"> 
                <div class="row" style="padding: 0px; margin: 0px; margin-bottom: 30px">
                    <a class="btn btn-primary glyphicon glyphicon-plus" href="addUser">Add User</a>
                </div>

                <div class="row">

                    <jstl:forEach var="user" items="${userList}">
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="thumbnail">
<!--                                <img class="img-responsive" data-src="holder.js/242x200" alt="...">-->
                                <!--add the generated img tag here-->
                                <div class="caption">
                                    <h4><span>${user.name}</span> </h4>

                                    <p>   
                                        <br><b>Username:</b> <span>${user.userName}</span>
                                        <br><b>Enabled:</b> <span>${user.enabled}</span>
                                        <br><b>Standing:</b> <span>${user.goodStanding}</span>
                                        <br>
                                    </p>
                                    <a  href="viewUserInfo?userId=${user.userId}" class="btn btn-warning">Additional Info</a><br><br>
                                    <a href="editUser?userId=${user.userId}" class="btn btn-primary" role="button">Edit</a>  <a href="deleteUser?userId=${user.userId}" class="btn btn-danger" role="button" data-toggle="modal">Delete</a><br/><br/>
                                    <a href="userAddNote?userId=${user.userId}" class="btn btn-success glyphicon glyphicon-plus">Add Note</a>
                                </div>
                            </div>
                        </div>
                    </jstl:forEach>


                </div><!--row-->
            </div><!--container-->

            <jsp:include page="include/footer.jsp"/>
        </div>
    </body>
</html>
