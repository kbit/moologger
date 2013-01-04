<%@ include file="/WEB-INF/jsp/header.jsp" %>
 
<html>
    <head>
        <title>Upload</title>
    </head>
    <body>
    	<moologger:navigation />
    	
    	<f:form action="upload/add" enctype="multipart/form-data" method="POST">  
			<ol>
				<li>
					<f:label path="client">Client</f:label>
					<f:select path="client">
						<f:option value="" label="Select" />
						<f:options items="${clients}" />
					</f:select>
				</li>
				<li>
					<f:label path="protocol">Protocol</f:label>
					<f:select path="protocol">
						<f:option value="" label="Select" />
						<f:options items="${protocols}" />
					</f:select>
				</li>
				<li>
					<f:label path="files">Files</f:label>
			    	<input name="files" type="file" multiple="" />
			    </li>
			    <li>
	    			<input name="Submit" type="submit" />
	    		</li>
	    	</ol>
    	</f:form>
    </body>
</html>