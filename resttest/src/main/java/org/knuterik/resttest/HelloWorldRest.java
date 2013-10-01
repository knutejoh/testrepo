/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.resttest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author martin
 */
@Path("helloworld")
public class HelloWorldRest {

    @Context 
    private UriInfo context;
    
    public HelloWorldRest() {
        
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public MyRestClass getJSON() {
        MyRestClass response = new MyRestClass();
        response.setAddress("Kjelsåsveien 1B");
        response.setAge(33);
        response.setName("Knut-Erik Johnsen");
        return response;
    }

}
