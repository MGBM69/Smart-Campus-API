/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.dao.GenericDAO;
import com.smartcampus.api.dao.MockDataBase;
import com.smartcampus.api.model.Room;
import com.smartcampus.api.model.Sensor;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author acer
 */

@Path("/sensors")
public class SensorResource {
    
    private GenericDAO<Sensor> sensorDAO= new GenericDAO<>(MockDataBase.sensorList);
    private GenericDAO<Room> roomsDAO= new GenericDAO<>(MockDataBase.roomList);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSensor(Sensor sensor, @Context UriInfo uriInfo ){
        //check whether user entered enough sensor details 
        if(sensor.getId()== null || sensor.getType()== null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Sensor ID and Sensor Type required!").build();
        }
        Room room = roomsDAO.getById(sensor.getRoomId());
        // Check Sensor is null
        if(room == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Room not found where you are trying to add new sensor!").build();
        }
        
//        boolean excistingRoom=false;
//        List <Room> roomList= roomsDAO.getAll();
//        for(int i=0;i<roomList.size();i++){
//            if(roomList.get(i).getId().equals(sensor.getRoomId())){
//                excistingRoom=true;
//                break;
//            }
//        }
        
        
            try{
                Sensor createdSensor=sensorDAO.add(sensor);
                //Adding sensor to its respective room's sensor id list
                
                room.getSensorIds().add(createdSensor.getId());
                
                URI uri = uriInfo.getAbsolutePathBuilder()
                .path(createdSensor.getId())
                .build();

        return Response.created(uri)
                .entity(createdSensor+":  Sensor created successfully and added room no :"+createdSensor.getRoomId()+" sensor list")
                .build();
                
             
                }catch(RuntimeException e){
                    return Response.status(409).entity(e.getMessage()).build();
                    
                }
            
        
        
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequestedSensor(@QueryParam("type") String type){
        List<Sensor> sensorList=sensorDAO.getAll();
        if(type == null || type.isEmpty()){
            return Response.ok(sensorList).build();
        }
        
        HashSet<Sensor> sameTypeSensors= new HashSet<>(); //Because of one Sensor should add one time hashset stores unique values only
        for(int i=0;i<sensorList.size();i++){
            String sensorType=sensorList.get(i).getType();
            if(type.equalsIgnoreCase(sensorType)){
                sameTypeSensors.add(sensorList.get(i));
            }
        }
        
        return Response.ok(sameTypeSensors).build();
        
    }
    
    @Path("/{sensorId}/reading")
    public SensorReadingResource getSensorReadingResourse(@PathParam ("sensorId") String sensorId){
        SensorReadingResource sensorReadingResource= new SensorReadingResource(sensorId);
        return sensorReadingResource;
    }
    
}
