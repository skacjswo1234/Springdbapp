<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>

<div class="container">


	<c:forEach var="post" items="${postsEntity}">
		<!-- var은 페이지스코프에들어감 -->

		<div class="card">
			<!-- Block : 넓이 끝가지 , inline : 넓이끝까지 안감. -->
			<div class="card-body">
				<h4 class="card-title">${post.title}</h4>
				<a href="/post/${post.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
		<br/>
		<!-- End of Card -->
	</c:forEach>



</div>
<!-- End Of Container -->

<%@ include file="../layout/footer.jsp"%>