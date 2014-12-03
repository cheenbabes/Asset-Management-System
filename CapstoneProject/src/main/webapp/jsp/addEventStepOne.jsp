<%-- 
    Document   : addEventStepOne
    Created on : Oct 29, 2014, 11:04:57 AM
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
        <title>Add Event Step One</title>
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

            <sf:form class="form-horizontal" action="submitAddEventStepOne" method="post" role="form" id="addEditEvent" name="editAddEvent" modelAttribute="event">
                <div class="form-group">
                    <sf:label path="eventName" cssClass="col-sm-2 control-label">Event Name</sf:label>
                        <div class="col-sm-5">
                        <sf:input path="eventName" type="text" cssClass="form-control" id="name" placeholder="Name" name="eventName"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">UserName</label>
                    <div class="col-sm-5">
                        <select class="control-label" name="userName">
                            <jstl:forEach var="user" items="${userList}">
                                <option value="${user.userId}">${user.userName}</option>
                            </jstl:forEach>
                        </select>
                    </div>
                </div>
                <!--                <div class="form-group">
                                    <label for="input" class="col-sm-2 control-label">Location</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="location" placeholder="Location" name="location"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inout" class="col-sm-2 control-label">Size</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="size" placeholder="How many are in your party" name="size"/>
                                    </div>
                                </div>-->
                <div class="form-group">
                    <sf:label path="checkOutDate" cssClass="col-sm-2 control-label">Start Date</sf:label>
                        <div class="col-sm-5">
                        <sf:hidden path="checkOutDate" value="${startDate}"/>
                        <label style="padding-top: 7px">${startDate}</label>
                    </div>
                </div>
                <div class="form-group">
                    <sf:label path="dueDate" cssClass="col-sm-2 control-label">End Date</sf:label>
                        <div class="col-sm-5">
                        <sf:input path="dueDate" type="date" cssClass="form-control" id="endDate" name="dueDate"/>
                    </div>
                </div>
                <!--                <div class="form-group">
                                    <label for="inputEmail" class="col-sm-2 control-label">Image</label>
                                    <div class="col-sm-10">
                                        <input type="file" class="form-control" name="image"/>
                                    </div>
                                </div>-->
                <!--                <div class="form-group">
                                    <label for="input" class="col-sm-2 control-label">Note</label>
                                    <div class="col-sm-10">
                                        <textArea class="form-control" id="note" name="note">Enter Note Here</textArea>
                                </div>
                            </div>-->

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" class="btn btn-primary glyphicon glyphicon-chevron-right" value="Next >"/>
                    </div>
                </div>
            </sf:form>



            <!--        <a class="btn btn-primary glyphicon glyphicon-chevron-right" href="addEventStepTwo.jsp" >Next</a>-->







            <jsp:include page="include/footer.jsp"/>
        </div>

        <script>
            $(document).ready(function () {
                $('#addEditEvent').bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        eventName: {
                            message: 'The name is not valid',
                            validators: {
                                notEmpty: {
                                    message: 'The name is required and cannot be empty'
                                },
                                stringLength: {
                                    min: 2,
                                    max: 30,
                                    message: 'The name must be more than 2 and less than 30 characters long'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z0-9_ ()]+$/,
                                    message: 'The name can only consist of alphabetical, number and underscore'
                                }
                            }
                        },
                        dueDate: {
                            message: 'The image path is not valid',
                            validators: {
                                notEmpty: {
                                    message: 'The Due Date is required and cannot be empty'
                                },
                            }
                        }


                    }
                });
            });
        </script>
        
    </body>
</html>
