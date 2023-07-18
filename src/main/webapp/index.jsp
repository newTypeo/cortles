<%@page import="com.cortles.project.movie.model.vo.Movie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
html {width: 10000px;}
</style>
<title>메인 페이지</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/index.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/modal.css" />
<%
	boolean memberIsLogin = loginMember != null;
%>

<script>
// 홈화면이 로딩되면 모든영화 가져올 method 실행
window.addEventListener("load", () => { 
	findAllMovies();
});

const findAllMovies = () => {
	 // 비동기로 모든 영화 가져올 ajax
	 $.ajax({
		url : "<%= request.getContextPath() %>/movie/json/findAllMovies",
		dataType : "json",
		success(movies) {
			console.log(movies); // DB에 있는 모든 영화들
			<% if(memberIsLogin) { %>
				const favoriteGenre = "<%= loginMember.getFavoriteGenre() %>";
				const favoriteGenres = favoriteGenre.split(","); // ','기준으로 배열화하여 랜덤인덱스에 있는 장르 추천을 위함
				
			<% } %>
			movies.forEach((movie) => {
				const {posterUrl, genre, movieCode} = movie;
				const imgHTML = `<img name=\${movieCode} src=\${posterUrl}>`;
				
				// 로그인 상태일 시 회원별 추천 영화
				<% if(memberIsLogin) { %>
						// 0부터 선호장르배열의 길이 사이의 정수	
						const randomIndex = Math.floor(Math.random() * favoriteGenres.length); 
							
						// 랜덤의 영화를 추천하기 위해 회원의 선호장르 중 랜덤의 장르(1개)가 영화의 장르(n개)에 포함될 시 실행되는 조건문
						if(genre.includes(favoriteGenres[randomIndex])) { 
							document.querySelector("#recommendedMovies").innerHTML += imgHTML;
						}
				<% } %>
				
				// DB에서 가져온 영화별로 장르에 맞는 article에 포스터 삽입
				if(genre != null && genre.includes("액션"))
					document.querySelector("#action").innerHTML += imgHTML;
				if(genre != null && genre.includes("SF"))
					document.querySelector("#sf").innerHTML += imgHTML;
				if (genre != null && genre.includes("공포"))
					document.querySelector("#horror").innerHTML += imgHTML;
				if (genre != null && genre.includes("로맨스"))
					document.querySelector("#romance").innerHTML += imgHTML;
				if (genre != null && genre.includes("드라마"))
					document.querySelector("#drama").innerHTML += imgHTML;
				if (genre != null && genre.includes("코메디"))
					document.querySelector("#comedy").innerHTML += imgHTML;
				if (genre != null && genre.includes("스릴러"))
					document.querySelector("#thriller").innerHTML += imgHTML;
				if (genre != null && genre.includes("판타지"))
					document.querySelector("#fantasy").innerHTML += imgHTML;
				if (genre != null && genre.includes("미스터리"))
					document.querySelector("#mystery").innerHTML += imgHTML;
				
			}) // 영화 포스터 삽입용 forEach
		}, // success
		complete() {
			[...document.querySelectorAll("img")].forEach((imgTag) => {
				imgTag.addEventListener('click', (e) =>{
					openModal(e.target.name);
					
				}) // eventListener
			}); // 모든 포스터에 clickEvent 추가용 forEach
		} // complete
	}) // ajax
}; // findAllMovies()
</script>

<section id="modal-section">
<div id="myModal" class="modal" style="display: none;">
  <div class="modal-content">
      <!-- 컨테이너 -->
      <div class="container">
      
      <% if(loginMember != null) { %>
      <form
      	name="myListFrm"
      	action="<%=request.getContextPath()%>/member/AddMyListServlet"
      	method="post">
      	  <input id="memberId" type="hidden" name="memberId" value="<%= loginMember.getMemberId()%>"/>
      	  <input id="movieCode" type="hidden" name="movieCode" value=""/>
      	  <button type="button" id="ggimButton"><img src="<%= request.getContextPath() %>/images/찜2.png"/></button>
      	  <span class="close" onclick="closeModal();">&times;</span>
      </form>
      <% } %>	  
         
          
          <!-- 동영상 재생 구역 -->
          <div class="video-container">
            <form>
              <iframe class="trailer" name="modal" width="800" height="470" src="" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
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
            <textarea style="width:765px; height:100px" rows="4" cols="50" id="commentInput" placeholder="댓글을 작성해주세요"></textarea>
            <br>
            <input type="submit" value="댓글 작성" onclick="addComment(event);" style="margin: 10px; height: 30px;">
        </form>
    </div>
        <!-- 댓글 목록 -->
        <div id="commentList">
            <ul class="comment-list"></ul>
        </div>
    </div>
  </div>
</div>
</section>

<section id="searchMovies-section">
<% 		if(memberIsLogin) { 		%>
			<div>
			<span><%= loginMember.getMemberId() %> 님이 좋아할만한 콘텐츠</span>
				<article id="recommendedMovies"></article>
			</div>
			<br/>
<%		} 							%>
			<div>
				<span>romance</span><article id="romance"></article>
			</div>
		<br/>
			<div>
				<span>SF</span><article id="sf"></article>
			</div>
		<br/>
			<div>
				<span>horror</span><article id="horror"></article>
			</div>
		<br/>
			<div>
				<span>thriller</span><article id="thriller"></article>
			</div>
		<br/>
			<div>
				<span>action</span><article id="action"></article>
			</div>
		<br/>
			<div>
				<span>drama</span><article id="drama"></article>
			</div>
		<br/>
			<div>
				<span>comedy</span><article id="comedy"></article>
			</div>
		<br/>
			<div>
				<span>fantasy</span><article id="fantasy"></article>
			</div>
		<br/>
			<div>
				<span>mystery</span><article id="mystery"></article>
			</div>
		<br/>
</section>		
			<div> 
				<article id="searchMovies-article"></article>
			</div>
<script>
const scroll = document.querySelector("body");

//모달 열기
function openModal(movie_code) {
	// console.log("(openModal)movie_code", movie_code);
	$.ajax({
		url : "<%= request.getContextPath() %>/movie/json/findOneMovies",
		data : {movie_code},
		success(movieInfo){
			// console.log("여기는 success= 	", movieInfo);
			const {actors, director, genre, openDate, runtime, story, title, titleEng, vod, movieCode} = movieInfo;
			document.querySelector(".trailer").src = vod;
			scroll.style.overflow = "hidden";	
		  	document.getElementById("myModal").style.display = "block";
		},
		complete (){
			// const src = document.querySelector('.video-container iframe').src;
			// console.log(src.getElementsByClassName('.play'));
			// document.querySelector(".trailer .play").click();
			
	  	  	// 버튼을 클릭했을 때 실행되는 코드
<%			if(memberIsLogin) { 					%>
		  		document.querySelector("#ggimButton").addEventListener("click", (e) => {
			  		const frm = document.myListFrm;
			  		frm.movieCode.value = movie_code;
			  		document.myListFrm.submit();
			  	}); // eventListener
<%			}										%>
		} // complete
	}) // ajax
}; // openModal()

// 모달 닫기
function closeModal() {
	scroll.style.overflow = "auto";
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
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>







