<%@ include file="/WEB-INF/jsp/include.jsp" %>

<format:formatDate var="startTimestamp" value="${conversationModel.conversation.startTimestamp}" type="both"/>
<format:formatDate var="endTimestamp" value="${conversationModel.conversation.endTimestamp}" type="both"/>

<div class="conversation">
    <span class="conversation-title">
        Conversation - ${startTimestamp}-${endTimestamp}
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
                <form:input path="conversation.messages[${status.index}].identifier" /> (<form:input path="conversation.messages[${status.index}].timestamp" />)
            </div>
            <div class="message-text">
                <form:input path="conversation.messages[${status.index}].text" />
            </div>
        </div>
    </core:forEach>

    <input type="submit" value="update" />
</form:form>