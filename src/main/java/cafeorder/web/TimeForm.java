package cafeorder.web;

import cafeorder.domain.Member;
import cafeorder.domain.Time;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/10/07}
 */

@Getter
@Setter
public class TimeForm {
    private int week1;
    private int week2;
    private int week3;
    private int week4;
    private int week5;

    private List<Member> members;

    public TimeForm(List<Member> members) {
        this.members = members;
    }

    private void setWage(int week, int hourlyWage) {
        for (Member m : members) {
            Time time = m.getTime();
            week1 = time.getOneWeekTime() * time.getHourlyWage();
        }

    }
}
