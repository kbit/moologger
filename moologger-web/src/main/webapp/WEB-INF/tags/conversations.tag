<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="conversation">
    <core:forEach items="${conversations}" var="conversation">
        <div class="conversation-header">
            Conversation - <format:formatDate value="${conversation.startTimestamp}" type="both"/>-<format:formatDate value="${conversation.endTimestamp}" type="both"/>
        </div>
        <core:forEach items="${conversation.messages}" var="message">
            <div class="message">
                <div class="message-alias">
                    ${message.identifier} (<format:formatDate value="${message.timestamp}" type="time"/>)
                </div>
                <div class="message-text">
                    ${message.text}
                </div>
            </div>
        </core:forEach>
    </core:forEach>
</div>