<%@ include file="/WEB-INF/jsp/include.jsp" %>

<core:forEach items="${conversations}" var="conversation">
    <format:formatDate var="startDate" value="${moo:toDate(conversation.startTimestamp)}" type="date"/>
    <format:formatDate var="startTime" value="${moo:toDate(conversation.startTimestamp)}" type="time"/>
    <format:formatDate var="endTime" value="${moo:toDate(conversation.endTimestamp)}" type="time"/>

    <div class="conversation">
            <span class="conversation-title">
                Conversation - ${startDate}: ${startTime}-${endTime}
            </span>
            <span class="conversation-actions">
                <a href="/conversations/${conversation.id}/edit" title="Edit">edit</a>
            </span>
    </div>
    <core:forEach items="${conversation.messages}" var="message">
        <core:set var="aliasKey" value="${message.client}+${message.protocol}=${message.alias}" />
        <core:set var="alias" value="${aliases[aliasKey]}" />
        <format:formatDate var="timestamp" value="${moo:toDate(message.timestamp)}" type="time"/>

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