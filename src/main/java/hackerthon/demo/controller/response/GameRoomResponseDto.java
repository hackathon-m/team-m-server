package hackerthon.demo.controller.response;


import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.enums.Category;
import hackerthon.demo.domain.enums.GameType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class GameRoomResponseDto {

    private String title;
    private int field;
    private Category category;
    private GameType gameType;

    public static GameRoomResponseDto fromEntity(GameRoom gameRoom) {
        return GameRoomResponseDto.builder()
                .title(gameRoom.getTitle())
                .field(gameRoom.getField())
                .category(gameRoom.getCategory())
                .gameType(gameRoom.getGameType())
                .build();
    }




}