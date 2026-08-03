package entities.enums;

import java.time.LocalDate;
import java.util.Date;

public class Order {
    private Integer ID;
    private LocalDate moment;
    private OrderStatus status;

    public Order() {

    }

    public Order(Integer ID, LocalDate moment, OrderStatus status) {
        this.ID = ID;
        this.moment = moment;
        this.status = status;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public LocalDate getMoment() {
        return moment;
    }

    public void setMoment(LocalDate moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + ID +
                ", moment=" + moment +
                ", status=" + status +
                '}';
    }
}
