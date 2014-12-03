<%-- 
    Document   : editAsset
    Created on : Oct 29, 2014, 10:40:13 AM
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
        <title>Edit Asset</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="bootstrapvalidator-0.5.2/dist/css/bootstrapValidator.min.css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>

        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="include/header.jsp"/>
            <div class="container">

            ${updateError}
            <div class="row">
                <div class="col-md-6">
                    <sf:form cssClass="form-horizontal" role="form" action="submitAssetUpdate" method="post" id="addEditAsset" modelAttribute="asset">



                        <%-- assetType --%>




                        <%-- inStock --%>
                        <sf:hidden path="assetType.assetTypeId"/>

                        <%-- damageStatus --%>
                        <div class="form-group">
                            <div class="col-md-3">
                                <sf:label path="damageStatus" cssClass="control-label">Status/Damage</sf:label>
                                </div>
                                <div class="col-md-9">
                                <sf:select path="damageStatus" cssClass="form-control">  
                                    <sf:option value="none">none</sf:option>
                                    <sf:option value="lost">Lost</sf:option>
                                    <sf:option value="stolen">Stolen</sf:option>
                                    <sf:option value="late">Late</sf:option>
                                    <%--<sf:option value="retired">Retired</sf:option>--%>
                                    <sf:option value="Damage: 1">Damage: 1</sf:option>
                                    <sf:option value="Damage: 2">Damage: 2</sf:option>
                                    <sf:option value="Damage: 3">Damage: 3</sf:option>
                                    <sf:option value="Damage: 4">Damage: 4</sf:option>
                                    <sf:option value="Damage: 5">Damage: 5</sf:option>
                                </sf:select>
                                    <sf:errors path="damageStatus" cssclass="error"></sf:errors><br/>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-3">
                                <sf:label path="inStock" cssClass="control-label">In Stock</sf:label>
                                </div>
                                <div class="col-md-9">
                                <sf:select path="inStock" cssClass="form-control" name="status">
                                    <sf:option value="true">In Stock</sf:option>
                                    <sf:option value="false">Out of Stock</sf:option>
                                </sf:select>
                                    <sf:errors path="inStock" cssclass="error"></sf:errors><br/>


                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-3">
                                <sf:label path="serialNumber" cssClass="control-label">Serial #</sf:label>
                                </div>
                                <div class="col-md-9">
                                <sf:input path="serialNumber" type="text" cssClass="form-control"/>
                                <sf:errors path="serialNumber" cssclass="error"></sf:errors><br/>
                            </div>
                        </div>

                        <sf:hidden path="assetId"/>
                        <input type="hidden" value="asset.assetTypeId"/>

                        <br/>
                        <div class="form-group">
                            <div class="col-md-3">
                                <input type="submit" value="Edit Asset" class="btn btn-primary"/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
            </div>

            <jsp:include page="include/footer.jsp"/>
        </div>

        <script>
            $(document).ready(function () {
                $('#addEditAsset').bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        typeId: {
                        },
                        damageStatus: {
                        },
                        inStock: {
                        },
                        serialNumber: {
                            message: 'The serial number is not valid',
                            validators: {
                                stringLength: {
                                    min: 0,
                                    max: 20,
                                    message: 'The serial number must be less than 20 characters long'
                                },
                                regexp: {
                                    regexp: /[a-zA-Z0-9]+/,
                                    message: 'The serial number can only consist of numbers'
                                }
                            }
                        }


                    }
                });
            });
        </script>
    </body>
</html>
