<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
 
<html>
    <head>
        <title>Uploads</title>
    </head>
    <body>
    	<f:form action="uploads/add" enctype="multipart/form-data" method="POST">  
	    	<input name="files" type="file" multiple="" />      
	    	<input name="Submit" type="submit" />  
    	</f:form>
    </body>
</html>