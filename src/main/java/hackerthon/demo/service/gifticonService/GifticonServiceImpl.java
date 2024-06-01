package hackerthon.demo.service.gifticonService;

import hackerthon.demo.controller.response.GifticonResponse;
import hackerthon.demo.domain.Gifticon;
import hackerthon.demo.domain.Member;
import hackerthon.demo.domain.converter.GifticonConverter;
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
public class GifticonServiceImpl implements GifticonService {

    private final MemberRepository memberRepository;
    private final GifticonRepository gifticonRepository;
    @Override
    @Transactional
    public List<GifticonResponse.GifticonResultDTO> findGifticonList(String serialId){

        Member member = memberRepository.findBySerialId(serialId).orElseThrow(()-> new IllegalArgumentException("해당 시리얼ID를 가진 멤버가 존재하지 않습니다."));

        List<Gifticon> gifticonList =gifticonRepository.findByMemberId(member.getId());

        if(gifticonList.isEmpty()){
            return Collections.emptyList();
        }

        List<GifticonResponse.GifticonResultDTO> responseDTOs = gifticonList.stream()
                .map(GifticonConverter::toGifticonResultDTO)
                .collect(Collectors.toList());

        return responseDTOs;
    }

}
