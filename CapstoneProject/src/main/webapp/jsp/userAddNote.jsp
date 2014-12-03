<%-- 
    Document   : userAddNote
    Created on : Nov 4, 2014, 11:49:52 AM
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
        <title>Add Note To User</title>
        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>

        <jsp:include page="include/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class="container">
                    <a href="viewUserInfo?userId=${user.userId}" class="btn btn-default">< Back</a><br>
                </div>
                
                <div class="col-md-12"> 
                    <br>
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
                            <td>${user.goodStanding}</td>
                            <td>${user.enabled}</td>
                    </table>
                </div>
                <div class="col-md-12"> 

                    <table class="table table-hover">
                        <tr>
                            <th>Notes</th>
                        </tr>
                        <jstl:forEach var="userNote" items="${userNoteList}"> 
                            <tr>
                                <td>${userNote.noteDate}: ${userNote.note}</td>
                            </tr>
                        </jstl:forEach>

                    </table>
                    <hr/>
                </div>


                ${newAssetNoteSubmissionError}
                <div class="col-md-8">

                    <sf:form class="form-horizontal" action="submitNewUserNote" method="post" role="form" modelAttribute="userNote">
                        <div class="form-group">
                            <div class="col-md-3">
                                <sf:label path="note" cssClass="control-label">Note</sf:label>
                                </div>
                                <div class="col-sm-9">
                                <sf:textarea path="note" cssClass="form-control" id="note" name="assetNote" placeholder="Enter Note Here"></sf:textarea>
                                </div>
                            </div>

                        <sf:hidden path="userId"/>
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input type="submit" class="btn btn-primary" value="Add Note"/>
                            </div>
                        </div>
                    </sf:form>
                    <a href="viewUserInfo?userId=${user.userId}" class="btn btn-default">< Back</a>
                </div>
            </div>


        </div>
        <jsp:include page="include/footer.jsp"/>

    </body>
</html>

