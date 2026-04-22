/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.dao;

import com.smartcampus.api.model.Room;
import com.smartcampus.api.model.Sensor;
import com.smartcampus.api.model.SensorReading;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class MockDataBase {
    public static final List<Room> roomList= new ArrayList<>();
    
    public static final List<Sensor> sensorList= new ArrayList<>();
    
    public static final List<SensorReading> sensorReadingList= new ArrayList<>();
    
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
        //http://localhost:8080/Smart-Campus-API/api/v1/sensors?type=Temparature get same type aka "Temparature" sensors using query parameter(GET Request)
        //http://localhost:8080/Smart-Campus-API/api/v1/sensors create sensor and add to specific room (POST Request)
        
        //Excisting Sensors T001- 'LAB-102',  T002- 'CAF-106', T003-'CAF-106', T004-'SRU-005', AIR001-'SRU-005'
        
        
        //To check specific Sensor reading 
        Room SRU= new Room("SRU-005","Student Relation Unit",10);
        Sensor Air001= new Sensor("AIR-001","Air Quality","ACTIVE",7,"SRU-005");
        Sensor T004= new Sensor("TEMP-004","Temparature","ACTIVE",19,"SRU-005");
        sensorList.add(Air001);
        sensorList.add(T004);
        
        SRU.getSensorIds().add(Air001.getId());
        SRU.getSensorIds().add(T004.getId());
        
        //make time differance for sensor reading
        long currentTime  = System.currentTimeMillis();
        long oneHourAgo   = currentTime - (60 * 60 * 1000);
        
        SensorReading airreading1= new SensorReading("R001",oneHourAgo,8,"AIR-001");
        SensorReading airreading2= new SensorReading("R002",currentTime,9,"AIR-001");
        
        SensorReading tempreading= new SensorReading("R003",currentTime,24,"TEMP-004");
        
        sensorReadingList.add(tempreading);
        sensorReadingList.add(airreading1);
        sensorReadingList.add(airreading2);
        
        //http://localhost:8080/Smart-Campus-API/api/v1/sensors/AIR-001/reading sensor readings output (GET Request)
        //http://localhost:8080/Smart-Campus-API/api/v1/sensors/TEMP-005/reading sensor not found output(GET Request)
        //http://localhost:8080/Smart-Campus-API/api/v1/sensors/AIR-001/reading create sensor reading for specific sensor (POST Request)
        //http://localhost:8080/Smart-Campus-API/api/v1/rooms/LAB-102 check 409 exception (@DELETE method ) deleting room with active Sensors.
        
    }
}
