package org.mercuriusframework.dataimport.services.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.dataimport.components.AbstractImportComponent;
import org.mercuriusframework.dataimport.components.common.CriteriaComponent;
import org.mercuriusframework.dataimport.components.common.CriteriaValueComponent;
import org.mercuriusframework.dataimport.components.common.ImportColumn;
import org.mercuriusframework.dataimport.components.insert.InsertImportComponent;
import org.mercuriusframework.dataimport.components.insert.InsertValue;
import org.mercuriusframework.dataimport.components.insertupdate.InsertUpdateImportComponent;
import org.mercuriusframework.dataimport.components.remove.RemoveImportComponent;
import org.mercuriusframework.dataimport.components.update.UpdateImportComponent;
import org.mercuriusframework.dataimport.components.update.UpdateValue;
import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.mercuriusframework.dataimport.services.DataImportService;
import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.enums.CriteriaValueType;
import org.mercuriusframework.providers.ApplicationContextProvider;
import org.mercuriusframework.services.EntityReflectionService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.query.CriteriaParameter;
import org.mercuriusframework.services.query.CriteriaValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data import service
 */
@Service("dataImportService")
public class DataImportServiceImpl implements DataImportService {

    /**
     * Constants
     */
    private static final String SET_METHOD_PREFIX = "set";
    private static final String IMPORT_ERROR_PREFIX = "IMPORT ERROR - ";

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Entity reflection service
     */
    @Autowired
    @Qualifier("entityReflectionService")
    protected EntityReflectionService entityReflectionService;

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Import data
     * @param xmlStringValue Xml string value
     * @return Log result
     */
    @Override
    public String importData(String xmlStringValue) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlStringValue.getBytes());
        return importData(inputStream);
    }

    /**
     * Import data
     * @param file Import data file
     * @return Log result
     */
    @Override
    public String importData(MultipartFile file) {
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(file.getBytes());
            return importData(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
            return IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>";
        }
    }

    /**
     * Import data
     * @param stream Import stream
     * @return Log result
     */
    @Override
    public String importData(InputStream stream) {
        /** Create xml builder */
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = null;
        try {
            xmlBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            return IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>";
        }
        /** Parse string value */
        Document document = null;
        try {
            document = xmlBuilder.parse(stream);
        } catch (SAXException exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            return IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>";
        } catch (IOException exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            return IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>";
        }
        /** Import data */
        return importDocument(document);
    }

    /**
     * Import classpath data
     * @param path Resource path
     * @return Log result
     */
    @Override
    public String importClasspathData(String path) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            if (inputStream == null) {
                throw new RuntimeException("No resource \"" + path + "\" has been found");
            }
            return importData(inputStream);
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            return IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>";
        }
    }

    /**
     * Parse and import xml-document
     * @param xmlDocument Xml document
     * @return Import result
     */
    private String importDocument(Document xmlDocument) {
        Element configElement = xmlDocument.getDocumentElement();
        if (!configElement.getNodeName().equals(MercuriusDataImportComponentConstants.DataImport.COMPONENT_NAME)) {
            LOGGER.error(IMPORT_ERROR_PREFIX + "root element must be \"{}\"", MercuriusDataImportComponentConstants.DataImport.COMPONENT_NAME);
            return IMPORT_ERROR_PREFIX + "root element must be \"" + MercuriusDataImportComponentConstants.DataImport.COMPONENT_NAME + "<br>";
        }
        /** Create components */
        List<AbstractImportComponent> components = new ArrayList<>(configElement.getChildNodes().getLength());
        StringBuilder log = new StringBuilder();
        NodeList childNodes = configElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                AbstractImportComponent component = parseNode(childNodes.item(i), log);
                if (component != null) {
                    if (!entityReflectionService.isEntityClassExist(component.getEntityName())) {
                        LOGGER.error(IMPORT_ERROR_PREFIX + "entity \"{}\" doesn't exist (no class with annotation @Entity(name = \"{}\")",
                                component.getEntityName(), component.getEntityName());
                        log.append(IMPORT_ERROR_PREFIX + "entity \"" + component.getEntityName() + "\" doesn't exist (no class with annotation" +
                                " @Entity(name = \"" + component.getEntityName() + "\")<br>");
                        continue;
                    } else {
                        components.add(component);
                    }
                }
            }
        }
        /** Import components */
        for (AbstractImportComponent importComponent : components) {
            importComponent(importComponent, log);
        }
        return log.toString();
    }

    /**
     * Parse node (element)
     * @param xmlElement Xml element
     * @param log Log
     * @return Parsed component
     */
    private AbstractImportComponent parseNode(Node xmlElement, StringBuilder log) {
        try {
            if (xmlElement.getNodeName().equals(MercuriusDataImportComponentConstants.Insert.COMPONENT_NAME)) {
                return new InsertImportComponent(xmlElement);
            }
            if (xmlElement.getNodeName().equals(MercuriusDataImportComponentConstants.Update.COMPONENT_NAME)) {
                return new UpdateImportComponent(xmlElement);
            }
            if (xmlElement.getNodeName().equals(MercuriusDataImportComponentConstants.InsertUpdate.COMPONENT_NAME)) {
                return new InsertUpdateImportComponent(xmlElement);
            }
            if (xmlElement.getNodeName().equals(MercuriusDataImportComponentConstants.Remove.COMPONENT_NAME)) {
                return new RemoveImportComponent(xmlElement);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            log.append(IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>");
            return null;
        }
        LOGGER.error(IMPORT_ERROR_PREFIX + "no available import configuration \"{}\"", xmlElement.getNodeName());
        log.append(IMPORT_ERROR_PREFIX + "no available import configuration \"" + xmlElement.getNodeName() + "\"<br>");
        return null;
    }

    /**
     * Import components
     * @param importComponent Import component
     * @param log Log
     */
    private void importComponent(AbstractImportComponent importComponent, StringBuilder log) {
        try {
            if (importComponent instanceof InsertUpdateImportComponent) {
                insertUpdateComponent((InsertUpdateImportComponent) importComponent, log);
                return;
            }
            if (importComponent instanceof InsertImportComponent) {
                insertComponent((InsertImportComponent) importComponent, log);
                return;
            }
            if (importComponent instanceof UpdateImportComponent) {
                updateComponent((UpdateImportComponent) importComponent, log);
                return;
            }
            if (importComponent instanceof RemoveImportComponent) {
                removeComponent((RemoveImportComponent) importComponent, log);
                return;
            }
        } catch (IllegalAccessException exception) {
            log.append(IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>");
            LOGGER.error(exception);
        } catch (InstantiationException exception) {
            log.append(IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>");
            LOGGER.error(exception);
        } catch (Exception exception) {
            log.append(IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>");
            LOGGER.error(exception);
        }
    }

    /**
     * Insert component
     * @param importComponent Insert import component
     * @param log Log
     */
    private void insertComponent(InsertImportComponent importComponent, StringBuilder log) throws IllegalAccessException, InstantiationException {
        Class entityClass = entityReflectionService.getEntityClassByEntityName(importComponent.getEntityName());
        for (InsertValue insertValue : importComponent.getValues()) {
            Object entityObject = entityClass.newInstance();
            List<ImportColumn> importColumns = new ArrayList<>(importComponent.getCommonColumns());
            importColumns.addAll(insertValue.getColumns());
            populateObject(entityObject, importColumns, entityClass, log);
            entityService.save((AbstractEntity) entityObject);
        }
    }

    /**
     * Update component
     * @param importComponent Insert import component
     * @param log Log
     */
    private void updateComponent(UpdateImportComponent importComponent, StringBuilder log) {
        Class entityClass = entityReflectionService.getEntityClassByEntityName(importComponent.getEntityName());
        /** Load data */
        if (importComponent.getValues().isEmpty()) {
            List<AbstractEntity> updatingData = loadUpdatingData(importComponent, null);
            List<ImportColumn> importColumns = new ArrayList<>(importComponent.getCommonColumns());
            /** Populate object */
            for (AbstractEntity entityObject : updatingData) {
                populateObject(entityObject, importColumns, entityClass, log);
                entityService.save(entityObject);
            }
        } else {
            for (UpdateValue updateValue : importComponent.getValues()) {
                List<ImportColumn> importColumns = new ArrayList<>(importComponent.getCommonColumns());
                importColumns.addAll(updateValue.getColumns());
                List<AbstractEntity> updatingData = loadUpdatingData(importComponent, updateValue);
                /** Populate objects */
                for (AbstractEntity entityObject : updatingData) {
                    populateObject(entityObject, importColumns, entityClass, log);
                    entityService.save(entityObject);
                }
            }
        }
    }

    /**
     * Insert-update component
     * @param importComponent Import component
     * @param log Log
     */
    private void insertUpdateComponent(InsertUpdateImportComponent importComponent, StringBuilder log) throws IllegalAccessException, InstantiationException {
        Class entityClass = entityReflectionService.getEntityClassByEntityName(importComponent.getEntityName());
        for (InsertValue insertValue : importComponent.getValues()) {
            List<AbstractEntity> updatingData = loadInsertUpdatingData(importComponent, insertValue);
            List<ImportColumn> importColumns = new ArrayList<>(importComponent.getCommonColumns());
            importColumns.addAll(insertValue.getColumns());
            if (updatingData.isEmpty()) {
                /** Create a new instance of entity */
                Object entityObject = entityClass.newInstance();
                populateObject(entityObject, importColumns, entityClass, log);
                entityService.save((AbstractEntity) entityObject);
            } else {
                for (AbstractEntity entityObject : updatingData) {
                    populateObject(entityObject, importColumns, entityClass, log);
                    entityService.save(entityObject);
                }
            }
        }
    }

    /**
     * Remove component
     * @param importComponent Import component
     * @param log Log
     */
    private void removeComponent(RemoveImportComponent importComponent, StringBuilder log) {
        List<AbstractEntity> removingData = Collections.emptyList();
        Class entityClass = entityReflectionService.getEntityClassByEntityName(importComponent.getEntityName());
        /** Load data */
        if (StringUtils.isNotEmpty(importComponent.getTextQuery())) {
            removingData = entityService.getListResultByQuery(importComponent.getTextQuery(), entityClass);
        } else {
           removingData = entityService.getListResultByCriteria(entityClass, new String[]{},
                    buildCriteriaParameters(entityClass, importComponent.getCriterias()));
        }
        entityService.removeAll(removingData);
    }

    /**
     * Populate object
     * @param entityObject Entity object
     * @param importColumns Import column
     * @param entityClass Entity class
     * @param log Log
     * @return Populated object
     */
    private Object populateObject(Object entityObject, List<ImportColumn> importColumns, Class entityClass, StringBuilder log) {
        try {
            for (ImportColumn importColumn : importColumns) {
                Field field = entityReflectionService.getField(entityClass, importColumn.getProperty());
                Method setMethod = getSetMethod(entityClass, importColumn.getProperty());
                setPropertyValue(importColumn, entityObject, field, setMethod);
            }
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
            log.append(IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>");
            LOGGER.error(exception);
        } catch (SecurityException exception) {
            exception.printStackTrace();
            log.append(IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>");
            LOGGER.error(exception);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.append(IMPORT_ERROR_PREFIX + exception.getMessage() + "<br>");
            LOGGER.error(exception);
        }
        return entityObject;
    }

    /**
     * Get set method
     * @param entityClass Entity class
     * @param property Entity property
     */
    private Method getSetMethod(Class entityClass, String property) throws NoSuchMethodException {
        for (Method method : entityClass.getMethods()) {
            if (method.getName().equals(SET_METHOD_PREFIX + StringUtils.capitalize(property))) {
                if (method.getParameterCount() == 1) {
                    return method;
                }
            }
        }
        throw new NoSuchMethodException(SET_METHOD_PREFIX +  StringUtils.capitalize(property));
    }

    /**
     * Load updating data
     * @param importComponent Import component
     * @param updateValue Update value
     * @return Updating data
     */
    private List<AbstractEntity> loadUpdatingData(UpdateImportComponent importComponent, UpdateValue updateValue) {
        Class entityClass = entityReflectionService.getEntityClassByEntityName(importComponent.getEntityName());
        List<CriteriaComponent> criteriaValues = new ArrayList<>();
        /** Check common search */
        if (importComponent.getCommonSearch() != null) {
            if (importComponent.getCommonSearch().getTextQuery() != null) {
                return entityService.getListResultByQuery(importComponent.getCommonSearch().getTextQuery(), entityClass);
            } else {
                criteriaValues.addAll(importComponent.getCommonSearch().getCriterias());
            }
        }
        /** Check value */
        if (updateValue != null) {
            if (updateValue.getSearch() != null) {
                if (updateValue.getSearch().getTextQuery() != null) {
                    return entityService.getListResultByQuery(updateValue.getSearch().getTextQuery(), entityClass);
                } else {
                    criteriaValues.addAll(updateValue.getSearch().getCriterias());
                }
            }
        }
        /** Check import columns */
        for (ImportColumn commonImportColumn : importComponent.getCommonColumns()) {
            if (commonImportColumn.isIncludeInSearch()) {
                criteriaValues.add(new CriteriaComponent(commonImportColumn.getProperty(),
                        new CriteriaValueComponent(commonImportColumn.getValueImportBeanName(),
                                commonImportColumn.getRawValue(), CriteriaValueType.EQUALS.getValue()))
                );
            }
        }
        for (ImportColumn importColumn : updateValue.getColumns()) {
            if (importColumn.isIncludeInSearch()) {
                criteriaValues.add(new CriteriaComponent(importColumn.getProperty(),
                        new CriteriaValueComponent(importColumn.getValueImportBeanName(),
                                importColumn.getRawValue(), CriteriaValueType.EQUALS.getValue()))
                );
            }
        }
        /** Search updating data */
        return entityService.getListResultByCriteria(entityClass, new String[]{},
                buildCriteriaParameters(entityClass, criteriaValues));
    }

    /**
     * Load insert-updating data
     * @param importComponent Import component
     * @param insertValue Insert value
     * @return Updating data
     */
    private List<AbstractEntity> loadInsertUpdatingData(InsertUpdateImportComponent importComponent, InsertValue insertValue) {
        Class entityClass = entityReflectionService.getEntityClassByEntityName(importComponent.getEntityName());
        List<CriteriaComponent> criteriaValues = new ArrayList<>();
        /** Check import columns */
        for (ImportColumn commonImportColumn : importComponent.getCommonColumns()) {
            if (commonImportColumn.isIncludeInSearch()) {
                criteriaValues.add(new CriteriaComponent(commonImportColumn.getProperty(),
                        new CriteriaValueComponent(commonImportColumn.getValueImportBeanName(),
                                commonImportColumn.getRawValue(), CriteriaValueType.EQUALS.getValue()))
                );
            }
        }
        for (ImportColumn importColumn : insertValue.getColumns()) {
            if (importColumn.isIncludeInSearch()) {
                criteriaValues.add(new CriteriaComponent(importColumn.getProperty(),
                        new CriteriaValueComponent(importColumn.getValueImportBeanName(),
                                importColumn.getRawValue(), CriteriaValueType.EQUALS.getValue()))
                );
            }
        }
        /** Search updating data */
        return entityService.getListResultByCriteria(entityClass, new String[]{},
                buildCriteriaParameters(entityClass, criteriaValues));
    }

    /**
     * Build criteria parameters
     * @param entityClass Entity class
     * @param criteriaComponents Criteria components
     * @return Array of criteria values
     */
    private CriteriaParameter[] buildCriteriaParameters(Class entityClass, List<CriteriaComponent> criteriaComponents) {
        List<CriteriaParameter> criteriaParameters = new ArrayList<>();

        for (CriteriaComponent criteriaComponent : criteriaComponents) {
            if (!criteriaComponent.getCriteriaValues().isEmpty()) {
                List<CriteriaValue> criteriaValues = new ArrayList<>();
                /** Create criteria values */
                for (CriteriaValueComponent criteriaValueComponent : criteriaComponent.getCriteriaValues()) {
                    CriteriaValueType criteriaValueType = CriteriaValueType.valueFromString(criteriaValueComponent.getType());
                    if (criteriaValueType != null) {
                        try {
                            if (criteriaValueComponent.getValueImportBeanName() != null) {
                                Field field = entityReflectionService.getField(entityClass, criteriaComponent.getProperty());
                                ValueImportBean valueImportBean = ApplicationContextProvider.getBean(criteriaValueComponent.getValueImportBeanName(),
                                        ValueImportBean.class);
                                criteriaValues.add(new CriteriaValue(criteriaValueType,
                                        valueImportBean.getValueByString(criteriaValueComponent.getRawValue(), field, null)));
                            } else {
                                Field field = entityReflectionService.getField(entityClass, criteriaComponent.getProperty());
                                criteriaValues.add(new CriteriaValue(criteriaValueType,
                                        getPrimitiveValue(field.getType(), criteriaValueComponent.getRawValue())));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                /** Create criteria parameter */
                if (!criteriaValues.isEmpty()) {
                    criteriaParameters.add(new CriteriaParameter(criteriaComponent.getProperty(), criteriaValues));
                }
            }
        }
        return criteriaParameters.toArray(new CriteriaParameter[criteriaParameters.size()]);
    }

    /**
     * Set property value
     * @param importColumn Import column
     * @param entity Entity value
     * @param field Property field
     * @param method Set method
     */
    private void setPropertyValue(ImportColumn importColumn, Object entity, Field field, Method method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class parameterType = field.getType();
        /** Insert value to the object */
        if (importColumn.getValueImportBeanName() != null) {
            setCompositeValue(importColumn.getRawValue(), entity, field, method, importColumn.getValueImportBeanName());
        } else {
            setPrimitiveValue(parameterType, importColumn.getRawValue(), entity, field, method);
        }
    }

    /**
     * Set primitive value
     * @param valueClass Value class
     * @param rawValue Raw value (string)
     * @param entityObject Entity object
     * @param field Property field
     * @param method Set method
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void setPrimitiveValue(Class valueClass, String rawValue, Object entityObject, Field field, Method method) throws InvocationTargetException, IllegalAccessException {
        method.invoke(entityObject, getPrimitiveValue(valueClass, rawValue));
    }

    /**
     * Get primitive value
     * @param valueClass Value class
     * @param rawValue Raw value
     * @return Primitive object
     */
    private Object getPrimitiveValue(Class valueClass, String rawValue) {
        /** String value */
        if (valueClass.equals(String.class)) {
            return rawValue;
        }
        /** Long value */
        if (valueClass.equals(Long.class)) {
            return Long.valueOf(rawValue);
        }
        /** Integer value */
        if (valueClass.equals(Integer.class)) {
            return Integer.valueOf(rawValue);
        }
        /** Short value */
        if (valueClass.equals(Short.class)) {
            return Short.valueOf(rawValue);
        }
        /** Float value */
        if (valueClass.equals(Float.class)) {
            return Float.valueOf(rawValue);
        }
        /** Double value */
        if (valueClass.equals(Double.class)) {
            return Double.valueOf(rawValue);
        }
        /** Boolean value */
        if (valueClass.equals(Boolean.class)) {
            return Boolean.valueOf(rawValue);
        }
        /** Big integer value */
        if (valueClass.equals(BigInteger.class)) {
            return new BigInteger(rawValue);
        }
        /** Big decimal value */
        if (valueClass.equals(BigDecimal.class)) {
            return new BigDecimal(rawValue);
        }
        throw new IllegalArgumentException("Class " + valueClass.getCanonicalName() + " is not supported " +
                "as a primitive type value");
    }

    /**
     * Set primitive value
     * @param rawValue Raw value (string)
     * @param entityObject Entity object
     * @param field Property field
     * @param method Set method
     * @param beanName Bean name
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void setCompositeValue(String rawValue, Object entityObject, Field field, Method method, String beanName) throws InvocationTargetException, IllegalAccessException {
        ValueImportBean valueImportBean = (ValueImportBean) ApplicationContextProvider.getBean(beanName);
        method.invoke(entityObject, valueImportBean.getValueByString(rawValue, field, entityObject));
    }
}
