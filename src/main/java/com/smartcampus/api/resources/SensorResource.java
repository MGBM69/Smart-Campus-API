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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    
}
