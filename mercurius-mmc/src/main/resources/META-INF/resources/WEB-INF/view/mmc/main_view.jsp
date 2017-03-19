<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="widgets" tagdir="/WEB-INF/tags/mmc/widgets" %>

<html>
<head>
    <title>Mercurius manager console</title>
    <jsp:include page="/WEB-INF/view/mmc/common/css_style_libraries.jsp"/>
    <link rel="stylesheet" href="/resources/mmc_app/css/mmc-styles.css"/>
</head>

<body ng-app>
<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
    <%-- Toolbar --%>
    <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
        <div class="mdl-layout__header-row">
            <div class="mdl-layout-spacer"></div>
            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
                <i class="material-icons">more_vert</i>
            </button>
            <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
                <li class="mdl-menu__item"><a href="<c:url value="/logout"/>">Log out</a></li>
            </ul>
        </div>
    </header>
    <%-- Navigation --%>
    <div class="demo-drawer mdl-layout__drawer mdl-color--light-green-800 mdl-color-text--blue-grey-50">
        <%-- Navigation header --%>
        <header class="demo-drawer-header" style="padding-bottom: 5px;">
            <img src="/resources/mmc_lib/images/user.jpg" class="demo-avatar">
            <div class="demo-avatar-dropdown" style="padding: 10px 0px 5px 5px;">
                <span style="font-weight: bold;">
                    <c:out value="${sessionScope.currentUser.name} [${sessionScope.currentUser.code}]"/>
                </span>
                <div class="mdl-layout-spacer"></div>
            </div>
        </header>
        <%-- Tree-view --%>
        <nav class="demo-navigation mdl-navigation mdl-color--grey-300">
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
    <main class="mdl-layout__content mdl-color--grey-100">
        <div class="panel panel-default" style="width: 100%;">
            <div id="main_panel_container" class="panel-body">
            </div>
        </div>
    </main>
</div>
<%-- Scripts --%>
<script>
    function loadEntityWidget(widgetName, entityName) {
        $.get("/mmc/widget/" + widgetName + "/" + entityName, function(data) {
            if (data != null) {
                if (data.status == 'ERROR') {
                    location.reload();
                    return;
                }
                if (data.status == "NOT_FOUND") {
                    alert("not found");
                    return;
                } else {
                    $("#main_panel_container").empty();
                    $("#main_panel_container").html(data.renderedWidget);
                    return;
                }
            } else {
                location.reload();
            }
        }).fail(function() {
            location.reload();
        });
    }
</script>
<%-- Javascript libraries --%>
<jsp:include page="/WEB-INF/view/mmc/common/javascript_libraries.jsp"/>
</body>
</html>
