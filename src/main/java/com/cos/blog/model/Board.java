package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	 // 시퀀스 쓴다는뜻
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터 쓸때
	private String content; // 섬머노트라는 라이브러리 를 쓸것 , 쓸대 Html 태그가 섞여서 디자인이 됨 글자의 용량이 굉장히 커짐
	
	private int count; // 조회수
	
	// EAGER 전략은 "무조건" 들고 오라는뜻임 . many to one 하면 기본으로 EAGER 가 됨
	@ManyToOne(fetch = FetchType.EAGER) // many = Board , one = User
	@JoinColumn(name="userId") //필드 값이될 , 참조하게될 FK키가 userId로 만들어지고 연관관계는 매니투원 으로 만들어짐 유저와 같이
	private User user;	// 유저 객체로 만들었으니 User를 참조함 fk키가 됐다는 뜻
	// user 는 데이터베이스에 들어갈때 fk 키 로 들어가고 자연스레 integer가 됨
	
	//원자성 법칙에 어긋나므로 db에 조인컬럼을 하지는 않음.
	// db에 쓰일때 같은 사람이 여러번 댓글 쓰면 reply id를 받을때 테이블이 성립이 안되므로.
	// mappedby 의 뜻은 나는 연관관계의 주인이 아니에요 라는뜻 
	// 그러니까 db에 컬럼을 만들지 말라는뜻, FK 는 reply 테이블의 board 변수 라는것
	// mappedby = " board " < 이 board는 필드 이름임. 테이블이 아니라.
	// 원투매니는 적지 않아도 기본으로 fetchtype 이 lazy로 됨.
	// 얼마나 많은지 모르기 때문에
	// 필요할때만 가져온다는 뜻
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 하나의 게시글에는 여러개의 댓글이 들어올수 있음, 그러므로 리스트로 받아야됨
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
