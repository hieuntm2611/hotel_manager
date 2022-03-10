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
import model.room.RoomState;

/**
 *
 * @author hieu2
 */
public class RoomStateDBContext extends DBContext<RoomState>{

    @Override
    public ArrayList<RoomState> all() {
        ArrayList<RoomState> rss = new ArrayList<>();
        String sql = "SELECT [id]\n" +
                    "      ,[name]\n" +
                    "  FROM [room_state]";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                RoomState rs = new RoomState();
                rs.setId(result.getInt("id"));
                rs.setName(result.getString("name"));
                rss.add(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rss;
    }

    @Override
    public RoomState get(int id) {
        String sql = "SELECT [id]\n" +
                    "      ,[name]\n" +
                    "  FROM [room_state]"
                + "where id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                RoomState rs = new RoomState();
                rs.setId(result.getInt("id"));
                rs.setName(result.getString("name"));
                return rs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(RoomState rs) {
        String sql = "INSERT INTO [dbo].[room_state]\n" +
                        "           ([name])\n" +
                        "     VALUES\n" +
                        "           (?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, rs.getName());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void update(RoomState roomState) {
        String sql = "UPDATE [room_state]\n" +
                        "   SET [name] = ?\n" +
                        " WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, roomState.getName());
            stm.setInt(2, roomState.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM [room_state]\n" +
                    "      WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomStateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
}
