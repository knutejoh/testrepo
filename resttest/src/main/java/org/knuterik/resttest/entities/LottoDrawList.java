/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.resttest.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author knut-erik.johnsen
 */
@XmlRootElement
public class LottoDrawList {

    List<LottoItem> items = new ArrayList<LottoItem>();

    public List<LottoItem> getItems() {
        return items;
    }

    public void setItems(List<LottoItem> items) {
        System.out.println("Trying to add items");
        this.items = items;
    }
}
