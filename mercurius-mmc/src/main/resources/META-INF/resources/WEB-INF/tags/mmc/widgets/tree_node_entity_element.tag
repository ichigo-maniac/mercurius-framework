<%@tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@attribute name="treeNode" required="true" type="org.mercuriusframework.mmc.widgets.treenodesview.TreeNodeEntityElement" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Tree element --%>
<li>
    <a style="color: #2A6598" href="#" onclick="loadEntityWidget('list-view', '<c:out value="${treeNode.entityName}"/>')">
        &nbsp;&#0183;&nbsp<c:out value="${treeNode.title}"/>
    </a>
</li>