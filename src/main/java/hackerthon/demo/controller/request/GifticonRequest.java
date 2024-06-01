package hackerthon.demo.controller.request;

import hackerthon.demo.domain.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GifticonRequest {

    @Getter
    public static class addGifticonDTO{
        private String name;
        private int price;
        private Category category;
    }


}
