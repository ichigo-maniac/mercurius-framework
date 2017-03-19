<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- Pagination --%>
<div>

    <c:if test="${productsResult.pagesCount > 1}">
        <c:choose>
            <%-- More than 6 pages --%>
            <c:when test="${productsResult.pagesCount > 6}">
                <ul class="pagination">
                        <%-- First --%>
                    <c:if test="${productsResult.currentPage != 0}">
                        <li>
                            <a href="<c:url value="${category.builtUrl}"><c:param name="page" value="0"/></c:url>" aria-label="First">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                        <%-- Current page - 2 --%>
                    <c:if test="${productsResult.currentPage - 1 > 0}">
                        <li>
                            <a href="<c:url value="${category.builtUrl}"><c:param name="page" value="${productsResult.currentPage - 2}"/></c:url>" aria-label="Current">
                                <span>${productsResult.currentPage - 1}</span>
                            </a>
                        </li>
                    </c:if>
                        <%-- Current page - 1 --%>
                    <c:if test="${productsResult.currentPage  > 0}">
                        <li>
                            <a href="<c:url value="${category.builtUrl}"><c:param name="page" value="${productsResult.currentPage - 1}"/></c:url>" aria-label="Current">
                                <span>${productsResult.currentPage}</span>
                            </a>
                        </li>
                    </c:if>
                        <%-- Current page --%>
                    <li class="active">
                        <a href="<c:url value="${category.builtUrl}"><c:param name="page" value="${productsResult.currentPage}"/></c:url>" aria-label="Current">
                            <span>${productsResult.currentPage + 1}</span>
                        </a>
                    </li>
                        <%-- Current page + 1 --%>
                    <c:if test="${productsResult.currentPage + 1 < productsResult.pagesCount - 1}">
                        <li>
                            <a href="<c:url value="${category.builtUrl}"><c:param name="page" value="${productsResult.currentPage + 1}"/></c:url>" aria-label="Current">
                                <span>${productsResult.currentPage + 2}</span>
                            </a>
                        </li>
                    </c:if>
                        <%-- Current page + 2 --%>
                    <c:if test="${productsResult.currentPage + 2 < productsResult.pagesCount - 1}">
                        <li>
                            <a href="<c:url value="${category.builtUrl}"><c:param name="page" value="${productsResult.currentPage + 2}"/></c:url>"
                               aria-label="Current">
                                <span>${productsResult.currentPage + 3}</span>
                            </a>
                        </li>
                    </c:if>
                        <%-- Last --%>
                    <c:if test="${productsResult.currentPage + 1 != productsResult.pagesCount}">
                        <li>
                            <a href="<c:url value="${category.builtUrl}"><c:param name="page" value="${productsResult.pagesCount - 1}"/></c:url>"
                               aria-label="Last">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </c:when>
            <%-- Lower than 6 pages --%>
            <c:otherwise>
                <ul class="pagination">
                    <c:forEach var="index" begin="0" end="${productsResult.pagesCount - 1}">
                        <li <c:if test="${index == productsResult.currentPage}">class="active"</c:if>>
                            <a href="<c:url value="${category.builtUrl}"><c:param name="page" value="${index}"/></c:url>">
                                <span aria-hidden="true">${index + 1}</span>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </c:if>

</div>