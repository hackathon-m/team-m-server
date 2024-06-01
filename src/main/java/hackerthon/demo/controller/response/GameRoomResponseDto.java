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

    private Long id;
    private String title;
    private int field;
    private Category category;
    private GameType gameType;
    private String gifticonTitle;

    public static GameRoomResponseDto fromEntity(GameRoom gameRoom) {

        String gifticonTitle = gameRoom.getGifticons().isEmpty() ? null : gameRoom.getGifticons().get(0).getName();

        return GameRoomResponseDto.builder()
                .id(gameRoom.getId())
                .title(gameRoom.getTitle())
                .field(gameRoom.getField())
                .category(gameRoom.getCategory())
                .gameType(gameRoom.getGameType())
                .gifticonTitle(gifticonTitle)
                .build();
    }

}
