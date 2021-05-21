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

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int week;

    private int time;

    @Column(nullable = false)
    private boolean isCheck = false;

    private int wage;

    public static Wage create(int week, int time, boolean check) {
        return new Wage(week, time, check);
    }

    public Wage(int week, int time, boolean check) {
        this.week = week;
        this.time = time;
        this.isCheck = check;
        this.wage = calcWage(time, check);
    }

    private int calcWage(int time, boolean check) {
        if (check) {
            return (int) (time * 1.2 * 8530);
        }
        return time * 8530;
    }

    public void addMember(Member member) {
        this.member = member;
    }
}
