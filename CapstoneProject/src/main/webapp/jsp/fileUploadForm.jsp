<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Form</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>File Upload Form</h1>
            <h2>${errorMsg}</h2>
            <p>Please upload an image:</p>
            <form role="form" method="POST" action="uploadFile" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name">Name It:</label>
                    <input type="text" id="name" name="name"/>
                </div>
                <div class="form-group">
                    <label for="uploadFile">Upload File:</label> 
                    <input type="file" id="uploadFile" name="uploadFile"/>
                </div>
                <input type="hidden" value="${typeId}" name="typeId"/>
                <input type="submit" value="Upload File"/>
            </form>
        </div>

    </body>
</html>
