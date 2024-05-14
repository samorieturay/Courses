import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AmazonCustomerOrderHistorySystem {
    enum ShipmentStatus {InProcess, Shipped, Delivered}
    public static void main(String[] args) {
        double price = 99.99;
        String creditCardNumber = "132-444-2347-7744";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMMM dd, yyyy ");
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        try {
            Date date = dateFormat.parse("22/5/2020");  // Convert String to date
            System.out.println(dateFormat2.format(date));   // Convert Date to String
            System.out.println(formatter.format(price));    // Formatting Currency
            System.out.println(ShipmentStatus.Delivered);   // How to print enum values
            System.out.println(creditCardNumber.substring(creditCardNumber.length() -4 ));  // Last 4 character of a credit card number
        }catch
        (ParseException e)
        {
            System.out.println("Problem parsing date...");
        }
    }
}
