package org.mercuriusframework.test.providers;

import org.junit.Test;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CurrencyEntity;
import org.mercuriusframework.entities.PriceEntity;
import org.mercuriusframework.entities.UnitEntity;
import org.mercuriusframework.providers.EntityMessageSourceProvider;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

/**
 * Entity message source provider test
 */
public class EntityMessageSourceProviderTest extends AbstractTest {

    /**
     * Russian locale
     */
    private static final Locale RU_LOCALE = new Locale("ru");

    /**
     * Entity message source provider
     */
    @Autowired
    private EntityMessageSourceProvider entityMessageSourceProvider;

    /**
     * Method test - entityMessageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest() {
        String message = entityMessageSourceProvider.getMessage(CatalogEntity.ENTITY_NAME, CatalogEntity.CREATION_TIME);
        assertEquals(message, "Creation time");
    }

    /**
     * Method test - entityMessageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest2() {
        String message = entityMessageSourceProvider.getMessage(PriceEntity.ENTITY_NAME, PriceEntity.CREATION_TIME, Locale.ENGLISH);
        assertEquals(message, "Creation time");
    }

    /**
     * Method test - entityMessageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest3() {
        String message = entityMessageSourceProvider.getMessage(UnitEntity.ENTITY_NAME, UnitEntity.CREATION_TIME, RU_LOCALE);
        assertEquals(message, "Время создания");
    }

    /**
     * Method test - entityMessageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest4() {
        String message = entityMessageSourceProvider.getMessage(CurrencyEntity.ENTITY_NAME, CurrencyEntity.CODE);
        assertEquals(message, "Iso code");
    }

    /**
     * Method test - entityMessageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest5() {
        String message = entityMessageSourceProvider.getMessage(CurrencyEntity.ENTITY_NAME, CurrencyEntity.CODE, Locale.ENGLISH);
        assertEquals(message, "Iso code");
    }

    /**
     * Method test - entityMessageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest6() {
        String message = entityMessageSourceProvider.getMessage(CurrencyEntity.ENTITY_NAME, CurrencyEntity.CODE, RU_LOCALE);
        assertEquals(message, "Iso код");
    }
}
