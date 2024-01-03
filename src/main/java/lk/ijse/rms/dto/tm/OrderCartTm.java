package lk.ijse.rms.dto.tm;

import javafx.scene.control.Button;

public class OrderCartTm {
    private String itemId;
    private String type;
    private int qty;
    private String note;
    private Button btn;

    public OrderCartTm() {
    }

    public OrderCartTm(String itemId, String type, int qty, String note, Button btn) {
        this.itemId = itemId;
        this.type = type;
        this.qty = qty;
        this.note = note;
        this.btn = btn;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "OrderCartTm{" +
                "itemId='" + itemId + '\'' +
                ", type='" + type + '\'' +
                ", qty=" + qty +
                ", note='" + note + '\'' +
                ", btn=" + btn +
                '}';
    }
}
