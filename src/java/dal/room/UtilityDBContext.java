/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.room;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.room.Utility;

/**
 *
 * @author hieu2
 */
public class UtilityDBContext extends DBContext<Utility>{

    @Override
    public ArrayList<Utility> all() {        
        ArrayList<Utility> utilities = new ArrayList<>();
        String sql = "SELECT [id]\n" +
                    "      ,[name]\n" +
                    "  FROM [utility]";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Utility utility = new Utility();
                utility.setId(result.getInt("id"));
                utility.setName(result.getString("name"));
                utilities.add(utility);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilities;
    }
    public ArrayList<Utility> getUtilitiesByCategory(int id){
        ArrayList<Utility> utilities = new ArrayList<>();
        String sql = "SELECT u.id\n" +
                    "      ,u.[name]\n" +
                    "  FROM [utility] u inner join category_utility cu on u.id=cu.utilityId\n" +
                    "  inner join category c on cu.categoryId = c.id\n" +
                    "  where c.id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Utility utility = new Utility();
                utility.setId(result.getInt("id"));
                utility.setName(result.getString("name"));
                utilities.add(utility);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilities;
    }

    @Override
    public Utility get(int id) {
        String sql = "SELECT [id]\n" +
                    "      ,[name]\n" +
                    "  FROM [utility]"
                + " where id =?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Utility utility = new Utility();
                utility.setId(result.getInt("id"));
                utility.setName(result.getString("name"));
                return utility;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }



    @Override
    public void update(Utility utility) {
        String sql = "UPDATE [utility]\n" +
                    "   SET [name] = ?\n" +
                    " WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, utility.getName());
            stm.setInt(2, utility.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM [utility]\n" +
                    "      WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public void insert(Utility u) {
        String sql = "INSERT INTO [utility]\n" +
                        "           ([name])\n" +
                        "     VALUES\n" +
                        "           (?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, u.getName());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UtilityDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
}
