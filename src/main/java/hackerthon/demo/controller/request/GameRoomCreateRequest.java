package hackerthon.demo.controller.request;

import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.Member;
import hackerthon.demo.domain.enums.Category;
import hackerthon.demo.domain.enums.GameType;
import hackerthon.demo.domain.enums.RoomStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class GameRoomCreateRequest {

    private final String title;
    @Enumerated(EnumType.STRING)
    private final Category category;
    private final int field;
    @Enumerated(EnumType.STRING)
    private final GameType gameType;
    private final Long gifticonId;


    @Builder
    public GameRoomCreateRequest(@NotNull Long hostId, @NotBlank String title, @NotNull Category category, int field, @NotNull GameType gameType, @NotNull Long gifticonId) {
        //this.hostId = hostId;
        this.title = title;
        this.category = category;
        this.field = field;
        this.gameType = gameType;
        this.gifticonId = gifticonId;
    }

    // 요청 객체를 엔티티로 변환하는 static 메서드
    public static GameRoom toEntity(GameRoomCreateRequest request, Member host) {
        return GameRoom.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .field(request.getField())
                .gameType(request.getGameType())
                .roomStatus(RoomStatus.RECRUIT)
                .host(host)
                .gifticons(new ArrayList<>())
                .build();
    }
}
