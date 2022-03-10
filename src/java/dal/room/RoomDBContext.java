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
import model.auth.User;
import model.room.Room;

/**
 *
 * @author hieu2
 */
public class RoomDBContext extends DBContext<Room> {

    @Override
    public ArrayList all() {
        ArrayList<Room> rooms = new ArrayList<>();
        String sql = "SELECT [id]\n" +
                        "      ,[name]\n" +
                        "      ,[categoryId]\n" +
                        "      ,[roomstateId]\n" +
                        "  FROM [room]";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Room r = new Room();
                r.setId(result.getInt("id"));
                r.setName(result.getString("name"));
                r.setCategoryId(result.getInt("categoryId"));
                r.setRoomstateId(result.getInt("roomstateId"));
                rooms.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms;
    }

    @Override
    public Room get(int id) {
        
    String sql = "SELECT [id]\n" +
                        "      ,[name]\n" +
                        "      ,[categoryId]\n" +
                        "      ,[roomstateId]\n" +
                        "  FROM [room]"
            + "where id =?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Room r = new Room();
                r.setId(result.getInt("id"));
                r.setName(result.getString("name"));
                r.setCategoryId(result.getInt("categoryId"));
                r.setRoomstateId(result.getInt("roomstateId"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }



    @Override
    public void update(Room room) {
        String sql = "UPDATE [room]\n" +
                        "   SET [name] = ?\n" +
                        "      ,[categoryId] = ?\n" +
                        "      ,[roomstateId] = ?\n" +
                        " WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, room.getName());
            stm.setInt(2, room.getCategoryId());
            stm.setInt(3, room.getRoomstateId());
            stm.setInt(4, room.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM [room]\n" +
                    "      WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public void insert(Room model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    

}
