/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.room;

/**
 *
 * @author hieu2
 */
public class Room {
    private int id;
    private String name;
    private int floor;
    private int categoryId;
    private int roomstateId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRoomstateId() {
        return roomstateId;
    }

    public void setRoomstateId(int roomstateId) {
        this.roomstateId = roomstateId;
    }
    
}
