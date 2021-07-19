<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>

<div class="container">

	<c:if test="${sessionScope.principal.id == postEntity.user.id}">
		<!-- empty 있는지없는지 유무 -->
		<a href="/post/${postEntity.id}/updateForm" class="btn btn-warning">수정</a>

		<form style="display: inline-block" onsubmit="deletePost(${postEntity.user.id})">
			<button id="btn-delete" class="btn btn-danger" type="submit">삭제</button>
		</form>
	</c:if>


	<br /> <br />
	<div>
		<span>글 번호 : ${postEntity.id}</span> 작성자 : <span><i> ${postEntity.user.username}</i></span>
	</div>
	<br />
	<div>
		<h3>${postEntity.title}제목</h3>
	</div>
	<hr />
	<div>
		<div>${postEntity.content}내용</div>
	</div>
	<hr />

	<div class="card">
		<form>
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>
	<br />

	<div class="card">
		<div class="card-header">
			<b>댓글 리스트</b>
		</div>

		<ul id="reply-box" class="list-group">

			<c:forEach var="comment" items="${commentsEntity}">
				<li id="reply-${comment.id}" class="list-group-item d-flex justify-content-between">
					<div>${comment.text}</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : ${comment.user.username}</div>
						<c:if test="${principal.id == comment.user.id}">
						<button class="badge" onclick="deleteComment(${comment.id})">삭제</button> <!-- 댓글삭제에서 파라미터로 바로 넘겨준이유는 -->
						</c:if>
					</div>
				</li>
			</c:forEach>

		</ul>


	</div>
	<br />
</div>

<Script>
	async function deleteComment(commentId) {
		let response = await fetch("/comment/" + commentId, {
			 method : "delete"
		});
		
		let parseResponse = await response.text();
		
		
		if(parseResponse === "ok");
			//location.reload();
			let deleteEL = document.qeuerySelector("#reply-"+commentId);
			deleteEL.removeChild(let deleteEL);	
			console.log(deleteEL);
			
	}


	
	async function deletePost(postId){
		event.preventDefault();
		let response = await fetch("/post/"+postId,{ //자바스크립트안에다가는 EL표현식을 넣으면안됀다. 파일로 빼낼때 해석이되지않기때문이다.
			method:"delete",									   
			
		});
		let parseResponse = await response.text();
		
		console.log(parseResponse);
		console.log("/post/${postEntity.id}");
		if(parseResponse === "ok"){
			
			location.href="/";
		}
	}
	

</Script>



<%@ include file="../layout/footer.jsp"%>

