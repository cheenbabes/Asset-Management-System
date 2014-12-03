<%-- 
    Document   : manageMyEvents
    Created on : Oct 29, 2014, 9:55:28 AM
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
        <div class="modal fade" id="manageMyEventsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">View My Events</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="col-md-8">
                                <table class="table table-hover">

                                    <tr>
                                        <th>#</th>
                                        <th></th>
                                        <th>Event Name</th>
                                        <th>Location</th>
                                        <th>User</th>
                                        <th>Size</th>
                                        <th>Date Begin</th>
                                        <th>Date End</th>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td><img class="image-responsive" src="../img/asset/placeholder.jpg" alt="..."></td>
                                        <td>Camping Trip</td>
                                        <td>Yosemite, CA</td>
                                        <td>Eugene</td>
                                        <td>5</td>
                                        <td>10-12-2014</td>
                                        <td>10-15-2014</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td><img class="image-responsive" src="../img/asset/placeholder.jpg" alt="..."></td>
                                        <td>Mountaneering</td>
                                        <td>Fairbanks, AK</td>
                                        <td>Sean</td>
                                        <td>3</td>
                                        <td>10-28-2014</td>
                                        <td>11-3-2014</td>
                                    </tr>

                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        


    </body>
</html>
