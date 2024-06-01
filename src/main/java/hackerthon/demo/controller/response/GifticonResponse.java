package hackerthon.demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GifticonResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GifticonResultDTO {
        private Long id;
        private String imageUrl;
        private String brand;
        private int price;
        private String name;
        private Boolean bet_earned;
    }
}
