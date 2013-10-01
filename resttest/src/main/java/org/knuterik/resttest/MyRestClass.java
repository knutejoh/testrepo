/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.resttest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abstract
 */
@XmlRootElement
public class MyRestClass {

    private String name;
    private int age;
    private String address;
    
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
