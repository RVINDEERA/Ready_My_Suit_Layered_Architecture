package lk.ijse.rms.entity;

public class Machine {
    private String machineId;
    private String tailorId;
    private String type;
    private String date;
    private String avail;

    public Machine() {
    }

    public Machine(String machineId, String tailorId, String type, String date, String avail) {
        this.machineId = machineId;
        this.tailorId = tailorId;
        this.type = type;
        this.date = date;
        this.avail = avail;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getTailorId() {
        return tailorId;
    }

    public void setTailorId(String tailorId) {
        this.tailorId = tailorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

}
