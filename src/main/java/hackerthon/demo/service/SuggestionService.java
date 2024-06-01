package hackerthon.demo.service;

import hackerthon.demo.controller.request.SuggestionCreateRequest;
import hackerthon.demo.controller.response.AcceptSuggetionResponseDto;
import hackerthon.demo.controller.response.SuggestionResponseDto;
import hackerthon.demo.domain.GameRoom;
import hackerthon.demo.domain.Gifticon;
import hackerthon.demo.domain.Member;
import hackerthon.demo.domain.Suggestion;
import hackerthon.demo.repository.GameRoomRepository;
import hackerthon.demo.repository.GifticonRepository;
import hackerthon.demo.repository.MemberRepository;
import hackerthon.demo.repository.SuggestionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;
    private final MemberRepository memberRepository;
    private final GameRoomRepository gameRoomRepository;
    private final GifticonRepository gifticonRepository;

    public SuggestionResponseDto createSuggestion(SuggestionCreateRequest request, HttpServletRequest httpServletRequest) {
        String serialId = httpServletRequest.getHeader("Authorization");
        Member suggester = memberRepository.findBySerialId(serialId).orElseThrow(() -> new EntityNotFoundException("해당 제안자 존재하지 않음"));

        GameRoom gameRoom = gameRoomRepository.findById(request.getGameRoomId()).orElseThrow(() -> new EntityNotFoundException("해당 게임 방 존재하지 않음"));
        Gifticon gifticon = gifticonRepository.findById(request.getGifticonId()).orElseThrow(() -> new EntityNotFoundException("해당 기프티콘 존재하지 않음"));

        Suggestion suggestion = SuggestionCreateRequest.toEntity(suggester, gameRoom, gifticon);
        Suggestion savedSuggestion = suggestionRepository.save(suggestion);
        return SuggestionResponseDto.fromEntity(savedSuggestion);

    }

    @Transactional
    public AcceptSuggetionResponseDto acceptSuggestion(HttpServletRequest request,  Long suggestionId){
        String serialId = request.getHeader("Authorization");
        Member member = memberRepository.findBySerialId(serialId).orElseThrow(() -> new EntityNotFoundException("해당 사용자 존재하지 않음"));

        Suggestion suggestion = suggestionRepository.findById(suggestionId).orElseThrow(() -> new EntityNotFoundException("해당 제안 존재하지 않음"));
        GameRoom gameRoom = gameRoomRepository.findById(suggestion.getGameRoom().getId()).orElseThrow(() -> new EntityNotFoundException("해당 게임방 존재하지 않음"));

        gameRoom.getGifticons().add(suggestion.getGifticon());
        gameRoomRepository.save(gameRoom);

        AcceptSuggetionResponseDto acceptSuggetionResponseDto = AcceptSuggetionResponseDto.resultDto(member.getId(), suggestion.getSuggester().getId(), gameRoom.getId());
        return acceptSuggetionResponseDto;
    }

}
