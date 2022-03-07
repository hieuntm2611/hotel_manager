/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.service;

import dal.DBContext;
import dal.room.RoomDBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.room.Room;
import model.service.Customer;
import model.service.Service;
import model.service.State;

/**
 *
 * @author hieu2
 */
public class ServiceDBContext extends DBContext<Service>{

    @Override
    public ArrayList<Service> all() {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT [id]\n" +
                    "      ,[startDate]\n" +
                    "      ,[endDate]\n" +
                    "      ,[dateCreate]\n" +
                    "      ,[dateUpdate]\n" +
                    "      ,[roomId]\n" +
                    "      ,[customerId]\n" +
                    "      ,[stateId]\n" +
                    "  FROM [service]";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Service service = new Service();
                service.setId(result.getInt("id"));
                service.setStart(result.getDate("startDate"));
                service.setEnd(result.getDate("endDate"));
                service.setCreate(result.getDate("dateCreate"));
                service.setUpdate(result.getDate("dateUpdate"));
                CustomerDBContext customerDBContext = new CustomerDBContext();
                service.setCustomer(customerDBContext.get(result.getInt("customerId")));
                StateDBContext sdbc = new StateDBContext();
                service.setState(sdbc.get(result.getInt("stateId")));
                services.add(service);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }

    @Override
    public Service get(int id) {
        String sql = "SELECT [id]\n" +
                    "      ,[startDate]\n" +
                    "      ,[endDate]\n" +
                    "      ,[dateCreate]\n" +
                    "      ,[dateUpdate]\n" +
                    "      ,[roomId]\n" +
                    "      ,[customerId]\n" +
                    "      ,[stateId]\n" +
                    "  FROM [service]"
                + "where id =?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Service service = new Service();
                service.setId(result.getInt("id"));
                service.setStart(result.getDate("startDate"));
                service.setEnd(result.getDate("endDate"));
                service.setCreate(result.getDate("dateCreate"));
                service.setUpdate(result.getDate("dateUpdate"));
                CustomerDBContext customerDBContext = new CustomerDBContext();
                Customer c = customerDBContext.get(result.getInt("customerId"));
                service.setCustomer(c);
                StateDBContext sdbc = new StateDBContext();
                State s = sdbc.get(result.getInt("stateId"));
                service.setState(s);
                RoomDBContext rdbc = new RoomDBContext();
                Room r = rdbc.get(result.getInt("roomId"));
                service.setRoom(r);
                return service;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public void insert(Service service){
        String sql = "INSERT INTO [service]\n" +
                    "           ([startDate]\n" +
                    "           ,[endDate]\n" +
                    "           ,[dateCreate]\n" +
                    "           ,[dateUpdate]\n" +
                    "           ,[roomId]\n" +
                    "           ,[customerId]\n" +
                    "           ,[stateId])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setDate(1, service.getStart());
            stm.setDate(2, service.getEnd());
            stm.setDate(3, service.getCreate());
            stm.setDate(4, service.getUpdate());
            stm.setInt(5, service.getRoom().getId());
            stm.setInt(6, service.getCustomer().getId());
            stm.setInt(7, service.getState().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public void update(Service service) {
        String sql = "UPDATE [dbo].[service]\n" +
                    "   SET [startDate] = ?\n" +
                    "      ,[endDate] = ?\n" +
                    "      ,[dateCreate] = ?\n" +
                    "      ,[dateUpdate] = ?\n" +
                    "      ,[roomId] = ?\n" +
                    "      ,[customerId] = ?\n" +
                    "      ,[stateId] = ?\n" +
                    " WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setDate(1, service.getStart());
            stm.setDate(2, service.getEnd());
            stm.setDate(3, service.getCreate());
            stm.setDate(4, service.getUpdate());
            stm.setInt(5, service.getRoom().getId());
            stm.setInt(6, service.getCustomer().getId());
            stm.setInt(7, service.getState().getId());
            stm.setInt(8, service.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM [dbo].[service]\n" +
                    "      WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
