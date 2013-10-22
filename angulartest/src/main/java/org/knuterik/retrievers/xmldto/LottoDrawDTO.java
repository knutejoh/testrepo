/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.retrievers.xmldto;

import java.util.Date;

/**
 *
 * @author knut-erik.johnsen
 */
public class LottoDrawDTO {
        private Date dato = null;
        private Long drawID = null;

        public Date getDato() {
            return dato;
        }

        public void setDato(Date dato) {
            this.dato = dato;
        }

        public Long getDrawID() {
            return drawID;
        }

        public void setDrawID(Long drawID) {
            this.drawID = drawID;
        }
        
        
    }