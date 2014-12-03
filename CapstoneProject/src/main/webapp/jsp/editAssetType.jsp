<%-- 
    Document   : editAssetType
    Created on : Nov 1, 2014, 3:15:13 PM
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
        <title>Edit Asset Type</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="include/header.jsp"/>
            <div class="container">

            <div class="row">
                <div class="col-md-6">
                    ${badCategoryError}
                    <sf:form cssClass="form-horizontal" role="form" id="addEditAssetType" action="submitAssetTypeUpdate" method="post" modelAttribute="assetType">
                        <div class="form-group">
                            <div class="col-md-3">
                                <sf:label path="name">Name</sf:label>
                                </div>
                                <div class="col-md-9">
                                <sf:input type="text" path="name" name="name" cssClass="form-control"/>
                                <sf:errors path="name" cssclass="error"></sf:errors><br/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-3">
                                <label>Category</label>
                            </div>
                            <div class="col-md-9">
                                <select class="form-control" name="categoryId">
                                    <jstl:forEach var="category" items="${categoryList}">
                                        <option ${category == assetType.category ? 'selected = "selected"' : ''} value="${category.categoryId}">${category.categoryName}</option>
                                    </jstl:forEach>       
                                </select>
                            </div>
                        </div>




                        <div class="form-group">
                            <div class="col-md-3">
                                <sf:label path="imagePath">Image</sf:label>
                                </div>
                                <div class="col-md-9">
                                    ${imageRef}
                                <sf:input type="hidden" path="imagePath" value="${imageRef}"/>
                                <a href="fileUploadForm?typeId=${assetType.assetTypeId}">Upload File </a>
                                <sf:errors path="imagePath" cssclass="error"></sf:errors><br/>
                            </div>
                        </div>

                        <sf:hidden path="assetTypeId"/>

                        <br/>
                        <div class="form-group">
                            <div class="col-md-3">
                                <input type="submit" value="Edit Asset Type" class="btn btn-primary"/>
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
                $('#addEditAssetType').bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        name: {
                            message: 'The name is not valid',
                            validators: {
                                notEmpty: {
                                    message: 'The name is required and cannot be empty'
                                },
                                stringLength: {
                                    min: 2,
                                    max: 30,
                                    message: 'The name must be more than 2 and less than 30 characters long'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z0-9_ ()]+$/,
                                    message: 'The name can only consist of alphabetical, number and underscore'
                                }
                            }
                        },
                        categoryId: {
                            message: 'The category is not valid',
                            validators: {
                                notEmpty: {
                                    message: 'The category is required and cannot be empty'
                                }
                            }
                        },
                        imagePath: {
                            message: 'The image path is not valid',
                            validators: {
                                notEmpty: {
                                    message: 'The image path is required and cannot be empty'
                                },
                            }
                        }


                    }
                });
            });
        </script>
    </body>
</html>
