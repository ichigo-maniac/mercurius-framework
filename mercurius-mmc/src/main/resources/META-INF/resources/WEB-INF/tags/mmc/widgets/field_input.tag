<%@tag body-content="empty" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@attribute name="fieldType" required="true" type="org.mercuriusframework.mmc.enums.FieldType" %>
<%@attribute name="fieldName" required="true" type="java.lang.String" %>
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
        <input name="${fieldName}_field_0" type="radio" value="true" checked><span>True</span>
        <input name="${fieldName}_field_0" type="radio" value="false"><span>False</span>
        <input name="${fieldName}_field_0" type="radio" value="null"><span>N/A</span>
    </div>
</c:if>