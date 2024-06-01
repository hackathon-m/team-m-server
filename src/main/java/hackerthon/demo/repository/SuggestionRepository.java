package hackerthon.demo.repository;

import hackerthon.demo.domain.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

}
