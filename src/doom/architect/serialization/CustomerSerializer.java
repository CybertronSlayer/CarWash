package doom.architect.serialization;

import doom.architect.model.Appointment;
import doom.architect.model.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CustomerSerializer {

    static Path path = Path.of("C:\\Users\\Iwann\\Desktop\\ObjectRelations\\data\\customers.txt");

    public void saveCustomers(List<Customer> customerList){
        ArrayList<String> lines = new ArrayList<>();
        for (Customer customer : customerList) {
            String line = toLine(customer);
            lines.add(line);
        }
        saveToFile(lines);
    }

    private String toLine(Customer customer) {
        return customer.firstName + " " + customer.lastName + " " + customer.phoneNumber
               + " " + findAppointmentsIDS(customer);
    }

    private String findAppointmentsIDS(Customer customer) {
        if (customer.appointments.isEmpty()){
            return "";
        }
        StringBuilder str = new StringBuilder();
        for (Appointment appointment : customer.appointments) {
            str.append(appointment.uuid).append(',');
        }
        //Deletes the last ','
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    protected void saveToFile(List<String> lines){
        try {
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
