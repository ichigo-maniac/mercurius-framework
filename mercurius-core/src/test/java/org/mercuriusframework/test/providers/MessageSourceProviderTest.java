package org.mercuriusframework.test.providers;

import org.junit.Test;
import org.mercuriusframework.providers.MessageSourceProvider;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

/**
 * Message source provide test
 */
public class MessageSourceProviderTest extends AbstractTest {

    /**
     * Russian locale
     */
    private static final Locale RU_LOCALE = new Locale("ru");

    /**
     * Message source provider
     */
    @Autowired
    private MessageSourceProvider messageSourceProvider;

    /**
     * Method test - messageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest() {
        String message = messageSourceProvider.getMessage("entity.Price.title");
        assertEquals(message, "Price");
    }

    /**
     * Method test - messageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest2() {
        String message = messageSourceProvider.getMessage("entity.Category.title", Locale.ENGLISH);
        assertEquals(message, "Category");
    }

    /**
     * Method test - messageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest3() {
        String message = messageSourceProvider.getMessage("entity.Catalog.title", RU_LOCALE);
        assertEquals(message, "Каталог");
    }

    /**
     * Method test - messageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest4() {
        String message = messageSourceProvider.getMessage("dummy.message.with.parameters.first", 123);
        assertEquals(message, "Dummy message 123");
    }

    /**
     * Method test - messageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest5() {
        String message = messageSourceProvider.getMessage("dummy.message.with.parameters.second", Locale.ENGLISH, 555, "Atata");
        assertEquals(message, "Dummy message 555 and Atata");
    }

    /**
     * Method test - messageSourceProvider.getMessage
     */
    @Test
    public void getMessageTest6() {
        String message = messageSourceProvider.getMessage("dummy.message.with.parameters.second", RU_LOCALE, 32, 44);
        assertEquals(message, "Тестовое сообщение 32 и 44");
    }


}
