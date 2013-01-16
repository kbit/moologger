<%@ include file="/WEB-INF/jsp/header.jsp" %>
 
<html>
    <head>
        <title>Aliases</title>
    </head>
    <body>
    	<moologger:navigation />
    	
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
    </body>
</html>