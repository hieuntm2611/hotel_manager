/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.service;

import controller.auth.BaseAuthController;
import dal.service.ServiceDBContext;
import dal.user.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.auth.User;
import model.room.Room;
import model.service.Customer;
import model.service.Service;
import model.service.State;

/**
 *
 * @author hieu2
 */
public class UpdateServiceController extends BaseAuthController {

    
    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "SERVICE", "UPDATE");
        return num >= 1;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Room room = new Room();
        room.setId(Integer.parseInt(request.getParameter("roomid")));
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(request.getParameter("customerid")));
        State state = new State();
        state.setId(Integer.parseInt(request.getParameter("stateid")));
        Date start = Date.valueOf(request.getParameter("start"));
        Date end = Date.valueOf(request.getParameter("end"));
        Date create = Date.valueOf(request.getParameter("create"));
        Date update = Date.valueOf(request.getParameter("update"));
        Service s = new Service();
        s.setId(Integer.parseInt(request.getParameter("id")));
        s.setRoom(room);
        s.setCreate(create);
        s.setCustomer(customer);
        s.setEnd(end);
        s.setStart(start);
        s.setState(state);
        s.setUpdate(update);
        ServiceDBContext db = new ServiceDBContext();
        db.insert(s);
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
