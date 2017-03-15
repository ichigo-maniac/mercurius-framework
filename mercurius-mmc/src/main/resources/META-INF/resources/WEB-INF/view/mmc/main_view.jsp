<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
    <title>Mercurius manager console</title>
    <jsp:include page="/WEB-INF/view/mmc/common/css_style_libraries.jsp"/>
    <link rel="stylesheet" href="/resources/mmc_app/css/mmc-styles.css"/>
</head>
<body>
<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
    <%-- Panel --%>
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
    <div class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
        <header class="demo-drawer-header">
            <img src="/resources/mmc_lib/images/user.jpg" class="demo-avatar">
            <div class="demo-avatar-dropdown">
                <span>hello@example.com</span>
                <div class="mdl-layout-spacer"></div>
            </div>
        </header>
        <nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
            <ul class="nav nav-pills nav-stacked nav-tree" id="myTree" data-toggle="nav-tree">
                <li>
                    <a href="http://www.example.com" target="_blank">Item One (With Children) (has link)</a>
                    <ul class="nav nav-pills nav-stacked nav-tree">
                        <li>
                            <a href="#">Item A (Without Children)</a>
                        </li>
                        <li>
                            <a href="#">Item B (Without Children)</a>
                        </li>
                        <li>
                            <a href="#">Item C (Without Children)</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">Item Two (Without Children)</a>
                </li>
                <li>
                    <a href="#">Item Three (With Children and Grandchildren)</a>
                    <ul class="nav nav-pills nav-stacked nav-tree">
                        <li>
                            <a href="#">Item A (With Children)</a>
                            <ul class="nav nav-pills nav-stacked nav-tree">
                                <li>
                                    <a href="#">Item I (Without Children)</a>
                                </li>
                                <li>
                                    <a href="#">Item II (Without Children)</a>
                                </li>
                                <li class="active">
                                    <a href="#">Item III (Without Children)</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">Item B (Without Children)</a>
                        </li>
                        <li>
                            <a href="#">Item C (With Children)</a>
                            <ul class="nav nav-pills nav-stacked nav-tree">
                                <li>
                                    <a href="#">Item I (Without Children)</a>
                                </li>
                                <li>
                                    <a href="#">Item II (Without Children)</a>
                                </li>
                                <li>
                                    <a href="#">Item III (Without Children)</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
            <%--<a class="mdl-navigation__link" href="">Home</a>--%>
            <%--<a class="mdl-navigation__link" href="">Inbox</a>--%>
            <%--<a class="mdl-navigation__link" href="">Trash</a>--%>
            <%--<a class="mdl-navigation__link" href="">Spam</a>--%>
            <%--<a class="mdl-navigation__link" href="">Forums</a>--%>
            <%--<a class="mdl-navigation__link" href="">Updates</a>--%>
            <%--<a class="mdl-navigation__link" href="">Promos</a>--%>
            <%--<a class="mdl-navigation__link" href="">Purchases</a>--%>
            <%--<a class="mdl-navigation__link" href="">Social</a>--%>
        </nav>
    </div>
    <main class="mdl-layout__content mdl-color--grey-100">
        <div class="mdl-grid demo-content">
        </div>
    </main>
</div>
<%-- Javascript libraries --%>
<jsp:include page="/WEB-INF/view/mmc/common/javascript_libraries.jsp"/>
</body>
</html>
