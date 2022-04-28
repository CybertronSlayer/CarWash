package doom.architect.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    public String firstName;
    public String lastName;
    public Long phoneNumber;
    public List<Appointment> appointments;

    public Customer(String firstName, String lastName) {
        this(firstName, lastName, null);

    }

    public Customer(String firstName, String lastName, Long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.appointments = new ArrayList<>();
    }

    public void newAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.customer = this;
    }

    @Override
    public String toString() {
        return "Customer{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", phoneNumber=" + phoneNumber +
               ", appointments=" + appointments +
               '}';
    }
}
