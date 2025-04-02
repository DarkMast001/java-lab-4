package org.example.task_6;

import java.util.Objects;

public class DiscountedItem extends Item{
    private double discount;

    public DiscountedItem(String description, double price, double discount) {
        super(description, price);
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Item)) return false;

        if (!(o instanceof DiscountedItem)) {
            return super.equals(o);
        }

        DiscountedItem that = (DiscountedItem) o;

        return super.equals(o) && Double.compare(that.discount, discount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discount);
    }
}
