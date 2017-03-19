<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-info" style="width: 100%;">
    <%-- Header --%>
    <div class="panel-heading">
        <b style="font-size: 110%;"><c:out value="${entityName}"/></b>
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
                        <td><c:out value="${item[currentColumn.property]}"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
