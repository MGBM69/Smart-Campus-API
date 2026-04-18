/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.dao.GenericDAO;
import com.smartcampus.api.dao.MockDataBase;
import com.smartcampus.api.model.SensorReading;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadingForRequestedSensor(){
        List<SensorReading> allReadings=sensorReadingDAO.getAll();
        List<SensorReading> readingsForSpecificSensor= new ArrayList<>();
        HashMap <String,List<SensorReading>> map= new HashMap<>();
        
        for(SensorReading reading: allReadings){
            String id=reading.getSensorId();
            if(id.equals(sensorId)){
                readingsForSpecificSensor.add(reading);
                
            }
        }
        
        map.put(sensorId, readingsForSpecificSensor);
        return Response.ok(map).build();
    }
    
}
