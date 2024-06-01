package hackerthon.demo.repository;

import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {
    @Query("SELECT g FROM GameRoom g WHERE :category IS NULL OR g.category = :category")
    List<GameRoom> findByCategoryOrAll(@Param("category") Category category);
}
