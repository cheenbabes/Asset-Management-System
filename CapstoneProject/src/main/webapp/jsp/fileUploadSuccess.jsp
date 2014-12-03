<%-- 
    Document   : fileUploadSuccess
    Created on : Nov 10, 2014, 11:07:31 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Success</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Success!</h1>
            <p>The file <b>${fileName}</b> was successfully uploaded.</p>
            <p>Click <a href="fileUploadForm">here</a> to upload another file.</p>
            <p><img src="${imageRef}"/></p>
        </div>
    </body>
</html>