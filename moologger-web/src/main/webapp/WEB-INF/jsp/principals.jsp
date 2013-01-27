<%@ include file="/WEB-INF/jsp/header.jsp" %>

<html>
    <head>
        <title>Principals</title>
        
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
						<div id="principals">
							<table>
								<tr>
									<th>Action</th>
									<th>Identifier</th>
									<th colspan="3">Aliases</th>
								</tr>
								<tr>
									<th colspan="2" />
									<th>Identifier</th>
									<th>Client</th>
									<th>Protocol</th>
								</tr>
								<core:forEach items="${principals}" var="principal">
									<tr>
										<td><a class="form-link" href="principals/${principal.principalId}/aliases" title="Edit Existing Aliases">Edit</a></td>
										<td>${principal.identifier}</td>
										<core:forEach items="${principal.aliases}" var="alias">
											<td>${alias.identifier}</td>
											<td>${alias.client}</td>
											<td>${alias.protocol}</td>
										</core:forEach>
									</tr>
								</core:forEach>
								<tr><td><a class="form-link" href="principals/new" title="Add a New Principal">Add</a></td></tr>
							</table>
						</div>
				    </div>
				</div>
	    	
	    		<moologger:navigation />
	    	</div>
	    </div>
	    
	    <moologger:footer />
    </body>
</html>