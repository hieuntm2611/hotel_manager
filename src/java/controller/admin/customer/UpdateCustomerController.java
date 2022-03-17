/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.customer;

import controller.auth.BaseAuthController;
import dal.service.CustomerDBContext;
import dal.user.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.auth.User;
import model.customer.Customer;

/**
 *
 * @author hieu2
 */
public class UpdateCustomerController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "CUSTOMER", "UPDATE");
        return num >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CustomerDBContext db = new CustomerDBContext();
        Customer customer = db.get(id);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/views/admin/customer/update.jsp").forward(request, response);
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String rawName = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        String name = rawName;
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String cmnd = request.getParameter("cmnd");
        Customer cus = new Customer();
        cus.setId(id);
        cus.setCmnd(cmnd);
        cus.setEmail(email);
        cus.setName(name);
        cus.setPhone(phone);
        CustomerDBContext db = new CustomerDBContext();
        db.update(cus);
        response.sendRedirect("/admin/customer");
        
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
