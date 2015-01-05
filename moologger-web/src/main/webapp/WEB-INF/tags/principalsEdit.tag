<%@ include file="/WEB-INF/jsp/include.jsp" %>

<form:form modelAttribute="principalModel" action="/principals/${principalModel.principal.id}" method="DELETE">
    <input type="submit" value="delete" />
</form:form>

<form:form modelAttribute="principalModel" action="/principals/${principalModel.principal.id}" method="PUT">
    <ol>
        <li>
            <form:label path="principal.identifier">Identifier</form:label>
            <form:input path="principal.identifier" />
        </li>
        <li>
            <form:label path="principal.aliases">Aliases</form:label>
            <form:select path="principal.aliases" multiple="true">
                <form:options items="${principalModel.principal.aliases}" itemLabel="identifier" itemValue="identifier" />
            </form:select>
        </li>
        <li>
            <input name="Submit" type="submit" />
        </li>
    </ol>
</form:form>