<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mmc-tags" uri="/WEB-INF/tlds/mmc-tags.tld" %>

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
