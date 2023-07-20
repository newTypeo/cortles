package com.cortles.project.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cortles.project.board.model.service.BoardService;
import com.cortles.project.board.model.vo.Attachment;
import com.cortles.project.board.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

@WebServlet("/board/boardCreate")
public class BoardCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final BoardService boardService = new BoardService();

	/**
	 * board.jsp에서 writing 버튼 클릭시 boardCreate.jsp로 이동.
	 * @author 창환
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardCreate.jsp")
			.forward(request, response);
	}
	
	/**
	 * boardCreate.jsp에서 writing 버튼 클릭시
	 * 사용자가 파일 업로드 할 수도 있으므로 MultipartRequest로 사용자 입력값 받아옴
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 업로드파일 저장경로 C:\\Workspaces\\web_server_workspace\\cortles\\src\\main\\webapp\\upload\\board
			ServletContext application = getServletContext();
			String saveDirectory = application.getRealPath("/upload/board");
			
			// 파일하나당 최대크기 10MB
			int maxPostSize = 1024 * 1024 * 10;
			// 인코딩
			String encoding = "utf-8";
			
			FileRenamePolicy policy = new DefaultFileRenamePolicy();
			
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			// 1. 사용자 입력값 처리
			// 사용자 입력값을 받아와서 board 객체 생성 후 삽입 
			String title = multiReq.getParameter("title"); // 사용자가 입력한 제목
			String writer = multiReq.getParameter("writer"); // 사용자 아이디
			String content = multiReq.getParameter("content"); // 사용자가 입력한 내용
			Board board = new Board();
			
			board.setTitle(title);
			board.setWriterId(writer);
			board.setContent(content);
			
			Enumeration<String> filenames = multiReq.getFileNames(); // upFile1, upFile2
			while(filenames.hasMoreElements()) {
				String name = filenames.nextElement(); // input:file[name]
				File upFile = multiReq.getFile(name);
				if(upFile != null) {
					Attachment attach = new Attachment();
					attach.setOriginalFilename(multiReq.getOriginalFileName(name));	// setOriginalFilename()의 인자는 사용자가 업로드한 파일 경로
					attach.setRenamedFilename(multiReq.getFilesystemName(name)); // renamedFilename
					board.addAttachment(attach);
				}
			}
			
			// 2. 업무로직 (db에 board객체를 전달)
			int result = boardService.insertBoard(board);
			
			// 게시글 등록 완료 알림을 위한 session 저장
			HttpSession session = request.getSession();
			session.setAttribute("msg", "게시글 등록 완료!");
			
			// 3. 응답처리 (목록페이지로 redirect) - POST방식 DML처리후 url변경을 위해 redirect처리
			// 작성한 detail 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + board.getBoardNo());
	}

}
