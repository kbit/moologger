<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
 
<html>
    <head>
        <title>Upload</title>
    </head>
    <body>
    	<f:form action="upload/add" enctype="multipart/form-data" method="POST">  
			<f:select path="client">
				<f:option value="" label="Select" />
				<f:options items="${clients}" />
			</f:select>
			<f:select path="protocol">
				<f:option value="" label="Select" />
				<f:options items="${protocols}" />
			</f:select>
	    	<input name="files" type="file" multiple="" />      
	    	<input name="Submit" type="submit" />  
    	</f:form>
    </body>
</html>