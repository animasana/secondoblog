<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp" %>

<div class="container">
  <div class="mb-3">
    글번호: <span>${article.id()}, </span>
    작성자: <span>${member.username()}</span>
  </div>
  <form>
    <div class="mb-3">
      <label for="title" class="form-label">Title</label>
      <input type="text" class="form-control" id="title" placeholder="Write a title" value="${article.title()}" />
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">Content</label>
      <textarea class="form-control" id="content" rows="3">${article.content()}</textarea>
    </div>
    <div class="mb-3">
      <input type="hidden" id="hidden-id" value="${article.id()}" />
    </div>    
  </form>
  
  <button id="btn-back" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
  <c:if test="${member.username() == principal.getUsername()}">
    <button id="btn-update" class="btn btn-warning">수정</button>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
  </c:if>
  
  <div class="card mt-3">
    <form>
      <input type="hidden" id="board-id" value="${article.id()}" />
      <input type="hidden" id="member-id" value="${principal.getId()}" />      
      <div class="card-body">
        <textarea id="reply-content" rows="1" class="form-control"></textarea>
      </div>
      <div class="card-footer">
        <button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
      </div>
    </form>    
  </div>
  <br />
  <div class="card">
    <div class="card-header">댓글리스트</div>
    <ul id="reply--items" class="list-group">
      <c:forEach var="reply" items="${article.replies()}">
        <li id="reply--item--1" class="list-group-item d-flex justify-content-between">
          <div>${reply.getContent()}</div>
          <div class="d-flex">
            <div class="font-italic">작성자: ${reply.getMember().getUsername()} &nbsp</div>
            <button class="badge">삭제</button>
          </div>
        </li>
      </c:forEach>      
    </ul>
  </div>
</div>

<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/updateBoard.js"></script>
<script type="text/javascript" src="/js/deleteBoard.js"></script>
<script type="text/javascript" src="/js/postReply.js"></script>
     
<%@ include file="../layout/footer.jsp" %>