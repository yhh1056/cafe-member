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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wage_id")
    private Wage wage;

    private int oneWeekWage;
    private int twoWeekWage;
    private int threeWeekWage;
    private int fourWeekWage;
    private int fiveWeekWage;
    private int totalWage;

    public Member(String name) {
        this.name = name;
        this.wage = new Wage();
        this.time = new Time();
    }

    public void updateInfo(String name) {
        this.name = name;
    }

    public void calcWage() {
        oneWeekWage = time.getOneWeekTime() * wage.getOneWeekWage();
        twoWeekWage = time.getTwoWeekTime() * wage.getTwoWeekWage();
        threeWeekWage = time.getThreeWeekTime() * wage.getThreeWeekWage();
        fourWeekWage = time.getFourWeekTime() * wage.getFourWeekWage();
        fiveWeekWage = time.getFiveWeekTime() * wage.getFiveWeekWage();

        totalWage = oneWeekWage + twoWeekWage + threeWeekWage + fourWeekWage + fiveWeekWage;
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }
}
