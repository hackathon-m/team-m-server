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
     * 회원가입
     * @param serialId
     * @param nickname
     */
    public void signUp(String serialId, String nickname) throws Exception {
        Optional<Member> existedMember = memberRepository.findBySerialId(serialId);
        if(existedMember.isPresent()) {
            throw new Exception("이미 존재하는 회원입니다.");
        } else {
            Member member = Member.builder()
                    .serialId(serialId)
                    .nickName(nickname)
                    .winRate((double) 0)
                    .build();

            memberRepository.save(member);
        }


    }

    /**
     * 로그인
     * @param serialId
     * @return
     */
    public void login(String serialId) throws Exception {
        Optional<Member> member = memberRepository.findBySerialId(serialId);

        if(member.isEmpty()) {
            //해당 회원은 없습니다.
            throw new Exception();
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
