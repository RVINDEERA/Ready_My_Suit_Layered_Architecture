package lk.ijse.rms.dto;

public class RentalBondDto {
    private String bondId;
    private String type;
    private String note;

    public RentalBondDto() {
    }

    public RentalBondDto(String bondId, String type, String note) {
        this.bondId = bondId;
        this.type = type;
        this.note = note;
    }

    public String getBondId() {
        return bondId;
    }

    public void setBondId(String bondId) {
        this.bondId = bondId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "RentalBondDto{" +
                "bondId='" + bondId + '\'' +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
