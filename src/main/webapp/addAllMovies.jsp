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

<script>
   
   $.ajax({
      url : "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2", // 영화정보 API
      data : {

         ServiceKey : "8N7WULS3PZ7ER312R70R", // 인증키
         listCount : 10, // 영화 요청 갯수
         releaseDts : "20201201", // 개봉일 기준
         genre : "로맨스"
      },
      dataType : 'json',
      success(responseData){
         const infoOfMovies = [];
         const {Data} = responseData;
         const {Result} = Data[0];
         console.log("Result", Result);
         
         Result.forEach((movie) => { 
        	// api에서 넘어온 영화 객체배열 반복문속에서 값 가져오기
            const vod = movie.vods.vod[0].vodUrl; // 상세페이지
            const story = movie.plots.plot[0].plotText; // 줄거리
            const director = movie.directors.director[0].directorNm; // 감독
            const DOCID = movie.DOCID;
            const title = movie.title; // 제목
            const titleEng = movie.titleEng; // 영어제목
            const genre = movie.genre; // 장르
            const repRlsDate = movie.repRlsDate; // 개봉일
            const runtime = movie.runtime; // 런타임
            const posters = movie.posters; // 포스터url
                        
            if( // 영화가 무조건 모든정보를 갖고있지 않아서 오류방지를 위한 조건 적용 
               vod !== "" && story !== "" && director !== "" && title !== "" && titleEng !== "" && genre !== "" 
               && repRlsDate !== "" && runtime !== "" && posters !== ""
               && !story.includes('옥토넛') && !genre.includes('에로') // 이유없는 오류나는 옥토넛...
            ) {
               
               let actors = ''; // 배우는 5명만 담기
               for(let i = 0; i < movie.actors.actor.length; i++) {
                  if(i == movie.actors.actor.length-1)
                     actors += movie.actors.actor[i].actorNm;
                  else
                     actors += movie.actors.actor[i].actorNm + ",";
                  
                  if(i == 5) break;
               }
               
               
               // 영화객체에서 추출한 정보 '#'기준으로 붙여서 infoOfMovies배열에 String형식으로 추가
               // 				[{"영화코드#제목#영화제목#장르#줄거리#개봉일#상영시간#포스터#감독#배우#티저"}]
               infoOfMovies.push(DOCID + "#" +  title + "#" + titleEng + "#" + genre + "#" + story + "#" + repRlsDate + 
                                    "#" + runtime + "#" + posters + "#" + director + "#" + actors + "#" + vod);
            }
         });
         console.log("infoOfMovies", infoOfMovies); // String[]에 모든영화 담고 로그 확인
         
         $.ajax({ // 영화정보배열 서블릿으로 보내기
            url : "<%= request.getContextPath() %>/setMovie/addAllMovies",
            data : {"infoOfMovies" : infoOfMovies},
            traditional: true, // 배열을 넘길 수 있는 속성
            dataType : "json",
            method : "post",
            success(responseData){
               console.log("투작스 성공~!", responseData);      
            }
         });
      }
   });
   
</script>
</body>
</html>