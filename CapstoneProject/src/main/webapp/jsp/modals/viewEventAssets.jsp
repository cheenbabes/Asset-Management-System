<%-- 
    Document   : viewEventAssets
    Created on : Oct 29, 2014, 9:51:25 AM
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
        <div class="modal fade" id="viewEventAssetsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">Event's Assets</h4>
                    </div>
                    <div class="modal-body">
                       
                            <div class="col-md-12">
                                <table class="table table-hover">

                                    <tr>
                                        <th></th>
                                        <th>Asset</th>
                                        <th>Asset Type</th>
                                        <th>Availability</th>
                                        <th>Damage</th>
                                        <th>Serial Number</th>
                                        <th>Category</th>
                                        <th>Notes</th>
                      
                                    </tr>

                                    <tr>
                                        <td><img class="image-responsive" src="../img/asset/placeholder.jpg" alt="..."></td>
                                        <td>Tent</td>
                                        <td>2-Person</td>
                                        <td>In Store</td>
                                        <td>Slight</td>
                                        <td>xyz-123</td>
                                        <td>Shelter</td>
                                        <td>Small rip in door- damage from use- non intentional</td>
                                        
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>Mud tracked all inside. Now infested with BEES?!</td>
                                    </tr>
                                </table>
                            </div>
                        <div class="modal-footer">
                            <button type="button" class="btn" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
