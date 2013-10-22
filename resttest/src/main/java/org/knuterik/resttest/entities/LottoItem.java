/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.resttest.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author knut-erik.johnsen
 */
@XmlRootElement
public class LottoItem {
    
    public static String YEAR_SEPARATOR = ":";
    public static String MONTH_SEPARATOR = "\\.";
    public static int DATE_OFFSET = 63;
    public static int DRAW_ID_DELTA = 32;

    private String drawData = "";
    private int firstYear = -1;
    private int gameID = -1;
    private List<LottoDraw> draws = new ArrayList<>();
    
    public String getDrawData() {
        return drawData;
    }

    public void setDrawData(String drawData) {
        this.drawData = drawData;
    }
    
    public int getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
    
    @Override
    public String toString() {
        return "Data for Item: GameID: " + gameID + " FirstYear: " + firstYear;
    }
    
    public List<LottoDraw> getDraws() {
        return draws;
    }
    
    public void addDraw(Date dato, String drawID) {
        LottoDraw draw = new LottoDraw();
        draw.setDato(dato);
        draw.setDrawID(drawID);
        draws.add(draw);
    }
    
    public class LottoDraw {
        private Date dato = null;
        private String drawID = null;

        public Date getDato() {
            return dato;
        }

        public void setDato(Date dato) {
            this.dato = dato;
        }

        public String getDrawID() {
            return drawID;
        }

        public void setDrawID(String drawID) {
            this.drawID = drawID;
        }
        
        
    }
}
