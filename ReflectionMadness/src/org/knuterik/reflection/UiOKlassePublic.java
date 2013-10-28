/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.reflection;

/**
 *
 * @author Abstract
 */
public class UiOKlassePublic implements UiOKlasse {

    public String publicKlasse = "informatikk";

    @Override
    public String getKlasse() {
        return publicKlasse;
    }
}