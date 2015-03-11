<%@ include file="/WEB-INF/jsp/include.jsp" %>

<core:forEach items="${conversations}" var="conversation">
    <format:formatDate var="startTimestamp" value="${conversation.startTimestamp}" type="both"/>
    <format:formatDate var="endTimestamp" value="${conversation.endTimestamp}" type="both"/>

    <div class="conversation">
            <span class="conversation-title">
                Conversation - ${startTimestamp}-${endTimestamp}
            </span>
            <span class="conversation-actions">
                <a href="/conversations/${conversation.id}/edit" title="Edit">edit</a>
            </span>
    </div>
    <core:forEach items="${conversation.messages}" var="message">
        <core:set var="aliasKey" value="${conversation.client}+${conversation.protocol}=${message.alias}" />
        <core:set var="alias" value="${aliases[aliasKey]}" />
        <format:formatDate var="timestamp" value="${message.timestamp}" type="time"/>

        <div class="message">
            <div class="message-title">
                    ${alias} (${timestamp})
            </div>
            <div class="message-text">
                    ${message.text}
            </div>
        </div>
    </core:forEach>
</core:forEach>