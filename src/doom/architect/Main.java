package doom.architect;

import doom.architect.model.Appointment;
import doom.architect.model.Customer;
import doom.architect.serialization.AppointmentSerializer;
import doom.architect.serialization.CustomerParser;
import doom.architect.serialization.CustomerSerializer;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

public class Main {


    //I have a car wash, and I want to be to save the appointments of my customers to my computer

    /*
    1) Data Classes
    2) Serializers
    TODO 3) Tests for serializers
    TODO 4) Parsers
    TODO 5) Tests for the parsers
     */

    public static void main(String[] args) {

        CustomerSerializer serializer = new CustomerSerializer();

        Customer customer = new Customer("Ilias", "Tzortzinis");

        Appointment appointment = new Appointment(LocalDate.now());

        customer.newAppointment(appointment);

        serializer.saveCustomers(List.of(customer));

        AppointmentSerializer appointmentSerializer = new AppointmentSerializer();

        appointmentSerializer.saveAppointments(List.of(appointment));

        CustomerParser customerParser = new CustomerParser();

        List<Customer> allCustomers = customerParser.getAllCustomers();

        System.out.println(allCustomers.get(0));

    }
}
