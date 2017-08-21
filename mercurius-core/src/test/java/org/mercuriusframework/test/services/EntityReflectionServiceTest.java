package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.*;
import org.mercuriusframework.services.EntityReflectionService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Entity reflection service interface
 */
public class EntityReflectionServiceTest extends AbstractTest {

    /**
     * Entity reflection service
     */
    @Autowired
    private EntityReflectionService entityReflectionService;

    /**
     * Method test - entityReflectionService.isEntityClassExist
     */
    @Test
    public void isEntityClassExistTest() {
        assertEquals(entityReflectionService.isEntityClassExist(AbstractUserEntity.ENTITY_NAME), true);
    }

    /**
     * Method test - entityReflectionService.isEntityClassExist
     */
    @Test
    public void isEntityClassExistTest2() {
        assertEquals(entityReflectionService.isEntityClassExist(ProductEntity.ENTITY_NAME), true);
    }

    /**
     * Method test - entityReflectionService.isEntityClassExist
     */
    @Test
    public void isEntityClassExistTest3() {
        assertEquals(entityReflectionService.isEntityClassExist("DUMMY"), false);
    }

    /**
     * Method test - entityReflectionService.getEntityClassByEntityName
     */
    @Test
    public void getEntityClassByEntityNameTest() {
        assertEquals(
                entityReflectionService.getEntityClassByEntityName(AbstractUserEntity.ENTITY_NAME).equals(AbstractUserEntity.class),
                true);
    }

    /**
     * Method test - entityReflectionService.getEntityClassByEntityName
     */
    @Test
    public void getEntityClassByEntityNameTest2() {
        assertEquals(
                entityReflectionService.getEntityClassByEntityName(CatalogEntity.ENTITY_NAME).equals(CatalogEntity.class),
                true);
    }

    /**
     * Method test - entityReflectionService.getEntityNameByClass
     */
    @Test
    public void getEntityNameByClassTest() {
        assertEquals(entityReflectionService.getEntityNameByClass(AbstractUserEntity.class).equals(AbstractUserEntity.ENTITY_NAME), true);
    }

    /**
     * Method test - entityReflectionService.getEntityNameByClass
     */
    @Test
    public void getEntityNameByClassTest2() {
        assertEquals(entityReflectionService.getEntityNameByClass(UnitEntity.class).equals(UnitEntity.ENTITY_NAME), true);
    }

    /**
     * Method test - entityReflectionService.getField
     */
    @Test
    public void getFieldTest() throws NoSuchFieldException {
        Field field = entityReflectionService.getField(CatalogUniqueCodeEntity.class, CatalogUniqueCodeEntity.CATALOG);
        assertEquals(field.getName().equals(CatalogUniqueCodeEntity.CATALOG) &&
            field.getType().equals(CatalogEntity.class), true);
    }

    /**
     * Method test - entityReflectionService.getField
     */
    @Test
    public void getFieldTest2() throws NoSuchFieldException {
        Field field = entityReflectionService.getField(StoreEntity.class, StoreEntity.CODE);
        assertEquals(field.getName().equals(StoreEntity.CODE) &&
                field.getType().equals(String.class), true);
    }

    /**
     * Method test - entityReflectionService.getField
     */
    @Test
    public void getFieldTest3() throws NoSuchFieldException {
        Field field = entityReflectionService.getField(ProductEntity.class, ProductEntity.CREATION_TIME);
        assertEquals(field.getName().equals(ProductEntity.CREATION_TIME) &&
                field.getType().equals(Date.class), true);
    }

    /**
     * Method test - entityReflectionService.getField
     */
    @Test
    public void getFieldTest4() throws NoSuchFieldException {
        Field field = entityReflectionService.getField(ProductEntity.class, ProductEntity.CATEGORIES);
        assertEquals(field.getName().equals(ProductEntity.CATEGORIES) &&
                field.getType().equals(Set.class), true);
    }

    /**
     * Method test - entityReflectionService.getField
     */
    @Test
    public void getFieldTest5() throws NoSuchFieldException {
        Field field = entityReflectionService.getField(FacetEntity.class, FacetEntity.CATEGORIES);
        assertEquals(field.getName().equals(FacetEntity.CATEGORIES) &&
                field.getType().equals(List.class), true);
    }

    /**
     * Method test - entityReflectionService.getFieldClass
     */
    @Test
    public void getFieldClassTest() throws NoSuchFieldException {
        Field field = entityReflectionService.getField(FacetEntity.class, FacetEntity.CATEGORIES);
        assertEquals(entityReflectionService.getFieldClass(field), CategoryEntity.class);
    }

    /**
     * Method test - entityReflectionService.getFieldClass
     */
    @Test
    public void getFieldClassTest2() throws NoSuchFieldException {
        Field field = entityReflectionService.getField(ProductEntity.class, ProductEntity.CATEGORIES);
        assertEquals(entityReflectionService.getFieldClass(field), CategoryEntity.class);
    }

    /**
     * Method test - entityReflectionService.getFieldClass
     */
    @Test
    public void getFieldClassTest3() throws NoSuchFieldException {
        Field field = entityReflectionService.getField(ProductEntity.class, ProductEntity.CATALOG);
        assertEquals(entityReflectionService.getFieldClass(field), CatalogEntity.class);
    }

    /**
     * Method test - entityReflectionService.getFieldClass
     */
    @Test
    public void getFieldClassTest4() throws NoSuchFieldException {
        assertEquals(entityReflectionService.getFieldClass(StockEntity.class, StockEntity.COUNT), Long.class);
    }

    /**
     * Method test - entityReflectionService.getFieldClass
     */
    @Test
    public void getFieldClassTest5() throws NoSuchFieldException {
        assertEquals(entityReflectionService.getFieldClass(StockEntity.class, StockEntity.PRODUCT), ProductEntity.class);
    }
}
