package hackerthon.demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcceptSuggestionResponseDto {

        private Long hostId;
        private Long suggesterId;
        private Long gameRoomId;

    public static AcceptSuggestionResponseDto resultDto(Long hostId, Long suggesterId, Long gameRoomId) {
        return AcceptSuggestionResponseDto.builder()
                .hostId(hostId)
                .suggesterId(suggesterId)
                .gameRoomId(gameRoomId)
                .build();
    }
}
