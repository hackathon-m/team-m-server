package hackerthon.demo.service;


import hackerthon.demo.controller.request.GameRoomCreateRequest;
import hackerthon.demo.controller.response.GameRoomResponseDto;
import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.Member;
import hackerthon.demo.domain.enums.Category;
import hackerthon.demo.repository.GameRoomRepository;
import hackerthon.demo.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameRoomService {

    private final GameRoomRepository gameRoomRepository;
    private final MemberRepository memberRepository;

    public List<GameRoom> getGameRooms(Category category) {
        return gameRoomRepository.findByCategoryOrAll(category);
    }

    @Transactional
    public GameRoomResponseDto createGameRoom(GameRoomCreateRequest request, HttpServletRequest httpServletRequest) {
        String serialId = httpServletRequest.getHeader("Authorization");
        Member host = memberRepository.findBySerialId(serialId).orElseThrow(() -> new EntityNotFoundException("해당 방장 존재하지 않음"));

        GameRoom gameRoom = GameRoomCreateRequest.toEntity(request, host);
        GameRoom savedRoom = gameRoomRepository.save(gameRoom);
        return GameRoomResponseDto.fromEntity(savedRoom);
    }
}
