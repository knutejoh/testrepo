/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.repository;

import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author knut-erik.johnsen
 */
@Singleton
public class InitialData {

    @Inject
    private UserRepository userRepo;
    
    
//    @PostConstruct
    public void addTestData() {
        if (userRepo.getUser("test") == null) {
            userRepo.createDefaultUser("test", "test", "test@test.com");
        }
        if (userRepo.getUser("Ali") == null) {
            userRepo.createDefaultUser("Ali", "Baba", "ali@baba.com");
        }
        if (userRepo.getUser("knudi") == null) {
            userRepo.createAdminUser("knudi", "knudi", "abstract@knut-erik.org");
        }
    }

}
