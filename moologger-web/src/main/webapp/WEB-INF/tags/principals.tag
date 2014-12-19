<%@ include file="/WEB-INF/jsp/include.jsp" %>

<table>
    <thead>
        <tr>
            <th>Action</th>
            <th>Identifier</th>
            <th colspan="3">Aliases</th>
        </tr>
        <tr>
            <th colspan="2"></th>
            <th>Identifier</th>
            <th>Client</th>
            <th>Protocol</th>
        </tr>
    </thead>
    <tbody>
        <core:forEach items="${principals}" var="principal">
            <tr>
                <td>
                    <a href="principals/${principal.id}/edit" title="Edit">edit</a>
                </td>
                <td>${principal.identifier}</td>
                <core:choose>
                    <core:when test="${empty principal.aliases}">
                        <td colspan="3">No Aliases Found</td>
                    </core:when>
                    <core:otherwise>
                        <core:forEach items="${principal.aliases}" var="alias">
                            <td>${alias.identifier}</td>
                            <td>${alias.client}</td>
                            <td>${alias.protocol}</td>
                        </core:forEach>
                    </core:otherwise>
                </core:choose>
            </tr>
        </core:forEach>
    </tbody>
</table>