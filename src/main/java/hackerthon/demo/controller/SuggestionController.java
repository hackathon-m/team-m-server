package hackerthon.demo.controller;

import hackerthon.demo.common.dto.Response;
import hackerthon.demo.controller.request.SuggestionCreateRequest;
import hackerthon.demo.controller.response.GameRoomResponseDto;
import hackerthon.demo.controller.response.SuggestionResponseDto;
import hackerthon.demo.service.SuggestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suggestions")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "제안 controller", description = "Suggestion API")
public class SuggestionController {

    private final SuggestionService suggestionService;

    @Operation(
            summary = "제안하기",
            responses = @ApiResponse(responseCode = "200", description = "해당 게임방에 제안 요청을 보냅니다. ")
    )
    @PostMapping()
    public Response<SuggestionResponseDto> suggest(@RequestBody SuggestionCreateRequest request, HttpServletRequest httpServletRequest) {

        SuggestionResponseDto suggestionDto = suggestionService.createSuggestion(request, httpServletRequest);
        return Response.of(201, "생성 성공", suggestionDto);

    }

    @Operation(
            summary = "받은 제안 조회",
            responses = @ApiResponse(responseCode = "200", description = "자신이 만든 방에 대해서 받은 제안 요청을 봅니다")
    )
    @GetMapping("/test2")
    public Response<String> getPendingSuggestions(){
        return Response.data("success");
    }

    @Operation(
            summary = "제안 수락하기 ",
            responses = @ApiResponse(responseCode = "200", description = "제안을 수락합니다.")
    )
    @GetMapping("/test")
    public Response<String> getPendingSuggestion(){
        return Response.data("success");
    }
}
