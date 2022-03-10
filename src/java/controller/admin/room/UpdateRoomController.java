/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.room;

import controller.auth.BaseAuthController;
import dal.room.RoomDBContext;
import dal.user.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.auth.User;
import model.room.Room;

/**
 *
 * @author hieu2
 */
public class UpdateRoomController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "CUSTOMER", "READ");
        return num >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomDBContext db = new RoomDBContext();
        ArrayList<Room> rooms = db.all();
        request.setAttribute("rooms", rooms);
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
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    int categoryId = Integer.parseInt(request.getParameter("categoryId"));
    int roomstateId= Integer.parseInt(request.getParameter("roomstateId"));
    Room r = new Room();
    r.setCategoryId(categoryId);
    r.setId(id);
    r.setName(name);
    r.setRoomstateId(roomstateId);
    RoomDBContext db = new RoomDBContext();
    db.update(r);
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
