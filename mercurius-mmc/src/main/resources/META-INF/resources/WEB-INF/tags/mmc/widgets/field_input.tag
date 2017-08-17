<%@tag body-content="empty" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@attribute name="fieldType" required="true" type="org.mercuriusframework.mmc.enums.FieldType" %>
<%@attribute name="fieldName" required="true" type="java.lang.String" %>
<%@attribute name="entityName" required="true" type="java.lang.String" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Number --%>
<c:if test="${fieldType == 'NUMBER'}">
    <input name="${fieldName}_field_0" class="number_field" type="text" style="width: 100%; margin-bottom: 13px;">
</c:if>
<%-- String --%>
<c:if test="${fieldType == 'STRING'}">
    <input name="${fieldName}_field_0" class="string_field" type="text" style="width: 100%; margin-bottom: 13px;">
</c:if>
<%-- Boolean --%>
<c:if test="${fieldType == 'BOOLEAN'}">
    <div style="margin-bottom: 16px;">
        <input name="${fieldName}_field_0" type="radio" value="true"><span class="filter-radio-span">True</span>
        <input name="${fieldName}_field_0" type="radio" value="false"><span class="filter-radio-span">False</span>
    </div>
</c:if>
<%-- Entity --%>
<c:if test="${fieldType == 'ENTITY'}">
    <div style="margin-bottom: 8px;">
        <select name="${fieldName}_field_0" style="width: 100%" multiple="multiple"></select>
    </div>
    <%-- Script --%>
    <script>
        var fieldName = '${fieldName}';
        var select = "[name='" + "${fieldName}" + "_field_0']";
        $(select).select2({
            ajax: {
                url: "/mmc/app_panel/load_entities/${entityName}" ,
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        search_text: params.term
                    };
                },
                processResults: function (data, params) {
                    var resultArray = [];
                    data.forEach(function(item, i, arr) {
                        var displayName = item.code + " - " + item.name;
                        if (item.catalog) {
                            displayName = displayName + " (" + item.catalog.code + ")";
                        }
                        resultArray.push({
                            id : item.uuid,
                            text : displayName
                        });
                    });
                    return {
                        results: resultArray
                    };
                },
                minimumInputLength: 2,
                cache: false
            }
        });
    </script>
</c:if>