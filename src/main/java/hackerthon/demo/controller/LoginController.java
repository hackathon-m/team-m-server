package hackerthon.demo.controller;

import hackerthon.demo.apiPayload.ApiResponse;
import hackerthon.demo.common.dto.MyPageResponseDto;
import hackerthon.demo.domain.Member;
import hackerthon.demo.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class LoginController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "serialId Authorization 헤더값에 추가, nickname 파라미터 추가")
    @GetMapping("/signup")
    public ApiResponse<String> signUp(HttpServletRequest request, @RequestParam("nickname") String nickname) throws Exception {
        String serialId = request.getHeader("Authorization");
        memberService.signUp(serialId, nickname);
        return ApiResponse.onSuccess("회원가입 success");
    }

    @Operation(summary = "로그인", description = "serialId만 Authorization 헤더값에 추가")
    @GetMapping("/login")
    public ApiResponse<String> login(HttpServletRequest request) throws Exception {
        String serialId = request.getHeader("Authorization");
        memberService.login(serialId);
        return ApiResponse.onSuccess("login success");
    }
    

    @GetMapping("/mypage")
    public ApiResponse<MyPageResponseDto> myPage(HttpServletRequest request) throws Exception {
        String serialId = request.getHeader("Authorization");

        Optional<Member> member = memberService.getInfo(serialId);

        if(member.isPresent()) {
            Member existedMember = member.get();
            MyPageResponseDto myPageResponseDto = MyPageResponseDto.convertor(existedMember);
            return ApiResponse.onSuccess(myPageResponseDto);
        } else {
            return ApiResponse.onFailure(HttpStatus.NOT_FOUND.toString(), "찾을 수 없습니다", null);
        }

    }
}