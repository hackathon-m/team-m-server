package hackerthon.demo.controller;

import hackerthon.demo.apiPayload.ApiResponse;
import hackerthon.demo.common.dto.Response;
import hackerthon.demo.controller.response.GifticonResponse;
import hackerthon.demo.service.gifticonService.GifticonService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gifticons")
@RequiredArgsConstructor
public class GifticonController {

    private final GifticonService gifticonService;

    @GetMapping("")
    @Operation(summary = "내 기프티콘 조회")
    public ApiResponse<List<GifticonResponse.GifticonResultDTO>> getGifticons(HttpServletRequest request){
        String serialId = request.getParameter("Authorization");
        try{
            List<GifticonResponse.GifticonResultDTO> gifticonResultDTOS = gifticonService.findGifticonList(serialId);

            return ApiResponse.onSuccess(gifticonResultDTOS);
        }catch(Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }




}
