<%-- 
    Document   : category
    Created on : Mar 16, 2022, 4:51:04 PM
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
                <div class="flex justify-end">
                    <a href="/admin/category/create" type="button" data-mdb-ripple="true" data-mdb-ripple-color="light" class="inline-block px-6 py-2.5 mb-2 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:shadow-lg focus:shadow-lg focus:outline-none focus:ring-0 active:shadow-lg transition duration-150 ease-in-out" style="background-color: #1877f2;">
                        Add
                    </a>
                </div>
                <div class="grid grid-cols-4 gap-5">
                    <c:forEach items="${requestScope.categorys}" var="category">
                        <div class="flex justify-center w-full">
                            <div class="block p-6 rounded-lg shadow-lg bg-white w-full">
                                <h5 class="text-gray-900 text-xl leading-tight font-medium mb-2 text-center">${category.name}</h5>
                                <p id="price-${category.id}" class="text-red-900 text-md leading-tight font-medium mb-2 text-center">${category.price}</p>
                                <script>
                                    var x = ${category.price};
                                    x = x.toLocaleString('vi', {style: 'currency', currency: 'VND'});
                                    $("#price-${category.id}").text(x);
                                </script>
                                <div class="flex space-x-4 mt-5 w-full justify-center"> 
                                    <a href="/admin/category/update?id=${category.id}" class=" inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">Edit</a>
                                    <a href="/admin/category/remove?id=${category.id}" class=" inline-block px-6 py-2.5 bg-red-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-red-700 hover:shadow-lg focus:bg-red-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-800 active:shadow-lg transition duration-150 ease-in-out">Remove</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <jsp:include page="../main/footer.jsp" />
    </body>
</html>
