/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.service.rest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.joda.time.DateTime;
import org.knuterik.data.LottoDrawing;
import org.knuterik.data.LottoDrawingDetails;
import org.knuterik.data.Status;
import org.knuterik.repository.LottoDrawRepository;
import org.knuterik.retrievers.LottoRetriever;
import org.knuterik.retrievers.xmldto.LottoDrawDTO;
import org.knuterik.retrievers.xmldto.LottoDrawListDTO;
import org.knuterik.retrievers.xmldto.LottoDrawingDetailDTO;
import org.knuterik.retrievers.xmldto.LottoItemDTO;

/**
 * REST Web Service
 *
 * @author knut-erik.johnsen
 */
@Path("lotto")
@Stateless
public class LottoService {
    
    @Inject
    private LottoRetriever lottoRetriever;
   
    @Inject
    private LottoDrawRepository lottoRepository;
    
    public LottoService() {
    }
    
    @GET
    @Path("ping")
    public String ping() {
        return "alive";
    }
    
    @GET
    @Path("drawlist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrawList() {
        
        return Response.ok(lottoRepository.getAllLottoDraws()).build();
    }
    
    @GET
    @Path("drawlistsmart")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrawListSmart() {
        
        List<LottoDrawing> drawings = lottoRepository.getAllLottoDrawsSorted();
        Map<String, Map<String, List<String>>> totalMap = new HashMap<>();
        
        
        for (LottoDrawing drawing : drawings) {
            if (drawing.getDrawDetails() != null) {
                String year = "" + drawing.getDrawDate().getYear();
                Map<String, List<String>> yearMap = totalMap.get(year);
                if (yearMap == null) {
                    yearMap = new HashMap<>();
                }

                String month = ""  + drawing.getDrawDate().getMonthOfYear();
                List<String> maaned = yearMap.get(month);
                if (maaned == null) {
                    maaned = new ArrayList<>();
                }

                maaned.add("" + drawing.getDrawDate().getDayOfMonth());

                yearMap.put(month, maaned);
                totalMap.put(year, yearMap);
            }
        }
        
//        List<List<List<String>String>String> data = List<<<>>>();
        
        return Response.ok(totalMap).build();
    }
    

    @GET
    @Path("drawinfo/{drawid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrawInfo(@PathParam("drawid") Long drawId) {
 
        LottoDrawing drawing = lottoRepository.getLottoDraw(drawId);
        if (drawing != null && drawing.getDrawDetails() != null) {
            return Response.ok(drawing.getDrawDetails()).build();
        }
        return Response.ok().build();
    }
 
    @GET
    @Path("admin/updatedrawlist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDrawList(@Context HttpServletRequest req) {
        Status status = new Status();
        if (! req.isUserInRole("admin")) {
            
        } else {
            
        }
 
        LottoDrawListDTO drawList = lottoRetriever.getLottoDrawings();
        
        List<Long> ids = lottoRepository.getAllLottoIDs();
        
        for (LottoItemDTO item : drawList.getItems()) {
            for (LottoDrawDTO drawDTO : item.getDraws()) {
                LottoDrawing drawing;
                
                if (ids.contains(drawDTO.getDrawID())) {
                    drawing = lottoRepository.getLottoDraw(drawDTO.getDrawID());
                } else {
                    drawing = lottoRepository.createLottoDraw(drawDTO.getDrawID(), new DateTime(drawDTO.getDato()));
                }
                
                if (drawing != null && drawing.getDrawDetails() == null && drawing.getId() < 10000) {
                    LottoDrawingDetailDTO detailsDTO = lottoRetriever.getLottoDrawingDetails(drawing.getId());
                    
                    if (detailsDTO != null && detailsDTO.getDrawID().equals(drawing.getId())) {
                        LottoDrawingDetails details = LottoDrawingDetails.fromDTO(detailsDTO);
                        details.setDrawing(drawing);
                        drawing.setDrawDetails(details);
                    } else {
                        System.out.println("Fant ikke matchende drawdetails: DrawID: " + drawing.getId() + " Details: " + (detailsDTO == null ? null : detailsDTO.getDrawID()));
                    }
                }
//                break;
                
            }
            
        }
        
//        json.setData(newUser); //just return the date we received
        
        
        return Response.ok().entity(status).build();
    }
}
