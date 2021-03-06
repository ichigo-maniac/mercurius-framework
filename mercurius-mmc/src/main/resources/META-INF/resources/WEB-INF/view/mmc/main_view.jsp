<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="widgets" tagdir="/WEB-INF/tags/mmc/widgets" %>

<html>
<head>
    <title><spring:message code="mmc.login.title"/></title>
    <jsp:include page="/WEB-INF/view/mmc/common/css_style_libraries.jsp"/>
    <link rel="stylesheet" href="/resources/mmc_app/css/mmc-styles.css"/>
</head>

<body ng-app>
<div class="mmc-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
    <%-- Toolbar --%>
    <header class="mmc-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
        <div class="mdl-layout__header-row">
            <div class="mdl-layout-spacer"></div>
            <%-- Admin actions --%>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <a href="<c:url value="/mmc/app_panel/rebuild"/>"><spring:message code="mmc.rebuild.button.label"/></a>
                <jsp:include page="/WEB-INF/view/mmc/tasks/solr_tasks_fragment.jsp"/>
            </security:authorize>
            <%-- Log out --%>
            <a style="margin-left: 20px;" href="<c:url value="/mmc/logout"/>"><spring:message code="mmc.logout.button.label"/></a>
        </div>
    </header>
    <%-- Navigation --%>
    <div class="mmc-drawer mdl-layout__drawer mdl-color--light-green-800 mdl-color-text--blue-grey-50">
        <%-- Navigation header --%>
        <header class="mmc-drawer-header" style="padding-bottom: 5px;">
            <img src="/resources/mmc_lib/images/user.jpg" class="mmc-avatar">
            <div class="mmc-avatar-dropdown" style="padding: 10px 0px 5px 5px;">
                <span style="font-weight: bold;">
                    <c:out value="${sessionScope.currentUser.name} [${sessionScope.currentUser.code}]"/>
                </span>
                <div class="mdl-layout-spacer"></div>
            </div>
        </header>
        <%-- Tree-view --%>
        <nav class="mmc-navigation mdl-navigation mdl-color--grey-300">
            <c:if test="${treeView != null}">
                <ul class="nav nav-pills nav-stacked nav-tree" id="main-nav-tree" data-toggle="nav-tree">
                    <c:forEach var="treeNode" items="${treeView.nodes}">
                        <c:choose>
                            <c:when test="${treeNode.type eq 'tree-node'}">
                                <widgets:tree_node treeNode="${treeNode}"/>
                            </c:when>
                            <c:when test="${treeNode.type eq 'tree-node-entity-element'}">
                                <widgets:tree_node_entity_element treeNode="${treeNode}"/>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </ul>
            </c:if>
        </nav>
    </div>
    <%-- Main view --%>
    <main id="main_panel_container" class="mdl-layout__content mdl-color--grey-100">
    </main>
</div>
<%-- Scripts --%>
<script>
    function loadEntityWidget(widgetName, entityName) {
        $.get("/mmc/widget/" + widgetName + "/" + entityName, widgetLoadResponse).fail(function() {
            location.reload();
        });
    }

    function widgetLoadResponse(data) {
        if (data != null) {
            if (data.status == 'ERROR') {
                $("#alert_dialog_title").text("Error");
                $("#alert_dialog_error_message").html(data.errorMessage);
                $("#alert_dialog").modal("show");
                return;
            }
            if (data.status == "NOT_FOUND") {
                $("#alert_dialog_title").text("Warning");
                $("#alert_dialog_error_message").html(data.errorMessage);
                $("#alert_dialog").modal("show");
                return;
            } else {
                $("#main_panel_container").empty();
                $("#main_panel_container").html(data.renderedWidget);
                return;
            }
        } else {
            location.reload();
        }
    }
</script>
<%-- Javascript libraries --%>
<jsp:include page="/WEB-INF/view/mmc/common/javascript_libraries.jsp"/>
</body>
</html>
