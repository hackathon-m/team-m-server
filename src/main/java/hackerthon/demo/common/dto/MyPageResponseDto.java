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
        double winRate = 0;
        if (member.getTotal() != 0) {
            winRate = (double) member.getWin() / member.getTotal();
        }
        MyPageResponseDto myPageResponseDto = MyPageResponseDto.builder()
                .nickname(member.getNickName())
                .winRate(winRate)
                .build();

        return myPageResponseDto;
    }
}
