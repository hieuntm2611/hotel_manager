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
public class RoomController extends BaseAuthController {

    
    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "ROOM", "READ");
        return num >= 1;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomDBContext db = new RoomDBContext();
        ArrayList<Room> rooms = db.all();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("/views/admin/room/rooms.jsp").forward(request, response);
    }

    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RoomDBContext db = new RoomDBContext();
        Room room = db.get(id);
        request.setAttribute("room", room);
        request.getRequestDispatcher("/").forward(request, response);
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
