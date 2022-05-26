package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	 // 시퀀스 쓴다는뜻
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne // 여러개의 댓글은 하나의 게시글에 존재할 수 있다
	//앞쪽인 매니가 현재의 클래스임
	//원투원이면 하나의 게시글에 하나의 답글
	// 원투매니면 하나의 댓글이 여러개의 게시글에 들어가니까 말이 안됨
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne //여러개의 댓글을 하나의 유저가 쓸 수 있으므로 매니투원
	@JoinColumn(name="userId")
	private User user;
	

	@CreationTimestamp
	private Timestamp createDate;
	
}
