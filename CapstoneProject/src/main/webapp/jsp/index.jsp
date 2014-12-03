
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="bootstrapvalidator-0.5.2/dist/css/bootstrapValidator.min.css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/mycss.css" rel="stylesheet"/>

        <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrapvalidator-0.5.2/dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">

            <jsp:include page="include/header.jsp"/>
            <div class="container">

                <div class="row" style="padding: 0px; margin: 0px; margin-bottom: 30px">
                    <a class="btn btn-primary glyphicon glyphicon-plus" href="addEvent">Add Event</a>
                    <p style="color: #ee0000">${badEventError}</p>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <jstl:forEach var="event" items="${eventList}" varStatus="i">
                        <jstl:if test="${i.index%4==0 || i.index==0}"><div class="row"></jstl:if><%--begin new row before first item of every four--%>

                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="thumbnail">
<!--                                    <img class="img-responsive" data-src="holder.js/242x200" alt="...">-->
                                    <!--add the generated img tag here-->
                                    <div class="caption">
                                        <h4><span>${event.eventName}</span></h4>

                                        <p>                        
                                            <br><b>User: </b> <span>${event.user.name}</span><br/>
        <!--                                   <b>${event.open}</b>-->
                                            <b>Start Date: </b> ${event.checkOutDate}<br>
                                            <b>Due Date: </b>${event.dueDate}<br>
                                            <br>
                                        </p>
                                        <a href="viewEventInfo?eventId=${event.eventId}" class="btn btn-default">Additional Info</a>
                                        <a href="editEvent?eventId=${event.eventId}" class="btn btn-primary" role="button">Edit</a><br/><br/>
                                        <jstl:if var="open" test="${event.open == false}">
                                            <a class="open-${event.eventId} btn btn-success" href="openEvent?eventId=${event.eventId}" role="button" id="open">Open Event</a><br/><br/>
                                        </jstl:if>
                                        <jstl:if var="open" test="${event.open == true}">
                                            <a class="close-${event.eventId} btn btn-danger" href="closeEvent?eventId=${event.eventId}" role="button" id="close">Close Event</a><br/><br/>
                                        </jstl:if>
                                        <a href="eventAddNote?eventId=${event.eventId}" class="btn btn-warning glyphicon glyphicon-plus"> Add Note</a>
                                    </div>
                                </div>
                            </div>

                            <script type="text/javascript">
                                function openEvent() {



                                }
                                $(document).ready(function () {
                                    $(".open-${event.eventId}").click(function () {
                                        $(".open-${event.eventId}").css("display", "none");
                                        $(".close-${event.eventId}").css("display", "block");
                                    });

                                });
                            </script>
                            <script type="text/javascript">
                                function closeEvent() {



                                }
                                $(document).ready(function () {
                                    $(".close-${event.eventId}").click(function () {
                                        $(".close-${event.eventId}").css("display", "none");
                                        $(".open-${event.eventId}").css("display", "block");
                                    });

                                });

                            </script>


                            <jstl:if test="${(i.index + 1)%4==0 && i.index!=0}"></div></jstl:if><%--end row after every fourth--%>
                    </jstl:forEach>
                </div>
            </div>





            <jsp:include page="modals/manageMyEvents.jsp"/>
            <jsp:include page="include/footer.jsp"/>

        </div>




        <!--        <script type="text/javascript">
                    function openEvent() {
        
        
        
                    }
                    $(document).ready(function () {
                        $("#open").click(function () {
                            //$("this").attr("disabled", "disabled");
                            $(this).css("display", "none");
                            $("#close").css("display", "block");
                        });
        
                    });
                </script>
                <script type="text/javascript">
                    function closeEvent() {
        
        
        
                    }
                    $(document).ready(function () {
                        $("#close").click(function () {
                            //$("this").attr("disabled", "disabled");
                            $(this).css("display", "none");
                            $("#open").css("display", "block");
                        });
        
                    });
                </script>-->


    </body>
</html>
