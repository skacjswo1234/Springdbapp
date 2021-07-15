<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="#"  onsubmit="updatePost()">
		<div class="form-group">
			<label for="title">title:</label> 
			<input id="title" value="${postEntity.title}" type="text" class="form-control" placeholder="Enter title" name="title" required="required" />
		</div>
		<div class="form-group">
			<textarea id="content" rows="10" class="form-control" name="content">
  			${postEntity.content}
	  </textarea>
		</div>
		
		<button type="submit" class="btn btn-primary">수정 완료</button>
	</form>
</div>
 
	<script>
		async function updatePost(){ 
			alert("나실행됨?");
			console.log(event);
			event.preventDefault(); // submit 동작을 막는다.
			
			let title = document.querySelector("#title").value;
			let content = document.querySelector("#content").value;
			
			console.log(title);
			console.log(content);
			
			// update변수
			let updateDto = {
					title: title,
					content: content
			};
			// await를 통해서 기다렸다가 response에 패치를 담고 내려간다.
			// $는 쓸수없다 .jsp가 el표현식으로 착각하기때문이다.
			// 안에 감싸면 자바가 먼저 해석한다.
			
			let response = await fetch("/post/${postEntity.id}", {
				method: "put",
				body: JSON.stringify(updateDto),
				headers:{
					"Content-Type": "application/json; charset=utf-8"
				}
			});
			
		
			let parseResponse = await response.text(); //json(). text()
			
			console.log(parseResponse);
			
			if(parseResponse === "ok") { // ==값 비교 , === 타입, 값을 같이 비교한다
				// location 객체 -> 화면으로 돌려주는 객체
				// history -> 뒤로가기 객체
				// document -> 화면의 element를 찾아주는 객체
				location.href ="/post/${postEntity.id}";
			}		
		}

		 $('#content').summernote({
		     placeholder: 'Hello Bootstrap 4',
		     tabsize: 2,
		     height: 500
		   });

</script>

<%@ include file="../layout/footer.jsp"%>