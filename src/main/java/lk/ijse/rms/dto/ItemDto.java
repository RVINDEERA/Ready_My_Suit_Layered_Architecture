package lk.ijse.rms.dto;

public class ItemDto {
    private String itemId;
    private String type;

    private int count;

    public ItemDto() {
    }

    public ItemDto(String itemId, String type, int count) {
        this.itemId = itemId;
        this.type = type;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "itemId='" + itemId + '\'' +
                ", type='" + type + '\'' +
                ", count=" + count +
                '}';
    }
}
