/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.dao.GenericDAO;
import com.smartcampus.api.dao.MockDataBase;
import com.smartcampus.api.model.Room;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room, @Context UriInfo uriInfo) {
    
    // Validation
    if (room.getId() == null || room.getName() == null) {
        return Response.status(400)
                .entity("Room ID and Name are required")
                .build();
    }

    try {
        Room createdRoom = roomsDAO.add(room);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(createdRoom.getId())
                .build();

        return Response.created(uri)
                .entity(createdRoom)
                .build();

    } catch (RuntimeException e) {
        return Response.status(409)
                .entity(e.getMessage())
                .build();
    }
}
    
    @GET
    @Path("/{roomId}")
    public Response getRoomById(@PathParam("roomId") String roomId){
        
        Room room = roomsDAO.getById(roomId);
        
        if(room == null){
          return Response.status(Response.Status.NOT_FOUND).entity("Room not found!").build();
        }
        
        return Response.ok(room,MediaType.APPLICATION_JSON).build();
    }
    
    
    
    
}
