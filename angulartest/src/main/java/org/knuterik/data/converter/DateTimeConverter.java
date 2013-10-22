/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.data.converter;

import java.sql.Timestamp;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.mappings.foundation.AbstractDirectMapping;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.DateTime;

/**
 *
 * @author knut-erik.johnsen
 */
public class DateTimeConverter implements Converter {

    private static final long serialVersionUID = 1L;

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        return dataValue == null ? null : new DateTime((Timestamp) dataValue);
    }

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue == null ? null : new Timestamp(((DateTime) objectValue).getMillis());
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        ((AbstractDirectMapping) mapping)
            .setFieldClassification(java.sql.Timestamp.class);
    }

    @Override
    public boolean isMutable() {
        return false;
    }
    
}
