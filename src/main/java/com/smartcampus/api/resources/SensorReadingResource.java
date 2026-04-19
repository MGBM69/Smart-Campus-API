/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.dao.GenericDAO;
import com.smartcampus.api.dao.MockDataBase;
import com.smartcampus.api.model.Sensor;
import com.smartcampus.api.model.SensorReading;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author acer
 */
public class SensorReadingResource {
    
    private String sensorId;
    private GenericDAO<SensorReading> sensorReadingDAO= new GenericDAO<>(MockDataBase.sensorReadingList);
    private GenericDAO<Sensor> sensorDAO= new GenericDAO<>(MockDataBase.sensorList);
    
    public SensorReadingResource(String id){
        this.sensorId=id;
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadingForRequestedSensor(){
        //validation
        Sensor sensor= sensorDAO.getById(sensorId);
        if(sensor==null){
            return Response.status(Response.Status.NOT_FOUND).entity("Sensor id is not found! NO Sensor Reading").build();
        }
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
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReadingForSpecificSensor(SensorReading reading, @Context UriInfo uriInfo){
        
        Sensor sensor= sensorDAO.getById(sensorId);
        if(sensor==null){
            return Response.status(Response.Status.NOT_FOUND).entity("Sensor id is not found! NO Sensor Reading add. create Sensor First").build();
        }
        if(reading==null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Atleast Sensor Reading is Required!").build();
        }
        reading.setSensorId(sensorId); //user no need to add sensorId as json response it is coming from URL
        if(reading.getTimestamp()==0){
            reading.setTimestamp(System.currentTimeMillis());// if time is not set it will set auto matically!
        }
        sensorReadingDAO.add(reading);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(reading.getId())
                .build();
        
        return Response.created(uri).entity(reading).build();
        
    }
    
}
