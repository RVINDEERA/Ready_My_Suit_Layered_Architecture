package lk.ijse.rms.dto.tm;

public class CoatTm {
        private String coatId;
        private String type;
        private String color;
        private String availability;
        private String date;
        private String price;
        private String size;

        public CoatTm() {
        }

        public CoatTm(String coatId, String type, String color, String availability, String date, String price, String size) {
            this.coatId = coatId;
            this.type = type;
            this.color = color;
            this.availability = availability;
            this.date = date;
            this.price = price;
            this.size = size;
        }

        public String getCoatId() {
            return coatId;
        }

        public void setCoatId(String coatId) {
            this.coatId = coatId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getAvailability() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability = availability;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
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

        @Override
        public String toString() {
            return "CoatTm{" +
                    "coatId='" + coatId + '\'' +
                    ", type='" + type + '\'' +
                    ", color='" + color + '\'' +
                    ", availability='" + availability + '\'' +
                    ", date='" + date + '\'' +
                    ", price='" + price + '\'' +
                    ", size='" + size + '\'' +
                    '}';
        }
    }


