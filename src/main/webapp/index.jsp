<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/index.css" />
</head>
<body>

	<section>
		<div>
		<span>홍길동 님의 취향저격 베스트 콘텐츠</span>
			<article id="art1">
			<div class="modal">
      			<div class="modal_body">
      				Hello
      				<button class="closepopup">닫기</button>
      				<form action="" method="POST" >
					<input type="button" value="찜하기">
				</form>	
      			</div>
    		</div>
				
				<div class="div-open-popup">
				<img src='https://ifh.cc/g/JlsdD9.jpg'>
				</div>
				<a href=""><img src="<%= request.getContextPath() %>/images/flower1.PNG"/></a>
				<img src="<%= request.getContextPath() %>/images/flower2.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower3.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower4.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower5.PNG"/>
				<img src="<%= request.getContextPath() %>/images/river1.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower1.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower2.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower3.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower4.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower5.PNG"/>
				<img src="<%= request.getContextPath() %>/images/river1.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower1.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower2.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower3.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower4.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower5.PNG"/>
				<img src="<%= request.getContextPath() %>/images/river1.PNG"/>
			</article>
		</div>
		<hr/>
		<div>
		<span>액션</span>
			<article id="art1">
				<img src="<%= request.getContextPath() %>/images/flower1.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower2.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower3.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower4.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower5.PNG"/>
				<img src="<%= request.getContextPath() %>/images/river1.PNG"/>
			</article>
		</div>
		<hr/>
		<div>
		<span>호러</span>
			<article id="art1">
				<img src="<%= request.getContextPath() %>/images/flower1.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower2.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower3.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower4.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower5.PNG"/>
				<img src="<%= request.getContextPath() %>/images/river1.PNG"/>
			</article>
		</div>
		<hr/>
		<div>
		<span>로맨스</span>
			<article id="art1">
				<img src="<%= request.getContextPath() %>/images/flower1.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower2.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower3.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower4.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower5.PNG"/>
				<img src="<%= request.getContextPath() %>/images/river1.PNG"/>
			</article>
		</div>
		<hr/>
		<div>
		<span>코미디</span>
			<article id="art1">
				<img src="<%= request.getContextPath() %>/images/flower1.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower2.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower3.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower4.PNG"/>
				<img src="<%= request.getContextPath() %>/images/flower5.PNG"/>
				<img src="<%= request.getContextPath() %>/images/river1.PNG"/>
			</article>
		</div>
		<hr/>
		<script>
		const modal = document.querySelector(".modal");
		const divopenPopup =document.querySelector(".div-open-popup");
		const closepopup = document.querySelector(".closepopup");
		
		closepopup.addEventListener('click', () => {
			modal.style.display = 'none';
		})
		divopenPopup.addEventListener('click', () => {
			modal.style.display = 'block';
		});
		
		
		</script>
	</section>
	
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>







