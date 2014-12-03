<%-- 
    Document   : viewEventInfo
    Created on : Nov 5, 2014, 2:47:04 PM
    Author     : apprentice
--%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event Info</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="bootstrapvalidator-0.5.2/dist/css/bootstrapValidator.min.css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>

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
                    <a href="eventAddNote?eventId=${event.eventId}" class="btn btn-success glyphicon glyphicon-plus"> Add Note</a><br/><br/>
                    <a href="editEvent?eventId=${event.eventId}" class="btn btn-primary glyphicon glyphicon-plus"> Edit Event</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">

                        <tr>
                            <th>Event Name</th>
                            <th>Username</th>
                            <th>User Good Standing</th>
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
                    <hr/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">

                        <tr>
                            <th>Asset Type</th>
                            <th>Category</th>
                            <th>Availability</th>
                            <th>Damage</th>
                            <th>Serial Number</th>
                            <th>Notes</th>
                        </tr>

                        <jstl:forEach var="asset" items="${event.assets}">
                            <tr>
                                <td>${asset.assetType.name}</td>
                                <td>${asset.assetType.category.categoryName}</td>
                                <td>${asset.inStock}</td>
                                <td>${asset.damageStatus}</td>
                                <td>${asset.serialNumber}</td>
                                <td style="width: 400px">
                                    <input type="hidden" value="${assets.assetId}" id="assetId"/>


                                    <a class="open-${asset.assetId} btn btn-success" role="button" id="open" style="width: 125px">Open Notes</a>
                                    <a class="close-${asset.assetId} btn btn-danger" role="button" id="close" style="width: 125px">Close Notes</a>

                                    <div class="notes-${asset.assetId}" id="notes">
                                        <jstl:forEach var="entry" items="${assetNotes}">
                                            <jstl:forEach items="${entry.value}" var="item">  
                                                <jstl:if test="${entry.key == asset}">
                                                    <p>${item.noteDate}: ${item.category}: ${item.note}</p>
                                                </jstl:if>
                                            </jstl:forEach>
                                        </jstl:forEach>
                                    </div>


                                    <br/><br/>
                                </td>
                                <td>
                                </td>
                            </tr>

                            <script type="text/javascript">

                                $(document).ready(function () {
                                    $(".close-${asset.assetId}").css("display", "none");
                                    $(".open-${asset.assetId}").css("display", "block");
                                    $(".notes-${asset.assetId}").css("display", "none");
                                });

                            </script>


                            <script type="text/javascript">
                                function openNotes() {
                                }
                                $(document).ready(function () {
                                    $(".open-${asset.assetId}").click(function () {
                                        $(".open-${asset.assetId}").css("display", "none");
                                        $(".close-${asset.assetId}").css("display", "block");
                                        $(".notes-${asset.assetId}").css("display", "block");
                                    });

                                });
                            </script>


                            <script type="text/javascript">
                                function closeNotes() {
                                }
                                $(document).ready(function () {
                                    $(".close-${asset.assetId}").click(function () {
                                        $(".close-${asset.assetId}").css("display", "none");
                                        $(".open-${asset.assetId}").css("display", "block");
                                        $(".notes-${asset.assetId}").css("display", "none");
                                    });

                                });

                            </script>


                        </jstl:forEach>

                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="include/footer.jsp"/>
    </body>
</html>
