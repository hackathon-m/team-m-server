package hackerthon.demo.service.GameLogService;

import hackerthon.demo.controller.GameLogController;
import hackerthon.demo.controller.response.GameLogResponse;
import hackerthon.demo.controller.response.GifticonResponse;
import hackerthon.demo.domain.GameLog;
import hackerthon.demo.domain.Member;
import hackerthon.demo.domain.converter.GameLogConverter;
import hackerthon.demo.domain.converter.GifticonConverter;
import hackerthon.demo.repository.GameLogRepository;
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

}
