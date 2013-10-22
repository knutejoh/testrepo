/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author knut-erik.johnsen
 */
@XmlRootElement
@Entity
@Table(name = "KNUDIAPP_USER")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique=true)
    private String username;
    private String password;
    private String email;
    
    @ElementCollection
    @CollectionTable(name="KNUDIAPP_USER_GROUPS", 
            joinColumns = @JoinColumn(name = "username", nullable=false, referencedColumnName = "username"),
            
            uniqueConstraints = {@UniqueConstraint(columnNames={"username","usergroup"})})
    @Enumerated(EnumType.STRING)
    @Column(name = "usergroup")
    private List<Group> groups = new ArrayList<>();
    
    
    public User() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Group> getGroups() {
        return groups;
    }
    
    public void setGroups(List<Group> newGroups) {
        if (newGroups != null) {
            groups.clear();
            groups.addAll(newGroups);
        }
    }
    
    public void addGroup(Group group) {
        if (group != null && ! groups.contains(group)) {
            groups.add(group);
        }
               
    }
    
    public void removeGroup(Group group) {
        if (group != null) {
            groups.remove(group);
        }
    }
}
