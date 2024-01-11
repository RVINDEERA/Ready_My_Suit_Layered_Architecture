package lk.ijse.rms.entity;

public class Fabric {
    private String fabricId;
    private String name;
    private String rollqty;
    private String type;
    private String colour;

    public Fabric() {
    }

    public Fabric(String fabricId, String name, String rollqty, String type, String colour) {
        this.fabricId = fabricId;
        this.name = name;
        this.rollqty = rollqty;
        this.type = type;
        this.colour = colour;
    }

    public String getFabricId() {
        return fabricId;
    }

    public void setFabricId(String fabricId) {
        this.fabricId = fabricId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollqty() {
        return rollqty;
    }

    public void setRollqty(String rollqty) {
        this.rollqty = rollqty;
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

    @Override
    public String toString() {
        return "FabricDto{" +
                "fabricId='" + fabricId + '\'' +
                ", name='" + name + '\'' +
                ", rollqty='" + rollqty + '\'' +
                ", type='" + type + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
