package hackerthon.demo.controller.response;

import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.Gifticon;
import hackerthon.demo.domain.Member;
import hackerthon.demo.domain.Suggestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class SuggestionResponseDto {

    private Long id;
    private Long gameRoomId;
    private Long suggesterId;

    public static SuggestionResponseDto fromEntity(Suggestion suggestion) {
        return SuggestionResponseDto.builder()
                .id(suggestion.getId())
                .gameRoomId(suggestion.getGameRoom().getId())
                .suggesterId(suggestion.getSuggester().getId())
                .build();
    }
}
