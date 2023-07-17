<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.7.0.js"></script>
</head>
<body>
<%-- <filedset>
<form 
id="addAllMoviesFrm" 
name="addAllMoviesFrm" 
action="<%= request.getContextPath() %>/admin/addAllMovies"
>
	
</form>
	<button type="submit">보내기</button>
</filedset> --%>

<Script>
	
	$.ajax({
		url : "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2",
		data : {
			ServiceKey : "8N7WULS3PZ7ER312R70R",
			listCount : 100,
			releaseDts : "20200201",
			genre : "SF"
		},
		dataType : 'json',
		success(responseData){
			const infoOfMovies = [];
			// console.log("responseData", responseData);
			const {Data} = responseData;
			// console.log("Data", Data);
			const {Result} = Data[0];
			console.log("Result", Result);
			
			Result.forEach((movie) => {
				// const {plots.plot[0].plotText} = movie;	
				const vod = movie.vods.vod[0].vodUrl; // 티저
				const story = movie.plots.plot[0].plotText; // 줄거리
				const director = movie.directors.director[0].directorNm; // 감독
				const DOCID = movie.DOCID;
				const title = movie.title;
				const titleEng = movie.titleEng;
				const genre = movie.genre;
				const repRlsDate = movie.repRlsDate;
				const runtime = movie.runtime;
				const posters = movie.posters;
								
				if(
					vod !== "" && story !== "" && director !== "" && title !== "" && titleEng !== "" && genre !== "" 
					&& repRlsDate !== "" && runtime !== "" && posters !== ""
					&& !story.includes('옥토넛') && !genre.includes('에로')
				) {
					
					let actors = ''; // 배우들
					for(let i = 0; i < movie.actors.actor.length; i++) {
						if(i == movie.actors.actor.length-1)
							actors += movie.actors.actor[i].actorNm;
						else
							actors += movie.actors.actor[i].actorNm + ",";
						
						if(i == 5) break;
					}
					
					// console.log("DOCID, title, titleEng, genre, repRlsDate, runtime, posters, vodUrl", DOCID, title, titleEng, genre, repRlsDate, runtime, posters, vodUrl);
					
					// 	               영화코드   제목     영화제목      장르    줄거리       개봉일       상영시간     포스터       감독	      배우     티저
					infoOfMovies.push(DOCID + "#" +  title + "#" + titleEng + "#" + genre + "#" + story + "#" + repRlsDate + 
												"#" + runtime + "#" + posters + "#" + director + "#" + actors + "#" + vod);
				}
			});
			console.log("infoOfMovies", infoOfMovies);
			
			$.ajax({
				url : "<%= request.getContextPath() %>/admin/addAllMovies",
				data : {"infoOfMovies" : infoOfMovies},
				traditional: true,
				dataType : "json",
				method : "post",
				success(responseData){
					console.log("투작스 석세스", responseData);		
					
				}
				
			});
			
		}
	});
	

	
</script>
</body>
</html>