/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;
import org.knuterik.data.converter.DateTimeConverter;
import org.knuterik.retrievers.xmldto.LottoDrawingDetailDTO;

/**
 *
 * @author knut-erik.johnsen
 */
@Entity
@Table(name="LOTTO_DRAWING_DETAILS")
@NamedQueries({
})  
public class LottoDrawingDetails implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    
    @ElementCollection
    @OrderColumn(name = "winnerorder")
    @CollectionTable(name="LOTTO_DRAWING_DETAILS_WINNERS",
            joinColumns = @JoinColumn(name = "drawdetails_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames={"drawdetails_id","winnernumber"})})
    @Column(name = "winnernumber")
    private List<Integer> mainTable;
    
    @ElementCollection
    @OrderColumn(name = "additionalorder")
    @CollectionTable(name="LOTTO_DRAWING_DETAILS_ADDITIONALS",
            joinColumns = @JoinColumn(name = "drawdetails_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames={"drawdetails_id","additionalnumber"})})
    @Column(name = "additionalnumber")
    private List<Integer> addTable;
    
    @ElementCollection
    @OrderColumn(name = "pricetableorder")
    @CollectionTable(name="LOTTO_DRAWING_DETAILS_PRICETABLE",
            joinColumns = @JoinColumn(name = "drawdetails_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames={"drawdetails_id","prize"})})
    @Column(name = "prize")
    private List<Long> prizeTable;
    
    @ElementCollection
    @OrderColumn(name = "pricacaptionorder")
    @CollectionTable(name="LOTTO_DRAWING_DETAILS_PRICECAPTIONTABLE",
            joinColumns = @JoinColumn(name = "drawdetails_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames={"drawdetails_id","prizecaption"})})
    @Column(name = "prizecaption")
    private List<String> prizeCaptionTable;
    
    private Long turnover;
    private Long totalNumberOfWinners;

    @Column(name = "drawDate")
    @Converter(name = "dateTimeConverter", converterClass = DateTimeConverter.class)
    @Convert("dateTimeConverter")
    private DateTime drawDate = null;
    
    @OneToOne(mappedBy = "drawDetails")
    private LottoDrawing drawing;
    
    
    public static LottoDrawingDetails fromDTO(LottoDrawingDetailDTO dto) {
        
        LottoDrawingDetails details = new LottoDrawingDetails();
        details.setAddTable(dto.getAddTable());
        details.setDrawDate(new DateTime(dto.getDrawDate()));
        details.setId(dto.getDrawID());
        details.setMainTable(dto.getMainTable());
        details.setPrizeCaptionTable(dto.getPrizeCaptionTable());
        details.setPrizeTable(dto.getPrizeTable());
        details.setTotalNumberOfWinners(dto.getTotalNumberOfWinners());
        details.setTurnover(dto.getTurnover());
        return details;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(DateTime drawDate) {
        this.drawDate = drawDate;
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

    public LottoDrawing getDrawing() {
        return drawing;
    }

    public void setDrawing(LottoDrawing drawing) {
        this.drawing = drawing;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LottoDrawingDetails)) {
            return false;
        }
        LottoDrawingDetails other = (LottoDrawingDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.knuterik.data.LottoDrawing[ id=" + id + " ]";
    }
    
}
