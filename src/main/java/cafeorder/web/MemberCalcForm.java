package cafeorder.web;

import cafeorder.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/30}
 */

@Getter
@Setter
public class MemberCalcForm {

    private String name;

    @NotNull
    private int weekTime;

    @NotNull
    private int hourlyWage;

    private List<Member> members;

    public MemberCalcForm(List<Member> members) {
        this.members = members;
    }

    public void addTimeInfo(String name, int weekTime, int hourlyWage) {
        for (Member m : members) {
            if (m.equals(name)) {
                m.addTime(weekTime, hourlyWage);
            }
        }
    }
}
