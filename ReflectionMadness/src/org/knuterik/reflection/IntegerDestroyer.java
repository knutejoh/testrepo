/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.reflection;

import java.lang.reflect.Field;

/**
 *
 * @author Abstract
 */
public class IntegerDestroyer {
    
    
    public static void main(String args[]) throws Exception {
        
        Integer tallet = 42;
        System.out.println("Tallet er: " + tallet);
        
        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);
        value.set(42, 43);
        
        System.out.println("Tallet er nå: " + tallet);
        
        Integer meaningOfLife = 6*7;
        System.out.println("Er ikke svaret alltid 42??? Dette blir: " + meaningOfLife);
        
    }
    
}
