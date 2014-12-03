<%-- 
    Document   : viewUserNotes
    Created on : Nov 4, 2014, 12:03:26 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Notes</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
            <script src="js/bootstrap.min.js" type="text/javascript"></script>
            <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="include/header.jsp"/>
        <div class="container">

                <div class="col-md-12"> 

                    <table class="table table-hover">

                        <tr>
                            <th>Name</th>
                            <th>User Name</th>
                            <th>Standing</th>
                            <th>Enabled</th>
                        </tr>

                        <tr>
                            <td>${user.name}</td>
                            <td>${user.userName}</td>
                            <td>${user.standing}</td>
                            <td>${user.enabled}</td>
                    </table>
                </div>
                <div class="col-md-12"> 

                    <table class="table table-hover">
                        <tr>
                            <th>Notes</th>
                        </tr>
                        <jstl:forEach var="assetNote" items="${userNotes}"> 
                            <tr>
                                <td>${userNote.noteDate}: ${userNote.note}</td>
                            </tr>
                        </jstl:forEach>
                    </table>
                    <a href="userAddNote?assetId=${user.userId}" class="btn btn-success glyphicon glyphicon-plus">Add Note</a><br/><br/>
                </div>


               
</div>
<jsp:include page="inclide/footer.jsp"/>
</body>
</html>
