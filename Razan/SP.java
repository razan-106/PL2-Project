package Razan;


// child class of user

public class SP extends user {
    private String serviceType;

    public SP() {
        super();
    }

    public SP(int id, String name, String email, String password, String serviceType , String role) {
        super(id, name, email, password, "SP");
        this.serviceType = serviceType;
    }

    public void receiveRequestFromPM(Booking newBooking) {
        newBooking.setStatus("Received by SP");
    }   

    public void calculatePrice(Booking newBooking, double price) {
        newBooking.setTotalPrice(price);
    }

    public void setReadyDate(Booking newBooking , String date) {
        newBooking.setStatus("Ready Date Assigned: " + date.toString());
    }

    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    
}
