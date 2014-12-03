<%-- 
    Document   : viewAssetNotes
    Created on : Nov 3, 2014, 11:55:10 AM
    Author     : apprentice
--%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asset Notes</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="include/header.jsp"/>

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
                    <jstl:forEach var="assetNote" items="${assetNotes}"> 
                        <tr>
                            <td>${assetNote.noteDate}: ${assetNote.category}: ${assetNote.note}</td>
                        </tr>
                    </jstl:forEach>
                </table>
                <a href="assetAddNote?assetId=${asset.assetId}" class="btn btn-success glyphicon glyphicon-plus">Add Note</a><br/><br/>
            </div>


            <jsp:include page="include/footer.jsp"/>
        </div>
    </body>
</html>
