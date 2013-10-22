/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.service.rest;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.knuterik.data.Group;
import org.knuterik.data.LoginUserRequest;
import org.knuterik.data.Status;
import org.knuterik.data.User;
import org.knuterik.repository.InitialData;
import org.knuterik.repository.UserRepository;

/**
 * REST Web Service
 *
 * @author knut-erik.johnsen
 */
@Path("auth")
@Stateless
public class AuthService {

    private static boolean addedTestData = false;
    
    @Inject
    private UserRepository userRepo;
    
    @Inject 
    private InitialData data;
    
    public AuthService() {
    }
    
    @GET
    @Path("ping")
    public String ping() {
        return "alive";
    }
    
    @GET
    @Path("timeout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response timeout(@Context HttpServletRequest req) {
        req.getSession();
        Status status = new Status();
        status.setStatus("OK");
        status.setMessage("TIMEOUT AVOIDED");

        return Response.ok().entity(status).build();
    }
    
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginUserRequest userRequest, @Context HttpServletRequest req) {
        
        req.getSession();
        
        if(req.getUserPrincipal() == null){
            try {
                req.login(userRequest.getUsername(), userRequest.getPassword());
                req.getServletContext().log("Authentication Demo: successfully logged in " + userRequest.getUsername());
            } catch (Exception e) {
//                e.printStackTrace();
                Status status = new Status();
                status.setStatus("FAILED");
                status.setMessage("Authentication failed");
                if (!addedTestData) {
                    data.addTestData();
                    addedTestData = true;
                }
                
                return Response.serverError().entity(status).build();
            }
        }else{
            req.getServletContext().log("Skip logged because already logged in: " +userRequest.getUsername());
        }
        
        
        User user = userRepo.getUser(userRequest.getUsername());
        
        return Response.ok(user).build();
    }
    

    @GET
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpServletRequest req) {
 
        Status status = new Status();
 
        try {
            req.logout();
            status.setStatus("SUCCESS");
            req.getSession().invalidate();
        } catch (ServletException e) {
            e.printStackTrace();
            status.setStatus("FAILED");
            status.setMessage("Logout failed on backend");
        }
        return Response.ok().entity(status).build();
    }
 
    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(User newUser, @Context HttpServletRequest req) {
 
        
//        json.setData(newUser); //just return the date we received
        Status status = new Status();
        //do some validation (in reality you would do some more validation...)
        //by the way: i did not choose to use bean validation (JSR 303)
        if (newUser.getPassword().length() == 0 || !newUser.getPassword().equals(newUser.getPassword())) {
            status.setMessage("Both passwords have to be the same - typo?");
            status.setStatus("FAILED");
            return Response.ok().entity(status).build();
        }
 
        User user = new User();
 
        List<Group> groups = new ArrayList<>();
        groups.add(Group.ADMINISTRATOR);
        groups.add(Group.USER);
        groups.add(Group.DEFAULT);
        user.setGroups(groups);
 
        //this could cause a runtime exception, i.e. in case the user already exists
        //such exceptions will be caught by our ExceptionMapper, i.e. javax.transaction.RollbackException
//        userBean.save(user); // this would use the clients transaction which is committed after save() has finished
        req.getServletContext().log("successfully registered new user: '" + newUser.getEmail() + "':'" + newUser.getPassword() + "'");
 
        req.getServletContext().log("execute login now: '" + newUser.getEmail() + "':'" + newUser.getPassword() + "'");
        try {
            req.login(newUser.getEmail(), newUser.getPassword());
            status.setStatus("SUCCESS");
        } catch (ServletException e) {
            e.printStackTrace();
            status.setMessage("User Account created, but login failed. Please try again later.");
            status.setStatus("FAILED"); //maybe some other status? you can choose...
        }
         
        return Response.ok().entity(status).build();
    }
}
