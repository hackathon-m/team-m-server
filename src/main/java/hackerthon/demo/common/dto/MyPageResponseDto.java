package hackerthon.demo.common.dto;


import hackerthon.demo.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyPageResponseDto {
    private String nickname;
    private Double winRate;

    public static MyPageResponseDto convertor(Member member) {
        MyPageResponseDto myPageResponseDto = MyPageResponseDto.builder()
                .nickname(member.getNickName())
                .winRate(member.getWinRate())
                .build();

        return myPageResponseDto;
    }
}
