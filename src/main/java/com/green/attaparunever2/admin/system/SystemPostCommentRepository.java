package com.green.attaparunever2.admin.system;

import com.green.attaparunever2.entity.SystemPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemPostCommentRepository extends JpaRepository<SystemPostComment, Long> {
}
