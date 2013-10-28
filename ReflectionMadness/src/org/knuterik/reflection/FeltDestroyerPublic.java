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
public class FeltDestroyerPublic {
       
    public static void main(String args[]) throws Exception {
        
        FeltDestroyerPublic destroyer = new FeltDestroyerPublic();
        destroyer.destroy();
    }
    
    public void destroy() throws Exception {
        UiOKlasse uioKlasse = new UiOKlassePublic();
        
        System.out.println("Klassen er " + uioKlasse.getKlasse());
        
        Field klasseFelt = UiOKlassePublic.class.getField("publicKlasse");
        klasseFelt.set(uioKlasse, "Økonomi og administrasjon...");
        
        System.out.println("Klassen er " + uioKlasse.getKlasse());
    }
    
    
}
