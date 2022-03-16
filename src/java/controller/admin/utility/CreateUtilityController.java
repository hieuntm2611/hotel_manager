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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.auth.User;
import model.room.Utility;

/**
 *
 * @author hieu2
 */
public class CreateUtilityController extends BaseAuthController {
    
    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "UTILITY", "CREATE");
        return num >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UtilityDBContext db = new UtilityDBContext();
        ArrayList<Utility> utilitys = db.all();
        request.setAttribute("utilitys", utilitys);
        request.getRequestDispatcher("/views/admin/utility/create.jsp").forward(request, response);
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
        UtilityDBContext db = new UtilityDBContext();
        Utility u = new Utility();
        u.setName(name);
        db.insert(u);
        response.sendRedirect("/admin/category/utility");
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}
