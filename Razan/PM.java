package Razan;

// child class of user

public class PM extends user {
    private String department; 

    public PM() {
        super();
    }

    public PM(int id, String name, String email, String password, String projectName, String role , String department) {
        super(id, name, email, password, "PM");
        this.department = department;
    }

    public void reciveRequest(Booking newBooking) {
        newBooking.setStatus("Received by PM");
    }

    public void forwardToSP(SP sp, Booking newBooking) {
        newBooking.setStatus("Sent to SP");
        sp.receiveRequestFromPM(newBooking);
    }

    public void showBoll(Booking newBooking){
        System.out.println("Booking ID: " + newBooking.getBookingId());
        System.out.println("Total price: " + newBooking.getTotalPrice());
    }

    public String getProjectName() {
        return department;
    }
    public void setProjectName(String department) {
        this.department = department;
    }
    
}
