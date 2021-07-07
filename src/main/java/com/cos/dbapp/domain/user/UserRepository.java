package com.cos.dbapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// 일반적으로는 어노테이션 등록되어있지 않지만, JpaRepository에는 떠있다. 
// JpaRepository 부모클래스에 어노테이션이 부착이 되어서 나온다.
// Data를 가져와서 자바 오브젝트로 만드는 것 !
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value ="SELECT * FROM user WHERE username = :username",nativeQuery = true)
	User mFindByUsername(String username);

	@Query(value ="SELECT id, username, address, email, null password FROM user WHERE username = :username AND password = :password",nativeQuery = true)
	User mLogin(String username, String password);

}
