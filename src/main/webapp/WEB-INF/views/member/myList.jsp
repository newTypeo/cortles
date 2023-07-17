<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mylist.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/modal.css" />
<section>
	<h1>My List</h1>
	
	<div>
		<article id="zzim"></article>
	</div>
	
	<!-- 사진부분 회원 테이블의 찜 영화코드 조회 후 script로 넣기 -->
	<script>
  <% if (loginMember != null && loginMember.getFavoriteMovieCode() != null) { %>
	    const favorite = '<%= loginMember.getFavoriteMovieCode() %>'.split(",");
	    // 찜한 영화코드를 배열로 만들어서 forEach
	    favorite.forEach((movieCode) => {
	      $.ajax({
	        url: "<%=request.getContextPath()%>/movie/json/findOneMovies",
	        data: { movie_code : movieCode },
	        dataType: "json",
	        success(movie) {
	          // 영화 1개 씩 뿌리기
	          // console.log("영화 날라 왔니? " , movie);
	        	const {posterUrl, genre, movieCode} = movie;
				const imgHTML = `<img name=\${movieCode} src=\${posterUrl}>`;
	          	console.log("posterUrl, genre, movieCode",posterUrl, genre, movieCode);
	          	document.querySelector("#zzim").innerHTML += imgHTML;
	        }, // success 
	        complete(){
	        	[...document.querySelectorAll("img")].forEach((imgTag) => {
					imgTag.addEventListener('click', (e) =>{
						// console.log("e.target", e.target.name);
						openModal(e.target.name);
					})
				});
	        }
	      }) // ajax
	    });
  <% } %>
  
//모달 열기 (찜목록)
const scroll = document.querySelector("body");
function openModal(movie_code) {
 	$.ajax({
 		url : "<%= request.getContextPath() %>/movie/json/findOneMovies",
 		data : {movie_code},
 		success(clickedMovie) {
 			const {actors, director, genre, openDate, runtime, story, title, titleEng, vod, movieCode} = clickedMovie;
 			document.querySelector(".trailer").src = vod;
 			scroll.style.overflow = "hidden";	
 		  	document.getElementById("myModal").style.display = "block";
 		},
 		complete (){
	  		document.querySelector("#ggimButton").addEventListener("click", (e) => {
		  		const frm = document.myListFrm;
		  		frm.movieCode.value = movieCode;
		  		document.myListFrm.submit();
	  		}) // EventListener
 		} // complete
 	}) // ajax
};
  
// 모달 닫기
function closeModal() {
	scroll.style.overflow = "auto";
  	document.getElementById("myModal").style.display = "none";
}

</script>

	<!-- 없을시에는 -->
	<!-- <h2>찜하신 영화가 없습니다.</h2>  -->
</section>

<section>
<div id="myModal" class="modal" style="display: none;">
  <div class="modal-content">
      <!-- 컨테이너 -->
      <div class="container">
      <% if(loginMember != null) { %>
      <form
      	name="myListFrm"
      	action="<%=request.getContextPath()%>/member/AddMyListServlet"
      	method="post"
      >
      	  <input id="memberId" type="hidden" name="memberId" value="<%= loginMember.getMemberId()%>"/>
      	  <input id="movieCode" type="hidden" name="movieCode" value=""/>
      	  <button type="button" id="ggimButton">찜</button>
      </form>
      <% } %>	  
          <span class="close" onclick="closeModal();">&times;</span>
          
          <!-- 동영상 재생 구역 -->
          <div class="video-container">
              <form>
                  <iframe class="trailer" width="560" height="315" src="" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
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
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>