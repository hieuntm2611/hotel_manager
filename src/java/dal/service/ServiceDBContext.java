/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.service;

import dal.DBContext;
import dal.room.RoomDBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.room.Room;
import model.customer.Customer;
import model.customer.Service;
import model.customer.State;
import model.room.Category;

/**
 *
 * @author hieu2
 */
public class ServiceDBContext extends DBContext<Service> {

    @Override
    public ArrayList<Service> all() {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT s.[id]\n"
                + "      ,s.[startDate]\n"
                + "      ,s.[endDate]\n"
                + "      ,s.[dateCreate]\n"
                + "      ,s.[dateUpdate]\n"
                + "      ,s.[roomId]\n"
                + "	  ,c.id as 'customer_id'\n"
                + "      ,c.[name] as 'customer_name'\n"
                + "	  ,c.phone_number\n"
                + "	  ,c.gender\n"
                + "	  ,c.cmnd\n"
                + "	  ,c.email\n"
                + "	  ,st.id as 'state_id'\n"
                + "       ,st.[name] as 'state_name'\n"
                + "	  ,r.id as 'room_id'\n"
                + "	  ,r.[name] as 'room_name'\n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n";
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
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setName(result.getString("customer_name"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                service.setCustomer(customer);
                State state = new State();
                state.setId(result.getInt("state_id"));
                state.setName(result.getString("state_name"));
                service.setState(state);
                Room room = new Room();
                room.setId(result.getInt("room_id"));
                room.setName(result.getString("room_name"));
                service.setRoom(room);
                services.add(service);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }
    
    
    public Hashtable<Integer,ArrayList<Service>> serviceDone() {
        Hashtable<Integer,ArrayList<Service>> services = new Hashtable<>();
        String sql = "SELECT s.[id]\n"
                + "      ,s.[startDate]\n"
                + "      ,s.[endDate]\n"
                + "      ,s.[dateCreate]\n"
                + "      ,s.[dateUpdate]\n"
                + "      ,s.[roomId]\n"
                + "	  ,c.id as 'customer_id'\n"
                + "      ,c.[name] as 'customer_name'\n"
                + "	  ,c.phone_number\n"
                + "	  ,c.gender\n"
                + "	  ,c.cmnd\n"
                + "	  ,c.email\n"
                + "	  ,st.id as 'state_id'\n"
                + "       ,st.[name] as 'state_name'\n"
                + "	  ,r.id as 'room_id'\n"
                + "	  ,r.[name] as 'room_name'\n"
                + "       ,cate.id as cateid \n"
                + "       ,cate.name as catename \n"
                + "       ,cate.price as cateprice \n"
                + "      ,ROW_NUMBER() OVER (ORDER BY [s].[id] DESC) as row_index\n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n"
                + " inner join category as cate on cate.id = r.categoryId \n"
                + " WHERE LOWER(st.[name]) = LOWER(N'Đã trả phòng')";
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
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setName(result.getString("customer_name"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                service.setCustomer(customer);
                State state = new State();
                state.setId(result.getInt("state_id"));
                state.setName(result.getString("state_name"));
                service.setState(state);
                Room room = new Room();
                room.setId(result.getInt("room_id"));
                room.setName(result.getString("room_name"));
                Category category = new Category();
                category.setId(result.getInt("cateid"));
                category.setName(result.getString("catename"));
                category.setPrice(result.getDouble("cateprice"));
                room.setCategory(category);
                service.setRoom(room);
                if(services.get(service.getStart().getMonth())!=null){
                    services.get(service.getStart().getMonth()).add(service);
                }else{
                    ArrayList<Service> list = new ArrayList<>();
                    list.add(service);
                    services.put(service.getStart().getMonth(), list);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }
    
    
    public Hashtable<Integer,ArrayList<Service>> serviceOrder() {
        Hashtable<Integer,ArrayList<Service>> services = new Hashtable<>();
        String sql = "SELECT s.[id]\n"
                + "      ,s.[startDate]\n"
                + "      ,s.[endDate]\n"
                + "      ,s.[dateCreate]\n"
                + "      ,s.[dateUpdate]\n"
                + "      ,s.[roomId]\n"
                + "	  ,c.id as 'customer_id'\n"
                + "      ,c.[name] as 'customer_name'\n"
                + "	  ,c.phone_number\n"
                + "	  ,c.gender\n"
                + "	  ,c.cmnd\n"
                + "	  ,c.email\n"
                + "	  ,st.id as 'state_id'\n"
                + "       ,st.[name] as 'state_name'\n"
                + "	  ,r.id as 'room_id'\n"
                + "	  ,r.[name] as 'room_name'\n"
                + "       ,cate.id as cateid \n"
                + "       ,cate.name as catename \n"
                + "       ,cate.price as cateprice \n"
                + "      ,ROW_NUMBER() OVER (ORDER BY [s].[id] DESC) as row_index\n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n"
                + " inner join category as cate on cate.id = r.categoryId \n"
                + " WHERE LOWER(st.[name]) = LOWER(N'Đặt trước')";
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
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setName(result.getString("customer_name"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                service.setCustomer(customer);
                State state = new State();
                state.setId(result.getInt("state_id"));
                state.setName(result.getString("state_name"));
                service.setState(state);
                Room room = new Room();
                room.setId(result.getInt("room_id"));
                room.setName(result.getString("room_name"));
                Category category = new Category();
                category.setId(result.getInt("cateid"));
                category.setName(result.getString("catename"));
                category.setPrice(result.getDouble("cateprice"));
                room.setCategory(category);
                service.setRoom(room);
                if(services.get(service.getStart().getMonth())!=null){
                    services.get(service.getStart().getMonth()).add(service);
                }else{
                    ArrayList<Service> list = new ArrayList<>();
                    list.add(service);
                    services.put(service.getStart().getMonth(), list);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }

    public ArrayList<Service> getServices(int pageIndex, int pageSize) {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM (SELECT s.[id]\n"
                + "      ,s.[startDate]\n"
                + "      ,s.[endDate]\n"
                + "      ,s.[dateCreate]\n"
                + "      ,s.[dateUpdate]\n"
                + "      ,s.[roomId]\n"
                + "	  ,c.id as 'customer_id'\n"
                + "      ,c.[name] as 'customer_name'\n"
                + "	  ,c.phone_number\n"
                + "	  ,c.gender\n"
                + "	  ,c.cmnd\n"
                + "	  ,c.email\n"
                + "	  ,st.id as 'state_id'\n"
                + "       ,st.[name] as 'state_name'\n"
                + "	  ,r.id as 'room_id'\n"
                + "	  ,r.[name] as 'room_name'\n"
                + "       ,cate.id as cateid \n"
                + "       ,cate.name as catename \n"
                + "       ,cate.price as cateprice \n"
                + "      ,ROW_NUMBER() OVER (ORDER BY [s].[id] DESC) as row_index\n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n"
                + " inner join category as cate on cate.id = r.categoryId \n"
                + " WHERE LOWER(st.[name]) != LOWER(N'Đã trả phòng') AND LOWER(st.[name]) != LOWER(N'Hủy')) [service]\n"
                + " WHERE row_index >= (? - 1) * ? + 1 AND row_index <= ? * ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, pageIndex);
            statement.setInt(2, pageSize);
            statement.setInt(3, pageIndex);
            statement.setInt(4, pageSize);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Service service = new Service();
                service.setId(result.getInt("id"));
                service.setStart(result.getDate("startDate"));
                service.setEnd(result.getDate("endDate"));
                service.setCreate(result.getDate("dateCreate"));
                service.setUpdate(result.getDate("dateUpdate"));
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setName(result.getString("customer_name"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                service.setCustomer(customer);
                State state = new State();
                state.setId(result.getInt("state_id"));
                state.setName(result.getString("state_name"));
                service.setState(state);
                Room room = new Room();
                room.setId(result.getInt("room_id"));
                room.setName(result.getString("room_name"));
                Category category = new Category();
                category.setId(result.getInt("cateid"));
                category.setName(result.getString("catename"));
                category.setPrice(result.getDouble("cateprice"));
                room.setCategory(category);
                service.setRoom(room);
                services.add(service);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }

    public ArrayList<Service> getServices(String search, Date start, Date end) {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT s.[id]\n"
                + "      ,s.[startDate]\n"
                + "      ,s.[endDate]\n"
                + "      ,s.[dateCreate]\n"
                + "      ,s.[dateUpdate]\n"
                + "      ,s.[roomId]\n"
                + "	  ,c.id as 'customer_id'\n"
                + "      ,c.[name] as 'customer_name'\n"
                + "	  ,c.phone_number\n"
                + "	  ,c.gender\n"
                + "	  ,c.cmnd\n"
                + "	  ,c.email\n"
                + "	  ,st.id as 'state_id'\n"
                + "       ,st.[name] as 'state_name'\n"
                + "	  ,r.id as 'room_id'\n"
                + "	  ,r.[name] as 'room_name'\n"
                + "       ,cate.id as cateid \n"
                + "       ,cate.name as catename \n"
                + "       ,cate.price as cateprice \n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n"
                + " inner join category as cate on cate.id = r.categoryId \n"
                + " WHERE (LOWER(st.[name]) != LOWER(N'Đã trả phòng') AND LOWER(st.[name]) != LOWER(N'Hủy')) \n"
                + " AND (LOWER(c.[name]) LIKE (?) OR LOWER(c.[phone_number]) LIKE (?) OR LOWER(c.[cmnd]) LIKE (?) OR LOWER(c.[email]) LIKE (?))\n";
        if (start != null) {
            sql += " AND ((s.[startDate] <= ? AND s.[endDate]>= ?) ";
            if (end != null) {
                sql += " OR (s.[startDate] <= ? AND s.[endDate]>= ?))";
            }else{
                sql+=") \n";
            }
        }else if (end != null) {
            sql += " AND (s.[startDate] <= ? AND s.[endDate]>= ?)";
        }
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");
            statement.setString(3, "%" + search + "%");
            statement.setString(4, "%" + search + "%");
            if (start != null) {
                statement.setDate(5, start);
                statement.setDate(6, start);
                if (end != null) {
                    statement.setDate(7, end);
                    statement.setDate(8, end);
                }
            } else if (end != null) {
                statement.setDate(5, end);
                statement.setDate(6, end);
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Service service = new Service();
                service.setId(result.getInt("id"));
                service.setStart(result.getDate("startDate"));
                service.setEnd(result.getDate("endDate"));
                service.setCreate(result.getDate("dateCreate"));
                service.setUpdate(result.getDate("dateUpdate"));
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setName(result.getString("customer_name"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                service.setCustomer(customer);
                State state = new State();
                state.setId(result.getInt("state_id"));
                state.setName(result.getString("state_name"));
                service.setState(state);
                Room room = new Room();
                room.setId(result.getInt("room_id"));
                room.setName(result.getString("room_name"));
                Category category = new Category();
                category.setId(result.getInt("cateid"));
                category.setName(result.getString("catename"));
                category.setPrice(result.getDouble("cateprice"));
                room.setCategory(category);
                service.setRoom(room);
                services.add(service);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }

    public ArrayList<Service> getCheckkouts() {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT s.[id]\n"
                + "      ,s.[startDate]\n"
                + "      ,s.[endDate]\n"
                + "      ,s.[dateCreate]\n"
                + "      ,s.[dateUpdate]\n"
                + "      ,s.[roomId]\n"
                + "	  ,c.id as 'customer_id'\n"
                + "      ,c.[name] as 'customer_name'\n"
                + "	  ,c.phone_number\n"
                + "	  ,c.gender\n"
                + "	  ,c.cmnd\n"
                + "	  ,c.email\n"
                + "	  ,st.id as 'state_id'\n"
                + "       ,st.[name] as 'state_name'\n"
                + "	  ,r.id as 'room_id'\n"
                + "	  ,r.[name] as 'room_name'\n"
                + "       ,cate.id as cateid \n"
                + "       ,cate.name as catename \n"
                + "       ,cate.price as cateprice \n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n"
                + " inner join category as cate on cate.id = r.categoryId \n"
                + " WHERE LOWER(st.[name]) = LOWER(N'Đã trả phòng') OR LOWER(st.[name]) = LOWER(N'Hủy')";
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
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setName(result.getString("customer_name"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                service.setCustomer(customer);
                State state = new State();
                state.setId(result.getInt("state_id"));
                state.setName(result.getString("state_name"));
                service.setState(state);
                Room room = new Room();
                room.setId(result.getInt("room_id"));
                room.setName(result.getString("room_name"));
                Category category = new Category();
                category.setId(result.getInt("cateid"));
                category.setName(result.getString("catename"));
                category.setPrice(result.getDouble("cateprice"));
                room.setCategory(category);
                service.setRoom(room);
                services.add(service);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }
    
    public ArrayList<Service> getCheckkouts(int pageIndex, int pageSize) {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM(SELECT s.[id]\n"
                + "      ,s.[startDate]\n"
                + "      ,s.[endDate]\n"
                + "      ,s.[dateCreate]\n"
                + "      ,s.[dateUpdate]\n"
                + "      ,s.[roomId]\n"
                + "	  ,c.id as 'customer_id'\n"
                + "      ,c.[name] as 'customer_name'\n"
                + "	  ,c.phone_number\n"
                + "	  ,c.gender\n"
                + "	  ,c.cmnd\n"
                + "	  ,c.email\n"
                + "	  ,st.id as 'state_id'\n"
                + "       ,st.[name] as 'state_name'\n"
                + "	  ,r.id as 'room_id'\n"
                + "	  ,r.[name] as 'room_name'\n"
                + "       ,cate.id as cateid \n"
                + "       ,cate.name as catename \n"
                + "       ,cate.price as cateprice \n"
                + "      ,ROW_NUMBER() OVER (ORDER BY [s].[id] DESC) as row_index\n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n"
                + " inner join category as cate on cate.id = r.categoryId \n"
                + " WHERE LOWER(st.[name]) = LOWER(N'Đã trả phòng') OR LOWER(st.[name]) = LOWER(N'Hủy')) [history]"
                + " WHERE row_index >= (? - 1) * ? + 1 AND row_index <= ? * ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, pageIndex);
            statement.setInt(2, pageSize);
            statement.setInt(3, pageIndex);
            statement.setInt(4, pageSize);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Service service = new Service();
                service.setId(result.getInt("id"));
                service.setStart(result.getDate("startDate"));
                service.setEnd(result.getDate("endDate"));
                service.setCreate(result.getDate("dateCreate"));
                service.setUpdate(result.getDate("dateUpdate"));
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setName(result.getString("customer_name"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                service.setCustomer(customer);
                State state = new State();
                state.setId(result.getInt("state_id"));
                state.setName(result.getString("state_name"));
                service.setState(state);
                Room room = new Room();
                room.setId(result.getInt("room_id"));
                room.setName(result.getString("room_name"));
                Category category = new Category();
                category.setId(result.getInt("cateid"));
                category.setName(result.getString("catename"));
                category.setPrice(result.getDouble("cateprice"));
                room.setCategory(category);
                service.setRoom(room);
                services.add(service);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }
    
    
    public ArrayList<Service> getCheckkouts(String search, Date start, Date end) {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT s.[id]\n"
                + "      ,s.[startDate]\n"
                + "      ,s.[endDate]\n"
                + "      ,s.[dateCreate]\n"
                + "      ,s.[dateUpdate]\n"
                + "      ,s.[roomId]\n"
                + "	  ,c.id as 'customer_id'\n"
                + "      ,c.[name] as 'customer_name'\n"
                + "	  ,c.phone_number\n"
                + "	  ,c.gender\n"
                + "	  ,c.cmnd\n"
                + "	  ,c.email\n"
                + "	  ,st.id as 'state_id'\n"
                + "       ,st.[name] as 'state_name'\n"
                + "	  ,r.id as 'room_id'\n"
                + "	  ,r.[name] as 'room_name'\n"
                + "       ,cate.id as cateid \n"
                + "       ,cate.name as catename \n"
                + "       ,cate.price as cateprice \n"
                + "      ,ROW_NUMBER() OVER (ORDER BY [s].[id] DESC) as row_index\n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n"
                + " inner join category as cate on cate.id = r.categoryId \n"
                + " WHERE (LOWER(st.[name]) = LOWER(N'Đã trả phòng') OR LOWER(st.[name]) = LOWER(N'Hủy')) \n"
                + " AND (LOWER(c.[name]) LIKE (?) OR LOWER(c.[phone_number]) LIKE (?) OR LOWER(c.[cmnd]) LIKE (?) OR LOWER(c.[email]) LIKE (?))\n";
        if (start != null) {
            sql += " AND ((s.[startDate] <= ? AND s.[endDate]>= ?) ";
            if (end != null) {
                sql += " OR (s.[startDate] <= ? AND s.[endDate]>= ?))";
            }else{
                sql+=") \n";
            }
        }else if (end != null) {
            sql += " AND (s.[startDate] <= ? AND s.[endDate]>= ?)";
        }
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");
            statement.setString(3, "%" + search + "%");
            statement.setString(4, "%" + search + "%");
            if (start != null) {
                statement.setDate(5, start);
                statement.setDate(6, start);
                if (end != null) {
                    statement.setDate(7, end);
                    statement.setDate(8, end);
                }
            } else if (end != null) {
                statement.setDate(5, end);
                statement.setDate(6, end);
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Service service = new Service();
                service.setId(result.getInt("id"));
                service.setStart(result.getDate("startDate"));
                service.setEnd(result.getDate("endDate"));
                service.setCreate(result.getDate("dateCreate"));
                service.setUpdate(result.getDate("dateUpdate"));
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setName(result.getString("customer_name"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                service.setCustomer(customer);
                State state = new State();
                state.setId(result.getInt("state_id"));
                state.setName(result.getString("state_name"));
                service.setState(state);
                Room room = new Room();
                room.setId(result.getInt("room_id"));
                room.setName(result.getString("room_name"));
                Category category = new Category();
                category.setId(result.getInt("cateid"));
                category.setName(result.getString("catename"));
                category.setPrice(result.getDouble("cateprice"));
                room.setCategory(category);
                service.setRoom(room);
                services.add(service);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }

    public ArrayList<Service> getServiceSetted(int roomId) {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT s.[id]\n"
                + "      ,s.[startDate]\n"
                + "      ,s.[endDate]\n"
                + "      ,s.[dateCreate]\n"
                + "      ,s.[dateUpdate]\n"
                + "      ,s.[roomId]\n"
                + "	  ,c.id as 'customer_id'\n"
                + "       ,c.[name] as 'customer_name'\n"
                + "	  ,c.phone_number\n"
                + "	  ,c.gender\n"
                + "	  ,c.cmnd\n"
                + "	  ,c.email\n"
                + "	  ,st.id as 'state_id'\n"
                + "       ,st.[name] as 'state_name'\n"
                + "	  ,r.id as 'room_id'\n"
                + "	  ,r.[name] as 'room_name'\n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId"
                + " where r.id = ? and st.name = N'Đặt trước'\n";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Service service = new Service();
                service.setId(result.getInt("id"));
                service.setStart(result.getDate("startDate"));
                service.setEnd(result.getDate("endDate"));
                service.setCreate(result.getDate("dateCreate"));
                service.setUpdate(result.getDate("dateUpdate"));
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setName(result.getString("customer_name"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                service.setCustomer(customer);
                State state = new State();
                state.setId(result.getInt("state_id"));
                state.setName(result.getString("state_name"));
                service.setState(state);
                Room room = new Room();
                room.setId(result.getInt("room_id"));
                room.setName(result.getString("room_name"));
                service.setRoom(room);
                services.add(service);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }

    @Override
    public Service get(int id) {
        String sql = "SELECT s.[id]\n"
                + "      ,s.[startDate]\n"
                + "      ,s.[endDate]\n"
                + "      ,s.[dateCreate]\n"
                + "      ,s.[dateUpdate]\n"
                + "      ,s.[roomId]\n"
                + "	  ,c.id as 'customer_id'\n"
                + "      ,c.[name] as 'customer_name'\n"
                + "	  ,c.phone_number\n"
                + "	  ,c.gender\n"
                + "	  ,c.cmnd\n"
                + "	  ,c.email\n"
                + "	  ,st.id as 'state_id'\n"
                + "       ,st.[name] as 'state_name'\n"
                + "	  ,r.id as 'room_id'\n"
                + "	  ,r.[name] as 'room_name'\n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n"
                + " WHERE s.id = ? ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Service service = new Service();
                service.setId(result.getInt("id"));
                service.setStart(result.getDate("startDate"));
                service.setEnd(result.getDate("endDate"));
                service.setCreate(result.getDate("dateCreate"));
                service.setUpdate(result.getDate("dateUpdate"));
                Customer customer = new Customer();
                customer.setId(result.getInt("customer_id"));
                customer.setName(result.getString("customer_name"));
                customer.setCmnd(result.getString("cmnd"));
                customer.setEmail(result.getString("email"));
                customer.setGender(result.getBoolean("gender"));
                customer.setPhone(result.getString("phone_number"));
                service.setCustomer(customer);
                State state = new State();
                state.setId(result.getInt("state_id"));
                state.setName(result.getString("state_name"));
                service.setState(state);
                Room room = new Room();
                room.setId(result.getInt("room_id"));
                room.setName(result.getString("room_name"));
                service.setRoom(room);
                return service;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Service service) {
        String sql = "INSERT INTO [service]\n"
                + "           ([startDate]\n"
                + "           ,[endDate]\n"
                + "           ,[dateCreate]\n"
                + "           ,[dateUpdate]\n"
                + "           ,[roomId]\n"
                + "           ,[customerId]\n"
                + "           ,[stateId])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
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
        } finally {
            if (stm != null) {
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
        String sql = "UPDATE [dbo].[service]\n"
                + "   SET [startDate] = ?\n"
                + "      ,[endDate] = ?\n"
                + "      ,[dateCreate] = ?\n"
                + "      ,[dateUpdate] = ?\n"
                + "      ,[roomId] = ?\n"
                + "      ,[customerId] = ?\n"
                + "      ,[stateId] = ?\n"
                + " WHERE id=?";
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
        } finally {
            if (stm != null) {
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
        String sql = "DELETE FROM [service]\n"
                + "      WHERE id=?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
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

    public int getSize() {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT COUNT(s.[id]) as 'size'\n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n"
                + " inner join category as cate on cate.id = r.categoryId \n"
                + " WHERE LOWER(st.[name]) != LOWER(N'Đã trả phòng') AND LOWER(st.[name]) != LOWER(N'Hủy')";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("size");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int getSizeCheckout() {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT COUNT(s.[id]) as 'size'\n"
                + "  FROM [service] s inner join customer c \n"
                + "  on s.customerId = c.id \n"
                + "  inner join [state] st\n"
                + "  on s.stateId = st.id\n"
                + "  inner join room as r on r.id = s.roomId\n"
                + " inner join category as cate on cate.id = r.categoryId \n"
                + " WHERE LOWER(st.[name]) = LOWER(N'Đã trả phòng') OR LOWER(st.[name]) = LOWER(N'Hủy')";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("size");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
