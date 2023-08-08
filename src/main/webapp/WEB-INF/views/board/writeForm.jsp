<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp" %>

<div class="container">
  <form>
    <div class="mb-3">
      <label for="title" class="form-label">Title</label>
      <input type="text" class="form-control" id="title" placeholder="Write a title">
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">Content</label>
      <textarea class="form-control" id="content" rows="3"></textarea>
    </div>  
  </form>
<button id="btn-save" class="btn btn-primary">글쓰기 저장</button>
</div>

<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/posting.js"></script>
     
<%@ include file="../layout/footer.jsp" %>