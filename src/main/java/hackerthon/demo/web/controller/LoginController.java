package hackerthon.demo.web.controller;

import hackerthon.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public ResponseEntity<String> login(String serialId) {
        memberService.login(serialId);
        return ResponseEntity.ok("login success");
    }
}
