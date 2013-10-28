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
        
        Field value = Integer.class.getDeclaredField("value");
        
        value.setAccessible(true);
        
        value.set(42, 43);
        
        Integer meaningOfLife = 6*7;
        
        System.out.println("Er ikke svaret alltid 42??? Dette blir: " + meaningOfLife);
        
    }
    
}
