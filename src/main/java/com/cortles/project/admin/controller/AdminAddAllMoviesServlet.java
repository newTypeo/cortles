package com.cortles.project.admin.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.admin.model.service.AdminService;
import com.cortles.project.movie.model.vo.Movie;

@WebServlet("/setMovie/addAllMovies")
public class AdminAddAllMoviesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AdminService adminService = new AdminService();
	
	/**
	 * ajax에서 넘어온 영화정보들 DB에 담기 - 종환
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ajax를 통해 넘어온 배열 데이터 선언
		// 배열로 왔기 때문에 getParameterValues()로 받는다. (traditional: true)
		String[] infoOfMovies = req.getParameterValues("infoOfMovies"); 
		
		// 영화 담을 리스트
		List<Movie> movies = new ArrayList<>();
		
		// 영화하나씩 파싱해서 리스트에 영화객체 모두 담기
		for (int i = 0; i < infoOfMovies.length; i++) {
			// 담아온 순서 = 영화코드, 제목, 영어제목, 장르, 줄거리, 개봉일, 상영시간, 포스터, 감독, 배우들, 상세 페이지
			String[] movieInfo = infoOfMovies[i].split("#"); // #기준으로 붙여놓은 영화 정보 파싱
			String movieCode = movieInfo[0]; 				 // 영화코드
			String title = movieInfo[1];	 				 // 제목
			String titleEng = movieInfo[2];	 				 // 영어제목
			String genre = movieInfo[3];	 				 // 장르
			String story = movieInfo[4];	 				 // 줄거리
			Date openDate = transformDate(movieInfo[5]); 	 // 개봉일
			String runtime = movieInfo[6];	 				 // 상영시간
			String[] _posterUrl = movieInfo[7].split("jpg"); // 포스터 (여러 포스터URL이 '|'로 이어져있는데 파싱이 자꾸 안되서 "jpg"로 끊고 다시 붙여주었다.)
			String posterUrl = _posterUrl[0] + "jpg";	 	 // 포스터는 1개만 사용
			String director = movieInfo[8]; 				 // 감독
			String actors = movieInfo[9]; 					 // 배우
			String vod = movieInfo[10]; 					 // 상세정보 페이지
			
			Movie movie = new Movie(movieCode, title, titleEng, 0, genre, story, openDate, runtime, posterUrl, director, actors, vod);
			movies.add(movie);							   // 평점 = 0
		}
		
		int result = adminService.addAllMovies(movies); // 영화삽입 DML 결과
		
		System.out.println("삽입된 영화 수 = " + result);
		resp.sendRedirect(req.getContextPath() + "/");
	}
	
	// String to Date 변환 메소드
	public Date transformDate(String date) {
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
        
        // Date로 변경하기 위해서는 날짜 형식을 yyyy-mm-dd로 변경해야 한다.
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
        
        java.util.Date tempDate = null;   
        
        try {
            // 현재 yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만든다.
            tempDate = beforeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
        String transDate = afterFormat.format(tempDate);
        
        // 반환된 String 값을 Date로 변경한다.
        Date d = Date.valueOf(transDate);
        return d;
        
    }
	
	
}
