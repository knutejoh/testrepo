/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.retrievers.xmldto.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Abstract
 */
public class NorskTippingDateConverter extends XmlAdapter<String, Date> {

    @Override
    public String marshal(Date date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
        return sdf.format(date);
    }

    @Override
    public Date unmarshal(String drawDateString) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
        return sdf.parse(drawDateString);
    }
    
}
