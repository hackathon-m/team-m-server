package hackerthon.demo.service.GameLogService;

import hackerthon.demo.controller.GameLogController;
import hackerthon.demo.controller.response.GameLogResponse;
import hackerthon.demo.controller.response.GifticonResponse;
import hackerthon.demo.domain.GameLog;
import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.Gifticon;
import hackerthon.demo.domain.Member;
import hackerthon.demo.domain.converter.GameLogConverter;
import hackerthon.demo.domain.converter.GifticonConverter;
import hackerthon.demo.repository.GameLogRepository;
import hackerthon.demo.repository.GameRoomRepository;
import hackerthon.demo.repository.GifticonRepository;
import hackerthon.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GameLogServiceImpl implements GameLogService {


    private final MemberRepository memberRepository;
    private final GameLogRepository gameLogRepository;
    private final GifticonRepository gifticonRepository;
    private final GameRoomRepository gameRoomRepository;

    @Override
    @Transactional
    public List<GameLogResponse.GameLogResultDTO> findGameLogList(String serialId){
        Member member = memberRepository.findBySerialId(serialId).orElseThrow(()-> new IllegalArgumentException("해당 시리얼ID를 가진 멤버가 존재하지 않습니다."));

        List<GameLog> gameLogList = gameLogRepository.findByMemberId(member.getId());
        if(gameLogList.isEmpty()){
            return Collections.emptyList();
        }

        List<GameLogResponse.GameLogResultDTO> responseDTOs = gameLogList.stream()
                .map(GameLogConverter::toGameLogResultDTO)
                .collect(Collectors.toList());

        return responseDTOs;
    }

    @Override
    @Transactional
    public String updateGameResult(Long winnerId, Long gameRoomId){
        Member member = memberRepository.findById(winnerId).orElseThrow(()-> new IllegalArgumentException("해당 시리얼ID를 가진 멤버가 존재하지 않습니다."));
        member.setWin(member.getWin() + 1);

        GameRoom gameRoom = gameRoomRepository.findById(gameRoomId).orElseThrow(()-> new IllegalArgumentException("해당 시리얼ID를 가진 멤버가 존재하지 않습니다."));

        List<Gifticon> gifticons = gameRoom.getGifticons();

        for (Gifticon gifticon : gifticons) {
            if(gifticon.getMember().getId() != member.getId()){
                gifticon.setMember(member);
                gifticon.setBetEarned(true);
                gifticonRepository.save(gifticon);
            }

        }

        return "성공적으로 반영되었습니다.";
    }
}
