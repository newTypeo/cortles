<%@page import="com.cortles.project.member.model.vo.Favorite"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header2.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mylist.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/modal.css" />
<%
	List<Favorite> favorites = (List<Favorite>)request.getAttribute("favorite");
%>

<section>
	<h1>My List</h1>
	
	<div>
		<article id="zzim"></article>
	</div>
	<!-- 사진부분 회원 테이블의 찜 영화코드 조회 후 script로 넣기 -->
	<!-- 없을시에는 -->
	<!-- <h2>찜하신 영화가 없습니다.</h2>  -->
</section>

<!-- 모달창 section -->
<section>
<div id="myModal" class="modal" style="display: none;">
  <div class="modal-content">
      <!-- 컨테이너 -->
      <div class="container">
      
      <% if(loginMember != null) { %>
	      <form
	      	name="myListFrm"
	      	action="<%=request.getContextPath()%>/member/deleteMyListServlet"
	      	method="post">
	      	  <input id="memberId" type="hidden" name="memberId" value="<%= loginMember.getMemberId()%>"/>
	      	  <input id="movieCode" type="hidden" name="movieCode" value=""/>
	      	  <button type="button" id="ggimButton">찜 취소</button>
	      </form>
      <% } %>
      	  
          <span class="close" onclick="closeModal();">&times;</span>
          
          <!-- 정보페이지 화면 -->
          <div class="video-container">
            <form>
              <iframe class="trailer" name="modal" width="800" height="470" src="" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
            </form>
          </div>
          
    <!-- 댓글 작성 폼 -->
	<form name="movieCommentFrm">  
		
        <!-- 별점 평점 구역 -->
        <div class="rating-container">
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
      	</div>
         <!-- 댓글 작성 폼 -->
        <div class="comment-form">
            <textarea style="width:765px; height:100px" rows="4" cols="50" id="commentInput" placeholder="댓글을 작성해주세요"></textarea>
            <input type="hidden" id="modal-movie-code" name = "modal-movie-code">
            <br>
            <input type="button" value="댓글 작성" onclick="createMovieComment();" style="margin: 10px; height: 30px;"/>
	    </div>
	</form>
	
        <!-- 댓글 목록 -->
        <table id="commentList">
	        <thead>
	  			<tr>
	  				<th>writer</th>
	  				<th>content</th>
	  				<th>date</th>
	  				<th>starGrade</th>
	  			</tr>      
	        </thead>
	        <tbody id="movie-comment-body"><!-- 한줄평 출력할 영역 --></tbody>
        </table>
    </div>	
  </div>
</div>
</section>

<script>
<%  if (loginMember != null && favorites != null) { 
	 	for(Favorite favorite : favorites){
	 %>
	    
	        $.ajax({
	            url: "<%=request.getContextPath()%>/movie/json/findOneMovies",
	            data: { movie_code: "<%= favorite.getMovieCode() %>" },
	            dataType: "json",
	            success(movie) {
	                const { posterUrl, genre, movieCode } = movie;
	                const imgHTML = `<img name=\${movieCode} src=\${posterUrl}>`;
	                document.querySelector("#zzim").innerHTML += imgHTML;
	            },
	            complete() {
	                [...document.querySelectorAll("img")].forEach((imgTag) => {
	                    imgTag.addEventListener('click', (e) => {
	                        openModal(e.target.name);
	                        document.querySelector("#modal-movie-code").value = e.target.name;
	                    }); //eventListener
	                }); // forEach
	            } // complete
	        }); // ajax
 	
<%	 	} // for
	} // if 	%>
 
	
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
		  		frm.movieCode.value = movie_code;
		  		document.myListFrm.submit();
	  		}); // EventListener
	  		printMovieComments(); // 모달열면 한줄평 출력
 		} // complete
 	}) // ajax
}; // method end
  
  
  
// 모달 닫기
function closeModal() {
	scroll.style.overflow = "auto";
  	document.getElementById("myModal").style.display = "none";
}



//댓글 작성
const createMovieComment = () => {
	<% if(loginMember == null){
		session.setAttribute("mag", "로그인 후 이용할 수 있습니다.");
	} %>
	const movieCode = document.querySelector("#modal-movie-code").value; // 영화코드
	const movieContent = document.querySelector("#commentInput").value; // 한줄평 내용
	console.log("movieCode",movieCode);
	console.log("movieContent",movieContent);
		
	// 별점입력 안했을 경우
	if(document.querySelector('input[name="rating"]:checked') == null){
		alert("별점은 필수입니다.");
	} 
	// 내용이 빈칸일 경우
	else if(movieContent == "") {
		alert("내용을 입력해주세요.");
		document.querySelector("#commentInput").focus();
	}
	// 위의 조건들이 만족하면 한줄평 등록 가능
	else {
		const starGrade = document.querySelector('input[name="rating"]:checked').value; // 별점
		// 한줄평 등록
		$.ajax({
			url : "<%= request.getContextPath() %>/movie/createMovieComment",
			data : {movieCode, movieContent, starGrade},
			dataType : "json",
			method : "post",
			success(duplitedMsg){
				
				if(duplitedMsg != null && duplitedMsg != ""){
					alert(`\${duplitedMsg}`);
				};
				
			}, // success
			complete(){
				document.movieCommentFrm.reset();
				printMovieComments();
			} // complete
		}) // ajax
	}; // else
}; // createMovieComment()



//모달에 댓글 출력
const printMovieComments = () => {
	const movieCode = document.querySelector("#modal-movie-code").value;
	$.ajax({
		url : "<%= request.getContextPath() %>/movie/findMovieComments",
		data : {movieCode},
		dataType : "json",
		method : "get",
		success(movieComments) {
				const body = document.querySelector("#movie-comment-body");
				body.innerHTML = "";
			// 가저온 comments 반복문			
			[...movieComments].forEach((comment) => {
				const {writerId, movieContent, regDate, starGrade} = comment;
				const commentHTML = `
					<tr>
						<td>\${writerId}</td>
						<td>\${movieContent}</td>
						<td>\${regDate}</td>
						<td>\${starGrade}</td>
					</tr>
				`;
				body.innerHTML += commentHTML;
			}); // forEach
		} // success
	}); // ajax
}; // printMovieComments()

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>