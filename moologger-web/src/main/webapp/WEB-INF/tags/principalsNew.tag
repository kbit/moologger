<%@ include file="/WEB-INF/jsp/include.jsp" %>

<form:form modelAttribute="principalModel" action="/principals" method="POST">
    <ol>
        <li>
            <form:label path="principal.identifier">Identifier</form:label>
            <form:input path="principal.identifier" />
        </li>
        <li>
            <input type="submit" value="add" />
        </li>
    </ol>
</form:form>