<%@ include file="/WEB-INF/jsp/include.jsp" %>

<form:form modelAttribute="conversationModel" action="/conversations" method="POST" enctype="multipart/form-data">
    <ol>
        <li>
            <form:label path="conversation.client">Client</form:label>
            <form:select path="conversation.client">
                <form:option value="" label="Select" />
                <form:options items="${clients}" />
            </form:select>
        </li>
        <li>
            <form:label path="conversation.protocol">Protocol</form:label>
            <form:select path="conversation.protocol">
                <form:option value="" label="Select" />
                <form:options items="${protocols}" />
            </form:select>
        </li>
        <li>
            <form:label path="files">Files</form:label>
            <input name="files" type="file" multiple="" />
        </li>
        <li>
            <input type="submit" value="add" />
        </li>
    </ol>
</form:form>