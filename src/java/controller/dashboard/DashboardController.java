/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dashboard;

import controller.auth.BaseAuthController;
import dal.service.ServiceDBContext;
import dal.user.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.auth.User;
import model.customer.Service;

/**
 *
 * @author hieu2
 */
public class DashboardController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getId(), "SERVICE", "READ");
        return numRead >= 1;
    }
    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceDBContext serviceDB = new ServiceDBContext();
        Hashtable<Integer,ArrayList<Service>> services = serviceDB.serviceDone();
        Hashtable<Integer, BigDecimal> dataService = new Hashtable<>();
        for (Map.Entry<Integer, ArrayList<Service>> entry : services.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Service> value = entry.getValue();
            BigDecimal sum = new BigDecimal(0);
            for (Service service : value) {
                sum = sum.add(service.getTotal());
            }
            dataService.put(key, sum);
        }
        Hashtable<Integer,ArrayList<Service>> servicesDattruoc = serviceDB.serviceOrder();
        Hashtable<Integer, BigDecimal> dataServiceOrder = new Hashtable<>();
        for (Map.Entry<Integer, ArrayList<Service>> entry : servicesDattruoc.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Service> value = entry.getValue();
            BigDecimal sum = new BigDecimal(0);
            for (Service service : value) {
                sum = sum.add(service.getTotal());
            }
            dataServiceOrder.put(key, sum);
        }
        request.setAttribute("dataService", dataService);
        request.setAttribute("dataServiceOrder", dataServiceOrder);
        request.getRequestDispatcher("/views/admin/dashboard/dashboard.jsp").forward(request, response);
    }
    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}
