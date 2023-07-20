package com.cortles.project.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.board.model.service.BoardService;
// 글 삭제 - 주혜, 창환 
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		String filename = request.getParameter("filename");
		
		int result = boardService.boardDelete(no);
		// 		/upload/board 하위의 사진 삭제 - 창환 
		ServletContext application = getServletContext();
		String saveDirectory = application.getRealPath("/upload/board");
		File file = new File(saveDirectory, filename);
		
		if(file != null)
			file.delete();
		
		request.getSession().setAttribute("msg", "게시글을 성공적으로 삭제했습니다.");
		response.sendRedirect(request.getContextPath()+"/board/boardList");
	}

}
