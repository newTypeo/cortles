package com.cortles.project.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.board.model.service.BoardService;
import com.cortles.project.board.model.vo.Attachment;
import com.cortles.project.board.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();

	/**
	 * boardDetail.jsp 에서 게시글 수정버튼을 누를시 doGet 실행
	 * @author 창환
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no")); // boardDetail에서 get방식으로 전달했던 게시글 번호를 가져옴.
		// 2. 업무로직
		Board board = boardService.findById(no); // board 테이블에서 조회해서 board객체에 담음.
		Attachment attachment = boardService.findAttachmentByBoardNo(no); // attachment 객체에 attachment 테이블에서 가져온 정보를 담음
		
		request.setAttribute("board", board);
		request.getSession().setAttribute("attachment", attachment);
		
		// 3. 응답처리
		request.getRequestDispatcher("/WEB-INF/views/board/boardUpdate.jsp") // boardUpdate.jsp로 이동
			.forward(request, response);
	}

	/**
	 * boardDetail.jsp에서 수정버튼을 클릭시 doPost 실행
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext application = getServletContext();
		String saveDirectory = application.getRealPath("/upload/board");
		
		int maxPostSize = 1024 * 1024 * 10;
		String encoding = "utf-8";
		
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		// 1. 사용자 입력값 처리
		int no = Integer.parseInt(multiReq.getParameter("no"));
		String title = multiReq.getParameter("title");
		String content = multiReq.getParameter("content");
		String writer = multiReq.getParameter("writer");
		// db attachment 행삭제, 저장된 파일삭제
		String[] delFiles = multiReq.getParameterValues("delFile");
		
		// update board set ? = ? where no = ?
		Board board = new Board();
		board.setBoardNo(no);
		board.setTitle(title);
		board.setWriterId(writer);
		board.setContent(content);
		
		// Attachment객체 생성 (Board 추가)
		Enumeration<String> filenames = multiReq.getFileNames();
		while(filenames.hasMoreElements()) {
			String name = filenames.nextElement(); // input:file[name]
			File upFile = multiReq.getFile(name);
			if(upFile != null) {
				Attachment attach = new Attachment();
				attach.setOriginalFilename(multiReq.getOriginalFileName(name));
				attach.setRenamedFilename(multiReq.getFilesystemName(name)); // renamedFilename
				attach.setBoardNo(no); // fk컬럼 boardNo 바로 설정 가능
				board.addAttachment(attach);
				
				// redirect 하기 때문에 session에다가 attachment를 담음.
				request.getSession().setAttribute("attachment", attach);
			}
		}
		
		// 2. 업무로직
		int result = boardService.updateBoard(board); // db에 있는 board 테이블의 정보를 수정해주기 위함
		
		// 첨부파일 삭제
		if(delFiles != null && !delFiles[0].equals("0")) { // boardDetail.jsp에서 삭제 버튼을 클릭했을 시
			for(String _attachNo : delFiles) {
				int attachNo = Integer.parseInt(_attachNo);
				// a. 파일삭제
				Attachment attach = boardService.findAttachmentById(attachNo); // db에서 attachment 테이블을 조회하고
				// java.io.File : 실제파일을 가리키는 자바객체
				File delFile = new File(saveDirectory, attach.getRenamedFilename()); // 파일 경로와 사진 이름이 있는 File 객체를 생성후
				if(delFile.exists()) // delFile이 존재 할 경우에만
					delFile.delete();	// delFile 삭제
				
				// b. db attachment 행 삭제
				result = boardService.deleteAttachment(attachNo); // db의 attachment 테이블에서 기존 정보 삭제.
			}
		}
		
		// 3. 응답처리 (목록페이지로 redirect) - POST방식 DML처리후 url변경을 위해 redirect처리
		response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + board.getBoardNo());
		
	}

}
