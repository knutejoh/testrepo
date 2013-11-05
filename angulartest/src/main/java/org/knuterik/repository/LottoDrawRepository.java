/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.joda.time.DateTime;
import org.knuterik.data.LottoDrawing;

/**
 *
 * @author knut-erik.johnsen
 */
@Stateless
public class LottoDrawRepository {
    
    
    @PersistenceContext
    private EntityManager em;
    
    public LottoDrawing getLottoDraw(DateTime dateOfDraw) {
        TypedQuery<LottoDrawing> query = em.createNamedQuery("LottoDrawing.findByDateQuery", LottoDrawing.class);
        query.setParameter("drawDate", dateOfDraw);
        List<LottoDrawing> lottodraws = query.getResultList();
        if (lottodraws.size() == 1) {
            return lottodraws.get(0);
        } else if (lottodraws.size() > 1) {
            throw new NonUniqueResultException("Found multiple results!");
        }
        return null;
    }
    
    public LottoDrawing getLottoDraw(Long id) {
        return em.find(LottoDrawing.class, id);
        
    }
    
    public List<LottoDrawing> getAllLottoDraws() {
        TypedQuery<LottoDrawing> query = em.createNamedQuery("LottoDrawing.findAll", LottoDrawing.class);
        return query.getResultList();
    }
    
    public List<LottoDrawing> getAllLottoDrawsSorted() {
        TypedQuery<LottoDrawing> query = em.createNamedQuery("LottoDrawing.findAllSorted", LottoDrawing.class);
        return query.getResultList();
    }
    
    public LottoDrawing createLottoDraw(Long ID, DateTime dateOfDraw) {
        LottoDrawing lottoDraw = new LottoDrawing();
        lottoDraw.setId(ID);
        lottoDraw.setDrawDate(dateOfDraw);
        
        em.persist(lottoDraw);
        
        return lottoDraw;
    }
    
    public List<Long> getAllLottoIDs() {
        TypedQuery<Long> query = em.createNamedQuery("LottoDrawing.findAllID", Long.class);
        return query.getResultList();
    }

    
    
}
