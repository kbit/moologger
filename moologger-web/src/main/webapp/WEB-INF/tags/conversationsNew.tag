<%@ include file="/WEB-INF/jsp/header.jsp" %>

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