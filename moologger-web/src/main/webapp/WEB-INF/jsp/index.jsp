<%@ include file="/WEB-INF/jsp/header.jsp" %>

<html>
	<head>
        <title>Index</title>
        
        <link rel="stylesheet" type="text/css" href="<core:url value="/css/main.css" />" />
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