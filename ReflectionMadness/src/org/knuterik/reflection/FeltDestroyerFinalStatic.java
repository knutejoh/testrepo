/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

/**
 *
 * @author Knut-Erik Johnsen
 */
public class FeltDestroyerFinalStatic {
       
    private static final ReflectionFactory reflection = ReflectionFactory.getReflectionFactory();
    
    public static void main(String args[]) throws Exception {
        
        FeltDestroyerFinalStatic destroyer = new FeltDestroyerFinalStatic();
        destroyer.destroy();
    }
    
    public void destroy() throws Exception {
        UiOKlasse uioKlasse = new UiOKlasseStaticFinal();
        
        System.out.println("Klassen er " + uioKlasse.getKlasse());
        
        Field klasseFelt = UiOKlasseStaticFinal.class.getDeclaredField("finalStaticKlasse");
        klasseFelt.setAccessible(true);
        
        Field modifiersFelt = Field.class.getDeclaredField("modifiers");
        modifiersFelt.setAccessible(true);
        
        int modifiers = modifiersFelt.getInt(klasseFelt);
        modifiers &= ~Modifier.FINAL;
        
        modifiersFelt.setInt(klasseFelt, modifiers);
              
        klasseFelt.set(uioKlasse, "Økonomi og administrasjon...");
        
        System.out.println("Klassen er " + uioKlasse.getKlasse());
    }
    
    
}
