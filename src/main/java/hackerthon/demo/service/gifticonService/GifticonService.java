package hackerthon.demo.service.gifticonService;

import hackerthon.demo.controller.response.GifticonResponse;

import java.util.List;

public interface GifticonService {
    List<GifticonResponse.GifticonResultDTO> findGifticonList(String serialId);
}
