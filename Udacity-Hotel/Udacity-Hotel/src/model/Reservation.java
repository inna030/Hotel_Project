package model;
import java.util.Date;
import java.util.Objects;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        boolean customerEquals=(this.customer==null &&that.customer==null) || (this.customer!=null && this.customer.equals(that.customer));
        boolean roomEquals = (this.room == null && that.room == null)
                || (this.room != null && this.room.equals(that.room));
        boolean checkInDateEquals = (this.checkInDate == null && that.checkInDate == null)
                || (this.checkInDate != null && this.checkInDate.equals(that.checkInDate));
        boolean checkOutDateEquals = (this.checkOutDate == null && that.checkOutDate == null)
                || (this.checkOutDate != null && this.checkOutDate.equals(that.checkOutDate));

        return customerEquals && roomEquals && checkInDateEquals && checkOutDateEquals;
    }

    @Override
    public int hashCode() {
        int result = 12;
        if (customer != null) {
            result = 31 * result + customer.hashCode();
        }
        if (room != null) {
            result = 31 * result + room.hashCode();
        }
        if (checkInDate != null) {
            result = 31 * result + checkInDate.hashCode();
        }
        if (checkOutDate != null) {
            result = 31 * result + checkOutDate.hashCode();
        }
        return result;
    }
}
