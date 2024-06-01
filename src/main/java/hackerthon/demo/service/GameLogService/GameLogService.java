package hackerthon.demo.service.GameLogService;

import hackerthon.demo.controller.response.GameLogResponse;

import java.util.List;

public interface GameLogService {

    List<GameLogResponse.GameLogResultDTO> findGameLogList(String serialId);

}
