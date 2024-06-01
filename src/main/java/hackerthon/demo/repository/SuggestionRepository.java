package hackerthon.demo.repository;

import hackerthon.demo.domain.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

    @Query("select s from Suggestion s where s.gameRoom.id = :gameRoomId")
    List<Suggestion> findByGameRoomId(Long gameRoomId);
}