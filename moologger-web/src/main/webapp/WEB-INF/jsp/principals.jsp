<%@ include file="/WEB-INF/jsp/header.jsp" %>
 
<html>
    <head>
        <title>Principals</title>
    </head>
    <body>
    	<moologger:navigation />
		
		<display:table name="principals" id="principal">
			<display:column title="Action"><a href="principals/${principal.principalId}/aliases">Edit</a></display:column>
			<display:column title="Identifier" property="identifier" />
			<display:column title="Aliases">
				<display:table name="${principal.aliases}" id="alias">
					<display:column property="identifier" title="Identifier" />
					<display:column property="client" title="Client" />
					<display:column property="protocol" title="Protocol" />
				</display:table>
			</display:column>
		</display:table>
    	
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
    </body>
</html>