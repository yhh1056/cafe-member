package cafeorder.domain;

import cafeorder.util.MoneyString;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {

    private BigDecimal amount;

    public Money(double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money = (Money)o;
        return Objects.equals(amount.intValue(), money.amount.intValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return MoneyString.of(this.amount);
    }

    public static Money zero() {
        return new Money(0);
    }

    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.getAmount()));
    }
}
