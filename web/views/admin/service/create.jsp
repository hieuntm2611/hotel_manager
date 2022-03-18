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
                <div class="min-h-screen flex flex-col justify-center items-center">
                    <div class="block p-6 rounded-lg shadow-lg bg-white max-w-xl">
                        <form action="/admin/service/create" class="w-[450px]" method="post">
                            <c:if test="${requestScope.error!=null}">
                                <div class="bg-red-100 rounded-lg py-5 px-6 mb-4 text-base text-red-700 mb-3" role="alert">
                                    ${requestScope.error}
                                </div>
                            </c:if>
                            <div class="form-group mb-6">
                                <input required type="text" id="name" name="name" value="${requestScope.customer.name}" placeholder="Name"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="form-group mb-6">
                                <input required type="text" id="phone" name="phone" value="${requestScope.customer.phone}" placeholder="Phone number"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="form-group mb-6">
                                <input type="text" id="email" name="email" value="${requestScope.customer.email}" placeholder="Email"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="flex justify-start mb-6">
                                <div class="flex items-center space-x-4">
                                    <div class="form-check">
                                        <input ${requestScope.customer.gender!=null && requestScope.customer.gender?requestScope.customer.gender?"checked":"":"checked"} class="form-check-input appearance-none rounded-full h-4 w-4 border border-gray-300 bg-white checked:bg-blue-600 checked:border-blue-600 focus:outline-none transition duration-200 mt-1 align-top bg-no-repeat bg-center bg-contain float-left mr-2 cursor-pointer" type="radio" name="gender" value="male" id="male" >
                                        <label class="form-check-label inline-block text-gray-800" for="male">
                                            Male
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input ${requestScope.customer.gender!=null && requestScope.customer.gender==false?"checked":""} class="form-check-input appearance-none rounded-full h-4 w-4 border border-gray-300 bg-white checked:bg-blue-600 checked:border-blue-600 focus:outline-none transition duration-200 mt-1 align-top bg-no-repeat bg-center bg-contain float-left mr-2 cursor-pointer" type="radio" name="gender" value="female" id="female" >
                                        <label class="form-check-label inline-block text-gray-800" for="female">
                                            Female
                                        </label>    
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mb-6">
                                <input required type="text" id="cmnd" name="cmnd" value="${requestScope.customer.cmnd}" placeholder="CMND"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="form-group mb-6">
                                <input required type="date" id="start_date" value="${requestScope.start}" name="start_date" placeholder="start date"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="form-group mb-6">
                                <input required type="date" id="end_date" value="${requestScope.end}" name="end_date" placeholder="end date"class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" >
                            </div>
                            <div class="flex justify-center mb-6">
                                <div class="mb-3 w-full">
                                    <select required name="room" onchange="getServiceByRoom(this)" class="form-select appearance-none block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding bg-no-repeat border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="Default select example">
                                        <option>-------room--------</option>
                                        <c:forEach items="${requestScope.rooms}" var="room">
                                            <option value="${room.id}">${room.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="flex justify-center mb-6">
                                <div class="mb-3 w-full">
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
                    <div class="" id="room-service"></div>
                </div>
            </div>
        </div>
        <script>
            const getServiceByRoom = (e) => {
                $("#room-service").html("")
                $.ajax({
                    method: "GET",
                    url: "/admin/service/roomsetted",
                    data: {id: $(e).val()},
                }).done(function (data) {
                    $("#room-service").html(data)
                })
            }
        </script>
        <jsp:include page="../main/footer.jsp" />
    </body>
</html>
