/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author knut-erik.johnsen
 */
@XmlRootElement
public class LoginUserRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
