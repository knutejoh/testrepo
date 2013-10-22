/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.retrievers.interceptors;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

/**
 *
 * @author knut-erik.johnsen
 */
public class NorskTippingInterceptor implements ReaderInterceptor {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        
        InputStream inputStream = context.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
        
        String returnData = "";
        String line = "";
        int lineNr = 0;
        while ((line = rd.readLine()) != null) {
            lineNr++;
            if (lineNr == 2) {
                returnData = line;
            }
//            System.out.println(line);
        }
        rd.close();
        context.setInputStream(new ByteArrayInputStream(returnData.getBytes("UTF-8")));
        return context.proceed();
    }
    
}
