package hackerthon.demo.service.gifticonService;

import hackerthon.demo.controller.request.GifticonRequest;
import hackerthon.demo.controller.response.GifticonResponse;
import hackerthon.demo.domain.enums.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GifticonService {
    List<GifticonResponse.GifticonResultDTO> findGifticonList(String serialId);
    GifticonResponse.GifticonResultDTO addGifticon(String serialId, String name, int price, Category category, String url);

}
