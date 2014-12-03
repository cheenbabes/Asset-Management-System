<%-- 
    Document   : editUser
    Created on : Nov 3, 2014, 10:37:38 AM
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
        <title>Add User</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="include/header.jsp"/>
            
            <div class="col-md-6">
            <sf:form action="submitEditUser" method="post" id="addEditUser" cssClass="form-horizontal" role="form" modelAttribute="user">
                <div class="form-group">
                    <sf:label path="name" cssClass="col-sm-2 control-label">Name</sf:label>
                        <div class="col-sm-10">
                        <sf:input path="name" type="text" cssClass="form-control" id="name" placeholder="Name" name="name"/>
                    </div>
                </div>
                <div class="form-group">
                    <sf:label path="userName" cssClass="col-sm-2 control-label">Username</sf:label>
                        <div class="col-sm-10">
                        <sf:input path="userName" type="text" cssClass="form-control" id="user" placeholder="Username" name="userName"/>
                    </div>
                </div>
                <div class="form-group">
                    <sf:label path="password" cssClass="col-sm-2 control-label">Password</sf:label>
                        <div class="col-sm-10">
                        <sf:input path="password" type="password" cssClass="form-control" id="location" placeholder="password" name="password"/>
                    </div>
                </div>
                <div class="form-group">
                    <sf:label path="goodStanding" cssClass="col-sm-2 control-label">Standing</sf:label>
                        <div class="col-sm-10">
                        <sf:select path="goodStanding" name="standing" cssClass="form-control">
                            <sf:option value="true">True</sf:option>
                            <sf:option value="false">False</sf:option>
                        </sf:select>
                    </div>
                </div>

                <div class="form-group">
                    <sf:label path="enabled" cssClass="col-sm-2 control-label">Enabled</sf:label>
                        <div class="col-sm-10">
                        <sf:select path="enabled" name="enabled" cssClass="form-control">
                            <sf:option value="1">Yes</sf:option>
                            <sf:option value="0">No</sf:option>
                        </sf:select>
                    </div>
                </div>

                <sf:hidden path="userId" value="${user.userId}"/>

                <sf:label path="events" cssClass="col-sm-2 control-label">Events</sf:label>

                <jstl:forEach var="event" items="${events}">
                    <div class="col-md-12"> 

                        <table class="table table-hover">

                            <tr>
                                <th></th>
                                <th>Event Name</th>
                                <th>check Out Date</th>
                                <th>Due Date</th>
                                <th>Assets</th>
                            </tr>
                            <tr>
                                <td><img class="image-responsive" src="${types.imagePath}" alt="..."></td>
                                <td>${event.eventName}</td>
                                <td>${event.checkOutDate}</td>
                                <td>${event.dueDate}</td>
                                <jstl:forEach var="assets" items="${eventAssets}">
                                    <td>${event.assets}</td>
                                <tr/>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </jstl:forEach>


                        </table>
                    </div>
                </jstl:forEach>


                <br><br>
                <div class="form-group">
                    <div class="col-sm-3">
                        <input type="submit" class="form-control btn btn-primary" value="Edit User"/>
                    </div>
                </div>
            </sf:form>
            </div>

            <jsp:include page="include/footer.jsp"/>
        </div>

        <script>
            $(document).ready(function () {
                $('#addEditUser').bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        name: {
                            message: 'The name is not valid',
                            validators: {
                                notEmpty: {
                                    message: 'The name is required and cannot be empty'
                                },
                                stringLength: {
                                    min: 3,
                                    max: 20,
                                    message: 'The name must be more than 3 and less than 20 characters long'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z ]/,
                                    message: 'The name can only consist of numbers'
                                }
                            }
                        },
                        userName: {
                            message: 'The user name is not valid',
                            validators: {
                                notEmpty: {
                                    message: 'The user name is required and cannot be empty'
                                },
                                stringLength: {
                                    min: 3,
                                    max: 20,
                                    message: 'The user name must be more than 3 and less than 20 characters long'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z0-9+]*$/,
                                    message: 'The user name can only consist of letters and numbers'
                                }
                            }
                        },
                        password: {
                            message: 'The user name is not valid',
                            validators: {
                                notEmpty: {
                                    message: 'The user name is required and cannot be empty'
                                },
                                stringLength: {
                                    min: 5,
                                    max: 20,
                                    message: 'The name must be more than 5 and less than 20 characters long'
                                }
                            }
                        },
                        standing: {
                        }
                    }

                });
            });
        </script>
    </body>
</html>
