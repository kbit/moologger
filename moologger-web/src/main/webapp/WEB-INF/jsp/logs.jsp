<%@ include file="/WEB-INF/jsp/header.jsp" %>
 
<html>
    <head>
        <title>Logs</title>
        
        <link rel="stylesheet" type="text/css" href="<core:url value="/css/main.css" />" />
    </head>
    <body>
    	<moologger:header />
    	
    	<div id="body-wrap">
    		<div id="body">
    			<div id="content-wrap">
			    	<div id="content">
						<table cellspacing="0" class="conversation">
							<core:forEach items="${conversations}" var="conversation">
								<tr>
									<th colspan="2">Conversation - <format:formatDate value="${conversation.startTimestamp}" type="both"/>-<format:formatDate value="${conversation.endTimestamp}" type="both"/></th>
								</tr>
								<core:forEach items="${conversation.messages}" var="message">
									<tr>
										<td class="conversation-alias">${message.alias.identifier} (<format:formatDate value="${message.timestamp}" type="time"/>)</td>
										<td class="conversation-message">${message.text}</td>
									</tr>
								</core:forEach>
							</core:forEach>
						</table>
				    	
				    	<form:form action="logs/new" enctype="multipart/form-data" method="POST">  
							<ol>
								<li>
									<form:label path="client">Client</form:label>
									<form:select path="client">
										<form:option value="" label="Select" />
										<form:options items="${clients}" />
									</form:select>
								</li>
								<li>
									<form:label path="protocol">Protocol</form:label>
									<form:select path="protocol">
										<form:option value="" label="Select" />
										<form:options items="${protocols}" />
									</form:select>
								</li>
								<li>
									<form:label path="files">Files</form:label>
							    	<input name="files" type="file" multiple="" />
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