/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.retrievers;

import java.util.Calendar;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.knuterik.retrievers.interceptors.NorskTippingInterceptor;
import org.knuterik.retrievers.xmldto.LottoDrawListDTO;
import org.knuterik.retrievers.xmldto.LottoDrawingDetailDTO;
import org.knuterik.retrievers.xmldto.LottoItemDTO;

/**
 *
 * @author knut-erik.johnsen
 */
@Stateless
public class LottoRetriever {
    
    
    
    public LottoDrawListDTO getLottoDrawings() {
        
        WebTarget api = getLottoAPI();
        
        
        final String mediaType = MediaType.APPLICATION_JSON;
        
        
        LottoDrawListDTO result = api.path("getDrawList.json").request(mediaType).get(LottoDrawListDTO.class);
        
        if (result != null) {
            for ( LottoItemDTO item : result.getItems()) {
                uncompressItem(item);
                System.out.println("Antall trekninker: " + item.getDraws().size());
            }
        }
        
//        System.out.println("Se hva vi fikk: " + result.getItems().size());
        return result;
    }
    
    public LottoDrawingDetailDTO getLottoDrawingDetails(Long id) {
        
        WebTarget api = getLottoAPI();
        
        
        final String mediaType = MediaType.APPLICATION_JSON;
        
        
        LottoDrawingDetailDTO result = api.path("getResultInfo.json")
                .queryParam("gameID", "1")
                .queryParam("drawID", id)
                .request(mediaType)
                .get(LottoDrawingDetailDTO.class);
        
        if (result != null && result.getSuperlottoResult() != null) {
            if (! result.getDrawID().equals(result.getSuperlottoResult().getDrawID())) {
                result.setSuperlottoResult(null);
            }
        }
//        System.out.println("Se hva vi fikk: " + result);
        return result;
    }
    
    
    private void uncompressItem(LottoItemDTO item) {
        
        
        String drawData = item.getDrawData();
        int firstYear = item.getFirstYear();                
        int gameIDString  = item.getGameID();
        
        int m, g, h = 9;
        
        
        // drawlist.tmp_firstYear = firstYear;
        g = -1;
        String[] years = drawData.split(LottoItemDTO.YEAR_SEPARATOR);
        /*
        for (var a = 0; a < drawlist.dateTable.length; a++) {
            var e = drawlist.dateTable[a], 
            var h = drawlist.firstYear - a, 
            var c = {label: String(h), value: []};
            for (var d = 0; d < e.length; d++) {
                var i = e[d], 
                var f = e.length - d;
                if (i.length == 0) {
                    continue
                }
                c.value.push({label: drawlist.MONTH_TABLE[e.length - (d + 1)]});
                for (var l = 0; l < i.length; l++) {
                    var b = i[l], 
                    var k = new Date(h, f - 1, b), 
                    j = com.formatDatePattern(k, "EEE dd/MM");
                    
                }
            }
            g.push(c)
        }
        */
        
        
        int currentYear = firstYear;
        
        for (String year : years) {
//            System.out.println("Behandler year: " + year);
            String[] months = year.split(LottoItemDTO.MONTH_SEPARATOR);
            
            int currentMonth = months.length;
            
            for (String month : months) {
//                System.out.println("Behandler month: " + month);
                
                m = 0;
                while (m < month.length()) {
                    int character = month.codePointAt(m) - LottoItemDTO.DATE_OFFSET;
                    
                    
                    m++;
                    if (character > 31) {
                        character -= LottoItemDTO.DRAW_ID_DELTA;
                        g = Integer.parseInt(month.substring(m, m + h), 16);
                        m += h;
                    }
                    String drawID = Integer.toString(g);
                    Calendar drawDate = Calendar.getInstance();
                    drawDate.clear();
                    drawDate.set(Calendar.YEAR, currentYear);
//                    System.out.println("Month feltet er: " + currentMonth);
                    drawDate.set(Calendar.MONTH, currentMonth - 1);
                    drawDate.set(Calendar.DAY_OF_MONTH, character);
                    
                    
                    item.addDraw(drawDate.getTime(), drawID);
                    
                    if (g < 0) {
                        g++;
                    } else {
                        g--;
                    }
                   
                    
                }
                currentMonth--;
            }
            currentYear--;
        }
       /*
        drawlist.tmp_drawIDTable = new Array(drawlist.tmp_dateTable.length);
        drawlist.tmp_gameIDTable = new Array(drawlist.tmp_dateTable.length);
        for (e = 0; e < drawlist.tmp_dateTable.length; e++) {
            drawlist.tmp_dateTable[e] = drawlist.tmp_dateTable[e].split(drawlist.MONTH_SEPARATOR);
            if (typeof(drawlist.tmp_dateTable[e].splice) != "function") {
                while (drawlist.tmp_dateTable[e].length < 12) {
                    drawlist.tmp_dateTable[e] = drawlist.insert(drawlist.tmp_dateTable[e], 0, [new Array()])
                }
            } else {
                while (drawlist.tmp_dateTable[e].length < 12) {
                    drawlist.tmp_dateTable[e].splice(0, 0, new Array())
                }
            }
            drawlist.tmp_drawIDTable[e] = new Array(drawlist.tmp_dateTable[e].length);
            drawlist.tmp_gameIDTable[e] = new Array(drawlist.tmp_dateTable[e].length);
            for (d = 0; d < drawlist.tmp_dateTable[e].length; d++) {
                l = drawlist.tmp_dateTable[e][d];
                drawlist.tmp_dateTable[e][d] = new Array();
                drawlist.tmp_drawIDTable[e][d] = new Array();
                drawlist.tmp_gameIDTable[e][d] = new Array();
                c = 0;
                m = 0;
                while (m < l.length) {
                    drawlist.tmp_dateTable[e][d][c] = l.charCodeAt(m) - drawlist.DATE_OFFSET;
                    m++;
                    if (drawlist.tmp_dateTable[e][d][c] > 31) {
                        drawlist.tmp_dateTable[e][d][c] -= drawlist.DRAW_ID_DELTA;
                        g = parseInt(l.substring(m, m + h), 16);
                        m += h
                    }
                    drawlist.tmp_drawIDTable[e][d][c] = String(g);
                    drawlist.tmp_gameIDTable[e][d][c] = gameID;
                    if (g < 0) {
                        g++;
                    } else {
                        g--;
                    }
                    c++;
                }
            }*/
        }
    
    
        private WebTarget root;
    
        private WebTarget getLottoAPI() {
            if (root == null) {
                Client client = ClientBuilder.newClient().register(NorskTippingInterceptor.class);
                root = client.target("https://www.norsk-tipping.no/api-lotto");
            }
            return root;
        }
    
}
