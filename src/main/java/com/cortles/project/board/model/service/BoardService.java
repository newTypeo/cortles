package com.cortles.project.board.model.service;

import java.util.List;

import com.cortles.project.board.model.dao.BoardDao;
import com.cortles.project.board.model.vo.BoardEntity;

public class BoardService {
	private final BoardDao boardDao = new BoardDao();

	public List<BoardEntity> findAll(int start, int end) {
		
		return null;
	}

}
