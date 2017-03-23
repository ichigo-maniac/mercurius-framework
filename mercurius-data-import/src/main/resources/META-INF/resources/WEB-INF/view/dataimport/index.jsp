<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title><spring:message code="dataimport.login.title"/></title>
    <jsp:include page="/WEB-INF/view/dataimport/common/css_style_libraries.jsp"/>
    <link rel="stylesheet" href="/resources/dataimport_app/css/styles.css"/>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <%-- Main container --%>
    <div class="mercurius-content mdl-layout__content">
        <a name="top"></a>
        <div class="mercurius-more-section">
            <%-- Login form --%>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div <c:choose>
                        <c:when test="${param.errorLogin}">class="panel panel-danger"</c:when>
                        <c:otherwise>class="panel panel-primary"</c:otherwise>
                    </c:choose>>
                        <div class="panel-heading"><spring:message code="dataimport.login.title"/></div>
                        <%-- Form --%>
                        <div class="panel-body">
                            <form:form method="POST" action="/dataimport/login">
                                <div class="form-group">
                                    <label for="username">
                                        <spring:message code="dataimport.login.username"/>
                                        <c:if test="${param.errorLogin}"><span style="color: red"> - <spring:message code="dataimport.login.error.label"/></span></c:if>
                                    </label>
                                    <input type="text" class="form-control" id="username" name="username">
                                </div>
                                <div class="form-group">
                                    <label for="password"><spring:message code="dataimport.login.password"/></label>
                                    <input type="password" class="form-control" id="password" name="password">
                                </div>
                                <div class="form-group" style="text-align: right">
                                    <button type="submit" class="btn btn-success"><spring:message code="dataimport.login.button.label"/></button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </div>
</div>
<%-- Javascript libraries --%>
<jsp:include page="/WEB-INF/view/dataimport/common/javascript_libraries.jsp"/>
</body>
</html>
