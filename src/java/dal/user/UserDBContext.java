/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.user;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.auth.Group;
import model.auth.User;

/**
 *
 * @author hieu2
 */
public class UserDBContext extends DBContext<User> {

     public int hasPermission(int id, String feature, String code) {
        String sql = "SELECT COUNT(*) AS 'total'\n" +
                    "FROM [user] INNER JOIN [user_group]\n" +
                    "ON [user].id = user_group.userId \n" +
                    "INNER JOIN [group]\n" +
                    "ON user_group.groupId = [group].id\n" +
                    "INNER JOIN [group_action] \n" +
                    "ON [group].id = [group_action].groupId\n" +
                    "INNER JOIN [action] \n" +
                    "ON [group_action].actionId=[action].id\n" +
                    "WHERE [user].id =? \n" +
                    "AND [action].feature =?\n" +
                    "AND [action].code=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, feature);
            statement.setString(3, code);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
               return result.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
     
     
    public User getUser(String username, String password) {
        try {
            String sql = "SELECT [user].[id]\n"
                    + "      ,[user].[username]\n"
                    + "      ,[user].[password]\n"
                    + "      ,[user].[first_name]\n"
                    + "      ,[user].[last_name]\n"
                    + "      ,[user].[birthday]\n"
                    + "      ,[user].[email]\n"
                    + "      ,[user].[gender]\n"
                    + "      ,[user].[is_admin]\n"
                    + "      ,[user].[permission]\n"
                    + "	  ,[group].[name] as 'groupName'\n"
                    + "	  ,[group].[id] as 'groupId'\n"
                    + "  FROM [user]\n"
                    + "  inner join [user_group] on [user_group].[userId] = [user].[id]\n"
                    + "  inner join [group] on [group].[id] = [user_group].[groupId]\n"
                    + " WHERE (username = ? or email = ?) and password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, username);
            stm.setString(3, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setBirthday(rs.getDate("birthday"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getBoolean("gender"));
                user.setIs_admin(rs.getBoolean("is_admin"));
                user.setPermission(rs.getString("permission"));
                Group group = new Group();
                group.setId(rs.getInt("groupId"));
                group.setName(rs.getString("groupName"));
                user.setGroup(group);
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<User> all() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
