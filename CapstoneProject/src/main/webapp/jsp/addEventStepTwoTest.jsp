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
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="../bootstrapvalidator-0.5.2/dist/css/bootstrapValidator.min.css"/>
        <link href="../css/bootstrap.min.css" rel="stylesheet"/>

        <script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="include/header.jsp"/>


            <h2 class="text-center">Add Event</h2>

            <div class="row" style="margin-left: 25px">
                <div class="col-md-12 col-sm-12 col-xs-12" style="border-bottom: 3px solid black; border-radius: 4px; padding-top: 15px; margin-bottom: 30px">

                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <h2 class="text-center">Add Event</h2>

                        <img class="img-responsive" data-src="holder.js/242x200" alt="..." style="float: left; padding-bottom: 25px">
                        <!--add the generated img tag here-->

                        <p>                        
                            <br><b style="margin-left: 20px">Event:</b> <span>Event Name</span>
                            <br>
                        </p>

                        <div class="col-md-12">
                            <table class="table table-hover">

                                <tr>
                                    <th>Event</th>
                                    <th>UserName</th>
                                    <th>Location</th>
                                    <th>Size</th>
                                    <th>Start Date</th>
                                    <th>Due Date</th>
                                    <th>Status</th>
                                </tr>
                                <tr>
                                    <td>${event.eventName}<br/><br/>
                                    </td>
                                    <td>${event.user.userName}<br/><br/>
                                    </td>
                                    <%--<td>${event.location}<br/><br/>
                                    </td>
                                    <td>${event.size}<br/><br/>
                                    </td> --%>
                                    <td>${event.checkOutDate}<br/><br/>
                                    </td>
                                    <td>${event.dueDate}<br/><br/>
                                    </td>
                                    <td>${event.open}<br/><br/>
                                    </td>
                                

                                </tr>
                                

                            </table>
                        </div>

                    </div>

                    <form action="submitAddEventStepTwo" id="addEditEvent" role="form" method="get">
                        <div class="col-md-12">
                            <h1>Assets For This Event</h1>
                            <div id="assetsForEvent-list"></div>
<!--                            <table class="table table-hover">

                                <tr>
                                    <th>Asset Type</th>
                                    <th>Category</th>
                                    <th>Availability</th>
                                    <th>Damage</th>
                                    <th>Serial Number</th>
                                    <th>Actions</th>
                                </tr>
                                <tr>
                                    <%--<jstl:forEach var="assets" items="${assetCheckedOutList}">
                                        <td>${asset.assetType.name}</td>
                                        <td>${asset.assetType.category.categoryName}</td>
                                        <td>${asset.inStock}</td>
                                        <td>${asset.damageStatus}</td>
                                        <td>${asset.serialNumber}</td>
                                        <td>
                                            <a href="viewAssetNotes?assetId=${asset.assetId}" role="button" class="btn btn-primary glyphicon glyphicon-list-alt">View Asset Notes</a><br/><br/>
                                            <a href="checkInAsset?assetId=${asset.assetId}" role="button" class="btn btn-danger glyphicon glyphicon-minus">Remove From Event</a>
                                        </td>
                                    </jstl:forEach>--%>
                                </tr>
                            </table>-->
                        </div>




                        <!--                        <h1>AssetTypes</h1>
                        
                                                <div id="assetType-list"></div>-->
                        <!--                    <h1>Assets</h1>
                                            <div id="asset-list"></div>-->



                        <h2>Available Assets</h2>
                        <div id="checkedIn-list"></div>

                        <h2>Unavailable Assets</h2>
                        <div id="checkedOut-list"></div>

                        <h1>Asset Info</h1>
                        <div>
                            <p id="detail-inStock"></p>
                            <p id="detail-serialNumber"></p>
                            <p id="detail-damageStatus"></p>
                        </div>

                        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
                        <script src="${pageContext.request.contextPath}/js/asset.js"></script>







                    <input type="hidden" value="${event.eventId}" name="eventId"/>
                    <input type="submit" value="Submit Event" class="btn btn-primary" style="float: right; font-size: 24px; margin: 10px 0px 30px 0px"/>
                    </form>

                    


                    <a href="index.jsp" class="btn btn-primary">Submit Event</a>



                </div>
            </div>


            <jsp:include page="include/addEditEventAssets.jsp"/>


            <jsp:include page="include/footer.jsp"/>
        </div>
    </body>
</html>
