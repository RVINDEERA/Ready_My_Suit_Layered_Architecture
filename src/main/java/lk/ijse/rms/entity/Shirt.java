package lk.ijse.rms.entity;

public class Shirt {
    private String smId;
    private String customerId;
    private String date;
    private String length;
    private String chest;
    private String shoulder;
    private String sleeveLength;
    private String collar;
    private String cuff;
    private String waist;

    public Shirt() {
    }

    public Shirt(String smId, String customerId, String date, String length, String chest, String shoulder, String sleeveLength, String collar, String cuff, String waist) {
        this.smId = smId;
        this.customerId = customerId;
        this.date = date;
        this.length = length;
        this.chest = chest;
        this.shoulder = shoulder;
        this.sleeveLength = sleeveLength;
        this.collar = collar;
        this.cuff = cuff;
        this.waist = waist;
    }

    public String getSmId() {
        return smId;
    }

    public void setSmId(String smId) {
        this.smId = smId;
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

    public String getCuff() {
        return cuff;
    }

    public void setCuff(String cuff) {
        this.cuff = cuff;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    @Override
    public String toString() {
        return "ShirtDto{" +
                "smId='" + smId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", date='" + date + '\'' +
                ", length='" + length + '\'' +
                ", chest='" + chest + '\'' +
                ", shoulder='" + shoulder + '\'' +
                ", sleeveLength='" + sleeveLength + '\'' +
                ", collar='" + collar + '\'' +
                ", cuff='" + cuff + '\'' +
                ", waist='" + waist + '\'' +
                '}';
    }
}
