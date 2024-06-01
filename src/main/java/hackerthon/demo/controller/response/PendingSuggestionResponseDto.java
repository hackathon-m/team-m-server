package hackerthon.demo.controller.response;


import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.Suggestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PendingSuggestionResponseDto {

    private Long id;

    public static PendingSuggestionResponseDto fromEntity(Suggestion suggestion) {
        return PendingSuggestionResponseDto.builder()
                .id(suggestion.getId())
                .build();
    }
}
