<%-- 
    Document   : create
    Created on : Mar 16, 2022, 3:13:26 PM
    Author     : hieu2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <div class="block p-6 rounded-lg shadow-lg bg-white max-w-x">
                        <form action="/admin/service/create" method="post">
                            <div class="form-group mb-6">
                                <input type="text" id="name" name="name" placeholder="Name"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="form-group mb-6">
                                <input type="text" id="phone" name="phone" placeholder="Phone number"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="form-group mb-6">
                                <input type="text" id="email" name="email" placeholder="Email"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="flex justify-start mb-6">
                                <div class="flex items-center space-x-4">
                                    <div class="form-check">
                                        <input class="form-check-input appearance-none rounded-full h-4 w-4 border border-gray-300 bg-white checked:bg-blue-600 checked:border-blue-600 focus:outline-none transition duration-200 mt-1 align-top bg-no-repeat bg-center bg-contain float-left mr-2 cursor-pointer" type="radio" name="gender" value="male" id="male" checked>
                                        <label class="form-check-label inline-block text-gray-800" for="flexRadioDefault1">
                                            Male
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input appearance-none rounded-full h-4 w-4 border border-gray-300 bg-white checked:bg-blue-600 checked:border-blue-600 focus:outline-none transition duration-200 mt-1 align-top bg-no-repeat bg-center bg-contain float-left mr-2 cursor-pointer" type="radio" name="gender" value="female" id="female" >
                                        <label class="form-check-label inline-block text-gray-800" for="flexRadioDefault2">
                                            Female
                                        </label>    
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mb-6">
                                <input type="text" id="cmnd" name="cmnd" placeholder="CMND"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="form-group mb-6">
                                <input type="date" id="start_date" name="start_date" placeholder="start date"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                             <div class="form-group mb-6">
                                <input type="date" id="end_date" name="end_date" placeholder="end date"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="flex justify-center mb-6">
                                <div class="mb-3 xl:w-96">
                                    <select name="room" class="form-select appearance-none block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding bg-no-repeat border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="Default select example">
                                        <c:forEach items="${requestScope.rooms}" var="room">
                                            <option value="${room.id}">${room.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="flex justify-center mb-6">
                                <div class="mb-3 xl:w-96">
                                    <select name="state" class="form-select appearance-none block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding bg-no-repeat border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="Default select example">
                                        <c:forEach items="${requestScope.states}" var="state">
                                            <option value="${state.id}">${state.name}</option>
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
