<%-- 
    Document   : viewEventInfo
    Created on : Oct 29, 2014, 9:51:01 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="modal fade" id="viewEventInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">Event Info</h4>
                    </div>
                    <div class="modal-body">


                        <div class="container">
                            <div class="col-md-8">
                                <table class="table table-hover">

                                    <tr>
                                        <th>Name</th>
                                        <th>Username</th>
                                        <th>Stading</th>
                                        <th>Events</th>
                                        <th>Start Date</th>
                                        <th>End Date</th>
                                        <th>Status</th>
                                        <!--                    <th>View Event Assets</th>-->
                                    </tr>
                                    <tr>
                                        <td>User X</td>
                                        <td>xxxuserxxx</td>
                                        <td>good</td>
                                        <td>Camping</td>
                                        <td>10/22/2014</td>
                                        <td>10/31/2014</td>
                                        <td>Open</td>
                                        <!--                    <td><a href="#viewEventAssetsModal" class="btn btn-warning" role="button" data-toggle="modal">View Event's Assets</a></td>-->
                                    </tr>
                                </table>
                            </div>



                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn" data-dismiss="modal">Close</button>
                        </div>
                        <button class="hidden" data-toggle="modal" data-target="#viewEventAssetsModal">
                            <jsp:include page="viewEventAssets.jsp"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
