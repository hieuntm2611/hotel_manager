/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.service;

import controller.auth.BaseAuthController;
import dal.service.ServiceDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.customer.Service;

/**
 *
 * @author giaki
 */
public class CheckoutHistory extends BaseAuthController {

    @Override
     protected boolean isPermission(HttpServletRequest request) {
         return true;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        }
        String startString = request.getParameter("start");
        String endString = request.getParameter("end");
        Date start = null;
        Date end = null;
        if (startString != null && !startString.isEmpty()) {
            start = Date.valueOf(startString);
        }
        if (endString != null && !endString.isEmpty()) {
            end = Date.valueOf(endString);
        }
        int pageSize = 8;
        String page = request.getParameter("page");
        int pageIndex = 1;
        if (page != null) {
            try {
                pageIndex = Integer.parseInt(page);
            } catch (Exception e) {
                pageIndex = 1;
            }
        }
        ServiceDBContext db = new ServiceDBContext();
        request.setAttribute("page", pageIndex);
        request.setAttribute("size", db.getSizeCheckout() / pageSize + 1);
        ArrayList<Service> services =  null;
        if (search.trim().isEmpty() && start == null && end == null) {
            services = db.getCheckkouts(pageIndex, pageSize);
        } else {
            request.setAttribute("search", search);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            services = db.getCheckkouts(search, start, end);
        }
        request.setAttribute("services", services);
        request.getRequestDispatcher("/views/admin/checkout/history.jsp").forward(request, response);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
