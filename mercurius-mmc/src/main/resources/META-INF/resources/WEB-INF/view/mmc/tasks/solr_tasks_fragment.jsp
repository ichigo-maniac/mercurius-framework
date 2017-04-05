<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty solrTasks}">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" id="solr"
       aria-expanded="false">
        <spring:message code="mmc.panel.solr.tasks.label"/>
        <span class="caret"></span>
    </a>
    <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="solr">
        <c:forEach var="solrTask" items="${solrTasks}">
            <c:choose>
                <c:when test="${solrTask.enabled}">
                    <li class="mdl-menu__item">
                        <c:out value="${solrTask.name} - ${solrTask.code}"/>
                        <img class="icon-button" style="margin-left: 5px;" src="/resources/mmc_app/images/play.png" width="16"
                            onclick="runSolrTask('<c:out value='${solrTask.code}'/>')"/>
                    </li>
                </c:when>
                <c:otherwise>
                    <li disabled class="mdl-menu__item"><c:out value="${solrTask.name} - ${solrTask.code}"/></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
    <%-- Solr tasks --%>
    <script>
        function runSolrTask(taskCode) {
            var token = $("meta[name='_csrf']").attr("content");
            $.post("/mmc/run_task/" + taskCode, {
                _csrf : token
            }, function () {
                alert("started");
            }).fail(function() {
                location.reload();
            });
        }
    </script>
</c:if>