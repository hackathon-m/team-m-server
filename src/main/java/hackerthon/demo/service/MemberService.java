package hackerthon.demo.service;

import hackerthon.demo.domain.Member;
import hackerthon.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> login(String serialId) {
        Optional<Member> member = memberRepository.findBySerialId(serialId);
            
        // Member가 비었을 때, 회원가입
        if(member.isEmpty()) {
            Member newMember = Member.builder()
                    .serialId(serialId)
                    .winRate((double) 0)
                    .build();

            memberRepository.save(newMember);
        }
        return member;
    }
}
