<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mercurius manager console</title>
    <jsp:include page="/WEB-INF/view/mmc/common/css_style_libraries.jsp"/>
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
                    <div class="panel panel-primary">
                        <div class="panel-heading">Mercurius manager console login</div>
                        <%-- Form --%>
                        <div class="panel-body">
                            <form>
                                <div class="form-group">
                                    <label for="login">Login</label>
                                    <input type="text" class="form-control" id="login">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password">
                                </div>
                                <div class="form-group" style="text-align: right">
                                    <button type="submit" class="btn btn-success">Submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </div>
</div>
<%-- Javascript libraries --%>
<jsp:include page="/WEB-INF/view/mmc/common/javascript_libraries.jsp"/>
</body>
</html>
