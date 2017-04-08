package org.mercuriusframework.mmc.renderers.impl;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.providers.MessageSourceProvider;
import org.mercuriusframework.mmc.renderers.TableViewColumnRenderer;
import org.springframework.stereotype.Service;

/**
 * Enum table view column renderer
 */
@Service("enumTableViewColumnRenderer")
public class EnumTableViewColumnRenderer implements TableViewColumnRenderer {

    /**
     * Render column
     * @param parentObject   Parent object
     * @param propertyObject Entity object
     * @return Rendered result
     */
    @Override
    public String render(Object parentObject, Object propertyObject) {
        if (propertyObject != null) {
            String enumClassName = propertyObject.getClass().getSimpleName();
            return MessageSourceProvider.getMessage(MercuriusConstants.LOCALIZATION.ENUM_PREFIX + enumClassName + "." + propertyObject.toString());
        } else {
            return "";
        }
    }
}
