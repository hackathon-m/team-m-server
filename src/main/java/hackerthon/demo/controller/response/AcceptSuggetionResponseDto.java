package hackerthon.demo.controller.response;

import hackerthon.demo.domain.Gifticon;
import hackerthon.demo.domain.enums.GameResultStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcceptSuggetionResponseDto {

        private Long hostId;
        private Long suggesterId;
        private Long gameRoomId;

    public static AcceptSuggetionResponseDto resultDto(Long hostId, Long suggesterId, Long gameRoomId) {
        return AcceptSuggetionResponseDto.builder()
                .hostId(hostId)
                .suggesterId(suggesterId)
                .gameRoomId(gameRoomId)
                .build();
    }
}
