/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.model;

/**
 *
 * @author acer
 */
public class SensorReading implements BaseModel {
    
    private String id;
    private long timestamp;
    private double value;
    
    public SensorReading(){}
    
    public SensorReading(String id, long timeStamp,double value){
        this.id=id;
        this.timestamp=timeStamp;
        this.value=value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }
    
}
