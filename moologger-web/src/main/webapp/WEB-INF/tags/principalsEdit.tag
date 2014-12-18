<%@ include file="/WEB-INF/jsp/include.jsp" %>

<div id="aliases-edit" class="form-content">
    <form:form action="/principals/${id}/edit" enctype="multipart/form-data" method="POST">
        <ol>
            <li>
                <form:label path="selectedAliases">Aliases</form:label>
                <form:select path="selectedAliases" multiple="true">
                    <core:forEach items="${aliases}" var="alias">
                        <optgroup label="${alias.key}">
                            <form:options items="${alias.value}" itemLabel="identifier" itemValue="aliasId" />
                        </optgroup>
                    </core:forEach>
                </form:select>
            </li>
            <li>
                <input name="Submit" type="submit" />
            </li>
        </ol>
    </form:form>
</div>