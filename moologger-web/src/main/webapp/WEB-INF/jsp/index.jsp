<%@ include file="/WEB-INF/jsp/header.jsp" %>

<html>
	<head>
        <title>Index</title>
        
        <link rel="stylesheet" type="text/css" href="<core:url value="/css/main.css" />" />
		<script type="text/javascript" src="<core:url value="/js/jquery-1.9.0-min.js" />"></script>
		<script type="text/javascript" src="<core:url value="/js/jquery-ui-1.10.0.custom.min.js" />"></script>
    	<script type="text/javascript" src="<core:url value="/js/moologger.js" />"></script>
    </head>
	<body>
		<moologger:header />
		
		<div id="body-wrap">
			<div id="body">
				<div id="content-wrap">
					<div id="content">
						Welcome to Moologger
					</div>
				</div>
				
				<moologger:navigation />
			</div>
		</div>
		
		<moologger:footer />
	</body>
</html>