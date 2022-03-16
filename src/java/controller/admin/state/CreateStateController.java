/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.state;

import controller.auth.BaseAuthController;
import dal.service.StateDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.customer.State;

/**
 *
 * @author hieu2
 */
public class CreateStateController extends BaseAuthController {

    
    @Override
    protected boolean isPermission(HttpServletRequest request) {
        return true;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        StateDBContext db = new StateDBContext();
        ArrayList<State> states = db.all();
        request.setAttribute("states", states);
        request.getRequestDispatcher("/views/admin/state1/create.jsp").forward(request, response);
    }

    
    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rawName = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        String name = rawName;
        StateDBContext db = new StateDBContext();
        State s = new State();
        s.setName(name);
        db.insert(s);
        response.sendRedirect("/admin/service/state");
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
