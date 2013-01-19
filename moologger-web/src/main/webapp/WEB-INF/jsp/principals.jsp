<%@ include file="/WEB-INF/jsp/header.jsp" %>
 
<html>
    <head>
        <title>Principals</title>
        
        <link rel="stylesheet" type="text/css" href="<core:url value="/css/main.css" />" />
    </head>
    <body>
    	<moologger:header />
    	
		<div id="body-wrap">
    		<div id="body">
				<div id="content-wrap">
					<div id="content">
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
									<td><a href="principals/${principal.principalId}/aliases">Edit</a></td>
									<td>${principal.identifier}</td>
									<core:forEach items="${principal.aliases}" var="alias">
										<td>${alias.identifier}</td>
										<td>${alias.client}</td>
										<td>${alias.protocol}</td>
									</core:forEach>
								</tr>
							</core:forEach>
						</table>
				    	
				    	<form:form action="/principals/new" enctype="multipart/form-data" method="POST">  
							<ol>
								<li>
									<form:label path="identifier">Identifier</form:label>
									<form:input path="identifier" />
								</li>
							    <li>
					    			<input name="Submit" type="submit" />
					    		</li>
					    	</ol>
				    	</form:form>
				    </div>
				</div>
	    	
	    		<moologger:navigation />
	    	</div>
	    </div>
	    
	    <moologger:footer />
    </body>
</html>