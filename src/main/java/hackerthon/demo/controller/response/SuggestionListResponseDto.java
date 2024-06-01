package hackerthon.demo.controller.response;

import hackerthon.demo.domain.Suggestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SuggestionListResponseDto {

    private Long suggestionId;
    private Long gifticonId;
    private String gifticonImageUrl;
    private String brand;
    private String name;
    private Long suggesterId;

    public static SuggestionListResponseDto fromEntity(Suggestion suggestion) {
        return SuggestionListResponseDto.builder()
                .suggesterId(suggestion.getId())
                .gifticonId(suggestion.getGifticon().getId())
                .gifticonImageUrl(suggestion.getGifticon().getImageUrl())
                .brand(suggestion.getGifticon().getBrand())
                .name(suggestion.getGifticon().getName())
                .suggesterId(suggestion.getSuggester().getId())
                .build();
    }
}
