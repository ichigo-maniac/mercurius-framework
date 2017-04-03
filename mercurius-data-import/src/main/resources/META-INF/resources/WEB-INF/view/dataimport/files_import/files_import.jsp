<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title><spring:message code="dataimport.login.title"/></title>
    <jsp:include page="/WEB-INF/view/dataimport/common/css_style_libraries.jsp"/>
    <link rel="stylesheet" href="/resources/dataimport_app/css/data-import-styles.css"/>
</head>

<body class="mdl-data-import mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">

    <%-- Header --%>
    <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
        <div class="mdl-layout--large-screen-only mdl-layout__header-row">
        </div>
        <div class="mdl-layout--large-screen-only mdl-layout__header-row">
            <h3><spring:message code="dataimport.login.title"/></h3>
        </div>
        <div class="mdl-layout--large-screen-only mdl-layout__header-row">
        </div>
        <%-- Tabs --%>
        <div class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">
            <a href="<c:url value="/dataimport/app_panel"/>" class="mdl-layout__tab"><spring:message code="dataimport.import.console.tab.label"/></a>
            <a href="#" class="mdl-layout__tab is-active">
                <spring:message code="dataimport.import.files.import.tab.label"/>
            </a>
            <a href="<c:url value="/dataimport/logout"/>" class="mdl-layout__tab">
                <spring:message code="dataimport.logout.button.label"/>
            </a>
        </div>
    </header>

    <%-- Main view --%>
    <main class="mdl-layout__content">
        <div class="mdl-layout__tab-panel is-active">
            <section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
                <div class="mdl-card mdl-cell mdl-cell--12-col">
                    <div class="mdl-card__supporting-text">
                        <h4 style="color: black; font-weight: bold"><spring:message code="dataimport.import.files.import.panel.label"/></h4>
                        <%-- Import form --%>
                        <form:form action="/dataimport/app_panel/import_files_data?${_csrf.parameterName}=${_csrf.token}" method="POST"
                                   enctype="multipart/form-data"
                                   class="form-inline">
                            <%-- Files input --%>
                            <div class="form-group" style="font-size: 150%">
                                <label for="importFiles" class="col-sm-2 control-label" style="color: black">
                                    <spring:message code="dataimport.import.files.files.label"/>
                                </label>
                                <div class="col-sm-10">
                                    <input id="importFiles" name="importFiles" type="file" multiple="true">
                                </div>
                            </div>
                            <%-- Submit --%>
                            <div class="form-group">
                                <div class="pull-right">
                                    <button type="submit" class="btn btn-primary" style="margin-right: 20px; width: 100px;">
                                        <spring:message code="dataimport.import.console.send.button.label"/>
                                    </button>
                                </div>
                            </div>
                        </form:form>
                        <%-- Error log --%>
                        <c:if test="${importFinish}">
                            <c:choose>
                                <c:when test="${not empty errorLog}">
                                    <div class="panel panel-danger">
                                        <div class="panel-heading">
                                            <h3 class="panel-title"><spring:message code="dataimport.import.console.result.panel.label"/></h3>
                                        </div>
                                        <div class="panel-body" style="font-size: 110%; color: black">
                                                ${errorLog}
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="panel panel-success">
                                        <div class="panel-heading">
                                            <h3 class="panel-title"><spring:message code="dataimport.import.console.result.panel.label"/></h3>
                                        </div>
                                        <div class="panel-body" style="font-size: 110%; color: black">
                                            <spring:message code="dataimport.import.console.result.panel.success.result"/>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                </div>
            </section>
        </div>
    </main>
</div>
<%-- Javascript libraries --%>
<jsp:include page="/WEB-INF/view/dataimport/common/javascript_libraries.jsp"/>
</body>
</html>
