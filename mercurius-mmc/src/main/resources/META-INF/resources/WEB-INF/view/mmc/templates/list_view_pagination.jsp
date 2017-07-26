<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- Pagination --%>
<div class="pull-right">
    <%-- Entities count --%>
    <span>
        <spring:message code="mmc.list.view.total.count.label"/> : <b><c:out value="${dataResult.totalEntriesCount}"/></b>
    </span>
    <%-- Pages --%>
    <c:if test="${dataResult.pagesCount > 1}">
        <c:choose>
            <%-- More than 6 pages --%>
            <c:when test="${dataResult.pagesCount > 6}">
                <ul class="pagination pagination-sm" style="margin: 0px 0px 5px 15px; float: right">
                        <%-- First --%>
                    <c:if test="${dataResult.currentPage != 0}">
                        <li>
                            <a onclick="loadListView('<c:out value="${entityName}"/>', 0, '<c:out value="${selectedFilterValues}"/>')" href="#" aria-label="First">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                        <%-- Current page - 2 --%>
                    <c:if test="${dataResult.currentPage - 1 > 0}">
                        <li>
                            <a onclick="loadListView('<c:out value="${entityName}"/>', ${dataResult.currentPage - 2}, '<c:out value="${selectedFilterValues}"/>')"  href="#" aria-label="Current">
                                <span>${dataResult.currentPage - 1}</span>
                            </a>
                        </li>
                    </c:if>
                        <%-- Current page - 1 --%>
                    <c:if test="${dataResult.currentPage  > 0}">
                        <li>
                            <a onclick="loadListView('<c:out value="${entityName}"/>', ${dataResult.currentPage - 1}, '<c:out value="${selectedFilterValues}"/>')" href="#" aria-label="Current">
                                <span>${dataResult.currentPage}</span>
                            </a>
                        </li>
                    </c:if>
                        <%-- Current page --%>
                    <li class="active">
                        <a onclick="loadListView('<c:out value="${entityName}"/>', ${dataResult.currentPage}, '<c:out value="${selectedFilterValues}"/>')" href="#" aria-label="Current">
                            <span>${dataResult.currentPage + 1}</span>
                        </a>
                    </li>
                        <%-- Current page + 1 --%>
                    <c:if test="${dataResult.currentPage + 1 < dataResult.pagesCount - 1}">
                        <li>
                            <a onclick="loadListView('<c:out value="${entityName}"/>', ${dataResult.currentPage + 1}, '<c:out value="${selectedFilterValues}"/>')" href="#" aria-label="Current">
                                <span>${dataResult.currentPage + 2}</span>
                            </a>
                        </li>
                    </c:if>
                        <%-- Current page + 2 --%>
                    <c:if test="${dataResult.currentPage + 2 < dataResult.pagesCount - 1}">
                        <li>
                            <a onclick="loadListView('<c:out value="${entityName}"/>', ${dataResult.currentPage +2}, '<c:out value="${selectedFilterValues}"/>')" href="#" aria-label="Current">
                                <span>${dataResult.currentPage + 3}</span>
                            </a>
                        </li>
                    </c:if>
                        <%-- Last --%>
                    <c:if test="${dataResult.currentPage + 1 != dataResult.pagesCount}">
                        <li>
                            <a onclick="loadListView('<c:out value="${entityName}"/>', ${dataResult.pagesCount - 1}, '<c:out value="${selectedFilterValues}"/>')" href="#" aria-label="Last">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </c:when>
            <%-- Lower than 6 pages --%>
            <c:otherwise>
                <ul class="pagination pagination-sm" style="margin: 0px 0px 0px 15px; float: right">
                    <c:forEach var="index" begin="0" end="${dataResult.pagesCount - 1}">
                        <li <c:if test="${index == dataResult.currentPage}">class="active"</c:if>>
                            <a onclick="loadListView('<c:out value="${entityName}"/>', ${index}, '<c:out value="${selectedFilterValues}"/>')" href="#">
                                <span aria-hidden="true">${index + 1}</span>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </c:if>

</div>
