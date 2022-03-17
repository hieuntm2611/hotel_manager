/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.category;

import controller.auth.BaseAuthController;
import dal.room.CategoryDBContext;
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
public class CreateCategoryController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "CATEGORY", "CREATE");
        return num >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDBContext db = new CategoryDBContext();
        ArrayList<Category> categorys = db.all();
        request.setAttribute("categorys", categorys);
        request.getRequestDispatcher("/views/admin/category/create.jsp").forward(request, response);
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Category category = new Category();
        String rawName = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        String name = rawName;
        double price = Double.parseDouble(request.getParameter("price"));
        category.setName(name);
        category.setPrice(price);
        CategoryDBContext db = new CategoryDBContext();
        int id = db.insertGetId(category);
        response.sendRedirect("/admin/category/update?id="+id);    
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
