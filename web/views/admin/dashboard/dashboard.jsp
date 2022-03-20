<%-- 
    Document   : dashboard
    Created on : Mar 1, 2022, 2:54:10 PM
    Author     : hieu2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
    </head>
    <jsp:include page="../main/header.jsp" />
    <body>
        <div class="flex">
            <div class="w-64 bg-gray-50" style="width: 308px">
                <jsp:include page="../main/slidebar.jsp" />
            </div>
            <div class="w-full px-5">
                <div class="mt-10">
                    <div id="chart"></div>
                </div>
                <div class="flex justify-center mt-10">
                    <div class="flex space-x-2 justify-center">
                        <a href="/admin/service/create" class="inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">Add service</a>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../main/footer.jsp" />
        <script>
            const dataService = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
            const dataServiceOrder = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
            <c:forEach items="${dataService}" var="item">
            dataService[${item.key}] = ${item.value}
            </c:forEach>
            <c:forEach items="${dataServiceOrder}" var="item">
            dataServiceOrder[${item.key}] = ${item.value}
            </c:forEach>
            var options = {
                series: [{
                        name: 'Doanh thu',
                        type: 'column',
                        data: dataService
                    }, {
                        name: 'Đặt trước',
                        type: 'line',
                        data: dataServiceOrder
                    }],
                chart: {
                    height: 850,
                    type: 'line',
                },
                stroke: {
                    width: [0, 4]
                },
                title: {
                    text: 'Service'
                },
                dataLabels: {
                    enabled: true,
                    enabledOnSeries: [1]
                },
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'May', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                xaxis: {
                    type: 'string',
                },
                yaxis: [{
                        title: {
                            text: 'Doanh thu',
                        },

                    }, {
                        opposite: true,
                        title: {
                            text: 'Đặt trước'
                        }
                    }]
            };

            var chart = new ApexCharts(document.querySelector("#chart"), options);
            chart.render();

        </script>
    </body>
</html>
