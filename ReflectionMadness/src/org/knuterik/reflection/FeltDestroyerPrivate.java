/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.reflection;

import java.lang.reflect.Field;

/**
 *
 * @author Knut-Erik Johnsen
 */
public class FeltDestroyerPrivate {
       
    public static void main(String args[]) throws Exception {
        
        FeltDestroyerPrivate destroyer = new FeltDestroyerPrivate();
        destroyer.destroy();
    }
    
    public void destroy() throws Exception {
        UiOKlasse uioKlasse = new UiOKlassePrivate();
        
        System.out.println("Klassen er " + uioKlasse.getKlasse());
        
        Field klasseFelt = UiOKlassePrivate.class.getDeclaredField("privateKlasse");
        klasseFelt.setAccessible(true);
        klasseFelt.set(uioKlasse, "Økonomi og administrasjon...");
        
        System.out.println("Klassen er " + uioKlasse.getKlasse());
    }
    
    
}
