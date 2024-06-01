package hackerthon.demo.controller;


import hackerthon.demo.common.dto.Response;
import hackerthon.demo.controller.request.GameRoomCreateRequest;
import hackerthon.demo.domain.enums.Category;
import hackerthon.demo.controller.response.GameRoomResponseDto;
import hackerthon.demo.service.GameRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "게임 방 controller", description = "Game Room API")
public class GameRoomController {

    private final GameRoomService gameRoomService;

    @Operation(
            summary = "전체 GameRoom 조회",
            responses = @ApiResponse(responseCode = "200", description = "게시글 전체를 반환합니다.")
    )
    @GetMapping
    public hackerthon.demo.apiPayload.ApiResponse<List<GameRoomResponseDto>> getGameRoomsByCategory(@RequestParam(required = false) Category category) {
        List<GameRoomResponseDto> gameRooms = gameRoomService.getGameRooms(category)
                .stream()
                .map(GameRoomResponseDto::fromEntity)
                .collect(Collectors.toList());
        return hackerthon.demo.apiPayload.ApiResponse.onSuccess(gameRooms);
    }

    @Operation(
            summary = "GameRoom 생성"
    )
    @PostMapping("/create")
    public hackerthon.demo.apiPayload.ApiResponse<GameRoomResponseDto> createGameRoom(@RequestBody GameRoomCreateRequest request, HttpServletRequest httpServletRequest) {

        GameRoomResponseDto gameRoomResponseDto = gameRoomService.createGameRoom(request, httpServletRequest);

        return hackerthon.demo.apiPayload.ApiResponse.onSuccess(gameRoomResponseDto);
    }


}
