<%-- 
    Document   : userAddNote
    Created on : Nov 4, 2014, 11:49:52 AM
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
        <title>Add Note To Event</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>

        <jsp:include page="include/header.jsp"/>

        <div class="container">

            <div class="row" style="margin-bottom: 20px">
                <div class="col-md-2">
                    <a href="index" class="btn btn-default"><span class="glyphicon glyphicon-chevron-left"></span> Back</a><br><br>
                    <a href="editEvent?eventId=${event.eventId}" class="btn btn-primary glyphicon glyphicon-plus"> Edit Event</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">

                        <tr>
                            <th>Event Name</th>
                            <th>Username</th>
                            <th>Good Standing</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Open</th>
                        </tr>
                        <tr>
                            <td>${event.eventName}</td>
                            <td>${event.user.userName}</td>
                            <td>${event.user.goodStanding}</td>
                            <td>${event.checkOutDate}</td>
                            <td>${event.dueDate}</td>
                            <td>${event.open}</td>
                        </tr>
                    </table>
                        <br><br>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">
                        <tr>
                            <th>Notes</th>
                        </tr>
                        <jstl:forEach var="eventNote" items="${eventNoteList}"> 
                            <tr>
                                <td>${eventNote.noteDate}: ${eventNote.note}</td>
                            </tr>
                        </jstl:forEach>

                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">

                    <sf:form class="form-horizontal" action="submitNewEventNote" method="post" role="form" modelAttribute="eventNote">
                        <div class="form-group">
                            <div class="col-md-3">
                                <sf:label path="note" cssClass="control-label">Note</sf:label>
                                </div>
                                <div class="col-sm-9">
                                <sf:textarea path="note" cssClass="form-control" id="note" name="eventNote" placeholder="Enter Note Here"></sf:textarea>
                                </div>
                            </div>

                        <sf:hidden path="eventId"/>
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input type="submit" class="btn btn-primary" value="Add Note"/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>

        </div>

    </body>
</html>

