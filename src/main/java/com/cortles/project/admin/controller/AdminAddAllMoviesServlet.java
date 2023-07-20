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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ajax를 통해 넘어온 배열 데이터 선언
		String[] infoOfMovies = req.getParameterValues("infoOfMovies");

		// 영화 담을 리스트
		List<Movie> movies = new ArrayList<>();
		
		// 영화하나씩 파싱해서 리스트에 영화객체 add
		for (int i = 0; i < infoOfMovies.length; i++) {
			// 영화코드, 제목, 영어제목, 장르, 줄거리, 개봉일, 상영시간, 포스터, 감독, 배우들, 티저
			String[] movieInfo = infoOfMovies[i].split("#");
			String movieCode = movieInfo[0]; // 영화코드 (api)
			String title = movieInfo[1];	// 제목 (api)
			String titleEng = movieInfo[2];	// 영어제목 (api)
			String genre = movieInfo[3];	// 장르
			String story = movieInfo[4];	// 줄거리
			Date openDate = transformDate(movieInfo[5]); 
			String runtime = movieInfo[6];	// 상영시간 (api)
			String[] _posterUrl = movieInfo[7].split("jpg");// 포스터
			String posterUrl = _posterUrl[0] + "jpg";// 포스터
			String director = movieInfo[8];// 감독 (api)
			String actors = movieInfo[9]; // 배우 (api)
			String vod = movieInfo[10]; // vod (api)
			
			Movie movie = new Movie(movieCode, title, titleEng, 0, genre, story, openDate, runtime, posterUrl, director, actors, vod);
			movies.add(movie);
		}
		
		int result = adminService.addAllMovies(movies);
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
