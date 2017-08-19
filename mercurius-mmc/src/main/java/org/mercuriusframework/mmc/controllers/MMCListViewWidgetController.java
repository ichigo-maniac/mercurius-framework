package org.mercuriusframework.mmc.controllers;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mercuriusframework.enums.CriteriaValueType;
import org.mercuriusframework.mmc.constants.MercuriusMMCConfigurationParameters;
import org.mercuriusframework.mmc.constants.MercuriusMMCConstants;
import org.mercuriusframework.mmc.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.mmc.controllers.response.CharArrayWriterResponse;
import org.mercuriusframework.mmc.dto.FilterValueContainer;
import org.mercuriusframework.mmc.dto.LoadWidgetResult;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.mmc.enums.LoadWidgetResultStatus;
import org.mercuriusframework.mmc.enums.WidgetType;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.mmc.services.MMCApplicationService;
import org.mercuriusframework.mmc.services.MMCFilterService;
import org.mercuriusframework.mmc.widgets.listview.ListViewWidget;
import org.mercuriusframework.providers.MessageSourceProvider;
import org.mercuriusframework.services.EntityReflectionService;
import org.mercuriusframework.services.ConfigurationService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.query.CriteriaParameter;
import org.mercuriusframework.services.query.CriteriaValue;
import org.mercuriusframework.services.query.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Mercurius manager console list view widget controller (mercurius-mmc)
 */
@Controller
@RequestMapping(MercuriusMMCConstants.URLS.BASE_WIDGETS_PATH)
public class MMCListViewWidgetController extends AbstractMMCViewWidgetController {

    /**
     * Constants
     */
    private static final String LIST_VIEW_TEMPLATE = "/WEB-INF/view/mmc/templates/list_view_template.jsp";
    private static final Integer PAGE_SIZE = 20;

    /**
     * User facade
     */
    @Autowired
    @Qualifier("userFacade")
    private UserFacade userFacade;

    /**
     * MMC application service
     */
    @Autowired
    @Qualifier("mmcApplicationService")
    private MMCApplicationService mmcApplicationService;

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    private EntityService entityService;

    /**
     * Entity reflection service
     */
    @Autowired
    @Qualifier("entityReflectionService")
    private EntityReflectionService entityReflectionService;

    /**
     * Configuration service
     */
    @Autowired
    @Qualifier("configurationService")
    private ConfigurationService configurationService;

    /**
     * MMC filter service
     */
    @Autowired
    @Qualifier("mmcFilterService")
    private MMCFilterService mmcFilterService;

    /**
     * Load widget
     * @param entityName Entity name
     * @param page Page
     * @param request Http-request
     * @param response Http-response
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = MercuriusMMCWidgetsConstants.ListView.WIDGET_NAME + "/{entityName}")
    @ResponseBody
    public LoadWidgetResult loadWidget(@PathVariable String entityName,
                                       @RequestParam(name = "renderFilter", defaultValue = "true") Boolean renderFilter,
                                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                       @RequestParam(name = "filterValuesJsonArray", required = false) String filterValues,
                                       HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userFacade.isCurrentUserEmployee()) {
            return new LoadWidgetResult(LoadWidgetResultStatus.ERROR,
                    MessageSourceProvider.getMessage(USER_IS_NOT_EMPLOYEE_MESSAGE));
        }
        ListViewWidget listViewWidget = (ListViewWidget) mmcApplicationService.getEntityWidgetXmlElement(WidgetType.LIST_VIEW, entityName);
        if (listViewWidget == null) {
            return new LoadWidgetResult(LoadWidgetResultStatus.NOT_FOUND,
                    MessageSourceProvider.getMessage(WIDGET_HAS_NOT_BEEN_FOUND_MESSAGE,
                            MercuriusMMCWidgetsConstants.ListView.WIDGET_NAME, entityName));
        }
        /** Load data */
        Class entityClass = entityReflectionService.getEntityClassByEntityName(entityName);
        if (entityClass == null) {
            return new LoadWidgetResult(LoadWidgetResultStatus.NOT_FOUND,
                    MessageSourceProvider.getMessage(WIDGET_HAS_NOT_BEEN_FOUND_MESSAGE,
                            MercuriusMMCWidgetsConstants.ListView.WIDGET_NAME, entityName));
        }
        Integer pageSize = configurationService.getIntParameter(MercuriusMMCConfigurationParameters.LIST_VIEW_PAGE_SIZE, PAGE_SIZE);
        CriteriaParameter[] criteriaParameters = parseFilterValues(entityClass, filterValues);
        PageableResult<ProductEntity> loadedData = entityService.getPageableResultByCriteria(page, pageSize, listViewWidget.getFetchFields(),
                entityClass, criteriaParameters);
        /** Transform widget */
        String rendererResult = renderListViewFragment(request, response, renderFilter, filterValues, entityName, listViewWidget, loadedData);
        if (StringUtils.isEmpty(rendererResult)) {
            return new LoadWidgetResult(LoadWidgetResultStatus.ERROR,
                    MessageSourceProvider.getMessage(WIDGET_RENDER_ERROR_MESSAGE));
        } else {
            return new LoadWidgetResult(rendererResult, LoadWidgetResultStatus.CORRECT);
        }
    }

    /**
     * Parse filter values
     * @param entityClass Entity class
     * @param filterValues Filter values (json)
     * @return Filter values (list of containers)
     */
    private CriteriaParameter[] parseFilterValues(Class entityClass, String filterValues) {
        if (StringUtils.isEmpty(filterValues)) {
            return new CriteriaParameter[0];
        } else {
            List<FilterValueContainer> filterValuesContainers = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(filterValues);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                FilterValueContainer filterValueContainer = new FilterValueContainer();
                filterValueContainer.setProperty(jsonObject.getString("property"));
                filterValueContainer.setCriteriaValueType(CriteriaValueType.valueFromString(jsonObject.getString("criteria")));
                Class propertyClass = entityReflectionService.getFieldClass(entityClass, filterValueContainer.getProperty());
                if (propertyClass == null) {
                    continue;
                }
                filterValueContainer.setValue(parseValue(propertyClass, jsonObject.get("value")));
                if (filterValueContainer.isValid()) {
                    filterValuesContainers.add(filterValueContainer);
                }
            }
            /** Create criteria values */
            Map<String, List<FilterValueContainer>> filterValuesMap = new HashMap<>();
            filterValuesContainers.stream().forEach(value -> {
                if (filterValuesMap.containsKey(value.getProperty())) {
                    filterValuesMap.get(value.getProperty()).add(value);
                } else {
                    List<FilterValueContainer> newList = new ArrayList<FilterValueContainer>();
                    newList.add(value);
                    filterValuesMap.put(value.getProperty(), newList);
                }
            });
            List<CriteriaParameter> criteriaParameters = new ArrayList<>(filterValuesMap.keySet().size());
            for (String property : filterValuesMap.keySet()) {
                List<CriteriaValue> criteriaValues = new ArrayList<>();
                for (FilterValueContainer filterValueContainer : filterValuesMap.get(property)) {
                    criteriaValues.add(new CriteriaValue(filterValueContainer.getCriteriaValueType(), filterValueContainer.getValue()));
                }
                criteriaParameters.add(new CriteriaParameter(property, criteriaValues.toArray(new CriteriaValue[criteriaValues.size()])));

            }
            return criteriaParameters.toArray(new CriteriaParameter[criteriaParameters.size()]);
        }
    }

    /**
     * Parse value
     * @param valueType Value class
     * @param rawValue Raw value
     * @return Parsed value
     */
    private Object parseValue(Class valueType, Object rawValue) {
        if (rawValue instanceof String) {
            if (String.class.equals(valueType)) {
                return rawValue;
            }
            if (Long.class.equals(valueType)) {
                return Long.valueOf((String)rawValue);
            }
            if (Integer.class.equals(valueType)) {
                return Integer.valueOf((String)rawValue);
            }
            if (Float.class.equals(valueType)) {
                return Float.valueOf((String)rawValue);
            }
            if (Double.class.equals(valueType)) {
                return Double.valueOf((String)rawValue);
            }
            if (Boolean.class.equals(valueType)) {
                return Boolean.valueOf((String)rawValue);
            }
        }
        if (rawValue instanceof JSONObject) {
            JSONObject object = (JSONObject) rawValue;
            if (object.has("entityName") && object.has("values")) {
                Class entityClass = entityReflectionService.getEntityClassByEntityName(object.getString("entityName"));
                JSONArray valuesArray = object.getJSONArray("values");
                List entityUuids = valuesArray.toList();
                return entityService.findByUuids(entityUuids, entityClass);
            }
        }
        return null;
    }


    /**
     * Render list view widget
     * @param request Http-request
     * @param response Http-response
     * @param renderFilter Render filter
     * @param selectedFilterValues Selected filter values
     * @param entityName Entity name
     * @param listViewWidget List view widget
     * @param dataResult Loaded data
     * @return Rendered html-fragment
     * @throws ServletException
     * @throws IOException
     */
    private String renderListViewFragment(HttpServletRequest request, HttpServletResponse response, Boolean renderFilter,
                                          String selectedFilterValues,
                                          String entityName, ListViewWidget listViewWidget, PageableResult dataResult) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(LIST_VIEW_TEMPLATE);
        CharArrayWriterResponse customResponse  = new CharArrayWriterResponse(response);
        request.setAttribute("entityName", entityName);
        request.setAttribute("listView", listViewWidget);
        request.setAttribute("dataResult", dataResult);
        request.setAttribute("selectedFilterValues", selectedFilterValues);
        if (listViewWidget.getFiltersView() != null && renderFilter) {
            request.setAttribute("filters", mmcFilterService.buildFilters(entityName, listViewWidget.getFiltersView().getFilters()));
        }
        requestDispatcher.forward(request, customResponse);
        return customResponse.getOutput();
    }
}
