/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author acer
 */
public class LinkedResourceNotFoundExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException> {

    @Override
    public Response toResponse(LinkedResourceNotFoundException e) {
        String errorJson = "{ \"status\": 422, \"error\": \"" + e.getMessage() + "\" }";

        return Response.status(422) // or Response.Status.BAD_REQUEST
                .entity(errorJson)
                .type(MediaType.APPLICATION_JSON)
                .build();

    }
    
}
