<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="layout/header.jsp" %>

<div class="container mt-2">
  <c:forEach items="${articles.content}" var="article">  
    <div class="card m-2">      
      <div class="card-body">
        <h5 class="card-title">${article.title}</h5>                    
        <a href="/board/${article.id}" class="btn btn-primary">상세 보기</a>
      </div>
    </div>  
  </c:forEach>

  <nav aria-label="..." class="m-2">
    <ul class="pagination justify-content-center">
      <c:choose>
        <c:when test="${articles.first}">
          <li class="page-item disabled">
            <a class="page-link" href="?page=${articles.number - 1}">Previous</a>
          </li>    
        </c:when>
        <c:otherwise>
          <li class="page-item">
            <a class="page-link" href="?page=${articles.number - 1}">Previous</a>
          </li>    
        </c:otherwise>      
      </c:choose>
      
      <c:choose>
        <c:when test="${articles.last}">
          <li class="page-item disabled">
            <a class="page-link" href="?page=${articles.number + 1}">Next</a>
          </li>    
        </c:when>
        <c:otherwise>
          <li class="page-item">
            <a class="page-link" href="?page=${articles.number + 1}">Next</a>
          </li>    
        </c:otherwise>      
      </c:choose>
          
    </ul>
  </nav>
</div>
     
<%@ include file="layout/footer.jsp" %>