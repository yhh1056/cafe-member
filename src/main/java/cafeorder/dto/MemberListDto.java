package cafeorder.dto;

import cafeorder.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

/**
 * author {yhh1056}
 * Create by {2020/10/13}
 * <p>
 * 돈 단위를 추가한다
 */
@Getter
@Setter
public class MemberListDto {
    private Long id;
    private String name;
    private String wage1;
    private String wage2;
    private String wage3;
    private String wage4;
    private String wage5;
    private String total;

    public MemberListDto(Member member) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        this.id = member.getId();
        this.name = member.getName();
        this.wage1 = formatter.format(member.getOneWeekWage()) + "원";
        this.wage2 = formatter.format(member.getTwoWeekWage()) + "원";
        this.wage3 = formatter.format(member.getThreeWeekWage()) + "원";
        this.wage4 = formatter.format(member.getFourWeekWage()) + "원";
        this.wage5 = formatter.format(member.getFiveWeekWage()) + "원";
        this.total = formatter.format(member.getTotalWage()) + "원";
    }
}