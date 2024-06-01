package hackerthon.demo.repository;

import hackerthon.demo.domain.Gifticon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GifticonRepository extends JpaRepository<Gifticon, Long> {

    List<Gifticon> findByMemberId(Long memberId);
}
