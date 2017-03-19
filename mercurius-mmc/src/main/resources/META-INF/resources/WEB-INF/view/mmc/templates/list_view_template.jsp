<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-hover">
    <%-- Header --%>
    <thead>
        <tr>
            <c:forEach var="currentColumn" items="${listView.tableView.columns}">
                <th><c:out value="${currentColumn.title}"/></th>
            </c:forEach>
        </tr>
    </thead>
    <%-- Result --%>
    <tbody>
        <c:forEach var="item" items="${dataResult.entries}">
            <tr>
                <c:forEach var="currentColumn" items="${listView.tableView.columns}">
                    <td><c:out value="${item[currentColumn.property]}"/>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>

    </tbody>
</table>