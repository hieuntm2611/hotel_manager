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
import model.service.Customer;

/**
 *
 * @author hieu2
 */
public class CreateCustomerController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "CUSTOMER", "CREATE");
        return num >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerDBContext db = new CustomerDBContext();
        ArrayList<Customer> customers = db.all();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/").forward(request, response);
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String cmnd = request.getParameter("cmnd");
        boolean gender = Boolean.getBoolean(request.getParameter("gender"));
        Customer customer = new Customer();
        customer.setName(name);
        customer.setCmnd(cmnd);
        customer.setEmail(email);
        customer.setGender(gender);
        customer.setPhone(phone);
        CustomerDBContext db = new CustomerDBContext();
        db.insert(customer);
        response.sendRedirect("./");
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
