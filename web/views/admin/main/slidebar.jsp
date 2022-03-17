<%-- 
    Document   : slidebar
    Created on : Mar 1, 2022, 4:18:18 PM
    Author     : hieu2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <div class="w-60 h-full shadow-md bg-white px-1 fixed top-0 left-0" style="width: 235px">
            <ul class="relative">
                <li class="relative">
                    <a href="/dashboard"  class="flex items-center text-sm py-4 px-6 h-12 overflow-hidden text-gray-700 text-ellipsis whitespace-nowrap rounded hover:text-gray-900 hover:bg-gray-100 transition duration-300 ease-in-out" data-mdb-ripple="true" data-mdb-ripple-color="dark">
                        <span>Dashboard</span>
                    </a>
                </li>
                <li class="relative">
                    <a href="/admin/category/utility"  class="flex items-center text-sm py-4 px-6 h-12 overflow-hidden text-gray-700 text-ellipsis whitespace-nowrap rounded hover:text-gray-900 hover:bg-gray-100 transition duration-300 ease-in-out"  data-mdb-ripple="true" data-mdb-ripple-color="dark">
                        <span>Utility</span>
                    </a>
                </li>
                <li class="relative">
                    <a href="/admin/service"  class="flex items-center text-sm py-4 px-6 h-12 overflow-hidden text-gray-700 text-ellipsis whitespace-nowrap rounded hover:text-gray-900 hover:bg-gray-100 transition duration-300 ease-in-out" data-mdb-ripple="true" data-mdb-ripple-color="dark">
                        <span>Service</span>
                    </a>
                </li>
                <li class="relative">
                    <a href="/admin/room/state"  class="flex items-center text-sm py-4 px-6 h-12 overflow-hidden text-gray-700 text-ellipsis whitespace-nowrap rounded hover:text-gray-900 hover:bg-gray-100 transition duration-300 ease-in-out" data-mdb-ripple="true" data-mdb-ripple-color="dark">
                        <span>Room State</span>
                    </a>
                </li>
                <li class="relative">
                    <a href="/admin/service/state"  class="flex items-center text-sm py-4 px-6 h-12 overflow-hidden text-gray-700 text-ellipsis whitespace-nowrap rounded hover:text-gray-900 hover:bg-gray-100 transition duration-300 ease-in-out" data-mdb-ripple="true" data-mdb-ripple-color="dark">
                        <span>Service State</span>
                    </a>
                </li>
                <li class="relative">
                    <a href="/admin/category"  class="flex items-center text-sm py-4 px-6 h-12 overflow-hidden text-gray-700 text-ellipsis whitespace-nowrap rounded hover:text-gray-900 hover:bg-gray-100 transition duration-300 ease-in-out" data-mdb-ripple="true" data-mdb-ripple-color="dark">
                        <span>Category</span>
                    </a>
                </li>
            </ul>
        </div>
    </body>
</html>
