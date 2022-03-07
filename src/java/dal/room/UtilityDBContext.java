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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public void update(Utility model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Utility model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
