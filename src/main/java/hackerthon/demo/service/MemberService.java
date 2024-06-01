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

    /**
     * 로그인
     * @param serialId, nickname
     * @return
     */
    public void login(String serialId, String nickname) {
        Optional<Member> member = memberRepository.findBySerialId(serialId);

        if(member.isEmpty()) {
            Member newMember = Member.builder()
                    .serialId(serialId)
                    .nickName(nickname)
                    .win((double)0)
                    .total((double) 0)
                    .build();

            memberRepository.save(newMember);
        }
    }

    /**
     * 회원 정보 불러오기
     * @param serialId
     * @return nickname, winRate
     * @throws Exception
     */
    public Optional<Member> getInfo(String serialId) throws Exception {
        Optional<Member> member = memberRepository.findBySerialId(serialId);

        if(member.isEmpty()) throw new Exception();
        else return member;

    }
}
