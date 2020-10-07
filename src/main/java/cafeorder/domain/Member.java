package cafeorder.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    public void calcWage() {
        oneWeekWage = time.getOneWeekTime() * time.getHourlyWage();
        twoWeekWage = time.getTwoWeekTime() * time.getHourlyWage();
        threeWeekWage = time.getThreeWeekTime() * time.getHourlyWage();
        fourWeekWage = time.getFourWeekTime() * time.getHourlyWage();
        fiveWeekWage = time.getFiveWeekTime() * time.getHourlyWage();

        totalWage = oneWeekWage + twoWeekWage + threeWeekWage + fourWeekWage + fiveWeekWage;
    }
}
