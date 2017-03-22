<!doctype html>

<html lang="en">
<head>
    <jsp:include page="/WEB-INF/view/dataimport/common/css_style_libraries.jsp"/>
    <link rel="stylesheet" href="/resources/dataimport_app/css/data-import-styles.css"/>
</head>

<body class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
        <div class="mdl-layout--large-screen-only mdl-layout__header-row">
        </div>
        <div class="mdl-layout--large-screen-only mdl-layout__header-row">
            <h3>Name &amp; Title</h3>
        </div>
        <div class="mdl-layout--large-screen-only mdl-layout__header-row">
        </div>
        <div class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">
            <a href="#overview" class="mdl-layout__tab is-active">Overview</a>
        </div>
    </header>
    <main class="mdl-layout__content">
        <div class="mdl-layout__tab-panel is-active" id="overview">
            <section class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
                <div class="mdl-card mdl-cell mdl-cell--12-col">
                    <div class="mdl-card__supporting-text">
                        <h4>Technology</h4>
                        Dolore ex deserunt aute fugiat aute nulla ea sunt aliqua nisi cupidatat eu. Nostrud in laboris labore nisi amet do dolor eu fugiat consectetur elit cillum esse. Pariatur occaecat nisi laboris tempor laboris eiusmod qui id Lorem esse commodo in. Exercitation aute dolore deserunt culpa consequat elit labore incididunt elit anim.
                    </div>
                    <div class="mdl-card__actions">
                        <a href="#" class="mdl-button">Read our features</a>
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