package hackerthon.demo.controller;

import hackerthon.demo.apiPayload.ApiResponse;
import hackerthon.demo.common.dto.Response;
import hackerthon.demo.controller.request.GifticonRequest;
import hackerthon.demo.controller.request.GifticonRequest;
import hackerthon.demo.controller.response.GifticonResponse;
import hackerthon.demo.domain.enums.Category;
import hackerthon.demo.service.gifticonService.GifticonService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gifticons")
@RequiredArgsConstructor
public class GifticonController {

    private final GifticonService gifticonService;

    @GetMapping("")
    @Operation(summary = "내 기프티콘 조회")
    public ApiResponse<List<GifticonResponse.GifticonResultDTO>> getGifticons(HttpServletRequest request){
        String serialId = request.getHeader("Authorization");
        try{
            List<GifticonResponse.GifticonResultDTO> gifticonResultDTOS = gifticonService.findGifticonList(serialId);

            return ApiResponse.onSuccess(gifticonResultDTOS);
        }catch(Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @PostMapping("")
    @Operation(summary = "기프티콘 등록")
    public ApiResponse<GifticonResponse.GifticonResultDTO> addGifticon(HttpServletRequest request,
                                                                       @RequestParam("name") String name,
                                                                       @RequestParam("price") int price,
                                                                       @RequestParam("category") Category category,
                                                                       @RequestParam("img") String url) throws IOException {
        String serialId = request.getHeader("Authorization");
        try{
            return ApiResponse.onSuccess(gifticonService.addGifticon(serialId, name, price, category, url));
        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }

    }



}
