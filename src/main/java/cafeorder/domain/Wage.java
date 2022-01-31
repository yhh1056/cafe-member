package cafeorder.domain;

import cafeorder.util.MoneyString;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wage {
    public static final int MINIMUM_WAGE = 8530;
    public static final int MAXIMUM_WORK_TIME = 52;

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Week week;

    @Embedded
    private Money totalAmount;

    private int workTime;

    private boolean holidayPay;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Wage(Week week, int workTime, boolean holidayPay, Member member) {
        validate(workTime);
        this.week = week;
        this.totalAmount = calculateWage(workTime, holidayPay);
        this.workTime = workTime;
        this.holidayPay = holidayPay;
        this.member = member;
        if (member != null) {
            member.getWages().add(this);
        }
    }

    private void validate(int workTime) {
        if (workTime > MAXIMUM_WORK_TIME) {
            throw new IllegalArgumentException("주 40시간을 넘길 수 없습니다.");
        }

        if (workTime < 0) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public Money calculateWage(int workTime, boolean isHolidayPay) {
        return new Money(pay(workTime, isHolidayPay));
    }

    public double pay(int workTime, boolean isHolidayPay) {
        if (isHolidayPay) {
            return MINIMUM_WAGE * workTime * 1.2;
        }
        return MINIMUM_WAGE * workTime;
    }

    public boolean isEqualsWeek(Week week) {
        return this.week == week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wage)) {
            return false;
        }
        Wage wage = (Wage)o;
        return week == wage.week;
    }

    @Override
    public int hashCode() {
        return Objects.hash(week);
    }

    @Override
    public String toString() {
        return this.totalAmount.toString();
    }

    public static Wage empty() {
        return Wage.builder().workTime(0).build();
    }
}
