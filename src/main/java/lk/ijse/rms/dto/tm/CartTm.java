package lk.ijse.rms.dto.tm;


import javafx.scene.control.Button;
public class CartTm {

    private String coatId;
    private String rentDate;
    private String returnDate;
    private String price;
    private String size;
    private String type;
    private  String colour;
    private String mfgDate;
    private String availability;
    private Button btn;

    public CartTm() {
    }

    public CartTm(String coatId, String rentDate, String returnDate, String price, String size, String type, String colour, String mfgDate, String availability, Button btn) {
        this.coatId = coatId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.price = price;
        this.size = size;
        this.type = type;
        this.colour = colour;
        this.mfgDate = mfgDate;
        this.availability = availability;
        this.btn = btn;
    }

    public String getCoatId() {
        return coatId;
    }

    public void setCoatId(String coatId) {
        this.coatId = coatId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(String mfgDate) {
        this.mfgDate = mfgDate;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CartTm{" +
                "coatId='" + coatId + '\'' +
                ", rentDate='" + rentDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", price='" + price + '\'' +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                ", colour='" + colour + '\'' +
                ", mfgDate='" + mfgDate + '\'' +
                ", availability='" + availability + '\'' +
                ", btn=" + btn +
                '}';
    }
}
