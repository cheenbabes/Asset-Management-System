<%-- 
    Document   : assetAddNote
    Created on : Nov 3, 2014, 8:21:32 AM
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
        <title>Add Note To Asset</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>

        <jsp:include page="include/header.jsp"/>
        <div class="container">
            <div class="row">


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
                    </table>
                </div>
                <div class="col-md-12"> 

                    <table class="table table-hover">
                        <tr>
                            <th>Notes</th>
                        </tr>
                        <jstl:forEach var="assetNote" items="${assetNoteList}"> 
                            <tr>
                                <td>${assetNote.note}</td>
                            </tr>
                        </jstl:forEach>

                    </table>
                    <hr/>
                </div>


                ${newAssetNoteSubmissionError}
                <div class="col-md-8">

                    <sf:form class="form-horizontal" action="submitNewAssetNote" method="post" role="form" modelAttribute="assetNote">
                        <div class="form-group">
                            <div class="col-md-3">
                                <sf:label path="note" cssClass="control-label">Note</sf:label>
                                </div>
                                <div class="col-sm-9">
                                <sf:textarea path="note" cssClass="form-control" id="note" name="assetNote" placeholder="Enter Note Here"></sf:textarea>
                                <sf:errors path="note" cssclass="error"></sf:errors><br/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-3">
                                <sf:label path="category" cssClass="control-label">Note Category</sf:label>
                                </div>
                                <div class="col-md-9">
                                <sf:select path="category" cssClass="form-control" name="category">
                                    <sf:option value="none">none</sf:option>
                                    <sf:option value="lost">Lost</sf:option>
                                    <sf:option value="stolen">Stolen</sf:option>
                                    <sf:option value="late">Late</sf:option>
                                    <sf:option value="retired">Retired</sf:option>
                                    <sf:option value="one">Damage: 1</sf:option>
                                    <sf:option value="two">Damage: 2</sf:option>
                                    <sf:option value="three">Damage: 3</sf:option>
                                    <sf:option value="four">Damage: 4</sf:option>
                                    <sf:option value="five">Damage: 5</sf:option>
                                </sf:select>
                                    <sf:errors path="category" cssclass="error"></sf:errors><br/>
                            </div>
                        </div>
                        <sf:hidden path="assetId"/>
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input type="submit" class="btn btn-primary" value="Add Note"/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>


        </div>
        <jsp:include page="include/footer.jsp"/>

    </body>
</html>
