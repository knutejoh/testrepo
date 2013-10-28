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
public class StringDestroyer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        System.out.println("Hallo");
        Field stringInnhold = String.class.getDeclaredField("value");
        stringInnhold.setAccessible(true);
        stringInnhold.set("Hallo", "Tjena".toCharArray());
        System.out.println("Hallo");        
        System.out.println("Er vi like? " + "Hallo".equals("Tjena"));
    }
}
