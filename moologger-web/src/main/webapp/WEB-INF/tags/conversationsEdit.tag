<%@ include file="/WEB-INF/jsp/include.jsp" %>

<div class="conversation">
    <format:formatDate var="startDate" value="${moo:toDate(conversationModel.conversation.startTimestamp)}" type="date"/>
    <format:formatDate var="startTime" value="${moo:toDate(conversationModel.conversation.startTimestamp)}" type="time"/>
    <format:formatDate var="endTime" value="${moo:toDate(conversationModel.conversation.endTimestamp)}" type="time"/>

    <span class="conversation-title">
        Conversation - ${startDate}: ${startTime}-${endTime}
    </span>
    <span class="conversation-actions">
        <form:form modelAttribute="conversationModel" action="/conversations/${conversationModel.conversation.id}" method="DELETE">
            <input type="submit" value="delete" />
        </form:form>
    </span>
</div>

<form:form modelAttribute="conversationModel" action="/conversations/${conversationModel.conversation.id}" method="PUT">
    <core:forEach items="${conversationModel.conversation.messages}" var="message" varStatus="status">
        <div class="message">
            <div class="message-title">
                <form:input path="conversation.messages[${status.index}].alias" /> (<form:input path="conversation.messages[${status.index}].timestamp" />)
            </div>
            <div class="message-text">
                <form:input path="conversation.messages[${status.index}].text" />
            </div>
        </div>
    </core:forEach>

    <input type="submit" value="update" />
</form:form>