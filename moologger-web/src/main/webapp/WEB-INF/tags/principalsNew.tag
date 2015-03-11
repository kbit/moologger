<%@ include file="/WEB-INF/jsp/include.jsp" %>

<form:form modelAttribute="principalModel" action="/principals" method="POST">
    <ol>
        <li>
            <form:label path="principal.identifier">Identifier</form:label>
            <form:input path="principal.identifier" />
        </li>
        <li>
            <form:label path="principal.aliases">Aliases</form:label>
            <form:select path="principal.aliases" multiple="true">
                <form:options items="${aliases}" itemLabel="identifier" itemValue="key" />
            </form:select>
        </li>
        <li>
            <input type="submit" value="add" />
        </li>
    </ol>
</form:form>