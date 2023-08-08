package org.gigsoft.secondoblog.repository;

import java.util.Optional;

import org.gigsoft.secondoblog.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
	public Optional<Member> findByUsername(String username);
	
	@Query(value="SELECT * FROM user WHERE username=? AND password=?", nativeQuery=true)
	Member login(String username, String password);

	public Optional<Member> findByEmail(String email);
}

//DAO(Data Access Object) == Entity, 
//DTO(Data Transfer Object): 로직 없이 getter/setter만 가짐, 
//VO(Value Object): DTO와 동일한 개념이지만 Read Only