package org.mercuriusframework.dataimport.components.insertupdate;

import org.mercuriusframework.dataimport.components.insert.InsertImportComponent;
import org.w3c.dom.Node;

/**
 * Insert-update import component
 */
public class InsertUpdateImportComponent extends InsertImportComponent {

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public InsertUpdateImportComponent(Node xmlElement) {
        super(xmlElement);
    }
}
