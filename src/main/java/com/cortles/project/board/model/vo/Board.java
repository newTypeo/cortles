package com.cortles.project.board.model.vo;

import java.util.ArrayList;
import java.util.List;


public class Board extends BoardEntity {
	private int attachCnt; // 첨부파일 갯수
	private List<Attachment> attachments = new ArrayList<>();
	private int commentCnt; // 댓글 갯수
	
	public int getAttachCnt() {
		return attachCnt;
	}

	public void setAttachCnt(int attachCnt) {
		this.attachCnt = attachCnt;
	}
	
	public List<Attachment> getAttachments() {
		return attachments;
	}
	
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void addAttachment(Attachment attach) {
		if(attach != null)
			this.attachments.add(attach);
	}
	
	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	@Override
	public String toString() {
		return "Board [attachCnt=" + attachCnt + ", attachments=" + attachments + ", commentCnt=" + commentCnt
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
