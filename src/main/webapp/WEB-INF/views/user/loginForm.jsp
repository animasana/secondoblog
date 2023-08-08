<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
  <form action="/user/login/process" method="POST">
    <div class="mb-3 mt-3">
      <label for="username" class="form-label">Username:</label> 
      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
    </div>

    <div class="mb-3 mt-3">
      <label for="pwd" class="form-label">Password:</label> 
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
    </div>    
    <button id="btn-login" type="submit" class="btn btn-primary">로그인</button>
    <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=4f66b92874c03fd1c08f3c5a0d2fe38d&redirect_uri=http://localhost:8000/auth/kakao/callback">
      <img alt="kakao login button" height="38px" src="/image/kakao_login_medium.png" />
    </a>    
  </form>  
</div>

<%@ include file="../layout/footer.jsp"%>