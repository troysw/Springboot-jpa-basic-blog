package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// jsp 로 치면 DAO
// 자동으로 bean 등록이 되서 @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// JPA naming Query 임 -> 실제 동작되는 sql 쿼리는 "SELECT * FROM USER WHERE USERNAME = ?(1번파라미터)"
	Optional<User> findByUsername(String username);
	
		
}

//JPA naming 전략
// Select * from user Where username = ? AND password = ?; 라는게 자동 동작함
//저 ? 인 제네릭스엔 자동으로 String username 과 password가 들어감
//User findByUsernameAndPassword(String username, String password);

//	@Query(value="Select * from user Where username = ? AND password = ?", nativeQuery = true)
//	User login(String username, String password)