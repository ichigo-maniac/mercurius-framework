package org.mercuriusframework.mmc.renderers.impl;

import com.Ostermiller.util.StringHelper;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.mmc.renderers.TableViewColumnRenderer;
import org.springframework.stereotype.Service;

/**
 * Catalog unique code table view column renderer
 */
@Service("catalogUniqueCodeTableViewColumnRenderer")
public class CatalogUniqueCodeTableViewColumnRenderer implements TableViewColumnRenderer {

    /**
     * Render column
     * @param parentObject   Parent object
     * @param propertyObject Entity object
     * @return Rendered result
     */
    @Override
    public String render(Object parentObject, Object propertyObject) {
        CatalogUniqueCodeEntity catalogUniqueCodeEntity = (CatalogUniqueCodeEntity) propertyObject;
        if (catalogUniqueCodeEntity != null) {
            return StringHelper.escapeHTML(catalogUniqueCodeEntity.getName() + " (" + catalogUniqueCodeEntity.getCode() + ")::" +
                    catalogUniqueCodeEntity.getCatalog().getName() + " (" + catalogUniqueCodeEntity.getCatalog().getCode() + ")");
        } else {
            return "";
        }
    }
}
