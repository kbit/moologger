<%@ include file="/WEB-INF/jsp/header.jsp" %>

<html>
    <head>
        <title>Logs</title>

        <link rel="stylesheet" type="text/css" href="<core:url value="/css/main.css" />" />
        <script type="text/javascript" src="<core:url value="/js/jquery-1.9.0-min.js" />"></script>
        <script type="text/javascript" src="<core:url value="/js/jquery-ui-1.10.0.custom.min.js" />"></script>
        <script type="text/javascript" src="<core:url value="/js/moologger.js" />"></script>
    </head>
    <body>
        <moologger:header />

        <main>
            <moologger:conversationsNew />
        </main>

        <moologger:footer />
    </body>
</html>