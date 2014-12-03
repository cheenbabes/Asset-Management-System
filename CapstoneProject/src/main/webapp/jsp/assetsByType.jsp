<%-- 
    Document   : assetsByType
    Created on : Oct 31, 2014, 2:07:25 PM
    Author     : apprentice
--%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assets By Types</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>


        <jsp:include page="include/header.jsp"/>
        <div class="container">
            <div class="row">



                <div class="col-md-12">

                        <div class="row" style="padding-bottom: 20px">
                            <div class="col-md-3">
                                <a class="btn btn-primary glyphicon glyphicon-plus" href="addAssetType">  Add Asset Type</a>
                            </div>
                            <div class="col-md-6"></div>
                            <div class="col-md-3">
                                <a href="manage_assets" class="btn btn-primary" role="button" style="float:right">Return to All Asset Types</a>
                            </div>

                        </div>

                        <div class="row" style="padding-bottom: 20px">
                            <div class="col-md-2">
                                <a class="btn btn-primary glyphicon glyphicon-plus" href="addAsset">  Add Asset</a>
                            </div>
                        </div>

                </div>

            <div class="col-md-12"> 

                <table class="table table-hover">

                    <tr>
                        <th></th>
                        <th>Category</th>
                        <th>Asset Type</th>
                        <th>In Stock</th>
                        <th>Serial Number</th>
                        <th>Damage Status</th>
                        <th>Actions</th>
                    </tr>

                    <jstl:forEach var="asset" items="${assetList}"> 
                        <tr>
                            <td><img class="image-responsive" src="${asset.assetType.imagePath}" alt="..." style="height: 200px; width: 200px"></td>
                            <td>${asset.assetType.category.categoryName}</td>
                            <td>${asset.assetType.name}</td>
                            <td>${asset.inStock}</td>
                            <td>${asset.serialNumber}</td>
                            <td>${asset.damageStatus}</td>

                            <td><a href="updateAsset?assetId=${asset.assetId}" class="btn btn-warning glyphicon glyphicon-pencil">  Edit</a><br/><br/>
                                <a href="removeAsset?assetId=${asset.assetId}" class="btn btn-danger glyphicon glyphicon-minus">  Retire</a><br/><br/>
                                <a href="assetAddNote?assetId=${asset.assetId}" class="btn btn-success glyphicon glyphicon-plus"> Add Note</a><br/><br/>
                                <a href="listAssetNotes?assetId=${asset.assetId}" class="btn btn-primary glyphicon glyphicon-list-alt">  View Asset Notes</a></td>
                        </tr>
                    </jstl:forEach>
                </table>
            </div>
            </div>
        </div>
        <jsp:include page="include/footer.jsp"/>

    </body>
</html>
