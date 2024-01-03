package lk.ijse.rms.dto;

public class CoatMeasurementsDto {
    private String cmId;
    private String customerId;
    private String date;
    private String length;
    private String chest;
    private String shoulder;
    private String sleeveLength;
    private String collar;
    private String waist;
    private String neck;
    private String elbow;

    public CoatMeasurementsDto() {
    }

    public CoatMeasurementsDto(String cmId, String customerId, String date, String length, String chest, String shoulder, String sleeveLength, String collar, String waist, String neck, String elbow) {
        this.cmId = cmId;
        this.customerId = customerId;
        this.date = date;
        this.length = length;
        this.chest = chest;
        this.shoulder = shoulder;
        this.sleeveLength = sleeveLength;
        this.collar = collar;
        this.waist = waist;
        this.neck = neck;
        this.elbow = elbow;
    }

    public String getCmId() {
        return cmId;
    }

    public void setCmId(String cmId) {
        this.cmId = cmId;
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

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getShoulder() {
        return shoulder;
    }

    public void setShoulder(String shoulder) {
        this.shoulder = shoulder;
    }

    public String getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(String sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public String getCollar() {
        return collar;
    }

    public void setCollar(String collar) {
        this.collar = collar;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getElbow() {
        return elbow;
    }

    public void setElbow(String elbow) {
        this.elbow = elbow;
    }

    @Override
    public String toString() {
        return "coatMeasurementsDto{" +
                "cmId='" + cmId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", date='" + date + '\'' +
                ", length='" + length + '\'' +
                ", chest='" + chest + '\'' +
                ", shoulder='" + shoulder + '\'' +
                ", sleeveLength='" + sleeveLength + '\'' +
                ", collar='" + collar + '\'' +
                ", waist='" + waist + '\'' +
                ", neck='" + neck + '\'' +
                ", elbow='" + elbow + '\'' +
                '}';
    }
}
