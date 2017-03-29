package org.mercuriusframework.mmc.renderers.impl;

import com.Ostermiller.util.StringHelper;
import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.mmc.renderers.TableViewColumnRenderer;
import org.springframework.stereotype.Service;

/**
 * Unique code table view column renderer
 */
@Service("uniqueCodeTableViewColumnRenderer")
public class UniqueCodeTableViewColumnRenderer implements TableViewColumnRenderer {

    /**
     * Render column
     * @param parentObject   Parent object
     * @param propertyObject Entity object
     * @return Rendered result
     */
    @Override
    public String render(Object parentObject, Object propertyObject) {
        UniqueCodeEntity uniqueCodeEntity = (UniqueCodeEntity) propertyObject;
        if (uniqueCodeEntity != null) {
            return StringHelper.escapeHTML(uniqueCodeEntity.getName() + " (" + uniqueCodeEntity.getCode() + ")");
        } else {
            return "";
        }
    }
}
