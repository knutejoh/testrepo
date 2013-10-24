/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;
import org.knuterik.data.converter.DateTimeConverterJAXB;
import org.knuterik.data.converter.DateTimeConverterJPA;

/**
 *
 * @author knut-erik.johnsen
 */
@Entity
@Table(name="LOTTO_DRAWING")
@NamedQueries({
    @NamedQuery(name = "LottoDrawing.findByDate", query = LottoDrawing.findByDateQuery),
    @NamedQuery(name = "LottoDrawing.findAll", query = LottoDrawing.findAllQuery),
    @NamedQuery(name = "LottoDrawing.findAllID", query = LottoDrawing.findAllIDQuery)
    
})  
public class LottoDrawing implements Serializable {
    
    protected static final String findByDateQuery = "SELECT c FROM LottoDrawing c WHERE c.drawDate = :drawDate";
    protected static final String findAllQuery = "SELECT c FROM LottoDrawing c";
    protected static final String findAllIDQuery = "SELECT c.id FROM LottoDrawing c";
    
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    @Column(name = "drawDate")
    @Converter(name = "dateTimeConverter", converterClass = DateTimeConverterJPA.class)
    @Convert("dateTimeConverter")
    @XmlJavaTypeAdapter(DateTimeConverterJAXB.class)
    private DateTime drawDate = null;
    
    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "drawdetailsid", referencedColumnName = "ID")
    private LottoDrawingDetails drawDetails = null;
    
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
    
    @XmlTransient
    public LottoDrawingDetails getDrawDetails() {
        return drawDetails;
    }

    public void setDrawDetails(LottoDrawingDetails drawDetails) {
        this.drawDetails = drawDetails;
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
        if (!(object instanceof LottoDrawing)) {
            return false;
        }
        LottoDrawing other = (LottoDrawing) object;
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
