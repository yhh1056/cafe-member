package cafeorder.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "member_name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "time_id")
    private Time time;

    private int oneWeekWage;
    private int twoWeekWage;
    private int threeWeekWage;
    private int fourWeekWage;
    private int fiveWeekWage;
    private int totalWage;

    public Member(String name) {
        this.name = name;
    }

    public void addTimeInfo(Time time) {
        this.time = time;
    }

    public void updateInfo(String name) {
        this.name = name;
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    public void calcWage() {
        oneWeekWage = calcOneWeek(time.getOneWeekTime(), time.getHourlyWage());
        twoWeekWage = calcOneWeek(time.getTwoWeekTime(), time.getHourlyWage());
        threeWeekWage = calcOneWeek(time.getThreeWeekTime(), time.getHourlyWage());
        fourWeekWage = calcOneWeek(time.getFourWeekTime(), time.getHourlyWage());
        fiveWeekWage = calcOneWeek(time.getFiveWeekTime(), time.getHourlyWage());

        totalWage = oneWeekWage + twoWeekWage + threeWeekWage + fourWeekWage + fiveWeekWage;
    }

    private int calcOneWeek(int weekTime, int hourlyWage) {
        if (weekTime >= 15) {
            return calcVacationWage(weekTime, hourlyWage);
        }
        return calcWage(weekTime, hourlyWage);

    }

    private int calcVacationWage(int weekTime, int hourlyWage) {
        return weekTime * calcVacationPay(hourlyWage);
    }

    private int calcWage(int weekTime, int hourlyWage) {
        return weekTime * hourlyWage;
    }

    private int calcVacationPay(int hourlyWage) {
        return (int) (hourlyWage * 1.2);
    }
}
