/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.retrievers.xmldto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author knut-erik.johnsen
 */
@XmlRootElement
public class LottoDrawingDetailDTO {
    
    private Long drawID;
    private String drawDate;
    private Date drawDateDate;
    private List<Integer> mainTable;
    private List<Integer> addTable;
    private List<Long> prizeTable;
    private List<String> prizeCaptionTable;
    private Long turnover;
    private Long totalNumberOfWinners;
    private SuperLottoDrawingDTO superlottoResult;
    
    /*
    "drawDate":"2013,08,03,18,00,00",
    "mainTable":[1,5,10,18,22,23,26],
    "addTable":[6,15,33],
    "prizeTable":["10743215","71825","6230","195","45"],
    "prizeCaptionTable":["7 rette","6 + 1 rette","6 rette","5 rette","4 + 1 rette"],
    "winnerList":[[null,"","0000","UKJENT","0","UKJENT"]],
    "turnover":61920548,"totalNumberOfWinners":119501,
    "superlottoResult":{"drawID":894,"drawDate":"2013,06,22,18,00,00","numberOfWinners":39,"prizeAmount":1000000},"superlottoNextDraw":{"numberOfDrawsUntil":9,"prizePool":18011368,"drawDate":"2013,12,21,20,00,00"}}
     * */
    public Long getDrawID() {
        return drawID;
    }

    public void setDrawID(Long drawID) {
        this.drawID = drawID;
    }

    public String getDrawDate() {
        return drawDate;
    }
    
    
    
    public void setDrawDate(String drawDateString) {
        this.drawDate = drawDateString;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
        try  {
            this.drawDateDate = sdf.parse(drawDateString);
        } catch (Exception e) {
            
        }
    }

    public Date getDrawDateDate() {
        return drawDateDate;
    }

    
    
    public List<Integer> getMainTable() {
        return mainTable;
    }

    public void setMainTable(List<Integer> mainTable) {
        this.mainTable = mainTable;
    }

    public List<Integer> getAddTable() {
        return addTable;
    }

    public void setAddTable(List<Integer> addTable) {
        this.addTable = addTable;
    }

    public List<Long> getPrizeTable() {
        return prizeTable;
    }

    public void setPrizeTable(List<Long> prizeTable) {
        this.prizeTable = prizeTable;
    }

    public List<String> getPrizeCaptionTable() {
        return prizeCaptionTable;
    }

    public void setPrizeCaptionTable(List<String> prizeCaptionTable) {
        this.prizeCaptionTable = prizeCaptionTable;
    }

    public Long getTurnover() {
        return turnover;
    }

    public void setTurnover(Long turnover) {
        this.turnover = turnover;
    }

    public Long getTotalNumberOfWinners() {
        return totalNumberOfWinners;
    }

    public void setTotalNumberOfWinners(Long totalNumberOfWinners) {
        this.totalNumberOfWinners = totalNumberOfWinners;
    }

    public SuperLottoDrawingDTO getSuperlottoResult() {
        return superlottoResult;
    }

    public void setSuperlottoResult(SuperLottoDrawingDTO superlottoResult) {
        this.superlottoResult = superlottoResult;
    }
    
    
    

    @Override
    public String toString() {
        return "LottoDrawingDetailDTO{" + "drawID=" + drawID + ", drawDate=" + drawDateDate + ", mainTable=" + mainTable + ", addTable=" + addTable + ", prizeTable=" + prizeTable + ", prizeCaptionTable=" + prizeCaptionTable + ", turnOver=" + turnover + ", totalNumberOfWinners=" + totalNumberOfWinners + ", superlottoResult=" + superlottoResult + '}';
    }
    
    
    
}
