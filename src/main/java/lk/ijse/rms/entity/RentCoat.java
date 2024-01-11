package lk.ijse.rms.entity;

import lk.ijse.rms.dto.tm.CartTm;

import java.util.ArrayList;
import java.util.List;

public class RentCoat {
    private  String rentId;
    private  String customerId;
    private String rentalBond;

    private List<CartTm> cartTmList = new ArrayList<>();

    public RentCoat() {
    }

    public RentCoat(String rentId, String customerId, String rentalBond, List<CartTm> cartTmList) {
        this.rentId = rentId;
        this.customerId = customerId;
        this.rentalBond = rentalBond;
        this.cartTmList = cartTmList;
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

    public String getRentalBond() {
        return rentalBond;
    }

    public void setRentalBond(String rentalBond) {
        this.rentalBond = rentalBond;
    }

    public List<CartTm> getCartTmList() {
        return cartTmList;
    }

    public void setCartTmList(List<CartTm> cartTmList) {
        this.cartTmList = cartTmList;
    }

    @Override
    public String toString() {
        return "RentCoatDto{" +
                "rentId='" + rentId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", rentalBond='" + rentalBond + '\'' +
                ", cartTmList=" + cartTmList +
                '}';
    }
}
