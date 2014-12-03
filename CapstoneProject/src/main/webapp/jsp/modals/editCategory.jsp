<%-- 
    Document   : editCategoryModal
    Created on : Nov 1, 2014, 5:11:02 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="modal fade" id="editCategory" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">Edit Category</h4>
                    </div>
                    <div class="modal-body">
                        <sf:form action="updateCategory" method="post" class="form-horizontal" role="form" modelAttribute="category">
                            <div class="form-group">
                                <sf:label path="categoryName" cssClass="col-sm-2 control-label">Name</sf:label>
                                    <div class="col-sm-10">
                                    <sf:input path="categoryName" type="text" cssClass="form-control"/>
                                </div>
                            </div>
                            <sf:hidden path="categoryId"/>
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <input type="submit" class="form-control btn btn-primary" value="Edit Category"/>
                                </div>
                            </div>
                        </sf:form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
