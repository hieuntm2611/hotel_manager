/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.utility;

import controller.auth.BaseAuthController;
import dal.room.UtilityDBContext;
import dal.user.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.auth.User;
import model.room.Utility;

/**
 *
 * @author hieu2
 */
public class UtilityController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "UTILITY", "READ");
        return num >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UtilityDBContext db = new UtilityDBContext();
        ArrayList<Utility> utilitys = db.all();
        request.setAttribute("utilitys", utilitys);
        request.getRequestDispatcher("/views/admin/utility/utility.jsp").forward(request, response);
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
