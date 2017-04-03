package org.mercuriusframework.dataimport.services.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.dataimport.components.AbstractImportComponent;
import org.mercuriusframework.dataimport.components.common.ImportColumn;
import org.mercuriusframework.dataimport.components.insert.InsertImportComponent;
import org.mercuriusframework.dataimport.components.insert.InsertValue;
import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.mercuriusframework.dataimport.services.DataImportService;
import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.helpers.ApplicationContextProvider;
import org.mercuriusframework.services.AnnotationService;
import org.mercuriusframework.services.EntityService;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
     * Annotation service
     */
    @Autowired
    @Qualifier("annotationService")
    protected AnnotationService annotationService;

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
                    if (!annotationService.isEntityClassExist(component.getEntityName())) {
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
            if (importComponent instanceof InsertImportComponent) {
                insertComponent((InsertImportComponent) importComponent, log);
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
        Class entityClass = annotationService.getEntityClassByEntityName(importComponent.getEntityName());
        Method[] methods = entityClass.getMethods();
        for (InsertValue insertValue : importComponent.getValues()) {
            try {
                Object entity = entityClass.newInstance();
                /** Common columns */
                for (ImportColumn commonImportColumn : importComponent.getCommonColumns()) {
                    setPropertyValue(commonImportColumn, entity, methods);
                }
                /** Import columns */
                for (ImportColumn importColumn : insertValue.getColumns()) {
                    setPropertyValue(importColumn, entity, methods);
                }
                entityService.save((AbstractEntity) entity);
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
        }
    }

    /**
     * Get method
     * @param methods Available methods
     * @param property Entity property
     */
    private Method getMethod(Method[] methods, String property) throws NoSuchMethodException {
        for (Method method : methods) {
            if (method.getName().equals(SET_METHOD_PREFIX +  StringUtils.capitalize(property))) {
                if (method.getParameterCount() == 1) {
                    return method;
                }
            }
        }
        throw new NoSuchMethodException(SET_METHOD_PREFIX +  StringUtils.capitalize(property));
    }

    /**
     * Set property value
     * @param importColumn Import column
     * @param entity Entity value
     * @param methods Available methods
     */
    private void setPropertyValue(ImportColumn importColumn, Object entity, Method[] methods) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getMethod(methods, importColumn.getProperty());
        Class parameterType = method.getParameterTypes()[0];
        /** Insert value to the object */
        if (importColumn.getValueImportBeanName() != null) {
            setCompositeValue(importColumn.getRawValue(), entity, method, importColumn.getValueImportBeanName());
        } else {
            setPrimitiveValue(parameterType, importColumn.getRawValue(), entity, method);
        }
    }

    /**
     * Set primitive value
     * @param valueClass Value class
     * @param rawValue Raw value (string)
     * @param entityObject Entity object
     * @param setMethod Set method
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void setPrimitiveValue(Class valueClass, String rawValue, Object entityObject, Method setMethod) throws InvocationTargetException, IllegalAccessException {
        /** String value */
        if (valueClass.equals(String.class)) {
            setMethod.invoke(entityObject, rawValue);
            return;
        }
        /** Long value */
        if (valueClass.equals(Long.class)) {
            setMethod.invoke(entityObject, Long.valueOf(rawValue));
            return;
        }
        /** Integer value */
        if (valueClass.equals(Integer.class)) {
            setMethod.invoke(entityObject, Integer.valueOf(rawValue));
            return;
        }
        /** Short value */
        if (valueClass.equals(Short.class)) {
            setMethod.invoke(entityObject, Short.valueOf(rawValue));
            return;
        }
        /** Float value */
        if (valueClass.equals(Float.class)) {
            setMethod.invoke(entityObject, Float.valueOf(rawValue));
            return;
        }
        /** Double value */
        if (valueClass.equals(Double.class)) {
            setMethod.invoke(entityObject, Double.valueOf(rawValue));
            return;
        }
        /** Boolean value */
        if (valueClass.equals(Boolean.class)) {
            setMethod.invoke(entityObject, Boolean.valueOf(rawValue));
            return;
        }
        /** Big integer value */
        if (valueClass.equals(BigInteger.class)) {
            setMethod.invoke(entityObject, new BigInteger(rawValue));
            return;
        }
        /** Big decimal value */
        if (valueClass.equals(BigDecimal.class)) {
            setMethod.invoke(entityObject, new BigDecimal(rawValue));
            return;
        }
    }

    /**
     * Set primitive value
     * @param rawValue Raw value (string)
     * @param entityObject Entity object
     * @param setMethod Set method
     * @param beanName Bean name
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void setCompositeValue(String rawValue, Object entityObject, Method setMethod, String beanName) throws InvocationTargetException, IllegalAccessException {
        ValueImportBean valueImportBean = (ValueImportBean) ApplicationContextProvider.getBean(beanName);
        setMethod.invoke(entityObject, valueImportBean.findValueByString(rawValue, setMethod));
    }
}
