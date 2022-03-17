/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.category;

import controller.auth.BaseAuthController;
import dal.room.CategoryDBContext;
import dal.room.CategoryUtilityDBContext;
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
import model.room.Category;
import model.room.Utility;

/**
 *
 * @author hieu2
 */
public class UpdateCategoryController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "CATEGORY", "UPDATE");
        return num >= 1;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CategoryDBContext db = new CategoryDBContext();
        Category category = db.get(id);
        UtilityDBContext udb = new UtilityDBContext();
        ArrayList<Utility> utilitys = udb.all();
        request.setAttribute("utilitys", utilitys);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/views/admin/category/update.jsp").forward(request, response);
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rawId = request.getParameter("id");
        CategoryDBContext db = new CategoryDBContext();
        int id = Integer.parseInt(rawId);
        Category category = db.get(id);
        String rawName = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        String name = rawName;
        double price = Double.parseDouble(request.getParameter("price"));
        String[] raw_utility = request.getParameterValues("utility");
        for (Utility utility : category.getUtilities()) {
            CategoryUtilityDBContext categoryDB = new CategoryUtilityDBContext();
            categoryDB.delete(id, utility.getId());
        }
        category.getUtilities().clear();
        for (String string : raw_utility) {
            Utility u = new Utility();
            u.setId(Integer.parseInt(string));
            CategoryUtilityDBContext categoryDB = new CategoryUtilityDBContext();
            categoryDB.insert(id, u.getId());
        }
        category.setName(name);
        category.setPrice(price);
        db.update(category);
        response.sendRedirect("/admin/category");
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
