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

        Member member = memberRepository.findBySerialId(serialId).orElseThrow(()-> new IllegalArgumentException());

        List<Gifticon> gifticonList =gifticonRepository.findByMemberId(member.getId());

        List<GifticonResponse.GifticonResultDTO> responseDTOs = gifticonList.stream()
                .map(GifticonConverter::toGifticonResultDTO)
                .collect(Collectors.toList());

        return responseDTOs;

    }

}
