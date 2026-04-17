/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.dao;

import com.smartcampus.api.model.Room;
import com.smartcampus.api.model.Sensor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class MockDataBase {
    public static final List<Room> roomList= new ArrayList<>();
    
    public static final List<Sensor> sensorList= new ArrayList<>();
    
    static{
        // Add Rooms 
        roomList.add(new Room("LIB-100","Library",50));// emty sensor room
        
        //with Active sensors
        Room newRoom=new Room("LAB-102","Laboratory",75);
        Sensor T001=new Sensor("TEMP-001","Temparature","ACTIVE",24,"LAB-102");
        sensorList.add(T001);
        newRoom.getSensorIds().add(T001.getId());
        
        roomList.add(newRoom);
        
        // without Active Sensors
        Room cafe=new Room("CAF-106","Cafateria",100);
        Sensor T002=new Sensor("TEMP-002","Temparature","INACTIVE",24,"CAF-106");
        Sensor T003=new Sensor("TEMP-003","Temparature","INACTIVE",24,"CAF-106");
        sensorList.add(T002);
        sensorList.add(T003);
        cafe.getSensorIds().add(T002.getId());
        cafe.getSensorIds().add(T003.getId());
        
        roomList.add(cafe);
    }
}
