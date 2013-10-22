/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.retrievers.xmldto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author knut-erik.johnsen
 */
public class SuperLottoDrawingDTO {
    private Long drawID;
    private String drawDate;
    private Date drawDateDate;
    private Long numberOfWinners;
    private Long prizeAmount;        
    
    /*,"superlottoNextDraw":{"numberOfDrawsUntil":9,"prizePool":18014020,"drawDate":"2013,12,21,20,00,00"}} */

    public Long getDrawID() {
        return drawID;
    }

    public void setDrawID(Long drawID) {
        this.drawID = drawID;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public Date getDrawDateDate() {
        return drawDateDate;
    }
    
    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
        try  {
            this.drawDateDate = sdf.parse(drawDate);
        } catch (Exception e) {
            
        }
        
    }

    public Long getNumberOfWinners() {
        return numberOfWinners;
    }

    public void setNumberOfWinners(Long numberOfWinners) {
        this.numberOfWinners = numberOfWinners;
    }

    public Long getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(Long prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    @Override
    public String toString() {
        return "SuperLottoDrawingDTO{" + "drawID=" + drawID + ", drawDate=" + drawDate + ", drawDateDate=" + drawDateDate + ", numberOfWinners=" + numberOfWinners + ", prizeAmount=" + prizeAmount + '}';
    }
    
    
}
