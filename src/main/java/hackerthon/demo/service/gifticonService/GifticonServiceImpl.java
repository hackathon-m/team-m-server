package hackerthon.demo.service.gifticonService;

import hackerthon.demo.controller.request.GifticonRequest;
import hackerthon.demo.controller.response.GifticonResponse;
import hackerthon.demo.domain.Gifticon;
import hackerthon.demo.domain.Member;
import hackerthon.demo.domain.converter.GifticonConverter;
import hackerthon.demo.domain.enums.Category;
import hackerthon.demo.repository.GifticonRepository;
import hackerthon.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    @Transactional
    public GifticonResponse.GifticonResultDTO addGifticon(String serialId, String name, int price, Category category, String url){
        Member member = memberRepository.findBySerialId(serialId).orElseThrow(()-> new IllegalArgumentException("해당 시리얼ID를 가진 멤버가 존재하지 않습니다."));

        Gifticon gifticon = Gifticon.builder()
                .name(name)
                .price(price)
                .category(category)
                .imageUrl(url)
                .member(member)
                .build();

        gifticonRepository.save(gifticon);
        GifticonResponse.GifticonResultDTO gifticonResultDTO = GifticonConverter.toGifticonResultDTO(gifticon);
        return gifticonResultDTO;
    }
//
//    @Transactional
//    public void changeGifticonOwner(Long roomId, Long newOwnerId) {
//        GameRoom gameRoom = gameRoomRepository.findById(roomId)
//                .orElseThrow(() -> new EntityNotFoundException("게임 룸을 찾을 수 없습니다."));
//
//        Member newOwner = memberRepository.findById(newOwnerId)
//                .orElseThrow(() -> new EntityNotFoundException("새 소유자를 찾을 수 없습니다."));
//
//        List<Gifticon> gifticons = gameRoom.getGifticons();
//
//        for (Gifticon gifticon : gifticons) {
//            gifticon.setMember(newOwner);
//            gifticonRepository.save(gifticon);
//        }
//    }


}
