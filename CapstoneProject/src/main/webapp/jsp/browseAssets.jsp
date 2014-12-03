<%-- 
    Document   : browseAssets
    Created on : Oct 29, 2014, 10:36:52 AM
    Author     : apprentice
--%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Browse Assets</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>

        <jsp:include page="include/header.jsp"/>
        <div class="container">

            <div class="row">
                <div class="col-md-3">
                    <form role="form" action="assets" method="get">
                        <div class="form-group">
                            <select name="selectCategory" class="form-control">
                                <option value="">All</option>
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
            </div>


            <div class="row">
                <jstl:forEach var="types" items="${assetTypeList}">

                    <div class="col-md-3 col-sm-6 col-xs-12" >
                        <div class="thumbnail">
                            <img class="img-responsive" src="${types.imagePath}" alt="...">
                            <!--add the generated img tag here-->
                            <div class="caption">
                                <p>${types.name}</p>
                                <p>${types.category.categoryName}</p>
                            </div>
                        </div>
                    </div>

                </jstl:forEach>
            </div>

            <a href="home" class="btn btn-primary glyphicon glyphicon-chevron-home">Home</a><br/><br/>


        </div>
        <jsp:include page="include/footer.jsp"/>

    </body>
</html>
