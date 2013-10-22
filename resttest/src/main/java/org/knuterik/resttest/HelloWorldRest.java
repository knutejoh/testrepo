/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.resttest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author martin
 */
@Path("helloworld")
@Stateless
public class HelloWorldRest {

    @Context 
    private UriInfo context;
    
    @PersistenceContext
    EntityManager em;
    
    public HelloWorldRest() {
        
        
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public MyRestClass getJSON(@Context SecurityContext  req) {
        
        if (req != null) {
            Principal user = req.getUserPrincipal();
            if (user != null) {
                System.out.println("Vi har bruker!! " + req.getUserPrincipal().getName());
            } else {
                System.out.println("Vi har anonym bruker... :(");
            }
            
            
        } else {
            System.out.println("Vi har IKKE req!!");
        }
        
        for (MyRestClass boss : createBosses()) {
            em.persist(boss);
        }
        MyRestClass found = em.find(MyRestClass.class, new Long(1));
        MyRestClass response = new MyRestClass();
        response.setAddress("Kjelsåsveien 1B");
        response.setAge(33);
        response.setName("Knut-Erik Johnsen");
        return response;
    }

    
    private List<MyRestClass> createBosses() {
        List<MyRestClass> bosses = new ArrayList<MyRestClass>();
        MyRestClass boss = new MyRestClass();
        boss.setAddress("Sentrum");
        boss.setAge(40);
        boss.setName("Thomas Johansen");
        bosses.add(boss);
        
        boss = new MyRestClass();
        boss.setAddress("Bortivekkistan");
        boss.setAge(37);
        boss.setName("Jon Fagerholm");
        bosses.add(boss);
        
        boss = new MyRestClass();
        boss.setAddress("Kværnerbyen");
        boss.setAge(31);
        boss.setName("Marius Torsrud");
        bosses.add(boss);
        
        boss = new MyRestClass();
        boss.setAddress("Kjelsåsveien 1B");
        boss.setAge(33);
        boss.setName("Knut-Erik Johnsen");
        bosses.add(boss);
        return bosses;
    }
    
}
