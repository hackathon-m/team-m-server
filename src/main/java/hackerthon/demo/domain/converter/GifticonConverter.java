package hackerthon.demo.domain.converter;

import hackerthon.demo.controller.response.GifticonResponse;
import hackerthon.demo.domain.Gifticon;

public class GifticonConverter {

    public static GifticonResponse.GifticonResultDTO toGifticonResultDTO(Gifticon gifticon) {
        return GifticonResponse.GifticonResultDTO.builder()
                .price(gifticon.getPrice())
                .name(gifticon.getName())
                .brand(gifticon.getBrand())
                .bet_earned(gifticon.getBetEarned())
                .imageUrl(gifticon.getImageUrl())
                .build();
    }
}
