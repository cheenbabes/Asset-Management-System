<%-- 
    Document   : CustomError
    Created on : Oct 29, 2014, 9:43:58 AM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="bootstrapvalidator-0.5.2/dist/css/bootstrapValidator.min.css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>

        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
        
    </head>
    <body>
        <jsp:include page="include/header.jsp"/>
        <div class ="container">
        <div>

           
            <h1 class="text-center">An error has occurred...</h1>
            

            <img class="image-responsive center-block" style="margin: 0 auto" src="img/error_image.jpg"/>

            <h3 class="text-center">${errorMessage}</h3>
        </div>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        
        </div>
         <jsp:include page="include/footer.jsp"/>
        
        
    </body>
</html>
