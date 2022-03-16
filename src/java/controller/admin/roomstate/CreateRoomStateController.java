/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.roomstate;

import controller.auth.BaseAuthController;
import dal.room.RoomStateDBContext;
import dal.user.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.auth.User;
import model.room.RoomState;

/**
 *
 * @author hieu2
 */
public class CreateRoomStateController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "ROOMSTATE", "CREATE");
        return num >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomStateDBContext db = new RoomStateDBContext();
        ArrayList<RoomState> roomStates = db.all();
        request.setAttribute("roomStates", roomStates);
        request.getRequestDispatcher("/views/admin/roomState/create.jsp").forward(request, response);
    }

    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rawName = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        String name = rawName;
        RoomState r = new RoomState();
        r.setName(name);
        RoomStateDBContext db = new RoomStateDBContext();
        db.insert(r);
        response.sendRedirect("/admin/room/state");
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
