package hackerthon.demo.controller.request;

import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.Gifticon;
import hackerthon.demo.domain.Member;
import hackerthon.demo.domain.Suggestion;
import hackerthon.demo.domain.enums.RoomStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SuggestionCreateRequest {

    private final Long gameRoomId;
    private final Long gifticonId;

    @Builder
    public SuggestionCreateRequest(@NotNull Long gmaeRoomId, @NotBlank Long gifticonId) {

        this.gameRoomId = gmaeRoomId;
        this.gifticonId = gifticonId;
    }

    // 요청 객체를 엔티티로 변환하는 static 메서드
    public static Suggestion toEntity(Member Suggester, GameRoom gameRoom, Gifticon gifticon) {
        return Suggestion.builder()
                .suggester(Suggester)
                .gameRoom(gameRoom)
                .gifticon(gifticon)
                .build();
    }
}
