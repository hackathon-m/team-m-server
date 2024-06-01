package hackerthon.demo.controller.response;

import hackerthon.demo.domain.Gifticon;
import hackerthon.demo.domain.enums.GameResultStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GameLogResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GameLogResultDTO {
        private Long id;
        private GameResultStatus resultStatus;
        private Gifticon myGifticon;
        private Gifticon yourGifticon;
    }
}
