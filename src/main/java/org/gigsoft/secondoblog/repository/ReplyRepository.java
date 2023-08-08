package org.gigsoft.secondoblog.repository;

import org.gigsoft.secondoblog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	@Modifying
	@Query(value="insert into reply(userId, boardId, content, createdDate) values(?, ?, ?, now())", nativeQuery=true)
	Long save(Long userId, Long boardId, String content);
}
