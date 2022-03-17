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
import model.room.Category;
import model.room.Utility;

/**
 *
 * @author hieu2
 */
public class CategoryDBContext extends DBContext<Category> {

    @Override
    public ArrayList<Category> all() {
        ArrayList<Category> categories = new ArrayList<>();
        UtilityDBContext db = new UtilityDBContext();
        String sql = "SELECT c.[id]\n"
                + "      ,c.[name]\n"
                + "      ,[price]\n"
                + "  FROM [category] c";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Category category = new Category();
                category.setId(result.getInt("id"));
                category.setName(result.getString("name"));
                category.setPrice(result.getDouble("price"));
                ArrayList<Utility> listU = db.getUtilitiesByCategory(category.getId());
                category.setUtilities(listU);
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    @Override
    public Category get(int id) {
        UtilityDBContext db = new UtilityDBContext();
        String sql = "SELECT c.[id]\n"
                + "      ,c.[name]\n"
                + "      ,[price]\n"
                + "  FROM [category] c \n"
                + "where c.id =?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Category category = new Category();
                category.setId(result.getInt("id"));
                category.setName(result.getString("name"));
                category.setPrice(result.getDouble("price"));
                ArrayList<Utility> listU = db.getUtilitiesByCategory(category.getId());
                category.setUtilities(listU);
                return category;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void update(Category c) {
        UtilityDBContext utilityDB = new UtilityDBContext();
        String sql = "UPDATE [category]\n"
                + "   SET [name] = ?\n"
                + "      ,[price] = ?\n"
                + " WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, c.getName());
            stm.setDouble(2, c.getPrice());
            stm.setInt(3, c.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM [category]\n"
                + "      WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void insert(Category c) {
        String sql = "INSERT INTO [category]\n"
                + "           ([name]\n"
                + "           ,[price])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, c.getName());
            stm.setDouble(2, c.getPrice());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public int insertGetId(Category c) {
        String sql = "INSERT INTO [category]\n"
                + "           ([name]\n"
                + "           ,[price])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql, stm.RETURN_GENERATED_KEYS);
            stm.setString(1, c.getName());
            stm.setDouble(2, c.getPrice());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }
    
    public int getMaxId(){
        String sql = "SELECT TOP (1) [id]\n" +
                    "  FROM [category]\n" +
                    "  order by id desc";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int id = Integer.parseInt(result.getString("id"));
                return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
