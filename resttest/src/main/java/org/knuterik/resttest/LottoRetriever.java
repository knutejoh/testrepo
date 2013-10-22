/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.resttest;

import org.knuterik.resttest.entities.LottoItem;
import org.knuterik.resttest.entities.LottoDrawList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.knuterik.resttest.entities.LottoItem.LottoDraw;



/**
 *
 * @author knut-erik.johnsen
 */
@Path("lotto")
@Stateless
public class LottoRetriever {
    
    public static void main(String args[]) throws Exception {
//        Class jsonProvider = Class.forName("org.glassfish.jersey.moxy.json.MoxyJsonFeature");
        LottoRetriever retriever = new LottoRetriever();
        retriever.getJSON();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public String getJSON() throws Exception {
        HttpClient client = HttpClients.createDefault();
        String ting = "https://www.norsk-tipping.no/api-lotto/getResultInfo.json?gameID=1&drawID=900";
        HttpGet request = new HttpGet("https://www.norsk-tipping.no/api-lotto/getDrawList.json");
	HttpResponse response = client.execute(request);
	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String returnData = "";
        String line = "";
        int lineNr = 0;
        while ((line = rd.readLine()) != null) {
            lineNr++;
            if (lineNr == 2) {
                returnData = line;
                haveFunWithJSON(returnData);
            }
//            System.out.println(line);
        }
        return returnData;
    }
    
    
    
    private void haveFunWithJSON(String jsonString) throws Exception {
//        Map<String, Object> properties = new HashMap<String, Object>(1);
//        MOXyJsonProvider
//        properties.put();
        
        JAXBContext jc = JAXBContext.newInstance("org.knuterik.resttest.entities");
        

        if (jc instanceof org.eclipse.persistence.jaxb.JAXBContext) {
            System.out.println("HURRA!!");
        } else {
            System.out.println("DYNGE!!");
        }
        
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        unmarshaller.setProperty("eclipselink.json.include-root", false);
        unmarshaller.setProperty(JAXBContextProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
        StringReader reader = new StringReader(jsonString);
        Source jsonSource = new StreamSource(reader);
        LottoDrawList drawList = unmarshaller.unmarshal(jsonSource, LottoDrawList.class).getValue();
        
        for (LottoItem item : drawList.getItems()) {
            System.out.println(item.toString());
            uncompressItem(item);
            
            System.out.println("Got " + item.getDraws().size() + " draws");
            int count = 0;
            for (LottoDraw draw : item.getDraws()) {
                
                if (count == 909) {
                    System.out.println("Getting details for: " + draw.getDrawID() + " med dato: " + draw.getDato());
                    String drawDetails = getDrawDetails(draw.getDrawID());
                    System.out.println("Details: " + drawDetails);
                    break;
                }
                count++;
            }
        }

//        Marshaller marshaller = jc.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(objectA, System.out);
    }
    
    
    
    private void uncompressItem(LottoItem item) {
        
        
        String drawData = item.getDrawData();
        int firstYear = item.getFirstYear();                
        int gameIDString  = item.getGameID();
        
        int m, g, h = 9;
        
        
        // drawlist.tmp_firstYear = firstYear;
        g = -1;
        String[] years = drawData.split(LottoItem.YEAR_SEPARATOR);
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
            String[] months = year.split(LottoItem.MONTH_SEPARATOR);
            
            int currentMonth = months.length;
            
            for (String month : months) {
//                System.out.println("Behandler month: " + month);
                
                m = 0;
                while (m < month.length()) {
                    int character = month.codePointAt(m) - LottoItem.DATE_OFFSET;
                    
                    
                    m++;
                    if (character > 31) {
                        character -= LottoItem.DRAW_ID_DELTA;
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
       
    
    private String getDrawDetails(String drawID) throws Exception {
        HttpClient client = HttpClients.createDefault();
        String url = "https://www.norsk-tipping.no/api-lotto/getResultInfo.json?gameID=1&drawID=" + drawID + "&winnerDetails=true";
        HttpGet request = new HttpGet(url);
	HttpResponse response = client.execute(request);
	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String returnData = "";
        String line = "";
        int lineNr = 0;
        while ((line = rd.readLine()) != null) {
            lineNr++;
            if (lineNr == 2) {
                returnData = line;
//                haveFunWithJSON(returnData);
            }
//            System.out.println(line);
        }
        return returnData;
    }
}
