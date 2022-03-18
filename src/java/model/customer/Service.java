/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.customer;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.concurrent.TimeUnit;
import model.room.Room;

/**
 *
 * @author hieu2
 */
public class Service {
    private int id;
    private Room room;
    private Customer customer;
    private State state;
    private Date start;
    private Date end;
    private Date create;
    private Date update;
    private double price;

    public double getPrice() {
        return price;
    }
    public BigDecimal getTotal(){
        long numDay = TimeUnit.MILLISECONDS.toDays(end.getTime() - start.getTime());
        double p = room.getCategory().getPrice();
        BigDecimal total = new BigDecimal(p*numDay);
        return  total;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

}
