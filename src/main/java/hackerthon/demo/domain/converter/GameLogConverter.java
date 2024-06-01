package hackerthon.demo.domain.converter;

import hackerthon.demo.controller.response.GameLogResponse;
import hackerthon.demo.controller.response.GifticonResponse;
import hackerthon.demo.domain.GameLog;
import hackerthon.demo.domain.Gifticon;

public class GameLogConverter {

    public static GameLogResponse.GameLogResultDTO toGameLogResultDTO(GameLog gameLog) {
        return GameLogResponse.GameLogResultDTO.builder()
                .id(gameLog.getId())
                .resultStatus(gameLog.getResultStatus())
//                .myGifticon(gameLog.getGameRoom().get)
//                .yourGiticon(gameLog.getGameRoom().get)
                .build();
    }
}
