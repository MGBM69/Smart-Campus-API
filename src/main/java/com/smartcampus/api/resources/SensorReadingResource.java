/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.dao.GenericDAO;
import com.smartcampus.api.dao.MockDataBase;
import com.smartcampus.api.model.SensorReading;

/**
 *
 * @author acer
 */
public class SensorReadingResource {
    
    private String sensorId;
    private GenericDAO<SensorReading> sensorReadingDAO= new GenericDAO<>(MockDataBase.sensorReadingList);
    
    public SensorReadingResource(String id){
        this.sensorId=id;
    }
    
}
