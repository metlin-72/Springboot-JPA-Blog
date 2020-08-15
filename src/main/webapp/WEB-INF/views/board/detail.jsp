<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>


<div class="container">
    <input type="hidden">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id eq principal.blogUser.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <br><br>

    <div>
        글번호: <span id="spnId"><i>${board.id} </i></span>
        작성자: <span><i>${board.user.username} </i></span>
    </div>
    <br>

    <div>
        <h3>${board.title}</h3>
    </div>
    <hr>

    <div>
        <div>${board.content}</div>
    </div>
    <hr>

    <div class="card">
        <input type="hidden" id="boardId" value="${board.id}">
        <input type="hidden" id="userId" value="${principal.blogUser.id}">
        <div class="card-body">
            <textarea id="reply_content" class="form-control" rows="1"></textarea>
        </div>
        <div class="card-footer">
            <button id="btn-reply-save" type="button" class="btn btn-primary">등록</button>
        </div>
    </div>
    <br>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply-box" class="list-group">
            <c:forEach var="reply" items="${board.replys}">
                <li id="reply-ly" class="list-group-item d-flex justify-content-between">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자: ${reply.user.username} &nbsp;</div>
                        <c:if test="${reply.user.id eq principal.blogUser.id}">
                            <button class="badge" data-boardid="${board.id}" data-replyid="${reply.id}">삭제</button>
                        </c:if>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>