/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class Room implements BaseModel{
    
    private String id;
    private String name;
    private int capacity;
    private List<String> sensorIds;
    
    
    public Room(){}
    
    public Room(String id, String name, int capacity){
        this.id=id;
        this.name=name;
        this.capacity=capacity;
        sensorIds=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getSensorIds() {
        return sensorIds;
    }

    public void setSensorIds(List<String> sensorIds) {
        this.sensorIds = sensorIds;
    }
    
    

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        
    }
    
}
