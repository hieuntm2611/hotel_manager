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
                <div class="mt-5">
                    <form class="flex items-center space-x-4">
                        <div class="xl:w-80">
                            <input type="text"
                                   class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                                   id="search"
                                   name="search"
                                   value="${search}"
                                   placeholder="Search"/>
                        </div>
                        <div class="xl:w-80">
                            <input type="date"
                                   class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                                   id="search"
                                   name="start"
                                   value="${start}"
                                   placeholder="Search"/>
                        </div>
                        <div class="xl:w-80">
                            <input type="date"
                                   class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                                   id="search"
                                   name="end"
                                   value="${end}"
                                   placeholder="Search"/>
                        </div>
                        <button type="submit">Search</button>
                    </form>
                </div>
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
                                                Price
                                            </th>
                                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                                State
                                            </th>
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
                                                <td id="price-${service.id}" class="text-xs text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                                    ${service.getTotal()}
                                                </td>
                                        <script>
                                            var x = ${service.getTotal()};
                                            x = x.toLocaleString('vi', {style: 'currency', currency: 'VND'});
                                            $("#price-${service.id}").text(x);
                                        </script>
                                        <td class="text-sm text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                                            ${service.state.name}
                                        </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <c:if test="${search==null && start==null && end==null}">
                            <div class="flex justify-end items-center mr-10 mt-10">
                                <div class="flex space-x-2 justify-center">
                                    <c:if test="${page>1}">
                                        <a href="history?page=${page-1>0?page-1:page}" class="inline-block px-6 py-2 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">
                                            Prev
                                        </a>
                                    </c:if>
                                    <p class="text-xl mx-10">${page}/${size}</p>
                                    <c:if test="${page<size}">
                                        <a  href="history?page=${page+1<=size?page+1:page}" class="inline-block px-6 py-2 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">
                                            Next
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../main/footer.jsp" />
    </body>
</html>
