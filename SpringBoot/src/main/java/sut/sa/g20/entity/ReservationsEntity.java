package sut.sa.g20.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data // lombox จะสร้าง method getter setter ให้เอง
@Getter @Setter
@NoArgsConstructor
@Table(name="Reservations") //ชื่อตาราง
public class ReservationsEntity {

    @Id
    @SequenceGenerator(name="reservations_seq",sequenceName="reservations_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="reservations_seq")
    @Column(name="Reservations_Id",unique = true, nullable = false)
private Long Reservations_id;
    private String dateStart;

private String dateEnd;
private int numberofpeople;
private int numberofroom;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "Customer_id")
    private CustomerEntity Customertype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "Hotel_id")
    private HotelEntity Hoteltype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "Promotion_id")
    private PromotionEntity Promotiontype;

    public CustomerEntity getCustomertype() {
        return Customertype;
    }

    public void setCustomertype(CustomerEntity customertype) {
        Customertype = customertype;
    }

    public HotelEntity getHoteltype() {
        return Hoteltype;
    }

    public void setHoteltype(HotelEntity hoteltype) {
        Hoteltype = hoteltype;
    }

    public PromotionEntity getPromotiontype() {
        return Promotiontype;
    }

    public void setPromotiontype(PromotionEntity promotiontype) {
        Promotiontype = promotiontype;
    }

    public Long getReservations_id() {
        return Reservations_id;
    }

    public void setReservations_id(Long reservations_id) {
        Reservations_id = reservations_id;
    }

    public int getNumberofpeople() {
        return numberofpeople;
    }

    public void setNumberofpeople(int numberofpeople) {
        this.numberofpeople = numberofpeople;
    }

    public int getNumberofroom() {
        return numberofroom;
    }

    public void setNumberofroom(int numberofroom) {
        this.numberofroom = numberofroom;
    }


    public String getDateStart() { return dateStart; }

    public void setDateStart(String dateStart) { this.dateStart = dateStart; }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}


