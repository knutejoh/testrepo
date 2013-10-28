/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.reflection;

/**
 *
 * @author Abstract
 */
public class UiOKlasseStaticFinal implements UiOKlasse {

    private final static Object finalStaticKlasse = "informatikk";

    @Override
    public String getKlasse() {
        return finalStaticKlasse.toString();
    }
}