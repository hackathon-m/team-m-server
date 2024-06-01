package hackerthon.demo.repository;

import hackerthon.demo.domain.GameLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameLogRepository extends JpaRepository<GameLog, Long> {

    List<GameLog> findByMemberId(Long memberId);
}