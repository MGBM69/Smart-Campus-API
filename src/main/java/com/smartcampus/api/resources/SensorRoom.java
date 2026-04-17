/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.dao.GenericDAO;
import com.smartcampus.api.dao.MockDataBase;
import com.smartcampus.api.model.Room;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author acer
 */

@Path("/rooms")
public class SensorRoom {
    private GenericDAO<Room> roomsDAO= new GenericDAO<>(MockDataBase.roomList);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(){
        return roomsDAO.getAll();
    
    }
    
    @GET
    @Path("/{roomId}")
    public Room getRoomById(@PathParam("roomId") String roomId){
        
        return roomsDAO.getById(roomId);
    }
    
    
    
}
