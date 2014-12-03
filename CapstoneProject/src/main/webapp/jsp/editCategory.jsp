<%-- 
    Document   : editCategory
    Created on : Nov 1, 2014, 5:38:06 PM
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
        <title>Edit Category</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>

        <jsp:include page="include/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <sf:form action="submitCategoryUpdate" method="post" id="manageCategories" class="form-horizontal" role="form" modelAttribute="category">
                        <div class="form-group">
                            <div class="col-md-3">
                                <sf:label path="categoryName">Name</sf:label>
                                </div>
                                <div class="col-md-9">
                                <sf:input type="text" path="categoryName" cssClass="form-control"/>
                                <sf:errors path="categoryName" cssclass="error"></sf:errors><br/>
                            </div>
                        </div>
                        <sf:hidden path="categoryId"/>
                        <br/>
                        <div class="form-group">
                            <div class="col-md-3">
                                <input type="submit" value="Edit Category" class="btn btn-primary"/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
        <jsp:include page="include/footer.jsp"/>
        
        <script>
            $(document).ready(function () {
                $('#manageCategories').bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        categoryName: {
                            message: 'The category name is not valid',
                            validators: {
                                notEmpty: {
                                    message: 'The category is required and cannot be empty'
                                },
                                stringLength: {
                                    min: 2,
                                    max: 30,
                                    message: 'The category must be more than 2 and less than 30 characters long'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z ]+$/,
                                    message: 'The serial number can only consist of letters and spaces'
                                }
                            }
                        }


                    }
                });
            });
        </script>

    </body>
</html>
