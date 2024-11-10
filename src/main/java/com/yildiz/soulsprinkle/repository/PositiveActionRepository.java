package com.yildiz.soulsprinkle.repository;

import com.yildiz.soulsprinkle.model.PositiveAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PositiveActionRepository extends JpaRepository <PositiveAction,Long>{

    Optional<PositiveAction> findByTaskId(Long taskId);
}
