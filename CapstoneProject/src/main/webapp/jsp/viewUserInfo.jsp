<%-- 
    Document   : viewUserInfo
    Created on : Oct 29, 2014, 2:59:39 PM
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
        <title>User Info</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="include/header.jsp"/>

            <div class="container">
                <div class="row" style="margin-bottom: 20px">
                    <div class="col-md-2">
                        <a href="userAddNote?userId=${user.userId}" class="btn btn-success glyphicon glyphicon-plus">Add Note</a><br/><br/>
                        <a href="editUser?userId=${user.userId}" class="btn btn-primary glyphicon glyphicon-plus">Edit User</a>
                    </div>
                </div>
            </div>


            <div class="col-md-12">
                <table class="table table-hover">

                    <tr>

                        <th>Name</th>
                        <th>Username</th>
                        <th>GoodStanding</th>
                        <th>Enabled</th>
                    </tr>
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.userName}</td>
                        <td>${user.goodStanding}</td>
                        <td>${user.enabled}</td>
                    </tr>
                </table>
                <h2 class="text-center">Events</h2>

                <div class="col-md-12">
                    <table class="table table-hover">
                        <td>
                            <%-- <jstl:forEach var="event" items="${events}">--%>

                            <jstl:forEach items="${events}" var="userEvents">

                            <tr>
                                <th></th>
                                <th>Event Name</th>
                                <th>check Out Date</th>
                                <th>Due Date</th>
                                <th>Additional Info</th>
                            </tr>
                            <tr>
                                <td><img class="image-responsive" src="${types.imagePath}" alt="..."></td>
                                <td>${userEvents.eventName}</td>
                                <td>${userEvents.checkOutDate}</td>
                                <td>${userEvents.dueDate}</td>

                                <td>
                                    <a href="viewEventInfo?eventId=${userEvents.eventId}" class="btn btn-default">Additional Info</a>
                                </td>

                            <tr/>

                        </jstl:forEach>

                    </table>
                </div>
                <div class="col-md-8">
                    <table class="table table-hover">
                        <tr>
                            <th>Notes</th>
                        </tr>
                        <jstl:forEach var="userNote" items="${userNoteList}"> 
                            <tr>
                                <td>${userNote.noteDate}: ${userNote.note}</td>
                            </tr>
                        </jstl:forEach>

                    </table>
                </div>


                <jsp:include page="include/footer.jsp"/>
            </div>
        </div>
    </body>
</html>
