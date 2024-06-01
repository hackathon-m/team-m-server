package hackerthon.demo.web.controller;

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



    @Operation(summary = "회원가입 및 로그인", description = "")
    @GetMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request, @RequestParam("nickname") String nickname) {
        String serialId = request.getHeader("Authorization");
        memberService.login(serialId, nickname);
        return ResponseEntity.ok("login success");
    }


    @GetMapping("/mypage")
    public ResponseEntity<MyPageResponseDto> myPage(HttpServletRequest request) throws Exception {
        String serialId = request.getHeader("Authorization");

        Optional<Member> member = memberService.getInfo(serialId);

        if(member.isPresent()) {
            Member existedMember = member.get();
            MyPageResponseDto myPageResponseDto = MyPageResponseDto.convertor(existedMember);
            return ResponseEntity.ok(myPageResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}