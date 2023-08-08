<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
  <sec:authentication property="principal" var="principal" />  
</sec:authorize>



<!DOCTYPE html>
<html
    xmlns="http://www.w3.org/1999/xhtml"
    xmlns:c="jakarta.tags.core"
    xmlns:fmt="jakarta.tags.fmt"
>
<head>
<meta charset="UTF-8">
<title>My First Blog</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" 
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous" />
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script defer src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.js" 
        integrity="sha512-RjvSEaeDqPCfUVQ9kna2/2OqHz/7F04IOl1/66LmQjB/lOeAzwq7LrbTzDbz5cJzlPNJ5qteNtHR56XaJSTNWw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script defer=defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>                

</head>
<body>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">GiGSoft</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarScroll">
        <c:choose>
          <c:when test="${empty principal}">
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
              <li class="nav-item"><a class="nav-link" href="/user/login">로그인</a></li>          
              <li class="nav-item"><a class="nav-link" href="/user/join">회원가입</a></li>          
            </ul>
          </c:when>
          <c:otherwise>
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
              <li class="nav-item"><a class="nav-link" href="/board/form">글쓰기</a></li>          
              <li class="nav-item"><a class="nav-link" href="/user/info">회원정보</a></li>
              <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>          
            </ul>
          </c:otherwise>
        </c:choose>        
      </div>
    </div>
  </nav>
  <br />