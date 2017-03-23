package org.mercuriusframework.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.components.AbstractImportComponent;
import org.mercuriusframework.components.insert.InsertImportComponent;
import org.mercuriusframework.constants.MercuriusDataImportComponentConstants;
import org.mercuriusframework.services.DataImportService;
import org.springframework.stereotype.Service;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Data import service
 */
@Service("dataImportService")
public class DataImportServiceImpl implements DataImportService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Import data
     * @param xmlStringValue Xml string value
     * @return Log result
     */
    @Override
    public String importData(String xmlStringValue) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlStringValue.getBytes());
        /** Create xml builder */
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = null;
        try {
            xmlBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException exception) {
            LOGGER.error(exception);
            return exception.getMessage();
        }
        /** Parse string value */
        Document document = null;
        try {
            document = xmlBuilder.parse(inputStream);
        } catch (SAXException exception) {
            LOGGER.error(exception);
            return exception.getMessage();
        } catch (IOException exception) {
            LOGGER.error(exception);
            return exception.getMessage();
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
            LOGGER.error("IMPORT ERROR - root element must be \"{}\"", MercuriusDataImportComponentConstants.DataImport.COMPONENT_NAME);
            return "IMPORT ERROR - root element must be \"" + MercuriusDataImportComponentConstants.DataImport.COMPONENT_NAME + "\"";
        }
        /** Create components */
        List<AbstractImportComponent> components = new ArrayList<>(configElement.getChildNodes().getLength());
        StringBuilder log = new StringBuilder();
        NodeList childNodes = configElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                AbstractImportComponent component = parseNode(childNodes.item(i), log);
                if (component != null) {
                    components.add(component);
                }
            }
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
            LOGGER.error(exception);
            log.append(exception.getMessage() + "\n");
            return null;
        }
        LOGGER.error("IMPORT ERROR - no available import configuration \"{}\"", xmlElement.getNodeName());
        log.append("IMPORT ERROR - no available import configuration \"" + xmlElement.getNodeName() + "\"");
        return null;
    }
}
