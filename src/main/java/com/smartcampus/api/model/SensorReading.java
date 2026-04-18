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
    private String sensorId;
    
    public SensorReading(){}
    
    public SensorReading(String id, long timeStamp,double value,String sensorId){
        this.id=id;
        this.timestamp=timeStamp;
        this.value=value;
        this.sensorId=sensorId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
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
