/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.service;

import controller.auth.BaseAuthController;
import dal.room.RoomDBContext;
import dal.room.RoomStateDBContext;
import dal.service.ServiceDBContext;
import dal.service.StateDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.customer.Service;
import model.customer.State;
import model.room.Room;
import model.room.RoomState;

/**
 *
 * @author giaki
 */
public class CheckoutController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        return true;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ServiceDBContext serviceDB = new ServiceDBContext();
        Service service = serviceDB.get(id);
        StateDBContext stateDB = new StateDBContext();
        if (service.getState().getName().equalsIgnoreCase("Đang sử dụng")) {
            RoomDBContext roomDB = new RoomDBContext();
            RoomStateDBContext roomStateDB = new RoomStateDBContext();
            RoomState roomState = roomStateDB.getByName("Chưa có người thuê");
            Room room = roomDB.get(service.getRoom().getId());
            room.setRoomState(roomState);
            roomDB.update(room);
            service.setRoom(room);
            State state = stateDB.getByName("Đã trả phòng");
            service.setState(state);
            serviceDB.update(service);
        } else {
            State state = stateDB.getByName("Hủy");
            service.setState(state);
            serviceDB.update(service);
        }
        response.sendRedirect("/admin/checkout/history");
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
