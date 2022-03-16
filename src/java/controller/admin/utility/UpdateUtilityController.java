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
public class UpdateUtilityController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "UTILITY", "UPDATE");
        return num >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UtilityDBContext db = new UtilityDBContext();
        int id = Integer.parseInt(request.getParameter("id"));
        Utility utility = db.get(id);
        request.setAttribute("utility", utility);
        request.getRequestDispatcher("/views/admin/utility/update.jsp").forward(request, response);
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UtilityDBContext db = new UtilityDBContext();
        String rawId = request.getParameter("id");
        String rawName = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        int id = Integer.parseInt(rawId);
        String name = rawName;
        Utility u = new Utility();
        u.setId(id);
        u.setName(name);
        db.update(u);
        response.sendRedirect("/admin/category/utility");
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
