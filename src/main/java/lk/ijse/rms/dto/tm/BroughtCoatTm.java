package lk.ijse.rms.dto.tm;

import java.awt.*;

public class BroughtCoatTm {
    private String rentId ;
    private String customerId;
    private String firstName;
    private String phoneNUmber;
    private String coatId;
    private String rebtalBond;
    private String rentDate;
    private String returnDate;
    private Button btn;



    public BroughtCoatTm() {
    }

    public BroughtCoatTm(String rentId, String customerId, String firstName, String phoneNUmber, String coatId, String rebtalBond, String rentDate, String returnDate, Button btn) {
        this.rentId = rentId;
        this.customerId = customerId;
        this.firstName = firstName;
        this.phoneNUmber = phoneNUmber;
        this.coatId = coatId;
        this.rebtalBond = rebtalBond;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.btn = btn;
    }

    public String getRentId() {
        return rentId;
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNUmber() {
        return phoneNUmber;
    }

    public void setPhoneNUmber(String phoneNUmber) {
        this.phoneNUmber = phoneNUmber;
    }

    public String getCoatId() {
        return coatId;
    }

    public void setCoatId(String coatId) {
        this.coatId = coatId;
    }

    public String getRebtalBond() {
        return rebtalBond;
    }

    public void setRebtalBond(String rebtalBond) {
        this.rebtalBond = rebtalBond;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "BroughtCoatTm{" +
                "rentId='" + rentId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNUmber='" + phoneNUmber + '\'' +
                ", coatId='" + coatId + '\'' +
                ", rebtalBond='" + rebtalBond + '\'' +
                ", rentDate='" + rentDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", btn=" + btn +
                '}';
    }
}
