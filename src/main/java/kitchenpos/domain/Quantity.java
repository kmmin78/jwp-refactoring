package kitchenpos.domain;

import static kitchenpos.constant.ExceptionMessages.QUANTITY_IS_LESS_THAN_ZERO;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Quantity {
    @Column(nullable = false)
    private long quantity;

    protected Quantity() {

    }

    public Quantity(long quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(QUANTITY_IS_LESS_THAN_ZERO);
        }
        this.quantity = quantity;
    }

    public long getValue() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Quantity quantity1 = (Quantity) o;
        return quantity == quantity1.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }
}
