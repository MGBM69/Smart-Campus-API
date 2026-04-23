/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author acer
 */

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable>  {

    @Override
    public Response toResponse(Throwable e) {
        String errorJson = "{ \"status\": 500, \"error\": \"Internal Server Error. Please contact support.\" }";

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorJson)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
