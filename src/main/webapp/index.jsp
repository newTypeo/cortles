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
				<div class="modal1-trailer">
					<video src="https://youtu.be/9qkyVaOUsRs" class="modal-video" controls></video>
				</div>
      			<div class="modal2-content">
      			안냉
      			
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
const divopenPopup = document.querySelector(".div-open-popup");
const closepopup = document.querySelector(".closepopup");
const WishList = document.querySelector(".wishList");

divopenPopup.addEventListener("click", () => {
  modal.style.display = "block";
});

// 윈도우 크기가 변경될 때 모달을 가운데로 위치시킴
window.addEventListener("resize", centerModal);

// 모달을 가운데로 위치시키는 함수
function centerModal() {
  var modal = document.querySelector(".modal");
  var modalContent = document.querySelector(".modal-content");

  var windowHeight =
    window.innerHeight ||
    document.documentElement.clientHeight ||
    document.body.clientHeight;
  var windowWidth =
    window.innerWidth ||
    document.documentElement.clientWidth ||
    document.body.clientWidth;

  var modalHeight = modalContent.offsetHeight;
  var modalWidth = modalContent.offsetWidth;

  var top = (windowHeight - modalHeight) / 2;
  var left = (windowWidth - modalWidth) / 2;

  modal.style.top = top + "px";
  modal.style.left = left + "px";
}
</script>
	</section>
	
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>







