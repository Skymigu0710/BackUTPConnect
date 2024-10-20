package com.utpconnectplatform.follows_service.repository;

import com.utpconnectplatform.follows_service.model.Follows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository <Follows, Long> {
}
