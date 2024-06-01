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

    public Optional<Member> login(String serialId, String nickname) {
        Optional<Member> member = memberRepository.findBySerialId(serialId);
            
        // Member가 비었을 때, 회원가입
        if(member.isEmpty()) {
            Member newMember = Member.builder()
                    .serialId(serialId)
                    .nickName(nickname)
                    .winRate((double) 0)
                    .build();

            memberRepository.save(newMember);
        }
        return member;
    }

    public Optional<Member> getInfo(String serialId) {
        Optional<Member> member = Optional.of(memberRepository.findBySerialId(serialId).orElseThrow());
        return member;
    }
}
