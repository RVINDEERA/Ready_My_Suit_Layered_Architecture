package lk.ijse.rms.dto.tm;

public class TailorTm {
        private String tailorId;
        private String firstName;
        private String lastName;
        private String tailorNIC;
        private String address;
        private  String phoneNumber;
        private String salary;

        public TailorTm() {
        }

    public TailorTm(String tailorId, String firstName, String lastName, String tailorNIC, String address, String phoneNumber, String salary) {
        this.tailorId = tailorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tailorNIC = tailorNIC;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public String getTailorId() {
        return tailorId;
    }

    public void setTailorId(String tailorId) {
        this.tailorId = tailorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTailorNIC() {
        return tailorNIC;
    }

    public void setTailorNIC(String tailorNIC) {
        this.tailorNIC = tailorNIC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "TailorTm{" +
                "tailorId='" + tailorId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tailorNIC='" + tailorNIC + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
