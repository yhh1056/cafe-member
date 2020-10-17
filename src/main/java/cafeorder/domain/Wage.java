package cafeorder.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * author {yhh1056}
 * Create by {2020/10/16}
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wage_id")
    private Long id;

    @OneToOne(mappedBy = "wage", cascade = CascadeType.ALL)
    private Member member;

    private int oneWeekWage;
    private int twoWeekWage;
    private int threeWeekWage;
    private int fourWeekWage;
    private int fiveWeekWage;

    public Wage(boolean[] checks) {
        this.oneWeekWage = getWage(checks[0]);
        this.twoWeekWage = getWage(checks[1]);
        this.threeWeekWage = getWage(checks[2]);
        this.fourWeekWage = getWage(checks[3]);
        this.fiveWeekWage = getWage(checks[4]);
    }

    public void changeInfo(boolean[] checks) {
        this.oneWeekWage = getWage(checks[0]);
        this.twoWeekWage = getWage(checks[1]);
        this.threeWeekWage = getWage(checks[2]);
        this.fourWeekWage = getWage(checks[3]);
        this.fiveWeekWage = getWage(checks[4]);
    }

    private int getWage(boolean check) {
        if (check) {
            return (int) (8590 * 1.2);
        }
        return 8590;
    }
}
