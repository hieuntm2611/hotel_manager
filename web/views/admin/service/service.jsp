<%-- 
    Document   : service
    Created on : Mar 15, 2022, 3:23:58 PM
    Author     : hieu2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="../main/header.jsp" />
    <body>
        <div class="flex">
            <div class="w-64 bg-gray-50" style="width: 290px">
                <jsp:include page="../main/slidebar.jsp" />
            </div>
            <div class="w-full px-5 py-5">
                <div class="flex justify-end">
                    <a href="/admin/service/create" type="button" data-mdb-ripple="true" data-mdb-ripple-color="light" class="inline-block px-6 py-2.5 mb-2 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:shadow-lg focus:shadow-lg focus:outline-none focus:ring-0 active:shadow-lg transition duration-150 ease-in-out" style="background-color: #1877f2;">
                        Add
                    </a>
                </div>
                <div class="flex flex-col">
                    <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
                        <div class="py-2 inline-block min-w-full sm:px-6 lg:px-8">
                            <div class="overflow-hidden">
                                <table class="min-w-full">
                                    <thead class="border-b">
                                        <tr>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                Id
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                Room
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                Name
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                Phone
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                Cmnd
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                Email
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                Gender
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                Start
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                End
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                State
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                Price
                                            </th>
                                            <th scope="col" class="text-base font-medium text-gray-900 px-6 py-4 text-left">
                                                Action
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.services}" var="service">
                                            <tr class="bg-white border-b">
                                                <td class="px-6 py-4 whitespace-nowrap text-xs font-medium text-gray-900">${service.id}</td>
                                                <td class="text-xs text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    ${service.room.name}
                                                </td>
                                                <td class="text-xs text-gray-900 font-medium  px-6 py-4 whitespace-nowrap">
                                                    ${service.customer.name}
                                                </td>
                                                <td class="text-xs text-gray-900 font-medium  px-6 py-4 whitespace-nowrap">
                                                    ${service.customer.phone}
                                                </td>
                                                <td class="text-xs text-gray-900 font-medium  px-6 py-4 whitespace-nowrap">
                                                    ${service.customer.cmnd}
                                                </td>
                                                <td class="text-xs text-gray-900 font-medium  px-6 py-4 whitespace-nowrap">
                                                    ${service.customer.email}
                                                </td>
                                                <td class="text-xs text-gray-900 font-medium  px-6 py-4 whitespace-nowrap">
                                                    ${service.customer.gender}  
                                                </td>
                                                <td class="text-xs text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    ${service.start}
                                                </td>
                                                <td class="text-xs text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    ${service.end}
                                                </td>
                                                <td class="text-xs text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    ${service.state.name}
                                                </td>
                                                <td id="price-${service.id}" class="text-xs text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    ${service.getTotal()}
                                                </td>
                                        <script>
                                            var x = ${service.getTotal()};
                                            x = x.toLocaleString('vi', {style: 'currency', currency: 'VND'});
                                            $("#price-${service.id}").text(x);
                                        </script>
                                        <td class="text-xs text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                            <a href="/admin/service/checkout?id=${service.id}" class="text-red-600 hover:text-blue-700 transition duration-300 ease-in-out mb-4">Checkout/Cancel</a>
                                        </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../main/footer.jsp" />
    </body>
</html>
