<%@ include file="/WEB-INF/jsp/include.jsp" %>

<div>
    <core:forEach items="${conversations}" var="conversation">
        <div class="conversation">
            <span class="conversation-title">
                Conversation - <format:formatDate value="${conversation.startTimestamp}" type="both"/>-<format:formatDate value="${conversation.endTimestamp}" type="both"/>
            </span>
            <span class="conversation-actions">
                <a href="/conversations/${conversation.id}/edit">edit</a>
            </span>
        </div>
        <core:forEach items="${conversation.messages}" var="message">
            <div class="message">
                <div class="message-title">
                    ${message.identifier} (<format:formatDate value="${message.timestamp}" type="time"/>)
                </div>
                <div class="message-text">
                    ${message.text}
                </div>
            </div>
        </core:forEach>
    </core:forEach>
</div>