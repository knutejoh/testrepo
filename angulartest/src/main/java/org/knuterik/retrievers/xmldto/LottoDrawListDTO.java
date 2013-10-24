/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.knuterik.retrievers.xmldto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author knut-erik.johnsen
 */
@XmlRootElement
public class LottoDrawListDTO {

    List<LottoItemDTO> items = new ArrayList<>();

    public List<LottoItemDTO> getItems() {
        return items;
    }

    public void setItems(List<LottoItemDTO> items) {
        this.items = items;
    }
}
