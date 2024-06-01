package hackerthon.demo.domain;

import hackerthon.demo.common.BaseEntity;
import hackerthon.demo.domain.enums.Category;
import hackerthon.demo.domain.enums.GameType;
import hackerthon.demo.domain.enums.RoomStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GameRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int field;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member host;

    @OneToMany
    private List<Gifticon> gifticons = new ArrayList<>();

    @Builder
    public GameRoom( String title, Category category, int field, GameType gameType, RoomStatus roomStatus, Member host) {
        this.title = title;
        this.category = category;
        this.field = field;
        this.gameType = gameType;
        this.roomStatus = roomStatus;
        this.host = host;
        this.gifticons = new ArrayList<>();
    }



}
