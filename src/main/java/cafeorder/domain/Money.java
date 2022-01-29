package cafeorder.domain;

import java.math.BigDecimal;
import java.util.Objects;

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
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
