package hackerthon.demo.service;


import hackerthon.demo.controller.request.GameRoomCreateRequest;
import hackerthon.demo.controller.response.GameRoomResponseDto;
import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.enums.Category;
import hackerthon.demo.repository.GameRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameRoomService {

    private final GameRoomRepository gameRoomRepository;

    public List<GameRoom> getGameRooms(Category category) {
        return gameRoomRepository.findByCategoryOrAll(category);
    }

    @Transactional
    public GameRoomResponseDto createGameRoom(GameRoomCreateRequest request) {
        GameRoom gameRoom = GameRoomCreateRequest.toEntity(request);
        GameRoom savedRoom = gameRoomRepository.save(gameRoom);
        return GameRoomResponseDto.fromEntity(savedRoom);
    }
}
