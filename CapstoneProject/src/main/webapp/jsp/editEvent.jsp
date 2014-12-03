<%-- 
    Document   : editEvent
    Created on : Oct 29, 2014, 11:10:49 AM
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
        <title>Edit Event</title> 
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="bootstrapvalidator-0.5.2/dist/css/bootstrapValidator.min.css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/mycss.css" rel="stylesheet"/>

        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>

    </head>
    <body>
        <jsp:include page="include/header.jsp"/>
        <div class="container">

            <div class="row">
                <div class="col-md-12">
                    <a href="viewEventInfo?eventId=${event.eventId}" class="btn btn-default">< Back to Event Info</a><br>

                    <span style="color: #ee0000">${badAssetError}</span>

                    <br>
                </div>
            </div>

            <div class="row">

                <div class="col-md-12"> 
                    <table class="table table-hover">
                        <tr>
                            <th>Event</th>
                            <th>User Name</th>
                            <th>Start Date</th>
                            <th>Due Date</th>
                            <th>Open</th>
                        </tr>
                        <tr>
                            <td>${event.eventName}</td>
                            <td>${event.user.name}</td>
                            <td>${event.checkOutDate}</td>
                            <td>${event.dueDate}</td>
                            <td>${event.open}</td>
                        </tr>
                    </table>
                    <div class="row">
                        <div class="col-md-4">
                            <sf:form id="addEditEvent" role="form" action="submitEditEvent" method="post" modelAttribute="event">
                                <div class="form-group">
                                    <sf:label path="eventName">Event Name</sf:label>
                                    <sf:input path="eventName" type="text" cssClass="form-control" id="name" name="eventName"/>
                                </div>
                                <div class="form-group">
                                    <label>UserName</label><br/>
                                    <select class="control-label" name="userName">
                                        <jstl:forEach var="user" items="${userList}">
                                            <option value="${user.userId}">${user.userName}</option>
                                        </jstl:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <sf:label path="checkOutDate">Start Date</sf:label><br/>
                                    <sf:input path="checkOutDate"  type="hidden" value="checkOutDate"/>
                                    <label>${event.checkOutDate}</label>
                                </div>
                                <div class="form-group">
                                    <sf:label path="dueDate">End Date</sf:label>
                                    <sf:input path="dueDate" type="date" cssClass="form-control" id="endDate" name="dueDate"/>
                                </div>
                                <div class="form-group">
                                    <sf:label path="open">Open</sf:label>
                                    <sf:select path="open" class="form-control" name="open">
                                        <sf:option value="true">True</sf:option>
                                        <sf:option value="false">False</sf:option>
                                    </sf:select>
                                </div>
                                <sf:hidden path="eventId" value="${event.eventId}"/>
                                <sf:hidden path="user.userId" value="${user.userId}"/><br><br>
                                <input type="submit" value="Edit Event" class="btn btn-primary"/>
                            </sf:form>
                        </div>
                    </div>
                </div>
            </div>


            <br>
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">
                        <tr>
                            <th>Asset Type</th>
                            <th>Category</th>
                            <th>Due</th>
                            <th>Returned</th>
                            <th>Damage</th>
                            <th>Serial Number</th>
                            <th>Notes</th>
                            <th>Actions</th>
                        </tr>
                        <jstl:forEach var="assets" items="${assetCheckedOutList}" varStatus="i">
                            <tr>
                                <td>${assets.assetType.name}</td>
                                <td>${assets.assetType.category.categoryName}</td>
                                <td>${event.dueDate}</td>
                                <td>${returnDates[i.index]}</td>
                                <%--<td><%-- Status --%><%--
                                    <jstl:choose>
                                        <jstl:when test="${false}"><%--overdue
                                            Overdue
                                        </jstl:when>
                                        <jstl:when test="${true}"><%--checked out
                                            Checked Out (current default)
                                        </jstl:when>
                                        <jstl:when test="${true}"><%--returned late
                                            Returned Late
                                        </jstl:when>
                                        <jstl:otherwise><%--returned on time
                                            Returned
                                        </jstl:otherwise>
                                    </jstl:choose>
                                </td>--%>
                                <td>${assets.damageStatus}</td>
                                <td>${assets.serialNumber}</td>
                                <td style="width: 400px">
                                    <input type="hidden" value="${assets.assetId}" id="assetId"/>


                                    <a class="open-${assets.assetId} btn btn-success" role="button" id="open" style="width: 125px">Open Notes</a>
                                    <a class="close-${assets.assetId} btn btn-danger" role="button" id="close" style="width: 125px">Close Notes</a>

                                    <div class="notes-${assets.assetId}" id="notes">
                                        <jstl:forEach var="entry" items="${assetNotes}">
                                            <jstl:forEach items="${entry.value}" var="item">  
                                                <jstl:if test="${entry.key == assets}">
                                                    <p>${item.noteDate}: ${item.category}: ${item.note}</p>
                                                </jstl:if>
                                            </jstl:forEach>
                                        </jstl:forEach>
                                    </div>


                                    <br/><br/>
                                </td>


                            <script type="text/javascript">

                                $(document).ready(function () {
                                    $(".close-${assets.assetId}").css("display", "none");
                                    $(".open-${assets.assetId}").css("display", "block");
                                    $(".notes-${assets.assetId}").css("display", "none");
                                });

                            </script>


                            <script type="text/javascript">
                                function openNotes() {
                                }
                                $(document).ready(function () {
                                    $(".open-${assets.assetId}").click(function () {
                                        $(".open-${assets.assetId}").css("display", "none");
                                        $(".close-${assets.assetId}").css("display", "block");
                                        $(".notes-${assets.assetId}").css("display", "block");
                                    });

                                });
                            </script>


                            <script type="text/javascript">
                                function closeNotes() {
                                }
                                $(document).ready(function () {
                                    $(".close-${assets.assetId}").click(function () {
                                        $(".close-${assets.assetId}").css("display", "none");
                                        $(".open-${assets.assetId}").css("display", "block");
                                        $(".notes-${assets.assetId}").css("display", "none");
                                    });

                                });

                            </script>
                            <td>
                                <jstl:if test="${returnDates[i.index]==null}">

                                    <div class="panel panel-primary" style="padding:12px">
                                        <form role="form" method="get" action="checkInAsset">
                                            <div class="form-group">
                                                <select name="damageStatus" class="form-control">
                                                    <option value="none">none</option>
                                                    <%--<option value="late">Late</option>--%>
                                                    <option value="Damage: 1">Damage: 1</option>
                                                    <option value="Damage: 2">Damage: 2</option>
                                                    <option value="Damage: 3">Damage: 3</option>
                                                    <option value="Damage: 4">Damage: 4</option>
                                                    <option value="Damage: 5">Damage: 5</option>
                                                    <option value="lost">Lost</option>
                                                    <option value="stolen">Stolen</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <input type="hidden" value="${assets.assetId}" name="assetId"/>
                                                <input type="hidden" value="${event.eventId}" name="eventId"/>
                                                <input type="submit" value="Check In" class="form-control"/>
                                            </div>
                                        </form>
                                    </div>
                                    <%--
                            <a href="checkInAsset?assetId=${assets.assetId}&eventId=${event.eventId}" role="button" class="btn btn-danger glyphicon glyphicon-minus">Check In</a>
                                    --%>
                                </jstl:if>
                            </td>
                            </tr>
                        </jstl:forEach>
                    </table>
                </div>
            </div>
        </div>

        <jsp:include page="include/footer.jsp"/>


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
                            message: 'The Due Date is not valid',
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
