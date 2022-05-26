package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data // 게터 세터 다 만들어주는거
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // insert 시에 null 인 필드를 제외 시켜준다.
@Entity	// User 클래스가 Mysql에 테이블이 생성되게 만들어주는 어노테이션
public class User {
	
	@Id	//Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 db의 넘버링 전략을 따라감.
	private int id; // 시퀀스 , auto-increment
	
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; //아이디
	
	@Column(nullable = false, length = 100) // 많이주는 이유는 해쉬로 변경해서 암호화 할 것이기 때문
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("user")
	// db에는 roletype이라는게 없다 그래서 어노테이션을 붙여 줘야함
	@Enumerated(EnumType.STRING)
	private RoleType role; // 정확하게 하려면 Enum을 쓰는게 좋다. // admin , user, manager 
	
	@CreationTimestamp // 시간이 자동으로 입력이 됨
	private Timestamp createDate;

}
