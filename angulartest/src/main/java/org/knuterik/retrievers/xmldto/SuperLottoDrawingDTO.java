/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.retrievers.xmldto;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.knuterik.retrievers.xmldto.converter.NorskTippingDateConverter;

/**
 *
 * @author knut-erik.johnsen
 */
public class SuperLottoDrawingDTO {
    private Long drawID;
    @XmlJavaTypeAdapter(NorskTippingDateConverter.class)
    private Date drawDate;
    private Long numberOfWinners;
    private Long prizeAmount;        
    
    /*,"superlottoNextDraw":{"numberOfDrawsUntil":9,"prizePool":18014020,"drawDate":"2013,12,21,20,00,00"}} */

    public Long getDrawID() {
        return drawID;
    }

    public void setDrawID(Long drawID) {
        this.drawID = drawID;
    }

    public Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
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
        return "SuperLottoDrawingDTO{" + "drawID=" + drawID + ", drawDate=" + drawDate + ", drawDateDate=" + drawDate + ", numberOfWinners=" + numberOfWinners + ", prizeAmount=" + prizeAmount + '}';
    }
    
    
}
