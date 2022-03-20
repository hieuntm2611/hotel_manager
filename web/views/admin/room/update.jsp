<%-- 
    Document   : update
    Created on : Mar 18, 2022, 3:49:56 PM
    Author     : hieu2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>%>
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
                <div class="flex justify-center min-h-screen items-center">
                    <div class="block p-6 rounded-lg shadow-lg bg-white max-w-md">
                        <form method="post">
                            <input type="hidden" name="id" value="${requestScope.state.id}" id="id"/>
                            <div class="form-group mb-6">
                                <input type="text" id="name" name="name" value="${requestScope.room.name}"  placeholder="Name"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="flex justify-center mb-6">
                                <div class="mb-3 w-full">
                                    <select required name="roomState" class="form-select appearance-none block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding bg-no-repeat border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="Default select example">
                                        <c:forEach items="${requestScope.roomStates}" var="roomState">
                                            <option value="${roomState.id}" ${room.roomState.id==roomState.id?"selected":""}>${roomState.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="flex justify-center mb-6">
                                <div class="mb-3 w-full">
                                    <select required name="category" class="form-select appearance-none block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding bg-no-repeat border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="Default select example">
                                        <c:forEach items="${requestScope.categorys}" var="category">
                                            <option value="${category.id}" ${room.categoryId==category.id?"selected":""}>${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <button type="submit" class="w-full px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">Send</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../main/footer.jsp" />
    </body>
</html>