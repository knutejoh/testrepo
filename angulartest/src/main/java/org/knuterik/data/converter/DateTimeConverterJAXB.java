/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.data.converter;

import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.DateTime;

/**
 *
 * @author knut-erik.johnsen
 */
public class DateTimeConverterJAXB extends XmlAdapter<String, DateTime> {

    private static final long serialVersionUID = 1L;

    @Override
    public String marshal(DateTime date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date.toDate());
    }

    @Override
    public DateTime unmarshal(String drawDateString) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new DateTime(sdf.parse(drawDateString));
    }
    
    
}
