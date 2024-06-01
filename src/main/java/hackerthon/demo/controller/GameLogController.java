package hackerthon.demo.controller;

import hackerthon.demo.apiPayload.ApiResponse;
import hackerthon.demo.controller.response.GameLogResponse;
import hackerthon.demo.controller.response.GifticonResponse;
import hackerthon.demo.service.GameLogService.GameLogService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gameLogs")
@RequiredArgsConstructor
public class GameLogController {

    private final GameLogService gameLogService;
    @GetMapping("")
    @Operation(summary = "내기 내역 조회")
    public ApiResponse<List<GameLogResponse.GameLogResultDTO>> getGameLogs(HttpServletRequest request){
        String serialId = request.getHeader("Authorization");
        try{
            List<GameLogResponse.GameLogResultDTO> gameLogResultDTOs = gameLogService.findGameLogList(serialId);

            return ApiResponse.onSuccess(gameLogResultDTOs);
        }catch(Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }


    @PostMapping("")
    @Operation(summary = "게임 결과 반영")
    public ApiResponse<String> updateGameResult(@RequestParam("winnerId")Long winnerId,
                                                @RequestParam("gameRoomId")Long gameRoomId){
        try{
            return ApiResponse.onSuccess(gameLogService.updateGameResult(winnerId, gameRoomId));
        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

}
