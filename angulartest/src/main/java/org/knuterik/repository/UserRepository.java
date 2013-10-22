/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.repository;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.knuterik.data.Group;
import org.knuterik.data.User;

/**
 *
 * @author knut-erik.johnsen
 */
@Stateless
public class UserRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public User getUser(String username) {
        TypedQuery<User> query = em.createQuery("SELECT c FROM " + User.class.getName() + " c WHERE c.username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        if (users.size() == 1) {
            return users.get(0);
        } else if (users.size() > 1) {
            throw new NonUniqueResultException("Found multiple results!");
        }
        return null;
    }

    public User createDefaultUser(String username, String password, String email) {
        
        List<Group> groups = new ArrayList<>();
        groups.add(Group.USER);
        groups.add(Group.DEFAULT);
        
        return createUser(username, password, email, groups);
    }

    
    public User createAdminUser(String username, String password, String email) {
        
        List<Group> groups = new ArrayList<>();
        groups.add(Group.USER);
        groups.add(Group.DEFAULT);
        groups.add(Group.ADMINISTRATOR);
        
        return createUser(username, password, email, groups);
    }
    
    private User createUser(String username, String password, String email, List<Group> groups) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setGroups(groups);
        
        em.persist(user);
        
        return user;
    }
    
    

    
    
}
