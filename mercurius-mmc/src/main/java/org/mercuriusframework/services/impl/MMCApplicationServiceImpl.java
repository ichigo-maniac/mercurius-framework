package org.mercuriusframework.services.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.converters.impl.RoleEntityConverter;
import org.mercuriusframework.dto.EmployeeEntityDto;
import org.mercuriusframework.dto.MMCWidgetContainer;
import org.mercuriusframework.dto.RoleEntityDto;
import org.mercuriusframework.dto.RoleEntityNameContainer;
import org.mercuriusframework.entities.RoleEntity;
import org.mercuriusframework.enums.WidgetType;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.services.AnnotationService;
import org.mercuriusframework.services.MMCApplicationService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.widgets.Widget;
import org.mercuriusframework.widgets.listview.ListViewWidget;
import org.mercuriusframework.widgets.treenodesview.TreeNodesViewWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * MMC application service
 */
@Service("mmcApplicationService")
public class MMCApplicationServiceImpl implements MMCApplicationService {

    /**
     * Constants
     */
    private static final String CONFIG_PREFIX = "mmc-config/";
    private static final String CONFIG_SUFFIX = "mmc-config.xml";
    private static final String ROLES_SPLITTER = ",";
    private static final RoleEntityDto EMPTY_ROLE = RoleEntityDto.getEmptyRole();

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Containers map
     */
    private Map<RoleEntityDto, MMCWidgetContainer> treeNodesViewWidgetsContainers;
    private Map<RoleEntityNameContainer, MMCWidgetContainer> listViewWidgetsContainers;

    /** Widgets map */
    private Map<RoleEntityDto, Widget> treeNodesViewWidgets;
    private Map<RoleEntityNameContainer, Widget> listViewWidgets;

    /**
     * Build status
     */
    private boolean buildStatus = false;

    /**
     * Unique code entity service
     */
    @Autowired
    @Qualifier("uniqueCodeEntityService")
    protected UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Role entity converter
     */
    @Autowired
    @Qualifier("roleEntityConverter")
    protected RoleEntityConverter roleEntityConverter;

    /**
     * User facade
     */
    @Autowired
    @Qualifier("userFacade")
    protected UserFacade userFacade;

    /**
     * Annotation service
     */
    @Autowired
    @Qualifier("annotationService")
    protected AnnotationService annotationService;

    /**
     * Build application (if application has been built - nothing 's gonna happen)
     */
    @Override
    public synchronized void build() {
        if (buildStatus) {
            return;
        }
        /** Init widgets map collections */
        treeNodesViewWidgetsContainers = new ConcurrentHashMap<>();
        listViewWidgetsContainers = new ConcurrentHashMap<>();
        /** Parse documents */
        List<Document> xmlDocuments = getXmlDocuments();
        xmlDocuments.parallelStream().forEach(xmlDocument -> parseDocument(xmlDocument));
        /** Build widgets */
        buildWidgets();
        this.buildStatus = true;
    }

    /**
     * Rebuild application (if application hasn't been built - this call's building it)
     */
    @Override
    public synchronized void rebuild() {
        this.buildStatus = false;
        build();
    }

    /**
     * Build widgets
     */
    private void buildWidgets() {
        treeNodesViewWidgets = new ConcurrentHashMap<>();
        listViewWidgets = new ConcurrentHashMap<>();
        /** Tree views */
        for (RoleEntityDto role : treeNodesViewWidgetsContainers.keySet()) {
            MMCWidgetContainer container = treeNodesViewWidgetsContainers.get(role);
            treeNodesViewWidgets.put(role, new TreeNodesViewWidget(container.getXmlElement(), container.getPriority()));
        }
        /** List views */
        for (RoleEntityNameContainer roleEntity : listViewWidgetsContainers.keySet()) {
            MMCWidgetContainer container = listViewWidgetsContainers.get(roleEntity);
            listViewWidgets.put(roleEntity, new ListViewWidget(container.getXmlElement(), container.getPriority()));
        }
    }

    /**
     * Get config xml-documents
     * @return List of documents
     */
    private List<Document> getXmlDocuments() {
        File currentJarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
        File jarsLibDirectory = currentJarFile.getParentFile();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = null;
        try {
            xmlBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException exception) {
            LOGGER.error(exception);
            return null;
        }

        List<Document> result = new ArrayList<>();
        /** Investigate jar files for mmc-config/*mmc-config.xml files */
        for (File jarFile : jarsLibDirectory.listFiles()) {
            try (ZipFile jarZipFile = new ZipFile(jarFile)) {
                if (jarZipFile != null) {
                    Enumeration<? extends ZipEntry> entries = jarZipFile.entries();
                    if (entries != null) {
                        while (entries.hasMoreElements()) {
                            /** Zip entry starts with "mmc-config/" and ends with "mmc-config.xml" */
                            ZipEntry entry = entries.nextElement();
                            if (entry.getName().startsWith(CONFIG_PREFIX) && entry.getName().endsWith(CONFIG_SUFFIX)) {
                                /** Create input stream ro byte array */
                                Document document = xmlBuilder.parse(jarZipFile.getInputStream(entry));
                                result.add(document);
                            }
                        }
                    }
                }
            } catch (IOException exception) {
                LOGGER.error(exception);
            } catch (SAXException exception) {
                LOGGER.error(exception);
            }
        }
        return result;
    }

    /**
     * Parse XML document
     * @param xmlDocument XML document
     */
    private void parseDocument(Document xmlDocument) {
        Element configElement = xmlDocument.getDocumentElement();
        if (!configElement.getNodeName().equals(MercuriusMMCWidgetsConstants.Config.WIDGET_NAME)) {
            LOGGER.error("MMC CONFIG ERROR - root element must be \"{}\"", MercuriusMMCWidgetsConstants.Config.WIDGET_NAME);
            return;
        }
        parseConfig(configElement);
    }

    /**
     * Parse configuration element
     * @param configElement Configuration element (root)
     */
    private void parseConfig(Element configElement) {
        parseTreeViews(configElement);
        parseListViews(configElement);
    }

    /**
     * Parse tree views
     * @param configElement Config xml element
     */
    private void parseTreeViews(Element configElement) {
        NodeList treeViews = configElement.getElementsByTagName(MercuriusMMCWidgetsConstants.TreeNodesView.WIDGET_NAME);
        for (int i = 0; i < treeViews.getLength(); i++) {
            Node treeViewNode = treeViews.item(i);
            /** Priority */
            Node priorityNode = treeViewNode.getAttributes().getNamedItem(MercuriusMMCWidgetsConstants.TreeNodesView.PRIORITY);
            String priorityValue = priorityNode != null ? priorityNode.getNodeValue() : "0";
            Integer priority = Integer.valueOf(priorityValue);
            /** Roles */
            Node rolesNode = treeViewNode.getAttributes().getNamedItem(MercuriusMMCWidgetsConstants.TreeNodesView.ROLES);
            String rolesValue = rolesNode != null ? rolesNode.getNodeValue() : null;
            /** Update map */
            updateViewMap(rolesValue, treeNodesViewWidgetsContainers, new MMCWidgetContainer(treeViewNode, priority));
        }
    }

    /**
     * Parse list views
     * @param configElement Config xml element
     */
    private void parseListViews(Element configElement) {
        NodeList listViews = configElement.getElementsByTagName(MercuriusMMCWidgetsConstants.ListView.WIDGET_NAME);
        for (int i = 0; i < listViews.getLength(); i++) {
            Node listViewNode = listViews.item(i);
            /** Priority */
            Node priorityNode = listViewNode.getAttributes().getNamedItem(MercuriusMMCWidgetsConstants.ListView.PRIORITY);
            String priorityValue = priorityNode != null ? priorityNode.getNodeValue() : "0";
            Integer priority = Integer.valueOf(priorityValue);
            /** Roles */
            Node rolesNode = listViewNode.getAttributes().getNamedItem(MercuriusMMCWidgetsConstants.ListView.ROLES);
            String rolesValue = rolesNode != null ? rolesNode.getNodeValue() : null;
            /** Entity name */
            Node entityNameNode = listViewNode.getAttributes().getNamedItem(MercuriusMMCWidgetsConstants.ListView.ENTITY_NAME);
            String entityName = entityNameNode != null ? entityNameNode.getNodeValue() : null;
            if (StringUtils.isEmpty(entityName)) {
                LOGGER.error("MMC CONFIG ERROR - element \"{}\"  must have \"{}\" attribute ",
                        MercuriusMMCWidgetsConstants.ListView.WIDGET_NAME, MercuriusMMCWidgetsConstants.ListView.ENTITY_NAME);
                continue;
            } else {
                if (!annotationService.isEntityClassExist(entityName)) {
                    LOGGER.error("MMC CONFIG ERROR - entity \"{}\" doesn't exist (no class with annotation @Entity(name = \"{}\") ",
                            entityName, entityName);
                    continue;
                }
            }
            /** Update map */
            updateEntityViewMap(rolesValue, entityName, listViewWidgetsContainers, new MMCWidgetContainer(listViewNode, priority));
        }
    }

    /**
     * Get rows from string
     * @param value Raw roles string
     * @return List of roles data transfer objects
     */
    private List<RoleEntityDto> getRolesFromStringValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return Collections.emptyList();
        }
        List<RoleEntityDto> roles = new ArrayList<>();
        String[] rawRoles = value.split(ROLES_SPLITTER);
        for (String rawRole : rawRoles) {
            if (!StringUtils.isEmpty(rawRole.trim())) {
                RoleEntity role = uniqueCodeEntityService.getEntityByCode(rawRole, RoleEntity.class);
                if (role == null) {
                    LOGGER.error("MMC CONFIG ERROR - no role with code \"{}\" ", rawRole);
                    continue;
                }
                roles.add(roleEntityConverter.convert(role));
            }
        }
        return roles;
    }

    /**
     * Update view map
     * @param rawRoles Raw roles string
     * @param viewMap View map
     * @param widgetContainer Widget container
     */
    private void updateViewMap(String rawRoles, Map<RoleEntityDto, MMCWidgetContainer> viewMap, MMCWidgetContainer widgetContainer) {
        if (rawRoles == null) {
            updateViewMap(viewMap, widgetContainer);
            return;
        }
        List<RoleEntityDto> roles = getRolesFromStringValue(rawRoles);
        updateViewMap(viewMap, roles, widgetContainer);
    }

    /**
     * Update entity view map
     * @param rawRoles Raw roles string
     * @param entityName Entity name
     * @param viewMap View map
     * @param widgetContainer Widget container
     */
    private void updateEntityViewMap(String rawRoles, String entityName,
                                     Map<RoleEntityNameContainer, MMCWidgetContainer> viewMap, MMCWidgetContainer widgetContainer) {
        if (rawRoles == null) {
            updateEntityViewMap(entityName, viewMap, widgetContainer);
            return;
        }
        List<RoleEntityDto> roles = getRolesFromStringValue(rawRoles);
        updateEntityViewMap(entityName, viewMap, roles, widgetContainer);
    }

    /**
     * Update view map (for all)
     * @param viewMap View map
     * @param widgetContainer Widget container
     */
    private void updateViewMap(Map<RoleEntityDto, MMCWidgetContainer> viewMap, MMCWidgetContainer widgetContainer) {
        MMCWidgetContainer currentContainer = viewMap.get(EMPTY_ROLE);
        if (currentContainer == null) {
            viewMap.put(EMPTY_ROLE, widgetContainer);
        } else {
            if (widgetContainer.getPriority() > currentContainer.getPriority()) {
                viewMap.put(EMPTY_ROLE, widgetContainer);
            }
        }
    }

    /**
     * Update entity view map (for all)
     * @param entityName Entity name
     * @param viewMap View map
     * @param widgetContainer Widget container
     */
    private void updateEntityViewMap(String entityName, Map<RoleEntityNameContainer, MMCWidgetContainer> viewMap, MMCWidgetContainer widgetContainer) {
        MMCWidgetContainer currentContainer = viewMap.get(EMPTY_ROLE);
        if (currentContainer == null) {
            viewMap.put(new RoleEntityNameContainer(EMPTY_ROLE, entityName), widgetContainer);
        } else {
            if (widgetContainer.getPriority() > currentContainer.getPriority()) {
                viewMap.put(new RoleEntityNameContainer(EMPTY_ROLE, entityName), widgetContainer);
            }
        }
    }


    /**
     * Update view map
     * @param viewMap View map
     * @param roles Roles
     * @param widgetContainer Widget container
     */
    private void updateViewMap(Map<RoleEntityDto, MMCWidgetContainer> viewMap, List<RoleEntityDto> roles,
                               MMCWidgetContainer widgetContainer) {
        for (RoleEntityDto role : roles) {
            MMCWidgetContainer currentContainer = viewMap.get(role);
            if (currentContainer == null) {
                viewMap.put(role, widgetContainer);
            } else {
                if (widgetContainer.getPriority() > currentContainer.getPriority()) {
                    viewMap.put(role, widgetContainer);
                }
            }
        }
    }

    /**
     * Update entity view map
     * @param entityName Entity name
     * @param viewMap View map
     * @param roles Roles
     * @param widgetContainer Widget container
     */
    private void updateEntityViewMap(String entityName, Map<RoleEntityNameContainer, MMCWidgetContainer> viewMap, List<RoleEntityDto> roles,
                               MMCWidgetContainer widgetContainer) {
        for (RoleEntityDto role : roles) {
            MMCWidgetContainer currentContainer = viewMap.get(new RoleEntityNameContainer(role, entityName));
            if (currentContainer == null) {
                viewMap.put(new RoleEntityNameContainer(role, entityName), widgetContainer);
            } else {
                if (widgetContainer.getPriority() > currentContainer.getPriority()) {
                    viewMap.put(new RoleEntityNameContainer(role, entityName), widgetContainer);
                }
            }
        }
    }

    /**
     * Get widget xml element
     * @param widgetType Widget type
     * @return Widget xml element
     */
    @Override
    public Widget getWidgetXmlElement(WidgetType widgetType) {
        if (widgetType == null) {
            return null;
        }
        Map<RoleEntityDto, Widget> widgetContainerMap = getMapContainer(widgetType);
        if (widgetContainerMap == null) {
            return null;
        }
        EmployeeEntityDto currentEmployee = (EmployeeEntityDto) userFacade.getCurrentUser();
        if (currentEmployee == null) {
            return null;
        }
        List<Widget> widgetContainers = getWidgetContainers(widgetContainerMap, currentEmployee.getRoles());
        if (widgetContainers.isEmpty()) {
            return null;
        } else {
            widgetContainers.sort((first, second) -> first.getPriority().compareTo(second.getPriority()));
            return widgetContainers.get(0);
        }
    }

    /**
     * Get entity widget xml element
     * @param widgetType Widget type
     * @param entityName Entity name
     * @return Widget xml element
     */
    @Override
    public Widget getEntityWidgetXmlElement(WidgetType widgetType, String entityName) {
        if (widgetType == null) {
            return null;
        }
        Map<RoleEntityNameContainer, Widget> widgetContainerMap = getEntityMapContainer(widgetType);
        if (widgetContainerMap == null) {
            return null;
        }
        EmployeeEntityDto currentEmployee = (EmployeeEntityDto) userFacade.getCurrentUser();
        if (currentEmployee == null) {
            return null;
        }
        List<Widget> widgetContainers = getEntityWidgetContainers(widgetContainerMap, entityName, currentEmployee.getRoles());
        if (widgetContainers.isEmpty()) {
            return null;
        } else {
            widgetContainers.sort((first, second) -> first.getPriority().compareTo(second.getPriority()));
            return widgetContainers.get(0);
        }
    }

    /**
     * Get widget containers
     * @param widgetContainerMap Widget container map
     * @param roles List of roles
     * @return List of widget containers
     */
    private List<Widget> getWidgetContainers(Map<RoleEntityDto, Widget> widgetContainerMap, List<RoleEntityDto> roles) {
        List<Widget> result = new ArrayList<>();
        for (RoleEntityDto role : roles) {
            Widget widgetContainer = widgetContainerMap.get(role);
            if (widgetContainer != null) {
                result.add(widgetContainer);
            }
        }
        if (result.isEmpty()) {
            Widget widgetContainer = widgetContainerMap.get(EMPTY_ROLE);
            if (widgetContainer != null) {
                result.add(widgetContainer);
            }
        }
        return result;
    }

    /**
     * Get widget containers
     * @param widgetContainerMap Widget container map
     * @param roles List of roles
     * @return List of widget containers
     */
    private List<Widget> getEntityWidgetContainers(Map<RoleEntityNameContainer, Widget> widgetContainerMap,
                                                         String entityName, List<RoleEntityDto> roles) {
        List<Widget> result = new ArrayList<>();
        for (RoleEntityDto role : roles) {
            Widget widgetContainer = widgetContainerMap.get(new RoleEntityNameContainer(role, entityName));
            if (widgetContainer != null) {
                result.add(widgetContainer);
            }
        }
        if (result.isEmpty()) {
            Widget widgetContainer = widgetContainerMap.get(new RoleEntityNameContainer(EMPTY_ROLE, entityName));
            if (widgetContainer != null) {
                result.add(widgetContainer);
            }
        }
        return result;
    }


    /**
     * Get widget container map
     * @param widgetType Widget type
     * @return Widget container map
     */
    private Map<RoleEntityDto, Widget> getMapContainer(WidgetType widgetType) {
        if (widgetType == WidgetType.TREE_NODES_VIEW) {
            return treeNodesViewWidgets;
        }
        return null;
    }

    /**
     * Get entity widget container map
     * @param widgetType Widget type
     * @return Widget container map
     */
    private Map<RoleEntityNameContainer, Widget> getEntityMapContainer(WidgetType widgetType) {
        if (widgetType == WidgetType.LIST_VIEW) {
            return listViewWidgets;
        }
        return null;
    }

    /**
     * Is application has been build
     * @return Check result
     */
    @Override
    public boolean isApplicationBuilt() {
        return buildStatus;
    }
}
