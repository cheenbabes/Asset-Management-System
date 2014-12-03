$(document).ready(function () {
    drawChartA();
    drawChartB();
    drawChartC();
    drawChartD();
    drawChartE();
    drawChartF();
    drawChartG();
    drawChartH();
    drawChartI();
    drawChartJ();


    function drawChartA() {
        $.get('http://localhost:8080/CapstoneProject/reports/chartA').success(function (data) {
            var dataTable = new google.visualization.DataTable(data);
            var options = {
                'title': 'Assets Damaged over the Last Six Months',
                vAxis: {title: 'Count of Damaged Items'},
                hAxis: {title: 'Asset Type'},
                'width': 500,
                'height': 400

            };
            var chartA = new google.visualization.ColumnChart(document.getElementById('chartDivA'));
            chartA.draw(dataTable, options);
        });
    }
    ;

    function drawChartB() {
        $.get('http://localhost:8080/CapstoneProject/reports/chartB').success(function (data) {
            var dataTable = new google.visualization.DataTable(data);
            var options = {
                'title': 'Assets Damaged by User',
                vAxis: {title: 'Name of User'},
                hAxis: {title: 'Count of Assets Damaged'},
                'width': 500,
                'height': 400};
            var chartB = new google.visualization.BarChart(document.getElementById('chartDivB'));
            chartB.draw(dataTable, options);
        });
    }
    ;

    function drawChartC() {
        $.get('http://localhost:8080/CapstoneProject/reports/chartC').success(function (data) {
            var dataTable = new google.visualization.DataTable(data);
            var options = {
                'title': 'Seasonal Utilization Rate',
                vAxis: {title: 'Count of Checkouts'},
                hAxis: {title: 'Month'},
                'width': 500,
                'height': 400};
            var chartC = new google.visualization.LineChart(document.getElementById('chartDivC'));
            chartC.draw(dataTable, options);
        });
    }
    ;

    function drawChartD() {
        $.get('http://localhost:8080/CapstoneProject/reports/chartD').success(function (data) {
            var dataTable = new google.visualization.DataTable(data);
            var options = {
                'title': 'Asset Utilization',
                vAxis: {title: 'Category'},
                hAxis: {title: 'Days checked out'},
                'width': 500,
                'height': 400};
            var chartD = new google.visualization.BarChart(document.getElementById('chartDivD'));
            chartD.draw(dataTable, options);
        });
    }
    ;

    function drawChartE() {
        $.get('http://localhost:8080/CapstoneProject/reports/chartE').success(function (data) {
            var dataTable = new google.visualization.DataTable(data);
            var options = {
                'title': 'Annual Number of Customer Events',
                vAxis: {title: 'Name of User'},
                hAxis: {title: 'Count of Assets Damaged'},
                'width': 500,
                'height': 400};
            var chartE = new google.visualization.BarChart(document.getElementById('chartDivE'));
            chartE.draw(dataTable, options);
        });
    }
    ;

    function drawChartF() {
        $.get('http://localhost:8080/CapstoneProject/reports/chartF').success(function (data) {
            var dataTable = new google.visualization.DataTable(data);
            var options = {
                'title': 'Average Items Per Event',
                vAxis: {title: 'Name of Category'},
                hAxis: {title: 'Average Checkout Per Event'},
                'width': 500,
                'height': 400};
            var chartF = new google.visualization.ColumnChart(document.getElementById('chartDivF'));
            chartF.draw(dataTable, options);
        });
    }
    ;

    function drawChartG() {
        $.get('http://localhost:8080/CapstoneProject/reports/chartG').success(function (data) {
            var dataTable = new google.visualization.DataTable(data);
            var options = {
                'title': 'Loss_Theft Per Person',
                vAxis: {title: 'Name of User'},
                hAxis: {title: 'Count of Assets Damaged'},
                'width': 500,
                'height': 400};
            var chartG = new google.visualization.PieChart(document.getElementById('chartDivG'));
            chartG.draw(dataTable, options);
        });
    }
    ;

    function drawChartH() {
        $.get('http://localhost:8080/CapstoneProject/reports/chartH').success(function (data) {
            var dataTable = new google.visualization.DataTable(data);
            var options = {
                'title': 'Loss_Theft Per Category',
                vAxis: {title: 'Asset Category'},
                hAxis: {title: 'Count of Assets Lost_Damaged'},
                'width': 500,
                'height': 400};
            var chartH = new google.visualization.PieChart(document.getElementById('chartDivH'));
            chartH.draw(dataTable, options);
        });
    }
    ;

    function drawChartI() {
        $.get('http://localhost:8080/CapstoneProject/reports/chartI').success(function (data) {
            var dataTable = new google.visualization.DataTable(data);
            var options = {
                'title': 'Late Items By User',
                vAxis: {title: 'Name of User'},
                hAxis: {title: 'Count of Late Items'},
                'width': 500,
                'height': 400};
            var chartI = new google.visualization.PieChart(document.getElementById('chartDivI'));
            chartI.draw(dataTable, options);
        });
    }
    ;

    function drawChartJ() {
        $.get('http://localhost:8080/CapstoneProject/reports/chartJ').success(function (data) {
            var dataTable = new google.visualization.DataTable(data);
            var options = {
                'title': 'Rate of Lateness',
                vAxis: {title: 'Event Name'},
                hAxis: {title: 'Days Late'},
                'width': 500,
                'height': 400};
            var chartJ = new google.visualization.BarChart(document.getElementById('chartDivJ'));
            chartJ.draw(dataTable, options);
        });
    }
    ;

});






