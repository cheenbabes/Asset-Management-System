<%-- 
    Document   : manageEventAssets
    Created on : Nov 4, 2014, 1:15:24 PM
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
        <title>Event: Manage Assets</title>



    </head>
    <body>
        <div class="row"><%--Error Message ROW--%>
            <div class="col-md-12" style="color: #ee0000">
                <p>${badEventError}</p>
                <p>${badAssetTypeError}</p>
                <p>${unavailableAssetError}</p>
                <p>${badAssetError}</p>
            </div>
        </div>


        <div class="row"><%--CHECKED OUT ASSETS ROW--%>
            <div class="col-md-12">
                <h3 class="text-center">Assets Checked Out</h3>
                <table class="table table-hover">

                    <tr>
                        <th>Asset Type</th>
                        <th>Category</th>
                            <%--<th>Availability</th>--%>
                        <th>Damage</th>
                        <th>Serial Number</th>
                        <th style="width: 400px">Notes</th>
                        <th>Actions</th>
                    </tr>

                    <jstl:forEach var="assets" items="${event.assets}">

                        <tr>
                            <td>${assets.assetType.name}</td>
                            <td>${assets.assetType.category.categoryName}</td>
                            <%--<td>${assets.inStock}</td>--%>
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
                            <td>

                                <a href="removeAssetFromEvent?assetId=${assets.assetId}&eventId=${event.eventId}" role="button" class="btn btn-danger glyphicon glyphicon-minus">Remove From Event</a>
                            </td>
                        </tr>

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


                    </jstl:forEach>

                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>

                        </td>
                    </tr>
                </table>
            </div>
        </div>



        <div class="row"><%--ASSET SELECTION ROW--%>
            <div class="col-md-12">

                <h3 class="text-center">Select Assets</h3>

                <div class="row">
                    <div class="col-md-3">
                        <form role="form" method="get" action="manage_assets">
                            <div class="form-group">
                                <label>Search By Category:</label>
                                <select name="selectCategory" class="form-control">
                                    <option value="all">All</option>
                                    <jstl:forEach var="category" items="${categoryList}">
                                        <option value="${category.categoryName}">${category.categoryName}</option>
                                    </jstl:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <input type="submit" value="Search" class="form-control"/>
                            </div>
                        </form>
                    </div>

                    <div class="col-md-9">
                        <table class="table table-hover">
                            <tr>
                                <th></th>
                                <th>Asset Type</th>
                                <th>Category</th>
                                <th>Actions</th>
                            </tr>

                            <jstl:forEach var="types" items="${assetTypeList}">
                                <tr>
                                    <td><img class="image-responsive" src="${types.imagePath}" alt="..."></td>
                                    <td>${types.name}</td>
                                    <td>${types.category.categoryName}</td>
                                    <td>
                                        <a href="addEventAsset?typeId=${types.assetTypeId}&eventId=${event.eventId}" class="btn btn-primary">Add to Event</a>
                                    </td>

                                </tr>
                            </jstl:forEach>

                        </table>
                    </div>
                </div>
            </div>
        </div>

        <%--
                 View Asset Notes Modal 
                <div class="modal fade" id="viewAssetNotes" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                                    <th>Category</th>
                                    <th>Asset Type</th>
                                    <th>In Stock</th>
                                    <th>Serial Number</th>
                                    <th>Damage Status</th>

                                </tr>

                                <tr>
                                    <td><img class="image-responsive" src="${asset.assetType.imagePath}" alt="..."></td>
                                    <td>${asset.assetType.category.categoryName}</td>
                                    <td>${asset.assetType.name}</td>
                                    <td>${asset.inStock}</td>
                                    <td>${asset.serialNumber}</td>
                                    <td>${asset.damageStatus}</td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-12"> 

                            <table class="table table-hover">
                                <tr>
                                    <th>Notes</th>
                                </tr>
                                <jstl:forEach var="notes" items="${notes}"> 
                                    <tr>
                                        <td>${notes.noteDate}: ${notes.category}: ${notes.note}</td>
                                        <td><div id="getNotesForAsset-list"></div>
                                            <p id="detail-category"></p>
                                            <p id="detail-noteDate"></p>
                                            <p id="detail-note"></p>
                                        </td>

                                    </tr>
                                </jstl:forEach>
                            </table>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>--%>


    </body>
</html>
