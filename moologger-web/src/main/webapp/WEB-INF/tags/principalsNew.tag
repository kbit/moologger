<%@ include file="/WEB-INF/jsp/include.jsp" %>

<div id="principals-new" class="form-content">
    <form:form action="/principals/new" enctype="multipart/form-data" method="POST">
        <ol>
            <li>
                <form:label path="identifier">Identifier</form:label>
                <form:input path="identifier" />
            </li>
            <li>
                <input name="Submit" type="submit" />
            </li>
        </ol>
    </form:form>
</div>