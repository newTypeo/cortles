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
<div id="myModal" class="modal" style="display: none;">
  <div class="modal-content">
      <!-- 컨테이너 -->
      <div class="container">
          <span class="close" onclick="closeModal();">&times;</span>
          
          <!-- 동영상 재생 구역 -->
          <div class="video-container">
              <h3>동영상 재생</h3>
              <form>
                  <iframe width="560" height="315" src="https://www.youtube.com/embed/WXLfyrmKQUc" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                </form>
            </div>
            
            <!-- 별점 평점 구역 -->
            <div class="rating-container">
                <form>
                    <div class="star-rating">
                        <input type="radio" id="star5" name="rating" value="5">
                        <label for="star5" title="5점"></label>
                        <input type="radio" id="star4" name="rating" value="4">
                        <label for="star4" title="4점"></label>
                        <input type="radio" id="star3" name="rating" value="3">
                        <label for="star3" title="3점"></label>
                        <input type="radio" id="star2" name="rating" value="2">
                        <label for="star2" title="2점"></label>
                    <input type="radio" id="star1" name="rating" value="1">
                  <label for="star1" title="1점"></label>
                </div>
            </form>
        </div>
        <!-- 댓글 작성 폼 -->
        <div class="comment-form">
        <form>
            <textarea rows="4" cols="50" id="commentInput" placeholder="댓글을 작성해주세요"></textarea>
            <br>
            <input type="submit" value="댓글 작성" onclick="addComment(event);">
        </form>
    </div>
        <!-- 댓글 목록 -->
        <div id="commentList">
            <ul class="comment-list">
            </ul>
        </div>
    </div>
  </div>
</div>

		<span>홍길동 님의 취향저격 베스트 콘텐츠</span>
			<article id="art1">
			
				
				<a href="javascript:void(0);" onclick="openModal();">
					<img src='https://ifh.cc/g/JlsdD9.jpg'>
				</a>
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

const scroll = document.querySelector("body");

//모달 열기
function openModal() {
	scroll.style.overflow = "hidden";	
  document.getElementById("myModal").style.display = "block";
}

// 모달 닫기
function closeModal() {
	scroll.style.overflow = "block";
  document.getElementById("myModal").style.display = "none";
}

// 동영상 재생
function playVideo() {
  var videoUrl = document.getElementById("videoUrl").value;
  var videoContainer = document.querySelector(".video-container");
  videoContainer.innerHTML = '<iframe width="100%" height="100%" src="' + videoUrl + '" frameborder="0" allowfullscreen></iframe>';
}

// 댓글 작성
function addComment(event) {
  event.preventDefault();
  
  var commentInput = document.getElementById("commentInput");
  var comment = commentInput.value;
  
  if (comment.trim() === "") {
    alert("댓글을 작성해주세요.");
    return;
  }
  
  var commentList = document.querySelector(".comment-list");
  var commentItem = document.createElement("li");
  commentItem.innerHTML = '<div class="comment-text">' + comment + '</div><div class="comment-date">' + getFormattedDate() + '</div>';
  commentList.appendChild(commentItem);
  
  commentInput.value = "";
}

// 현재 날짜 시간 반환
function getFormattedDate() {
  var now = new Date();
  var year = now.getFullYear();
  var month = ("0" + (now.getMonth() + 1)).slice(-2);
  var day = ("0" + now.getDate()).slice(-2);
  var hours = ("0" + now.getHours()).slice(-2);
  var minutes = ("0" + now.getMinutes()).slice(-2);
  var seconds = ("0" + now.getSeconds()).slice(-2);
  
  return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}


</script>
	</section>
	
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>







