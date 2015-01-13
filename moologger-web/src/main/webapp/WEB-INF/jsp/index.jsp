<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
	<head>
        <title>Index</title>
        
        <link rel="stylesheet" type="text/css" href="<core:url value="/css/main.css" />" />
		<script type="text/javascript" src="<core:url value="/js/jquery-1.9.0-min.js" />"></script>
		<script type="text/javascript" src="<core:url value="/js/jquery-ui-1.10.0.custom.min.js" />"></script>
    	<script type="text/javascript" src="<core:url value="/js/moologger.js" />"></script>
    </head>
	<body>
		<div class="elastic">
			<header>
				<moologger:nav />
			</header>

			<main>
				<h1>Recent Activity</h1>

				<moologger:conversations />
			</main>
		</div>

		<footer>
			<moologger:info />
		</footer>
	</body>
</html>