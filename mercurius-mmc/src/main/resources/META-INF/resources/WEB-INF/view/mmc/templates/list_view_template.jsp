<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mmc-tags" uri="/WEB-INF/tlds/mmc-tags.tld" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="widgets" tagdir="/WEB-INF/tags/mmc/widgets" %>

<%-- Filter view --%>
<c:if test="${not empty filters}">
    <div class="panel panel-warning" style="width: 100%;margin-bottom: 0px;">
        <div class="panel-heading">
            <b style="font-size: 110%;">
                <spring:message code="mmc.filter.view.header"/>
            </b>
        </div>
        <%-- Filters --%>
        <div class="panel-body" style="padding: 0px;">
            <%-- Include on start filters --%>
            <div>
                <c:forEach var="filter" items="${filters}">
                    <%-- Hidden values --%>
                    <input type="hidden" class="hidden_filter_type" value='${filter.property}'>
                    <input id="filter_field_type_${filter.property}" type="hidden" value='${filter.fieldType}'>
                    <input id="filter_criteria_types_${filter.property}" type="hidden" value='${filter.jsonCriteriaTypes}'>
                    <input id="filter_label_${filter.property}" type="hidden" value="<c:out value="${filter.label}"/>">
                    <%-- Filter view --%>
                    <c:if test="${filter.includeOnStart == true}">
                        <div id="filter_${filter.property}" class="row" style="border-bottom: 1px solid #DDDDDD; padding: 10px 0px 10px 0px">
                            <input id="filter_current_number_${filter.property}" type="hidden" value="0"/>
                            <%-- Label --%>
                            <div class="col-md-3">
                                <h5 style="padding-left: 15px; color: black">
                                    <b><c:out value="${filter.label}"/></b>
                                </h5>
                            </div>
                            <%-- Criteria types --%>
                            <div id="filter_criterias_column_${filter.property}" class="col-md-2">
                                <select class="selectpicker" name="${filter.property}_criteriaSelector_0">
                                    <c:forEach var="criteriaType" items="${filter.criteriaTypes}">
                                        <option value="${criteriaType.value}">
                                            <c:out value="${criteriaType.value}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <%-- Value --%>
                            <div id="filter_field_column_${filter.property}" class="col-md-6">
                                <widgets:field_input fieldType="${filter.fieldType}" fieldName="${filter.property}"/>
                            </div>
                            <%-- Drop button --%>
                            <div class="col-md-1" style="text-align: center">
                                <img class="icon-button" style="margin-left: 5px; margin-top: 7px;" src="/resources/mmc_app/images/remove-filter.png" width="16"
                                     onclick="$('#filter_' + '${filter.property}').remove()"/>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
                <%-- All available filters --%>
                <div id="all_available_filters" class="row" style="padding: 10px 0px 10px 0px">
                    <div class="col-md-3">
                        <h5 style="padding-left: 15px; color: black">
                            <b><spring:message code="mmc.filter.view.available.filters.label"/></b>
                        </h5>
                    </div>
                    <%-- Available filters --%>
                    <div class="col-md-2">
                        <select id="current_available_filter" class="selectpicker" style="width: 100%;">
                            <c:forEach var="filter" items="${filters}">
                                <option value="${filter.property}">
                                    <c:out value="${filter.label}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <%-- Add filter button --%>
                    <div class="col-md-3">
                        <button id="add_filter_button" class="btn btn-success">
                            <spring:message code="mmc.filter.view.add.filter.button"/>
                        </button>
                        <button id="search_filter_button" class="btn btn-primary" style="width: 140px;">
                            <spring:message code="mmc.filter.view.search.button"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<%-- List view --%>
<div class="panel panel-info" style="width: 100%;">
    <%-- Header --%>
    <div class="panel-heading">
        <b style="font-size: 110%;"><c:out value="${entityName}"/></b>
        <jsp:include page="/WEB-INF/view/mmc/templates/list_view_pagination.jsp"/>
    </div>
    <%-- Table view --%>
    <div class="panel-body" style="padding: 0px;">
        <table class="table table-hover table-bordered" style="margin-bottom: 0px">
            <%-- Header --%>
            <thead>
            <tr>
                <c:forEach var="currentColumn" items="${listView.tableView.columns}">
                    <th style="font-size: 85%;"><c:out value="${currentColumn.title}"/></th>
                </c:forEach>
            </tr>
            </thead>
            <%-- Result --%>
            <tbody>
            <c:forEach var="item" items="${dataResult.entries}">
                <tr>
                    <c:forEach var="currentColumn" items="${listView.tableView.columns}">
                        <td>
                            <c:choose>
                                <c:when test="${currentColumn.rendererBean != null}">
                                    <mmc-tags:table-view-column-renderer parentObject="${item}" propertyObject="${item[currentColumn.property]}"
                                                                         columnRenderer="${currentColumn.rendererBean}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${item[currentColumn.property]}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">

    /**
     * Select picker initialization
     */
    $(document).ready(function() {
        $(".selectpicker").selectpicker({});
        $(".number_field").numberMask({type:'float', beforePoint:10, afterPoint:6, decimalMark:'.'});
    });

    /**
     * Load list view
     * @param entityName Entity name
     * @param currentPage Current page
     */
    function loadListView(entityName, currentPage) {
        $.get("/mmc/widget/list-view/" + entityName, {
            page : currentPage
        }, listViewLoadResponse).fail(function() {
            location.reload();
        });
    }

    /**
     * List view load response
     */
    function listViewLoadResponse(data) {
        if (data != null) {
            if (data.status == 'ERROR') {
                $("#alert_dialog_title").text("Error");
                $("#alert_dialog_error_message").html(data.errorMessage);
                $("#alert_dialog").modal("show");
                return;
            }
            if (data.status == "NOT_FOUND") {
                $("#alert_dialog_title").text("Warning");
                $("#alert_dialog_error_message").html(data.errorMessage);
                $("#alert_dialog").modal("show");
                return;
            } else {
                $("#main_panel_container").empty();
                $("#main_panel_container").html(data.renderedWidget);
                return;
            }
        } else {
            location.reload();
        }
    }

    /**
     * Search button listener
     */
    $("#search_filter_button").click(function() {
        /** Build criteria parameters */
        var selectedCriteriaValues = [];
        var allFilters = $(".hidden_filter_type").each(function(){
            var currentFilter = $(this).val();
            if ($("#filter_current_number_" + currentFilter)[0] != null) {
                var fieldType = $("#filter_field_type_" + currentFilter).val();
                var currentNumber = parseInt($("#filter_current_number_" + currentFilter).val());
                for (var i = 0; i <= currentNumber; i++) {
                    var criteriaType = $("[name='" + currentFilter + "_criteriaSelector_" + i + "']").val();
                    var criteriaValue = getFilterValue(currentFilter, fieldType, i);
                    if (criteriaType != null && criteriaValue!= null && (criteriaValue.trim().length > 0)) {
                        selectedCriteriaValues.push({
                            property : currentFilter,
                            criteria : criteriaType,
                            value : criteriaValue
                        });
                    }
                }

            }
        });
        /** Get data */
        if (selectedCriteriaValues.length > 0) {
            alert(selectedCriteriaValues);
        }
    });

    /**
     * Get filter value
     * @param filterName Filter property name
     * @param filterType Filter type
     * @param Number filter number
     * @return Filter value or null
     */
    function getFilterValue(filterName, filterType, number) {
        if (filterType == "STRING") {
            return $("[name='" + filterName + "_field_" + number + "']").val();
        }
        if (filterType == "NUMBER") {
            return $("[name='" + filterName + "_field_" + number + "']").val();
        }
        if (filterType == "BOOLEAN") {
            return $("[name='" + filterName + "_field_" + number + "']:checked").val();
        }
        return null;
    }

    /**
     * Add filter button listener
     */
    $("#add_filter_button").click(function(){
        var currentFilter = $("#current_available_filter").val();
        var criteriaTypes = JSON.parse($("#filter_criteria_types_" + currentFilter).val());
        var fieldType = $("#filter_field_type_" + currentFilter).val();
        /** Add filter to the view */
        if ($("#filter_" + currentFilter)[0] != null) {
            increaseFieldNumber(currentFilter);
            $("#filter_criterias_column_" + currentFilter).append(createCriteriaSelector(criteriaTypes, currentFilter, getCurrentFieldNumber(currentFilter)));
            $("#filter_field_column_" + currentFilter).append(createFilterField(currentFilter, fieldType, getCurrentFieldNumber(currentFilter)));

        } else {
            $("#all_available_filters").before(createFilterRow(currentFilter, criteriaTypes, fieldType));
        }
        $(".selectpicker").selectpicker({});
        $(".number_field").numberMask({type:'float', beforePoint:10, afterPoint:6, decimalMark:'.'});
    });

    /**
    * Create filter row
    * @param filterProperty Filter property
    * @param criteriaTypes Criteria types
    * @param fieldType Field type
    * @returns {*|jQuery|HTMLElement}
     */
    function createFilterRow(filterProperty, criteriaTypes, fieldType) {
        var filterRow = $("<div class='row' style='border-bottom: 1px solid #DDDDDD; padding: 10px 0px 10px 0px'></div>");
        filterRow.attr("id", "filter_" + filterProperty);
        /** Current number */
        var currentNumberInput = $("<input type='hidden' value='0'/>");
        currentNumberInput.attr("id", "filter_current_number_" + filterProperty);
        filterRow.append(currentNumberInput);
        /** Filter label */
        var labelColumn = $("<div class='col-md-3'><h5 style='padding-left: 15px; color: black'><b>" +
                $("#filter_label_" + filterProperty).val() + "</b></h5></div>");
        filterRow.append(labelColumn);
        /** Criteria types */
        var criteriasColumn = $("<div class='col-md-2'></div>");
        criteriasColumn.attr("id", "filter_criterias_column_" + filterProperty);
        criteriasColumn.append(createCriteriaSelector(criteriaTypes, filterProperty, 0));
        filterRow.append(criteriasColumn);
        /** Field column */
        var fieldColumn = $("<div class='col-md-6'></div>");
        fieldColumn.attr("id", "filter_field_column_" + filterProperty);
        fieldColumn.append(createFilterField(filterProperty, fieldType, 0));
        filterRow.append(fieldColumn);
        /** Remove button */
        var removeButtonColumn = $("<div class='col-md-1' style='text-align: center'></div>");
        var removeButtonImage = $("<img class='icon-button' style='margin-left: 5px; margin-top: 7px;' src='/resources/mmc_app/images/remove-filter.png' width='16'/>");
        removeButtonImage.click(function(){
            $("#filter_" + filterProperty).remove();
        });
        removeButtonColumn.append(removeButtonImage);
        filterRow.append(removeButtonColumn);
        return filterRow;
    }

    /**
    * Create criterias selector
    * @param criteriaTypes Criteria types
    * @param Field property
    * @param Filter selector number
    * @returns {*|jQuery|HTMLElement}
     */
    function createCriteriaSelector(criteriaTypes, fieldProperty, number) {
        var selectorComponent = $("<select style='padding-top: 5px' class='selectpicker'></select>");
        selectorComponent.attr("name", fieldProperty + "_criteriaSelector_" + number);
        criteriaTypes.forEach(function(item, i, arr) {
            var option = $("<option></option>");
            option.val(item);
            option.text(item);
            selectorComponent.append(option);
        });
        return selectorComponent;
    }


    /**
     * Create filter field
     * @param fieldProperty Field property
     * @param fieldType Field type
     * @param number Filter field number
     * @return Field element
     */
    function createFilterField(fieldProperty, fieldType, number) {
        if (fieldType == "STRING") {
            var field = $("<input class='string_field' type='text' style='width: 100%; margin-bottom: 13px;'>");
            field.attr("name", fieldProperty + "_field_" + number);
            return field;
        }
        if (fieldType == "NUMBER") {
            var field = $("<input class='number_field' type='text' style='width: 100%; margin-bottom: 13px;'>");
            field.attr("name", fieldProperty + "_field_" + number);
            return field;
        }
        if (fieldType == "BOOLEAN") {
            var container = $("<div style='margin-bottom: 16px;'></div>");
            var trueField = $("<input type='radio' value='true'>");
            trueField.attr("name", fieldProperty + "_field_" + number);
            container.append(trueField);
            container.append($("<span class='filter-radio-span'>True</span>"));
            var falseField = $("<input name='temp' type='radio' value='false'>");
            falseField.attr("name", fieldProperty + "_field_" + number);
            container.append(falseField);
            container.append($("<span class='filter-radio-span'>False</span>"));
            var naField = $("<input name='temp' type='radio' value='na'>");
            naField.attr("name", fieldProperty + "_field_" + number);
            container.append(naField);
            container.append($("<span class='filter-radio-span'>N/A</span>"));
            return container;
        }
        return null;
    }

    /**
    * Get current field number
    * @param fieldProperty Field property
    * @returns {Number} Current number
     */
    function getCurrentFieldNumber(fieldProperty) {
        return parseInt($("#filter_current_number_" + fieldProperty).val());
    }

    /**
    * Increase field number
    * @param fieldProperty
     */
    function increaseFieldNumber(fieldProperty) {
        $("#filter_current_number_" + fieldProperty).val(getCurrentFieldNumber(fieldProperty) + 1);
    }
</script>
