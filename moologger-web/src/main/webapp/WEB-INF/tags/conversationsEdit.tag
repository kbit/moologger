<%@ include file="/WEB-INF/jsp/include.jsp" %>

<div>
    <form:form action="/conversations/${id}/edit" enctype="multipart/form-data" method="POST">
        <div class="conversation">
            <span class="conversation-title">
                Conversation - <format:formatDate value="${conversation.startTimestamp}" type="both"/>-<format:formatDate value="${conversation.endTimestamp}" type="both"/>
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
    </form:form>
</div>