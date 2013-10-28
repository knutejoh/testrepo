/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.reflection;

/**
 *
 * @author Abstract
 */
public class UiOKlassePrivate implements UiOKlasse {

    private String privateKlasse = "informatikk";

    @Override
    public String getKlasse() {
        return privateKlasse.toString();
    }
}