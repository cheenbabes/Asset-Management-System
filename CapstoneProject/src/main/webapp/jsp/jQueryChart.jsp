<%-- 
    Document   : jQueryChart
    Created on : Oct 23, 2014, 2:50:32 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <title>QuarterMaster Reports Charts</title>
        <script></script>
    </head>
    <body>
        <jsp:include page="include/header.jsp"/>
        <div class="container">





            <h1 class="text-center">Reports</h1>
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                Damage Reports
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <div class="row">
                                <div class="text-center"><h3>Damage Reports</h3></div>
                                <div class="col-md-6"><div id="chartDivA"></div></div>
                                <div class="col-md-6"><div id="chartDivB"></div></div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                Asset Usage
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="text-center"><h3>Asset Usage</h3></div>
                                <div class="col-md-6"><div id="chartDivC"></div></div>
                                <div class="col-md-6"><div id="chartDivD"></div></div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                Customer Usage
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="text-center"><h3>Customer Usage</h3></div>
                                <div class="col-md-6"><div id="chartDivE"></div></div>
                                <div class="col-md-6"><div id="chartDivF"></div></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
                                Loss And Theft
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="row">
                            <div class="text-center"><h3>Loss & Theft</h3></div>
                            <div class="col-md-6"><div id="chartDivG"></div></div>
                            <div class="col-md-6"><div id="chartDivH"></div></div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
                                Late Returns
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="row">
                            <div class="text-center"><h3>Late Returns</h3></div>
                            <div class="col-md-6"><div id="chartDivI"></div></div>
                            <div class="col-md-6"><div id="chartDivJ"></div></div>
                        </div>
                    </div>
                </div>
            </div>







<!--            <div class="text-center"><h1>QuarterMaster Reports Charts</h1></div>
            <div class="text-center"><a href="/CapstoneProject" class="btn btn-primary">Home</a></div>
            <hr/>
            <div class="row">
                <div class="text-center"><h3>Damage Reports</h3></div>
                <div class="col-md-6"><div id="chartDivA"></div></div>
                <div class="col-md-6"><div id="chartDivB"></div></div>

            </div>
            <hr/>
            <div class="row">
                <div class="text-center"><h3>Asset Usage</h3></div>
                <div class="col-md-6"><div id="chartDivC"></div></div>
                <div class="col-md-6"><div id="chartDivD"></div></div>

            </div>
            <hr/>
            <div class="row">
                <div class="text-center"><h3>Customer Usage</h3></div>
                <div class="col-md-6"><div id="chartDivE"></div></div>
                <div class="col-md-6"><div id="chartDivF"></div></div>
            </div>
            <hr/>
            <div class="row">
                <div class="text-center"><h3>Loss & Theft</h3></div>
                <div class="col-md-6"><div id="chartDivG"></div></div>
                <div class="col-md-6"><div id="chartDivH"></div></div>
            </div>
            <hr/>
            <div class="row">
                <div class="text-center"><h3>Late Returns</h3></div>
                <div class="col-md-6"><div id="chartDivI"></div></div>
                <div class="col-md-6"><div id="chartDivJ"></div></div>
            </div>
            <hr/>-->
            <!-- Bootstrap core Javascript
            ========================================= -->
            <!-- Placed at the end of the document so the pages load faster -->
            <script type="text/javascript" src="http://www.google.com/jsapi"></script>
            <script> google.load('visualization', '1.0', {'packages': ['corechart']});</script>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/jQueryChart.js"></script>
        </div>
        <jsp:include page="include/footer.jsp"/>
    </body>
</html>
