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
            <div class="w-full px-5">
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
                                                Category
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                Room State
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                Action
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.rooms}" var="room">
                                            <tr class="bg-white border-b">
                                                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${room.id}</td>
                                                <td class="text-sm text-gray-900 px-6 py-4 whitespace-nowrap">
                                                    ${room.name}
                                                </td>
                                                <td class="text-sm text-gray-900 px-6 py-4 whitespace-nowrap">
                                                    ${room.category.name}
                                                </td>
                                                <td class="text-sm text-gray-900 px-6 py-4 whitespace-nowrap">
                                                    ${room.roomState.name}
                                                </td>
                                                <td class="text-xs text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    <a href="/admin/room/update?id=${room.id}" class="text-red-600 hover:text-blue-700 transition duration-300 ease-in-out mb-4">Edit</a>
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