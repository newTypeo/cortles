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
		<article id="zzim"  style="display: inline-block; width: 100vw;"></article>
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
      
      <!--  -->
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
		<div class="avgMovieGrade"></div>
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
	  			<tr id="review-thead-tr">
	  			
	  			</tr>      
	        </thead>
	        <tbody id="movie-comment-body"><!-- 한줄평 출력할 영역 --></tbody>
        </table>
    </div>	
  </div>
</div>
</section>

<script>
	//로그인 정보가 있고 찜목록이 null이 아닌 경우
<%  if (loginMember != null && favorites != null) { 
	
	/*  favorites안에 있는 것을 없을떄 까지 favorite안에 넣어주기 */
	 	for(Favorite favorite : favorites){
	 %>
	    // ajax시작 
	        $.ajax({
	        	// 요청할 url
	            url: "<%=request.getContextPath()%>/movie/json/findOneMovies",
	            // 서버에 전달할 데이터를 가져와서 전달해주기
	            
	            data: { movie_code: "<%= favorite.getMovieCode() %>" },
	            
	            // 서버로 받을 데이터 타입 지정
	            
	            dataType: "json",
	            
	            // 요청이 성공적으로 완료되었을 때 실행되는 콜백 함수      
	            
	            success(movie) {
	            	 // 서버로부터 전달된 JSON 데이터를 객체로 분해하여 변수에 저장
	            	 
	                const { posterUrl, genre, movieCode } = movie;
	                
	                // 이미지 태그를 동적으로 생성하여 #zzim 요소에 추가
	                
	                const imgHTML = `<img name=\${movieCode} src=\${posterUrl}>`;
	                
	                document.querySelector("#zzim").innerHTML += imgHTML;
	                
	            },
	     		// 요청이 성공적으로 완료되었을 때 실행되는 콜백 함수
	            complete() {
	                // 이미지 태그들에 대해 클릭 이벤트 리스너를 추가
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
// 모달 열면 body의 scroll를 없애기 위해 가져오기
const scroll = document.querySelector("body");
function openModal(movie_code) {
	
 	$.ajax({
 		
 		// 위의 URL에 해당하는 서버로 비동기 요청을 보냄
 		url : "<%= request.getContextPath() %>/movie/json/findOneMovies",
 		
 		// 서버에 전달할 데이터 (movie_code 변수의 값을 전달)
 		data : {movie_code},
 		 																
 		// 요청이 성공적으로 완료되었을 때 실행되는 콜백 함수
 		success(clickedMovie) {
 			
 			//  서버로부터 전달된 JSON 데이터를 객체로 분해하여 변수에 저장
 			const {actors, director, genre, openDate, runtime, story, title, titleEng, vod, movieCode} = clickedMovie;
 			
 	        // 서버에서 받은 vod 값을 .trailer 요소의 src 속성에 할당하여 동영상 재생
 			document.querySelector(".trailer").src = vod;
 	        
 			// 스크롤을 제한하여 모달창이 열릴 때 스크롤을 막음
 			scroll.style.overflow = "hidden";
 			
 			// 모달창 scroll띄우기
 		  	document.getElementById("myModal").style.display = "block";
 		},
 		complete (){
	  		document.querySelector("#ggimButton").addEventListener("click", (e) => {
		  		const frm = document.myListFrm;
		  		
		  		// 폼안에 value값에 movieCode넣어주기
		  		frm.movieCode.value = movie_code;
		  		
		  		// 다 실행되면 제출
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
			// 데이터 형식 json으로 지정
			dataType : "json",
			// post방식으로 보내기
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
		success(mapData) {
            const {movieComments, avgMovieGrade} = mapData;
            document.querySelector(".avgMovieGrade").innerHTML = ""; // 평점 칸 비우기
            // 한줄평이 하나라도 있을 때만 평점 출력
            if(avgMovieGrade != 0){
            	document.querySelector("#review-thead-tr").innerHTML =  `<th>writer</th>
														                 <th>content</th>
														                 <th>date</th>
														                 <th>starGrade</th>`;
													            	
               document.querySelector(".avgMovieGrade").innerHTML = "★" + avgMovieGrade;
            } else {
            	document.querySelector("#review-thead-tr").innerHTML = ""; // 한줄평이 하나도 없으면 타이틀 제거
            }
            const body = document.querySelector("#movie-comment-body");
            body.innerHTML = "";
            
            // 영화 평점 입력하기 
            
	         // 가져온 comments 반복문         
	         [...movieComments].forEach((comment) => {
	            const {writerId, movieContent, regDate, starGrade} = comment;
	            const stars = starGrade == 1 ? "★" : starGrade == 2 ? "★★" : starGrade == 3 ? "★★★"
	            			 : starGrade == 4 ? "★★★★" : "★★★★★";
	            const commentHTML = `
	               <tr>
	                  <td>\${writerId}</td>
	                  <td>\${movieContent}</td>
	                  <td>\${regDate}</td>
	                  <td>\${stars}</td>
	               </tr>
	            `;
	            body.innerHTML += commentHTML;
	         }); // forEach
      } // success
	}); // ajax
}; // printMovieComments()

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>