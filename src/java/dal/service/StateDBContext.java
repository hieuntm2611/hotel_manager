/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.service;

import dal.DBContext;
import dal.room.RoomStateDBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.customer.State;
import model.room.RoomState;

/**
 *
 * @author hieu2
 */
public class StateDBContext extends DBContext<State>{

    @Override
    public ArrayList<State> all() {
        ArrayList<State> states = new ArrayList<>();
        String sql = "SELECT [id]\n" +
                    "      ,[name]\n" +
                    "  FROM [state]";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                State state = new State();
                state.setId(result.getInt("id"));
                state.setName(result.getString("name"));
                states.add(state);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return states;
    }

    @Override
    public State get(int id) {
        String sql = "SELECT [id]\n" +
                    "      ,[name]\n" +
                    "  FROM [state]"
                + "where state.id =?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                State state = new State();
                state.setId(result.getInt("id"));
                state.setName(result.getString("name"));
                return state;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public State getByName(String name) {
        String sql = "SELECT [id]\n" +
                    "      ,[name]\n" +
                    "  FROM [state]"
                + " where LOWER(name)=LOWER(?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                State st = new State();
                st.setId(result.getInt("id"));
                st.setName(result.getString("name"));
                return st;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void update(State state) {
        String sql = "UPDATE [state]\n" +
                    "   SET [name] = ?\n" +
                    " WHERE id =?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, state.getName());
            stm.setInt(2, state.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM [state]\n" +
                    "      WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    @Override
    public void insert(State s) {
        String sql = "INSERT INTO [state]\n" +
                    "           ([name])\n" +
                    "     VALUES\n" +
                    "           (?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, s.getName());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (stm!=null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StateDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
