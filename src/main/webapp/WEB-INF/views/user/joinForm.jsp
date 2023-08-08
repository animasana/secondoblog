<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
  <form>
    <div class="mb-3 mt-3">
      <label for="username" class="form-label">Username:</label> 
      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
    </div>
    <div class="mb-3 mt-3">
      <label for="email" class="form-label">Email:</label> 
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
    <div class="mb-3 mt-3">
      <label for="pwd" class="form-label">Password:</label> 
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
    </div>    
  </form>
  <button id="btn-save" type="button" class="btn btn-primary">회원가입</button>
</div>

<script src="/js/common.js"></script>
<script src="/js/join.js"></script>

<%@ include file="../layout/footer.jsp"%>