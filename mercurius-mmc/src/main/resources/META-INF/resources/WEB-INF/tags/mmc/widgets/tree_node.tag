<%@tag body-content="empty" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@attribute name="treeNode" required="true" type="org.mercuriusframework.mmc.widgets.treenodesview.TreeNodeElement" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="widgets" tagdir="/WEB-INF/tags/mmc/widgets"%>

<%-- Tree element --%>
<li>
    <a style="color: #2A6598; font-weight: bold;" href="#"><c:out value="${treeNode.title}"/></a>
    <%-- Child elements --%>
    <c:if test="${not empty treeNode.nodes}">
        <ul class="nav nav-pills nav-stacked nav-tree">
            <c:forEach var="currentNode" items="${treeNode.nodes}">
                <c:choose>
                    <c:when test="${currentNode.type eq 'tree-node'}">
                        <widgets:tree_node treeNode="${currentNode}"/>
                    </c:when>
                    <c:when test="${currentNode.type eq 'tree-node-entity-element'}">
                        <widgets:tree_node_entity_element treeNode="${currentNode}"/>
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>
    </c:if>
</li>