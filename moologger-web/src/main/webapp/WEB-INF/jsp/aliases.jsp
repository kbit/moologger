<%@ include file="/WEB-INF/jsp/header.jsp" %>
 
<html>
    <head>
        <title>Aliases</title>
        
        <link rel="stylesheet" type="text/css" href="<core:url value="/css/main.css" />" />
    </head>
    <body>
    	<moologger:header />
    	
    	<div id="body-wrap">
			<div id="body">
				<div id="content-wrap">
					<div id="content">
				    	<form:form action="/principals/${principalId}/aliases/edit" enctype="multipart/form-data" method="POST">  
							<ol>
								<li>
									<form:label path="selectedAliases">Aliases</form:label>
									<form:select path="selectedAliases" multiple="true">
										<core:forEach items="${aliases}" var="alias">
											<optgroup label="${alias.key}">
												<form:options items="${alias.value}" itemLabel="identifier" itemValue="aliasId" />
											</optgroup>
										</core:forEach>
									</form:select>
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