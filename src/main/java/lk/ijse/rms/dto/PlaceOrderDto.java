package lk.ijse.rms.dto;

import lk.ijse.rms.dto.tm.OrderCartTm;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderDto {
    private String orderId;
    private String date;
    private String customerId;
    private String tailorId;
    private double fullAmount;
    private double advance;
    private double balance;
    private String status;
    private String completeDate;
    private List<OrderCartTm> orderCartTmList= new ArrayList<>();

    public PlaceOrderDto() {
    }

    public PlaceOrderDto(String orderId, String date, String customerId, String tailorId, double fullAmount, double advance, double balance, String status, String completeDate, List<OrderCartTm> orderCartTmList) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.tailorId = tailorId;
        this.fullAmount = fullAmount;
        this.advance = advance;
        this.balance = balance;
        this.status = status;
        this.completeDate = completeDate;
        this.orderCartTmList = orderCartTmList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTailorId() {
        return tailorId;
    }

    public void setTailorId(String tailorId) {
        this.tailorId = tailorId;
    }

    public double getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(double fullAmount) {
        this.fullAmount = fullAmount;
    }

    public double getAdvance() {
        return advance;
    }

    public void setAdvance(double advance) {
        this.advance = advance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public List<OrderCartTm> getOrderCartTmList() {
        return orderCartTmList;
    }

    public void setOrderCartTmList(List<OrderCartTm> orderCartTmList) {
        this.orderCartTmList = orderCartTmList;
    }

    @Override
    public String toString() {
        return "PlaceOrderDto{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", customerId='" + customerId + '\'' +
                ", tailorId='" + tailorId + '\'' +
                ", fullAmount=" + fullAmount +
                ", advance=" + advance +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                ", completeDate='" + completeDate + '\'' +
                ", orderCartTmList=" + orderCartTmList +
                '}';
    }
}
