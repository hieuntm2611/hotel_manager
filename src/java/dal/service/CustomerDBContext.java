/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.service;

import dal.DBContext;
import dal.room.UtilityDBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.customer.Customer;

/**
 *
 * @author hieu2
 */
public class CustomerDBContext extends DBContext<Customer>{

    @Override
    public ArrayList<Customer> all() {
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT [id]\n" +
                    "      ,[name]\n" +
                    "      ,[gender]\n" +
                    "      ,[phone_number]\n" +
                    "      ,[cmnd]\n" +
                    "      ,[email]\n" +
                    "  FROM [customer]";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
    
    public  Customer getCustomerByPhone(String phone){
        String sql = "SELECT [id]\n" +
                    "      ,[name]\n" +
                    "      ,[gender]\n" +
                    "      ,[phone_number]\n" +
                    "      ,[cmnd]\n" +
                    "      ,[email]\n" +
                    "  FROM [customer]"
                + "where customer.phone_number=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, phone);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                return customer;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public void insert(Customer customer){
        String sql = "INSERT INTO [dbo].[customer]\n" +
                    "           ([name]\n" +
                    "           ,[gender]\n" +
                    "           ,[phone_number]\n" +
                    "           ,[cmnd]\n" +
                    "           ,[email])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, customer.getName());
            stm.setBoolean(2, customer.isGender());
            stm.setString(3, customer.getPhone());
            stm.setString(4, customer.getCmnd());
            stm.setString(5, customer.getEmail());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public Customer get(int id) {
        String sql = "SELECT [id]\n" +
                    "      ,[name]\n" +
                    "      ,[gender]\n" +
                    "      ,[phone_number]\n" +
                    "      ,[cmnd]\n" +
                    "      ,[email]\n" +
                    "  FROM [customer]"
                + "where customer.id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                return customer;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public void update(Customer customer) {
        String sql = "UPDATE [dbo].[customer]\n" +
                    "   SET [name] = ?" +
                    "      ,[gender] = ?" +
                    "      ,[phone_number] = ?" +
                    "      ,[cmnd] = ?" +
                    "      ,[email] = ?" +
                    " WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, customer.getName());
            stm.setBoolean(2, customer.isGender());
            stm.setString(3, customer.getPhone());
            stm.setString(4, customer.getCmnd());
            stm.setString(5, customer.getEmail());
            stm.setInt(6, customer.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE [customer]\n" +
                        " WHERE [id] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
