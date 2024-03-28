package util;

import java.io.Serializable;

public class savedOrder implements Serializable {
    private Order order;
    private String text;

    public savedOrder(Order order, String text)
    {
        this.order = order;
        this.text = text;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Order getOrder() {
        return order;
    }

    public String getText() {
        return text;
    }
}
