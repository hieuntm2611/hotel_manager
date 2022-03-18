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
                <div class="flex flex-col">
                    <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
                        <div class="py-2 inline-block min-w-full sm:px-6 lg:px-8">
                            <div class="overflow-hidden">
                                <table class="min-w-full">
                                    <thead class="border-b">
                                        <tr>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                Id
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                Room
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                Name
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                Phone
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                Cmnd
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                Email
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                Gender
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                Start
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                End
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                State
                                            </th
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.services}" var="service">
                                            <tr class="bg-white border-b">
                                                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${service.id}</td>
                                                <td class="text-sm text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    ${service.room.name}
                                                </td>
                                                <td class="text-sm text-gray-900 font-medium  px-6 py-4 whitespace-nowrap">
                                                    ${service.customer.name}
                                                </td>
                                                <td class="text-sm text-gray-900 font-medium  px-6 py-4 whitespace-nowrap">
                                                    ${service.customer.phone}
                                                </td>
                                                <td class="text-sm text-gray-900 font-medium  px-6 py-4 whitespace-nowrap">
                                                    ${service.customer.cmnd}
                                                </td>
                                                <td class="text-sm text-gray-900 font-medium  px-6 py-4 whitespace-nowrap">
                                                    ${service.customer.email}
                                                </td>
                                                <td class="text-sm text-gray-900 font-medium  px-6 py-4 whitespace-nowrap">
                                                    ${service.customer.gender}  
                                                </td>
                                                <td class="text-sm text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    ${service.start}
                                                </td>
                                                <td class="text-sm text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    ${service.end}
                                                </td>
                                                <td class="text-sm text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    ${service.state.name}
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
