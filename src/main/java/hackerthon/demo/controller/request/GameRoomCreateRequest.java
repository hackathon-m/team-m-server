package hackerthon.demo.controller.request;

import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.Member;
import hackerthon.demo.domain.enums.Category;
import hackerthon.demo.domain.enums.GameType;
import hackerthon.demo.domain.enums.RoomStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GameRoomCreateRequest {

    private final Long hostId;
    private final String title;
    @Enumerated(EnumType.STRING)
    private final Category category;
    private final int field;
    @Enumerated(EnumType.STRING)
    private final GameType gameType;
    @Enumerated(EnumType.STRING)
    private final RoomStatus roomStatus;
    private final Member host;

    @Builder
    public GameRoomCreateRequest(@NotNull Long hostId, @NotBlank String title, @NotNull Category category, int field, @NotNull GameType gameType, @NotNull RoomStatus roomStatus, @NotNull Member host) {
        this.hostId = hostId;
        this.title = title;
        this.category = category;
        this.field = field;
        this.gameType = gameType;
        this.roomStatus = roomStatus;
        this.host = host;
    }

    // 요청 객체를 엔티티로 변환하는 static 메서드
    public static GameRoom toEntity(GameRoomCreateRequest request) {
        return GameRoom.builder()
                .hostId(request.getHostId())
                .title(request.getTitle())
                .category(request.getCategory())
                .field(request.getField())
                .gameType(request.getGameType())
                .roomStatus(request.getRoomStatus())
                .host(request.getHost())
                .build();
    }
}
