package lk.ijse.rms.dto;

public class TrouserDto {
    private String trmId;
    private String customerId;
    private String date;
    private String length;
    private String wast;
    private String seat;
    private String halfSeat;
    private String Knee;
    private String bottm;
    private String crotch;

    public TrouserDto() {
    }

    public TrouserDto(String trmId, String customerId, String date, String length, String wast, String seat, String halfSeat, String knee, String bottm, String crotch) {
        this.trmId = trmId;
        this.customerId = customerId;
        this.date = date;
        this.length = length;
        this.wast = wast;
        this.seat = seat;
        this.halfSeat = halfSeat;
        Knee = knee;
        this.bottm = bottm;
        this.crotch = crotch;
    }

    public String getTrmId() {
        return trmId;
    }

    public void setTrmId(String trmId) {
        this.trmId = trmId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWast() {
        return wast;
    }

    public void setWast(String wast) {
        this.wast = wast;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getHalfSeat() {
        return halfSeat;
    }

    public void setHalfSeat(String halfSeat) {
        this.halfSeat = halfSeat;
    }

    public String getKnee() {
        return Knee;
    }

    public void setKnee(String knee) {
        Knee = knee;
    }

    public String getBottm() {
        return bottm;
    }

    public void setBottm(String bottm) {
        this.bottm = bottm;
    }

    public String getCrotch() {
        return crotch;
    }

    public void setCrotch(String crotch) {
        this.crotch = crotch;
    }

    @Override
    public String toString() {
        return "TrouserDto{" +
                "trmId='" + trmId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", date='" + date + '\'' +
                ", length='" + length + '\'' +
                ", wast='" + wast + '\'' +
                ", seat='" + seat + '\'' +
                ", halfSeat='" + halfSeat + '\'' +
                ", Knee='" + Knee + '\'' +
                ", bottm='" + bottm + '\'' +
                ", crotch='" + crotch + '\'' +
                '}';
    }
}
