/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.service;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.service.State;

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

    @Override
    public State insert(State model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(State model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
