/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abstract
 */
public class HashMapDestroyer {
    
    
    public static void main(String args[]) throws Exception {
        
        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);
        value.set(42, 43);
        
        Map<Integer, String> meaningOfLife = new HashMap<Integer, String>();
        
        meaningOfLife.put(42, "The hitchhiker's guide to the galaxy");
        
        System.out.println("Boken er for 42: " + meaningOfLife.get(42));
        System.out.println("Boken er for 43: " + meaningOfLife.get(43));
        
    }
    
}
