package cafeorder.domain;

import lombok.Builder;
import org.apache.commons.lang3.BooleanUtils;

public class Wage {
    public static final int MINIMUM_WAGE = 8350;
    public static final int MAXIMUM_WORK_TIME = 40;

    private Money totalAmount;

    private int workTime;

    private boolean isHolidayPay;

    @Builder
    public Wage(int workTime, boolean isHolidayPay) {
        validate(workTime);
        this.totalAmount = calculateWage(workTime, isHolidayPay);
        this.workTime = workTime;
        this.isHolidayPay = isHolidayPay;
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

    public Money getTotalAmount() {
        return totalAmount;
    }
}
