/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin.service;

import controller.auth.BaseAuthController;
import dal.room.RoomDBContext;
import dal.room.RoomStateDBContext;
import dal.service.CustomerDBContext;
import dal.service.ServiceDBContext;
import dal.service.StateDBContext;
import dal.user.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.auth.User;
import model.room.Room;
import model.customer.Customer;
import model.customer.Service;
import model.customer.State;
import model.room.RoomState;

/**
 *
 * @author hieu2
 */
public class CreateServiceController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int num = userDB.hasPermission(user.getId(), "SERVICE", "CREATE");
        return num >= 1;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceDBContext db = new ServiceDBContext();
        StateDBContext statedb = new StateDBContext();
        ArrayList<State> states = statedb.all();
        request.setAttribute("states", states);
        RoomDBContext roomdb = new RoomDBContext();
        ArrayList<Room> rooms = roomdb.allHaveRoom();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("/views/admin/service/create.jsp").forward(request, response);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String cmnd = request.getParameter("cmnd");
        String rawgender = request.getParameter("gender");
        boolean gender = (rawgender.equals("male")) ? true : false;

        Customer customer = new Customer();
        customer.setName(name);
        customer.setCmnd(cmnd);
        customer.setEmail(email);
        customer.setGender(gender);
        customer.setPhone(phone);

        Date start = Date.valueOf(request.getParameter("start_date"));
        Date end = Date.valueOf(request.getParameter("end_date"));

        RoomDBContext roomDB = new RoomDBContext();
        if (start.getTime() > end.getTime()) {
            StateDBContext statedb = new StateDBContext();
            ArrayList<State> states = statedb.all();
            request.setAttribute("states", states);
            ArrayList<Room> rooms = roomDB.allHaveRoom();
            request.setAttribute("rooms", rooms);
            request.setAttribute("error", "Start time need smaller end time!");
            request.setAttribute("customer", customer);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.getRequestDispatcher("/views/admin/service/create.jsp").forward(request, response);
            return;
        }

        ServiceDBContext db = new ServiceDBContext();
        Room room = new Room();
        try {
            room.setId(Integer.parseInt(request.getParameter("room")));
            room = roomDB.get(Integer.parseInt(request.getParameter("room")));
        } catch (Exception e) {
            StateDBContext statedb = new StateDBContext();
            ArrayList<State> states = statedb.all();
            request.setAttribute("states", states);
            ArrayList<Room> rooms = roomDB.allHaveRoom();
            request.setAttribute("rooms", rooms);
            request.setAttribute("error", "Please choose room");
            request.setAttribute("customer", customer);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.getRequestDispatcher("/views/admin/service/create.jsp").forward(request, response);
            return;
        }
        ArrayList<Service> servicesSetteds = db.getServiceSetted(room.getId());
        for (Service servicesSetted : servicesSetteds) {
            if ((servicesSetted.getStart().getTime() <= start.getTime() || start.getTime() <= servicesSetted.getEnd().getTime()
                    || servicesSetted.getStart().getTime() <= end.getTime() || end.getTime() <= servicesSetted.getEnd().getTime())) {
                request.setAttribute("error", "You cannot book during this time. "
                        + "This room has been used from "
                        + start + " to " + end);
                request.setAttribute("customer", customer);
                StateDBContext statedb = new StateDBContext();
                ArrayList<State> states = statedb.all();
                request.setAttribute("states", states);
                RoomDBContext roomdb = new RoomDBContext();
                ArrayList<Room> rooms = roomdb.allHaveRoom();
                request.setAttribute("rooms", rooms);
                request.setAttribute("start", start);
                request.setAttribute("end", end);
                request.getRequestDispatcher("/views/admin/service/create.jsp").forward(request, response);
                return;
            }
        }

        CustomerDBContext customerdb = new CustomerDBContext();
        int customer_id = customerdb.insertHaveId(customer);
        customer.setId(customer_id);
        StateDBContext stateDB = new StateDBContext();
        State state = new State();
        state.setId(Integer.parseInt(request.getParameter("state")));
        state = stateDB.get(state.getId());
        if(state.getName().equalsIgnoreCase("Đang sử dụng")){
            RoomStateDBContext roomStateDB = new RoomStateDBContext();
            RoomState roomState = roomStateDB.getByName("Đang có người thuê");
            room.setRoomState(roomState);
            room.setRoomstateId(roomState.getId());
            roomDB.update(room);
        }
        Service s = new Service();
        s.setRoom(room);
        s.setCreate(new Date(new java.util.Date().getTime()));
        s.setCustomer(customer);
        s.setEnd(end);
        s.setStart(start);
        s.setState(state);
        s.setUpdate(new Date(new java.util.Date().getTime()));
        db.insert(s);
        response.sendRedirect("/admin/service");
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
