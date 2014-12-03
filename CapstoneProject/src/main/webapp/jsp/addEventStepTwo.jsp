<%-- 
    Document   : addEventStepTwo
    Created on : Oct 29, 2014, 11:06:05 AM
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
        <title>Add Event Step Two</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="bootstrapvalidator-0.5.2/dist/css/bootstrapValidator.min.css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>

        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="include/header.jsp"/>

            <div class="row" style="margin-left: 25px">
                <div class="col-md-12 col-sm-12 col-xs-12" style="border-bottom: 3px solid black; border-radius: 4px; padding-top: 15px; margin-bottom: 30px">
                    <form action="submitAddEventStepTwo" id="addEditEvent" role="form" method="get">

                        <div class="row"><%--EVENT INFO ROW--%>
                            <div class="col-md-12 col-sm-12 col-xs-12">

                                <h2 class="text-center">Add Event: Select Assets</h2>

                                <%--<div class="col-md-12" style="background-color: #ccccaa">--%>
                                <table class="table table-hover">

                                    <tr>
                                        <th>Event</th>
                                        <th>UserName</th>
                                        <th>Start Date</th>
                                        <th>Due Date</th>
                                        <th>Status</th>
                                    </tr>
                                    <tr>
                                        <td>${event.eventName}<br/><br/>
                                        </td>
                                        <td>${event.user.userName}<br/><br/>
                                        </td>
                                        <td>${event.checkOutDate}<br/><br/>
                                        </td>
                                        <td>${event.dueDate}<br/><br/>
                                            <input type="hidden" value="${event.eventId}" id="eventId"/>
                                        </td>
                                    
                                        <td>
                                            <jstl:choose>
                                                <jstl:when test="${event.open}">Open</jstl:when>
                                                <jstl:when test="${!event.open}">Closed</jstl:when>

                                            </jstl:choose>
                                            <br/><br/>
                                        </td>
                                        

                                    </tr>
                                </table>
                            </div>
                        </div>

                        <div class="row"><%--MANAGE EVENT ASSETS ROW--%>
                            <div class="col-md-12">
                                <jsp:include page="include/manageEventAssets.jsp"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>


            <%--<jsp:include page="include/addEditEventAssets.jsp"/>--%>


            <jsp:include page="include/footer.jsp"/>
        </div>
    </body>
</html>
